package com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles;

import com.centaurean.jetflow.sim.environment.obstacles.Triangle;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Vector2D;

/**
 * jetFlow
 * guillaume
 * 04/03/13 21:27
 */
public class Triangle2D extends Triangle<Anchors2D, Coordinates2D> {
    public Triangle2D(Integer a, Integer b, Integer c) {
        super(a, b, c);
    }

    /**
     * Determine if coordinates are included in the triangle
     *
     * @param anchors2D     the list of points in the environment
     * @param coordinates2D a given set of 2D coordinates
     * @return true if the triangle contains the given coordinates, false otherwise
     */
    @Override
    public boolean includes(Anchors2D anchors2D, Coordinates2D coordinates2D) {
        Vector2D v0 = new Vector2D(anchors2D.get(a()).coordinates());
        Vector2D v1 = new Vector2D(anchors2D.get(b()).coordinates().add(anchors2D.get(a()).coordinates().multiply(-1.0)));
        Vector2D v2 = new Vector2D(anchors2D.get(c()).coordinates().add(anchors2D.get(a()).coordinates().multiply(-1.0)));

        double multiplier = 1.0 / (v1.coordinates().y() * v2.coordinates().x() - v1.coordinates().x() * v2.coordinates().y());
        double a = (v0.coordinates().y() * v2.coordinates().x() - v0.coordinates().x() * v2.coordinates().y() + v2.coordinates().y() * coordinates2D.x() - v2.coordinates().x() * coordinates2D.y()) * (-multiplier);
        double b = (v0.coordinates().y() * v1.coordinates().x() - v0.coordinates().x() * v1.coordinates().y() + v1.coordinates().y() * coordinates2D.x() - v1.coordinates().x() * coordinates2D.y()) * multiplier;

        return (a > 0.0) && (b > 0.0) && (a + b < 1.0);
    }
}
