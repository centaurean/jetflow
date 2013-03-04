package com.centaurean.jetflow.sim.environment.obstacles;

/**
 * jetFlow
 * guillaume
 * 04/03/13 21:23
 */
public interface Collisionable<A, C> {
    public boolean includes(A anchors, C coordinates);
}
