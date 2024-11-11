package org.example;

// Used ChatGPT to generate. It's just a bunch of boiler plate code anyway so I don't really see anything wrong with that.
public class Item {
    private final String itemName;
    private final String acronym;
    private final int rap;
    private final int value;
    private final int absoluteValue;
    private final String demand;
    private final String trend;
    private final boolean isProjected;
    private final boolean isHyped;
    private final boolean isRare;

    public Item() {
        this.itemName = "";
        this.acronym = "";
        this.rap = -1;
        this.value = -1;
        this.absoluteValue = -1;
        this.demand = "";
        this.trend = "";
        this.isProjected = false;
        this.isHyped = false;
        this.isRare = false;
    }

    public Item(String itemName, String acronym, int rap, int value, int absoluteValue, String demand, String trend,
                String isProjected, String isHyped, String isRare) {
        this.itemName = itemName;
        this.acronym = acronym;
        this.rap = rap;
        this.value = value;
        this.absoluteValue = absoluteValue;
        this.demand = demand;
        this.trend = trend;
        this.isProjected = convertToBoolean(isProjected);
        this.isHyped = convertToBoolean(isHyped);
        this.isRare = convertToBoolean(isRare);
    }

    public String getItemName() {
        return itemName;
    }

    public String getAcronym() {
        return acronym;
    }

    public int getRap() {
        return rap;
    }

    public int getValue() {
        return value;
    }

    public int getAbsoluteValue() {
        return absoluteValue;
    }

    public String getDemand() {
        return demand;
    }

    public String getTrend() {
        return trend;
    }

    public boolean isProjected() {
        return isProjected;
    }

    public boolean isHyped() {
        return isHyped;
    }

    public boolean isRare() {
        return isRare;
    }

    public boolean convertToBoolean(String bool) {
        return !bool.equals("-1");
    }

    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", acronym='" + acronym + '\'' +
                ", rap=" + rap +
                ", value=" + value +
                ", absoluteValue=" + absoluteValue +
                ", demand='" + demand + '\'' +
                ", trend='" + trend + '\'' +
                ", isProjected='" + isProjected + '\'' +
                ", isHyped='" + isHyped + '\'' +
                ", isRare='" + isRare + '\'' +
                '}';
    }
}