package Kwood11.a6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CoinGecko {
    private static final HttpClient httpClient= HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static void updateCurrentPrice(Coin coin){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.congecko.com/api/v3/simple/pirce?ids=" + coin.getName() +"&vs_currencies=usd"))
                .setHeader("User-Agent", "Java 11 HttpClient Kwood11.a6")
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode() + ":" + response.body());
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
