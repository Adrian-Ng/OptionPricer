package com.adrian.ng;

import java.util.HashMap;

/**
 * Created by Adrian on 12/10/2018.
 */
public abstract class BinomialTree implements PricingType{

    private static int T = 3;
    private static double dt = 1.0/12.0;
    private double interest;       // interest rate
    private double strike;
    private double[][] stockPrice;
    private double p;

    // this is actually the constructor. What am i doing putting all this crap in here
    public BinomialTree(HashMap<String, Double> hashMap) {
        double stock        = hashMap.get("stock");
        this.strike         = hashMap.get("strike");
        double volatility   = hashMap.get("volatility");
        this.interest       = hashMap.get("interest");

        double u = Math.exp(volatility*Math.sqrt(dt));           // stock price increase
        double d = Math.exp(-volatility*Math.sqrt(dt));          // stock price decrease
        stockPrice = stockPrices(stock, u, d, T);
        p = (Math.exp(interest*dt)-d)/(u-d);                // 0.4975
    }

    private double[][] stockPrices(double S0, double u, double d, int T) {
        double[][] stockPrice = new double[T][T];
        stockPrice[0][0] = S0;
        // compute increase by u on the extreme side only
        for (int i = 1; i < T; i++)
            stockPrice[0][i] = stockPrice[0][i-1] * u;
        // compute all subsequent decrease by d
        for (int j = 1; j < T; j++)
            for(int i = 1; i< T; i++)
                stockPrice[j][i] = stockPrice[j-1][i-1] * d;
        return stockPrice;
    }

    public double setCall(double[][] stockPrice, double strike, double interest, double p, double dt, int T){
        double[][] optionPrice = new double[T][T];
        //compute option prices at maturity
        for (int i = 0; i < T; i++)
            optionPrice[i][T-1] = Math.max(stockPrice[i][T-1]-strike,0.0);
        // compute subsequent option prices
        for (int i = T-2;i >= 0; i--)
            for(int j = T-2; j >= 0; j--)
                optionPrice[j][i] = Math.exp(-interest*dt)*((p*optionPrice[j][i+1])+(1-p)*optionPrice[j+1][i+1]);
        return optionPrice[0][0];
    }

    abstract public double setPut(double[][] stockPrice, double strike, double interest, double p, double dt, int T);

    public double getCall(){
       double fCall = setCall(stockPrice, strike, interest, p, dt, T);
       return fCall;
    }
    public double getPut(){
        double fPut = setPut(stockPrice, strike, interest, p, dt, T);
        return fPut;
    };
}
