package com.centaurean.jetflow.sim.geometry;

/**
 * jetFlow
 * guillaume
 * 02/03/13 15:54
 */
public interface Quadrilateral<P, S> extends Polygon<P, S> {
    public P a();

    public P b();

    public P c();

    public P d();
}
