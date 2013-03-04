package com.centaurean.jetflow.sim.geometry;

/**
 * jetFlow
 * guillaume
 * 05/03/13 00:22
 */
public interface Operandable<T> {
    public T add(T t);

    public T multiply(double c);
}
