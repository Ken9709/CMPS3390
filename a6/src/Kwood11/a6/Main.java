package Kwood11.a6;


/**
 * Main driver application for our updated functionality
 * allowing the app to pull data from an external api and
 * put it into our labels and charts
 * @author Kenneth Wood
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        Coin bitcoin = new Coin("bitcoin");
        Coin ethereum = new Coin("ethereum");
        CoinGecko.updateCurrentPrice(bitcoin);
        CoinGecko.updateCurrentPrice(ethereum);
        CoinGecko.updatePriceHistory(bitcoin, 7);

        System.out.println(bitcoin);
        System.out.println(ethereum);
    }
}
