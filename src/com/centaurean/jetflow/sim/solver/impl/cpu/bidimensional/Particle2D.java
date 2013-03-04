package com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional;

import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;
import com.centaurean.jetflow.sim.solver.Particle;

/**
 * jetFlow
 * guillaume
 * 03/03/13 20:13
 */
public class Particle2D extends Particle<Coordinates2D, Speed2D> {
    private Coordinates2D coordinates;
    private Speed2D speed;

    public Particle2D() {
    }

    @Override
    public Coordinates2D coordinates() {
        return coordinates;
    }

    @Override
    public Speed2D speed() {
        return speed;
    }
}
