package com.centaurean.jetflow.sim.geometry;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:47
 */
public interface Triangle<P> {
    public P a();

    public P b();

    public P c();

    public P centerOfGravity();
}
