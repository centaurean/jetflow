package com.centaurean.jetflow.sim.environment.obstacles;

import java.util.Collection;

/**
 * jetFlow
 * guillaume
 * 03/03/13 20:27
 */
public interface Obstacle<A, C, T> extends Collection<T>, Collisionable<A, C> {
}
