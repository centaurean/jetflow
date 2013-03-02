package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

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
        this.midPoint = new Point2D(0.5 * (a().coordinates().x() + b().coordinates().x()), 0.5 * (a().coordinates().y() + b().coordinates().y()));
    }

    @Override
    public Point2D a() {
        return a;
    }

    @Override
    public Point2D b() {
        return b;
    }

    @Override
    public Point2D midPoint() {
        return midPoint;
    }

    @Override
    public double position(Point2D c) {
        return (b().coordinates().x() - a().coordinates().x()) * (c.coordinates().y() - a().coordinates().y()) - (b().coordinates().y() - a().coordinates().y()) * (c.coordinates().x() - a().coordinates().x());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (!(object instanceof Segment2D))
            return false;
        Segment2D segment2D = (Segment2D) object;
        return this.a().equals(segment2D.a()) && this.b().equals(segment2D.b()) || this.a().equals(segment2D.b()) && this.b().equals(segment2D.a());
    }

    @Override
    public int hashCode() {
        return a().hashCode() * b().hashCode();
    }
}
