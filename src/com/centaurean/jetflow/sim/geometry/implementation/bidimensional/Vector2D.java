package com.centaurean.jetflow.sim.geometry.implementation.bidimensional;

import com.centaurean.jetflow.sim.geometry.Vector;

import static java.lang.Math.sqrt;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public class Vector2D implements Vector<Vector2D> {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Point2D a, Point2D b) {
        this(b.x() - a.x(), b.y() - a.y());
    }

    public Vector2D(Segment2D segment2D) {
        this(segment2D.b().x() - segment2D.a().x(), segment2D.b().y() - segment2D.a().y());
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double length() {
        return sqrt(x() * x() + y() * y());
    }

    @Override
    public Vector2D add(Vector2D objet) {
        return new Vector2D(x() + objet.x(), y() + objet.y());
    }

    @Override
    public Vector2D multiply(double coefficient) {
        return new Vector2D(x() * coefficient, y() * coefficient);
    }

    @Override
    public double dotProduct(Vector2D v) {
        return x() * v.y() - y() * v.x();
    }

    @Override
    public String toString() {
        return "{" + x() + ", " + y() + "}";
    }
}
