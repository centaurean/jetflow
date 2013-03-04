package com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles;

import com.centaurean.jetflow.sim.environment.obstacles.Obstacles;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;

import java.util.LinkedList;

/**
 * jetFlow
 * guillaume
 * 04/03/13 21:26
 */
public class Obstacles2D extends LinkedList<Obstacle2D> implements Obstacles<Anchors2D, Obstacle2D, Coordinates2D> {
    @Override
    public boolean includes(Anchors2D anchors2D, Coordinates2D coordinates2D) {
        for (Obstacle2D obstacle : this)
            if (obstacle.includes(anchors2D, coordinates2D))
                return true;
        return false;
    }
}
