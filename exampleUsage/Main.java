package org.example;

public class Main {
    public static void main(String[] args) {

        long myId = 1341100454L; // My user id
        String[] itemNames = {"seotn", "supa", "Princess Hat"}; // The items I want to advertise
        String[] tags = {"demand", "upgrade"}; // The tags I want (Should probably use something like an enum tbh)

        try { // Try to send a request
            Bodies.Items items = Requests.getItemDetails(); 
            items = new Bodies.Items(items);
            for(int i = 0; i < 60; i++) {
                long[] itemsToAdvertise = new long[itemNames.length]; // new empty long array of the length of item names

                for(int j = 0; j < itemsToAdvertise.length; j++) {
                    long id = items.findIdByName(itemNames[j]); // Gets id by name

                    if(id == -1) { // If id can't be found by name then it must be an acronym, So try to get the item by acronym.
                        id = items.findIdByAcronym(itemNames[j]);
                    }

                    itemsToAdvertise[j] = id;
                }

                // Create a new Bodies.TradeAd object
                Bodies.TradeAd tradeAd = new Bodies.TradeAd(myId, itemsToAdvertise, new long[]{}, tags);
                // Send the POST request with the newly made payload and rolisecurity cookie. (Yes this is my actual current rolimon cookie, go nuts.)
                String response = Requests.postTradeAd(tradeAd, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ2ZXJzaW9uIjoxLCJwbGF5ZXJfZGF0YSI6eyJuYW1lIjoiVHJHaXJsR2FtaW5nIiwiaWQiOjEzNDExMDA0NTR9LCJpYXQiOjE3MjgwMTE5MzEsImV4cCI6MTczNTc4Nzk5MX0.G-m_cJVQpCRDQwQofIDK62Fozf9tI1Y1hXb0rnP5Nu8");

                System.out.println(response); // Print whether the response was a success or not (Not needed but convenient)
                Thread.sleep(Wait.waitMinutes(15)); // Wait for 15 minutes (Wait class isn't needed but I just find it useful for making quick changes.)
            }

        } catch(Exception e) { // If the request fails print the error
            e.printStackTrace();
        }

    }
}