package com.centaurean.jetflow.sim.geometry;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:47
 */
public interface Segment<P> {
    public P a();

    public P b();

    public double position(P p);

    public P midPoint();
}
