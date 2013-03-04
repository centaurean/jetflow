package com.centaurean.jetflow.sim.environment.obstacles;

/**
 * jetFlow
 * guillaume
 * 04/03/13 21:07
 */
public abstract class Triangle<A, C> extends Triplet<Integer> implements Collisionable<A, C> {
    public Triangle(Integer a, Integer b, Integer c) {
        super(a, b, c);
    }
}
