package com.adrian.ng;

import java.util.HashMap;

/**
 * Created by Adrian on 12/10/2018.
 */
public class EuropeanMonteCarlo extends MonteCarlo{

    public EuropeanMonteCarlo(HashMap<String, Double> hashMap){
        super(hashMap);
    }

    public double simuluatePath(int N, double S0, double dt, double r, double sigma) {
        // allocate memory to grid
        double[] grid = new double[N];
        grid[0] = S0;
        for (int i = 1; i < N; i++){
            double dz = stepsRandomWalk(dt);
            grid[i] = grid[i-1] + (r*grid[i-1]*dt)+(sigma*grid[i-1]*dz);
        }
        return grid[N-1];
    }
}
