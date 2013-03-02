package com.centaurean.jetflow.sim.geometry.implementation.bidimensional;

import static java.lang.Math.abs;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public class Quadrilateral2D {
    private Point2D a;
    private Point2D b;
    private Point2D c;
    private Point2D d;

    public Quadrilateral2D(Point2D a, Point2D b, Point2D c, Point2D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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

    public Point2D d() {
        return d;
    }

    // Aire
    public double aire() {
        return 0.5f * abs((b.x() - d.x()) * (a.y() - c.y()) - (a.x() - c.x()) * (b.y() - d.y()));
    }
}
