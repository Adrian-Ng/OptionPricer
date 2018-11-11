package com.adrian.ng;

import java.util.HashMap;

/**
 * Created by Adrian on 12/10/2018.
 */
public class PricingFactory {

    private HashMap<String, Double> hashMap = new HashMap<>();

    public PricingFactory(HashMap<String, Double> hashMap){
        this.hashMap = hashMap;
    }


    public PricingType getPricingType(String type){

        if(type == null)
            return null;

        if(type.equals("American Binomial Tree"))
            return new AmericanTree(hashMap);

        if(type.equals("Asian Monte Carlo"))
            return new Asian(hashMap);

        if(type.equals("European Monte Carlo"))
            return new EuropeanMonteCarlo(hashMap);

        if(type.equals("European Binomial Tree"))
            return new EuropeanTree(hashMap);

        if(type.equals("Black Scholes"))
            return new BlackScholes(hashMap);

        return null;
    }


}