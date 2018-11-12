package com.adrian.ng;

import java.util.HashMap;
/**
 * Created by Adrian on 12/10/2018.
 */
public class PricingFactory extends Utils{

    public PricingType getPricingType(String type){

        if(type == null)
            return null;

        if(type.equals("American Binomial Tree")){
            String[] inputValues = readTxt("Binomial.txt");
            HashMap<String, Double> hashMap = hashMapInPutValues(inputValues);
            return new TreeAmerican(hashMap);
        }

        if(type.equals("European Binomial Tree")){
            String[] inputValues = readTxt("Binomial.txt");
            HashMap<String, Double> hashMap = hashMapInPutValues(inputValues);
            return new TreeEuropean(hashMap);
        }

        if(type.equals("Asian Monte Carlo")){
            String[] inputValues = readTxt("MonteCarlo.txt");
            HashMap<String, Double> hashMap = hashMapInPutValues(inputValues);
            return new MC_Asian(hashMap);
        }

        if(type.equals("European Monte Carlo")){
            String[] inputValues = readTxt("MonteCarlo.txt");
            HashMap<String, Double> hashMap = hashMapInPutValues(inputValues);
            return new MC_Euro(hashMap);
        }

        if(type.equals("Black Scholes")){
            String[] inputValues = readTxt("BlackScholes.txt");
            HashMap<String, Double> hashMap = hashMapInPutValues(inputValues);
            return new BlackScholes(hashMap);
        }

        return null;
    }


}
