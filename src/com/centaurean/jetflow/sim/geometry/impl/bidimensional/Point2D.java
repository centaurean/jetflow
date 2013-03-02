package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

import com.centaurean.jetflow.sim.geometry.Point;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public class Point2D implements Point<Coordinates2D, Point2D, Vector2D> {
    private Coordinates2D coordinates;

    public Point2D(double x, double y) {
        coordinates = new Coordinates2D(x, y);
    }

    @Override
    public Coordinates2D coordinates() {
        return coordinates;
    }

    @Override
    public Point2D translation(Vector2D vector2D) {
        return new Point2D(coordinates().x() + vector2D.x(), coordinates().y() + vector2D.y());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (!(object instanceof Point2D))
            return false;
        Point2D point = (Point2D) object;
        return (this.coordinates().x() == point.coordinates().x() && this.coordinates().y() == point.coordinates().y());
    }
}
