package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

import com.centaurean.jetflow.sim.geometry.Vector;

import static java.lang.Math.sqrt;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public class Vector2D implements Vector<Coordinates2D, Vector2D> {
    private Coordinates2D coordinates;

    public Vector2D(double x, double y) {
        coordinates = new Coordinates2D(x, y);
    }

    public Vector2D(Point2D a, Point2D b) {
        this(b.coordinates().x() - a.coordinates().x(), b.coordinates().y() - a.coordinates().y());
    }

    public Vector2D(Segment2D segment2D) {
        this(segment2D.b().coordinates().x() - segment2D.a().coordinates().x(), segment2D.b().coordinates().y() - segment2D.a().coordinates().y());
    }

    @Override
    public Coordinates2D coordinates() {
        return coordinates;
    }

    public double length() {
        return sqrt(coordinates().x() * coordinates().x() + coordinates().y() * coordinates().y());
    }

    @Override
    public Vector2D add(Vector2D object) {
        return new Vector2D(coordinates().x() + object.coordinates().x(), coordinates().y() + object.coordinates().y());
    }

    @Override
    public Vector2D multiply(double coefficient) {
        return new Vector2D(coordinates().x() * coefficient, coordinates().y() * coefficient);
    }

    @Override
    public double dotProduct(Vector2D v) {
        return coordinates().x() * v.coordinates().y() - coordinates().y() * v.coordinates().x();
    }

    @Override
    public String toString() {
        return "{" + coordinates().x() + ", " + coordinates().y() + "}";
    }
}
