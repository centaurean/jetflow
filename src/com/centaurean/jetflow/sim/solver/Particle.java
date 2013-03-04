package com.centaurean.jetflow.sim.solver;

/**
 * jetFlow
 * guillaume
 * 02/03/13 16:34
 */
public abstract class Particle<C, S> {
    private Mass mass;
    private Pressure pressure;
    private Viscosity viscosity;

    public abstract C coordinates();

    public abstract S speed();

    public Mass mass() {
        return mass;
    }

    public Pressure pressure() {
        return pressure;
    }

    public Viscosity viscosity() {
        return viscosity;
    }
}
