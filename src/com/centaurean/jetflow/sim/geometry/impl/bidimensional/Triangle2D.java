package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

import com.centaurean.jetflow.sim.geometry.Triangle;

import static java.lang.Math.abs;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public class Triangle2D implements Triangle<Point2D, Segment2D> {
    private Point2D a;
    private Point2D b;
    private Point2D c;
    private Segment2D ab;
    private Segment2D bc;
    private Segment2D ca;
    private Points2D points2D;
    private Segments2D segments2D;
    private Point2D centerOfGravity;

    public Triangle2D(Point2D a, Point2D b, Point2D c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.points2D = new Points2D();
        this.points2D.add(a);
        this.points2D.add(b);
        this.points2D.add(c);
        this.ab = new Segment2D(a, b);
        this.bc = new Segment2D(b, c);
        this.ca = new Segment2D(c, a);
        this.segments2D = new Segments2D();
        this.segments2D.add(this.ab);
        this.segments2D.add(this.bc);
        this.segments2D.add(this.ca);
        this.centerOfGravity = new Point2D((1.0f / 3.0f) * (a.coordinates().x() + b.coordinates().x() + c.coordinates().x()), (1.0f / 3.0f) * (a.coordinates().y() + b.coordinates().y() + c.coordinates().y()));
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
    public Point2D c() {
        return c;
    }

    @Override
    public Segment2D ab() {
        return ab;
    }

    @Override
    public Segment2D bc() {
        return bc;
    }

    @Override
    public Segment2D ca() {
        return ca;
    }

    @Override
    public Points2D points() {
        return points2D;
    }

    @Override
    public Segments2D segments() {
        return segments2D;
    }

    @Override
    public double surface() {
        return 0.5 * abs(a().coordinates().x() * (b.coordinates().y() - c.coordinates().y()) + b.coordinates().x() * (c.coordinates().y() - a.coordinates().y()) + c.coordinates().x() * (a.coordinates().y() - b.coordinates().y()));
    }

    @Override
    public Point2D centerOfGravity() {
        return centerOfGravity;
    }
}
