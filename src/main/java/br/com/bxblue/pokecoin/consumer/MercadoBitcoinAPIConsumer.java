package br.com.bxblue.pokecoin.consumer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class MercadoBitcoinAPIConsumer {
    private final HttpClient client = HttpClient.newHttpClient();

    public Double getBtcPrice() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.mercadobitcoin.net/api/BTC/ticker/"))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Gson gson = new Gson();

                JsonObject json = gson.fromJson(response.body(), JsonObject.class);
                Double sell = json.get("ticker").getAsJsonObject().get("sell").getAsDouble();
                return sell;
            } else {
                throw new RuntimeException("Error " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
