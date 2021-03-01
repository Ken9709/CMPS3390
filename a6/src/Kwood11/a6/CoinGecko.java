package Kwood11.a6;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Factor class for the coinGecko API
 * which allows us to make a request for
 * a coins historical values and interpret that
 * information for use in our application
 */
public class CoinGecko {
    private static final HttpClient httpClient= HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    /**
     * Method to get and update a json string with information
     * from the coingecko api about a specific coin object
     * over the amount of days passed in for use in the charts
     * @param coin a coin object, named after an existing cryptocoin
     * @param days the amount of days information is needed for
     */
    public static void updatePriceHistory(Coin coin, int days){
        coin.getHistoricalValues().getData().clear();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.coingecko.com/api/v3/coins/"
                        + coin.getName() + "/market_chart?vs_currency=usd&days="
                        + days + "&interval=daily"))
                .setHeader("User-Agent", "Java 11 HttpClient Kwood11.a6")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body());
            JSONArray priceArray = obj.getJSONArray("prices");
            for(int i=0; i<priceArray.length(); i++){
                JSONArray tempArray = priceArray.getJSONArray(i);
                double tmpValue =tempArray.getDouble(1);
                coin.addHistoricalValue(i - priceArray.length() + 1,tmpValue);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to update the current price of a coin for display
     * on the buttons on the main page of our application
     * @param coin a coin object of an existing cryptocoin
     */
    public static void updateCurrentPrice(Coin coin){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.coingecko.com/api/v3/simple/price?ids=" + coin.getName() + "&vs_currencies=USD"))
                .setHeader("User-Agent", "Java 11 HttpClient Kwood11.a6")
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            double value = jsonObject.getJSONObject(coin.getName()).getDouble("usd");
            coin.setCurrentPrice(value);


        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
