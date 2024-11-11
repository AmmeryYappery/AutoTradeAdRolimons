package org.example;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * When serializing and deserializing, GSON uses these pojos which match the JSON structure of their request
 * */
public class Bodies {

    /**
     * Pojo for <a href="https://api.rolimons.com/items/v1/itemdetails">...</a>
     * */
    public static class Items {
        @SerializedName("success")
        private final boolean success; // Was the request a success
        @SerializedName("item_count")
        private final int itemCount; // Amount of limited items in the returned list
        @SerializedName("items")
        /*
        * Items gotten from the request are an array with a bunch of information about the item.
        * This information isn't labeled very well so I convert that map into another map which hopefully reduces confusion.
        * Idk if doing it like this is a good idea but it's what I thought made sense.
        * */
        private final  Map<String, String[]> rawItemsMap;
        @Expose(deserialize = false, serialize = false)
        private final LinkedHashMap<Long, Item> itemsMap;

        public Items(boolean success, int itemCount, Map<String, String[]> rawItemsMap) {
            this.success = success;
            this.itemCount = itemCount;
            this.rawItemsMap = rawItemsMap;
            itemsMap = convertItemsMap(rawItemsMap);
        }

        public Items(Items items) {
            this.success = items.success;
            this.itemCount = items.itemCount;
            this.rawItemsMap = items.rawItemsMap;
            itemsMap = convertItemsMap(items.rawItemsMap);
        }
        public boolean getSuccess() { return success; }

        public int getItemCount() { return itemCount; }

        /**
         * Finds an item by it's name. If the item can't be found it just returns an empty item object
         * @param name the name of the item you are looking for
         * */
        public Item findItemByName(String name) {
            for(Map.Entry<Long, Item> itemEntry : itemsMap.entrySet()) {
                Item value = itemEntry.getValue();

                if(value.getItemName().equalsIgnoreCase(name)) {
                    return value;
                }
            }

            return new Item();
        }


        /**
         * Finds the id of an item by its name and return -1 if no id is found
         * @param name the name of the item you are looking for
         * */
        public long findIdByName(String name) {
            for(Map.Entry<Long, Item> itemEntry : itemsMap.entrySet()) {
                Item value = itemEntry.getValue();

                if(value.getItemName().equalsIgnoreCase(name)) {
                    return itemEntry.getKey();
                }
            }

            return -1;
        }

        /**
         * Find an id by an acroynm and return -1 if no id is found
         * */
        public long findIdByAcronym(String acronym) {
            for(Map.Entry<Long, Item> itemEntry : itemsMap.entrySet()) {
                Item value = itemEntry.getValue();

                if(value.getAcronym().equalsIgnoreCase(acronym)) {
                    return itemEntry.getKey();
                }
            }
            return -1;
        }

        /**
         * Get an item by it's id
         * */
        public Item findItemById(Long id) { return itemsMap.get(id); }

        private LinkedHashMap<Long, Item> convertItemsMap(Map<String, String[]> map) {
            LinkedHashMap<Long, Item> converted = new LinkedHashMap<>();

            for(Map.Entry<String, String[]> element : map.entrySet()) {
                long key = Long.decode(element.getKey());
                String[] value = element.getValue();
                Item item = new Item(
                        value[0],                          // itemName
                        value[1],                          // acronym
                        Integer.parseInt(value[2]),        // rap
                        Integer.parseInt(value[3]),        // value
                        Integer.parseInt(value[4]),        // absoluteValue
                        value[5],                          // demand
                        value[6],                          // trend
                        value[7],                          // isProjected
                        value[8],                          // isHyped
                        value[9]
                );

                converted.put(key, item);
            }

            return converted;
        }
    }

    public static class TradeAd {
        private final long player_id;
        private final long[] offer_item_ids;
        private final long[] request_item_ids;
        private final String[] request_tags;

        public TradeAd(long player_id, long[] offer_item_ids, long[] request_item_ids, String[] request_tags) {
            this.player_id = player_id;
            this.offer_item_ids = offer_item_ids;
            this.request_item_ids = request_item_ids;
            this.request_tags = request_tags;
        }
    }
}
