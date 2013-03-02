package com.centaurean.jetflow.sim.geometry.implementation.bidimensional;

import com.centaurean.jetflow.sim.geometry.Triangle;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public class Triangle2D implements Triangle<Point2D> {
    private Point2D a;
    private Point2D b;
    private Point2D c;
    private Segment2D ab;
    private Segment2D bc;
    private Segment2D ca;
    private Segments2D segments2D;
    private Point2D centreGravite;

    public Triangle2D(Point2D a, Point2D b, Point2D c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.ab = new Segment2D(a, b);
        this.bc = new Segment2D(b, c);
        this.ca = new Segment2D(c, a);
        this.segments2D = new Segments2D();
        this.segments2D.add(this.ab);
        this.segments2D.add(this.bc);
        this.segments2D.add(this.ca);
        this.centreGravite = new Point2D((1.0f / 3.0f) * (a.x() + b.x() + c.x()), (1.0f / 3.0f) * (a.y() + b.y() + c.y()));
    }

    public Point2D a() {
        return a;
    }

    public Point2D b() {
        return b;
    }

    public Point2D c() {
        return c;
    }

    public Segment2D ab() {
        return ab;
    }

    public Segment2D bc() {
        return bc;
    }

    public Segment2D ca() {
        return ca;
    }

    public Segments2D segments() {
        return segments2D;
    }

    @Override
    public Point2D centerOfGravity() {
        return centreGravite;
    }
}
