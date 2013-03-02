package com.centaurean.jetflow.sim.geometry;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:47
 */
public interface Triangle<P, S> extends Polygon<P, S> {
    public P a();

    public P b();

    public P c();

    public S ab();

    public S bc();

    public S ca();

    public P centerOfGravity();
}
