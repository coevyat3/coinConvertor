package coinsType;


import coinns.*;

public class CoinFactory {


    public static Coin getCoinInstance(Coins coin){
        switch(coin) {

            case ILS -> {
                return new ILS();
            }
            case USD -> {
                return new USD();
            }
            case EURO -> {

                return new EURO();
            }

        }

        return null; }



}