package com.centaurean.jetflow.sim.environment.obstacles;

import java.util.Collection;

/**
 * jetFlow
 * guillaume
 * 04/03/13 20:53
 */
public interface Obstacles<A, O, C> extends Collection<O>, Collisionable<A, C> {
}
