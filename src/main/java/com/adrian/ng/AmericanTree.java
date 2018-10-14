package com.adrian.ng;

import java.util.HashMap;

/**
 * Created by Adrian on 14/10/2018.
 */
public class AmericanTree extends BinomialTree {

    public AmericanTree(HashMap<String, Double> hashMap){
        super(hashMap);
    }

    @Override
    public double setPut(double[][] stockPrice, double strike, double interest, double p, double dt, int T) {
        double[][] optionPrice = new double[T][T];
        //compute option prices at maturity
        for (int i = 0; i < T; i++)
            optionPrice[i][T-1] = Math.max(stockPrice[i][T-1]-strike,0.0);
        // compute subsequent option prices
        for (int i = T-2;i >= 0; i--)
            for(int j = T-2; j >= 0; j--)
                // consider Early Exercise. What's larger: the calculated option price or the difference between the strike and share price?
                optionPrice[j][i] = Math.max(Math.exp(-interest*dt)*((p*optionPrice[j][i+1])+(1-p)*optionPrice[j+1][i+1]),strike-stockPrice[j][i]);
        return optionPrice[0][0];
    }


}
