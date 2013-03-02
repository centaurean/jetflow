package com.centaurean.jetflow.sim.geometry.implementation.bidimensional;

import com.centaurean.jetflow.sim.geometry.Point;

import static java.lang.Math.sqrt;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public class Point2D implements Point<Point2D, Vector2D> {
    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public Point2D translation(Vector2D vector2D) {
        return new Point2D(x() + vector2D.x(), y() + vector2D.y());
    }

    public double distance(Point2D p) {
        double delta_x = x() - p.x();
        double delta_y = y() - p.y();
        return sqrt(delta_x * delta_x + delta_y * delta_y);
    }

    @Override
    public boolean equals(Object objet) {
        if (objet == null)
            return false;
        if (objet == this)
            return true;
        if (!(objet instanceof Point2D))
            return false;
        Point2D point = (Point2D) objet;
        return (this.x() == point.x() && this.y() == point.y());
    }
}
