package br.com.bxblue.pokecoin.consumer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;


public class MercadoBitcoinAPIConsumer {
    private final HttpClient client = HttpClient.newHttpClient();

    public Double getBtcPrice() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.mercadobitcoin.net/api/BTC/ticker/";
        String jsonResponse = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(jsonResponse, JsonObject.class);
        return json.get("ticker").getAsJsonObject().get("sell").getAsDouble();
    }

}
