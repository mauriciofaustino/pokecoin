package br.com.bxblue.pokecoin.consumer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


public class MercadoBitcoinAPIConsumer {

    public Double getBtcPrice() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.mercadobitcoin.net/api/BTC/ticker/";
        restTemplate.setInterceptors(Collections.singletonList((request, body, execution) -> {
            request.getHeaders().add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            return execution.execute(request, body);
        }));
        String jsonResponse = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(jsonResponse, JsonObject.class);
        return json.get("ticker").getAsJsonObject().get("sell").getAsDouble();
    }

}
