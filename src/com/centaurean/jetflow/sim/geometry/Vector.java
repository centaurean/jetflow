package com.centaurean.jetflow.sim.geometry;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:47
 */
public interface Vector<C, V> extends Operandable<V> {
    public C coordinates();

    public double length();

    public double dotProduct(V v);
}
