package Kwood11.a6;



public class Main {

    public static void main(String[] args) {
	// write your code here
        Coin bitcoin = new Coin("bitcoin)");
        CoinGecko.updateCurrentPrice(bitcoin);
    }
}
