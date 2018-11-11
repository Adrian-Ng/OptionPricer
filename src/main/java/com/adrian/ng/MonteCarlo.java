package com.adrian.ng;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Adrian on 12/10/2018.
 */
//https://stackoverflow.com/questions/1320745/abstract-class-in-java
public abstract class MonteCarlo implements PricingType {
    static final int paths;
    static {
        paths = 100000;                                 // number of random walks we will compute}
    }

    private static double interest;       // interest rate
    private double timeHorizon;
    private double CallPayoff;
    private double PutPayoff;

    public MonteCarlo(HashMap<String, Double> hashMap) {
        double stock        = hashMap.get("stock");
        double strike       = hashMap.get("strike");
        double volatility   = hashMap.get("volatility");
        this.interest       = hashMap.get("interest");
        this.timeHorizon    = hashMap.get("timehorizon");
        int N = (int) Math.ceil(365.0/2.0);                 // 6 months expressed in days. this is the number of steps.
        double dt = timeHorizon/N;                          // size of the step where each step is 1 day
        for (int i = 0; i < paths ; i ++) {
            // Return final projected Stock Price St = stock at time t
            double St = simulateRandomWalk(N, stock, dt, interest, volatility);
            CallPayoff += Math.max(St-strike,0);
            PutPayoff += Math.max(strike-St,0);
        }
    }

    public double basicWeinerProcess(double dt) {
        Random random = new Random();
        // sample from random Gaussian of mean 0 and sd 1
        double epsilon = random.nextGaussian();
        double dz = epsilon*Math.sqrt(dt);
        // return a step
        return dz;
    }
    // the benefit of this abstraction is that subclasses have their own implementation for this method.
    // but nothing else needs to be duplicated as they only differ at this point
    abstract double simulateRandomWalk(int N, double S0, double dt, double interest, double sigma);

    public double getCall(){
        return Math.exp(-interest*timeHorizon)* CallPayoff /paths;
    }

    public double getPut(){
        return Math.exp(-interest*timeHorizon)* PutPayoff /paths;
    }
}
