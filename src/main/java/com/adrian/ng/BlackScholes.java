package com.adrian.ng;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.HashMap;

/**
 * Created by Adrian on 12/10/2018.
 */

public class BlackScholes implements PricingType {
    private double stock;           // initial asset price
    private double strike;          // strike price
    private double volatility;      // volatility
    private double interest;        // interest rate
    private double timehorizon;     // half a year
    private double d1;
    private double d2;
    private final NormalDistribution distribution = new NormalDistribution(0, 1);

    public BlackScholes(HashMap<String, Double> hashMap) {
        stock          = hashMap.get("stock");
        strike         = hashMap.get("strike");
        volatility     = hashMap.get("volatility");
        interest       = hashMap.get("interest");
        timehorizon    = hashMap.get("timehorizon");

        d1             = (  Math.log(stock / strike)
                            + (interest + (Math.pow(volatility, 2) / 2)) * timehorizon)
                        / (volatility * Math.sqrt(timehorizon));

        d2             = d1 - (volatility * Math.sqrt(timehorizon));
    }

    public double getCall() {
        return      (stock * distribution.cumulativeProbability(d1))
                -   (strike * Math.exp(-interest * timehorizon)
                *    distribution.cumulativeProbability(d2));
    }
    public double getPut() {
        return  strike * Math.exp(-interest * timehorizon)
                * distribution.cumulativeProbability(-d2)
                - stock * distribution.cumulativeProbability(-d1);
    }

}
