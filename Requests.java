package org.example;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Requests {
    private final static  Gson gson = new Gson();

    public static String postTradeAd(Bodies.TradeAd offer, String cookie) throws URISyntaxException, IOException, InterruptedException {
        try(HttpClient httpClient = HttpClient.newHttpClient()) {
            // Convert the Bodies class to Json with gson
            String payload = gson.toJson(offer);

            // Send the post request
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.rolimons.com/tradeads/v1/createad"))
                    .header("Cookie", "_RoliVerification="+cookie)
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            // Get the response of the post request
            HttpResponse<String> postResponse =  httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            return postResponse.body();
        }
    }

    public static Bodies.Items getItemDetails() throws URISyntaxException, IOException, InterruptedException {
        try(HttpClient httpClient = HttpClient.newHttpClient()) {
            // Send the get request
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.rolimons.com/items/v1/itemdetails"))
                    .header("content-type", "application/json")
                    .build();

            // Get the response of the get request
            HttpResponse<String> getResponse =  httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(getResponse.body(), Bodies.Items.class);
        }
    }
}
