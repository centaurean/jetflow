package com.centaurean.jetflow.sim.geometry;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:47
 */
public interface Vector<V> {
    public V add(V v);

    public V multiply(double c);

    public double length();

    public double dotProduct(V v);
}
