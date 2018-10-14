package com.adrian.ng;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.HashMap;

/**
 * Created by Adrian on 12/10/2018.
 */




public class BlackScholes implements PricingType {

    private double stock;      // initial asset price
    private double strike;      // strike price
    private double volatility;   // volatility
    private double interest;       // interest rate
    private double timehorizon; // half a year
    private double d1;
    private double d2;
    private final NormalDistribution distribution = new NormalDistribution(0, 1);

    public BlackScholes(HashMap<String, Double> hashMap) {
        this.stock          = hashMap.get("stock");
        this.strike         = hashMap.get("strike");
        this.volatility     = hashMap.get("volatility");
        this.interest       = hashMap.get("interest");
        this.timehorizon    = hashMap.get("timehorizon");
        this.d1             = (Math.log(this.stock / this.strike) + (this.interest + (Math.pow(this.volatility, 2) / 2)) * timehorizon) / (this.volatility * Math.sqrt(this.timehorizon));
        this.d2 = d1 - (this.volatility * Math.sqrt(timehorizon));
    }

    public double getCall() {
        return (stock * distribution.cumulativeProbability(d1)) - (strike * Math.exp(-this.interest * this.timehorizon) * distribution.cumulativeProbability(d2));
    }

    public double getPut() {
        return this.strike * Math.exp(-this.interest * this.timehorizon) * distribution.cumulativeProbability(-d2) - this.stock * distribution.cumulativeProbability(-d1);
    }

}
