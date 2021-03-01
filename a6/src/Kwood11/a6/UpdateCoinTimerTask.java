package Kwood11.a6;


/**
 * Thread scheduler that allows the coins values
 * to update on timer that runs while the application is open
 */
public class UpdateCoinTimerTask implements Runnable{
    private Coin coin;

    /**
     * Method to set the coin we need to update on a timer
     * @param coin a coin object of an existing cryptocoin
     */
    public UpdateCoinTimerTask(Coin coin) {
        this.coin = coin;
    }

    /**
     * Override of the run method to use a runnable thread
     * that constantly checks for updates on the coins in use
     */
    @Override
    public void run() {
        System.out.println("Checking for update on " + coin.getName());
        double curValue = this.coin.getCurrentPrice();
        CoinGecko.updateCurrentPrice(this.coin);
        if(curValue != this.coin.getCurrentPrice()){
            System.out.println("---------------------------- Price Changed " + this.coin.getName()
                    + " " + curValue + " --> " + this.coin.getCurrentPrice() + " ------------------------");
        }

    }
}
