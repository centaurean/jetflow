package com.centaurean.jetflow.sim.geometry.implementation.bidimensional;

import com.centaurean.jetflow.sim.geometry.Segment;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public class Segment2D implements Segment<Point2D> {
    private Point2D a;
    private Point2D b;
    private Point2D midPoint;

    public Segment2D(Point2D a, Point2D b) {
        this.a = a;
        this.b = b;
        this.midPoint = new Point2D(0.5f * (a().x() + b().x()), 0.5f * (a().y() + b().y()));
    }

    public Point2D a() {
        return a;
    }

    public Point2D b() {
        return b;
    }

    public Point2D midPoint() {
        return midPoint;
    }

    // Renvoie un flottant dont le signe détermine la position du point c par rapport au segment2D
    public double position(Point2D c) {
        return (b().x() - a().x()) * (c.y() - a().y()) - (b().y() - a().y()) * (c.x() - a().x());
    }

    @Override
    public boolean equals(Object objet) {
        if (objet == null)
            return false;
        if (objet == this)
            return true;
        if (!(objet instanceof Segment2D))
            return false;
        Segment2D segment2D = (Segment2D) objet;
        return this.a().equals(segment2D.a()) && this.b().equals(segment2D.b()) || this.a().equals(segment2D.b()) && this.b().equals(segment2D.a());
    }

    @Override
    public int hashCode() {
        return a().hashCode() * b().hashCode();
    }
}
