package com.centaurean.jetflow.sim.solver;

/**
 * jetFlow
 * guillaume
 * 02/03/13 16:36
 */
public class Viscosity {
    private double pascalSeconds;

    public Viscosity(double pascalSeconds) {
        this.pascalSeconds = pascalSeconds;
    }

    public double pascalSeconds() {
        return pascalSeconds;
    }
}
