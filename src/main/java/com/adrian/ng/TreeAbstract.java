package com.adrian.ng;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Adrian on 12/10/2018.
 */
public abstract class TreeAbstract implements PricingType{

    private static int T = 4;
    private static double dt = 1.0/12.0;
    private double interest;       // interest rate
    private double strike;
    private double[][] stockPrice;
    private double p;

    // this is actually the constructor. What am i doing putting all this crap in here
    public TreeAbstract(HashMap<String, Double> hashMap) {
        double stock        = hashMap.get("stock");
        this.strike         = hashMap.get("strike");
        double volatility   = hashMap.get("volatility");
        this.interest       = hashMap.get("interest");

        double u = Math.exp(volatility*Math.sqrt(dt));           // stock price increase
        double d = Math.exp(-volatility*Math.sqrt(dt));          // stock price decrease
        stockPrice = stockPrices(stock, u, d, T);
        p = (Math.exp(interest*dt)-d)/(u-d);
    }

    private double[][] stockPrices(double S0, double u, double d, int T) {
        double[][] stockPrice = new double[T][T];
        stockPrice[0][0] = S0;
        for (int hori = 1; hori < T; hori++) {
            // compute increase by u on the extreme side only
            stockPrice[0][hori] = stockPrice[0][hori - 1] * u;
            for (int vert = 1; vert < T; vert++)
                // compute all decrease by d
                stockPrice[vert][hori] = stockPrice[vert - 1][hori - 1] * d;
        }
        return stockPrice;
    }

    private double computeCall(double[][] stockPrice, double strike, double interest, double p, double dt, int T) {
        double[][] optionPrice = new double[T][T];
        for(int hori = T-1; hori >= 0; hori--)
            for(int vert = hori; vert >= 0; vert--){
                // compute option prices at maturity
                if (hori == T -1)
                    optionPrice[vert][hori] = Math.max(stockPrice[vert][hori]-strike,   0.0);
                // compute option prices prior to maturity
                else
                    optionPrice[vert][hori] = Math.exp(-interest*dt)*((p*optionPrice[vert][hori+1])+(1-p)*optionPrice[vert+1][hori+1]);
            }
        return optionPrice[0][0];
    }

    abstract public double computePut(double[][] stockPrice, double strike, double interest, double p, double dt, int T);

    public double getCall(){
       double fCall = computeCall(stockPrice, strike, interest, p, dt, T);
       return fCall;
    }
    public double getPut(){
        double fPut = computePut(stockPrice, strike, interest, p, dt, T);
        return fPut;
    }
}
