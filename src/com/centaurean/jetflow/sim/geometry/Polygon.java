package com.centaurean.jetflow.sim.geometry;

import java.util.Collection;

/**
 * jetFlow
 * guillaume
 * 02/03/13 15:56
 */
public interface Polygon<P, S> {
    public Collection<P> points();

    public Collection<S> segments();

    public double surface();
}
