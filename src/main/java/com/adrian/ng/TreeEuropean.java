package com.adrian.ng;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Adrian on 14/10/2018.
 */

public class TreeEuropean extends TreeAbstract {

    public TreeEuropean(HashMap<String, Double> hashMap){
        super(hashMap);
    }

    @Override
    public double computePut(double[][] stockPrice, double strike, double interest, double p, double dt, int T) {
        double[][] optionPrice = new double[T][T];
        for(int hori = T-1; hori >= 0; hori--)
            for(int vert = hori; vert >= 0; vert--){
                // compute option prices at maturity
                if (hori == T -1)
                    optionPrice[vert][hori] = Math.max(strike-stockPrice[vert][hori],0.0);
                    // compute option prices prior to maturity
                else
                    optionPrice[vert][hori] = Math.exp(-interest*dt)*((p*optionPrice[vert][hori+1])+(1-p)*optionPrice[vert+1][hori+1]);
            }
        for (int i = 0; i < T; i++)
            System.out.println(Arrays.toString(optionPrice[i]));
        return optionPrice[0][0];
    }
}
