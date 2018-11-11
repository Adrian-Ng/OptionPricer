package com.adrian.ng;

import java.util.HashMap;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by Adrian on 12/10/2018.
 */
public class EuropeanMonteCarlo extends MonteCarlo {

    public EuropeanMonteCarlo(HashMap<String, Double> hashMap) {
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
