package com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles;

import com.centaurean.jetflow.sim.environment.obstacles.Obstacle;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;

import java.util.LinkedList;

/**
 * jetFlow
 * guillaume
 * 04/03/13 21:21
 */
public class Obstacle2D extends LinkedList<Triangle2D> implements Obstacle<Anchors2D, Coordinates2D, Triangle2D> {
    @Override
    public boolean includes(Anchors2D anchors2D, Coordinates2D coordinates2D) {
        for (Triangle2D triangle : this)
            if (triangle.includes(anchors2D, coordinates2D))
                return true;
        return false;
    }
}
