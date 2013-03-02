package com.centaurean.jetflow.sim.geometry;

/**
 * jetFlow
 * guillaume
 * 02/03/13 16:37
 */
public interface Coordinates<C> {
    public double distance(C c);

    @Override
    public boolean equals(Object o);
}
