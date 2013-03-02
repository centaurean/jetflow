package com.centaurean.jetflow.sim.solver;

import com.centaurean.jetflow.sim.geometry.Coordinates;

/**
 * jetFlow
 * guillaume
 * 02/03/13 16:34
 */
public abstract class Particle {
    private Coordinates coordinates;
    private Mass mass;
    private Speed speed;
    private Pressure pressure;
    private Viscosity viscosity;

    public Coordinates coordinates() {
        return coordinates;
    }

    public Mass mass() {
        return mass;
    }

    public Speed speed() {
        return speed;
    }

    public Pressure pressure() {
        return pressure;
    }

    public Viscosity viscosity() {
        return viscosity;
    }
}
