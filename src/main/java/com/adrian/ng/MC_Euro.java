package com.adrian.ng;

import java.util.HashMap;
/**
 * Created by Adrian on 12/10/2018.
 */
public class MC_Euro extends MC_Abstract {

    public MC_Euro(HashMap<String, Double> hashMap) {
        super(hashMap);
    }

    public double simulateRandomWalk
            (int N, double S0, double dt, double interest, double sigma) {
        double St = S0;
        for (int t = 1; t < N; t++) {
            double dz = basicWeinerProcess(dt);
            St = St + (interest * St * dt) + (sigma * St * dz);
        }
        return St;
    }
}
