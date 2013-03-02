package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

import com.centaurean.jetflow.sim.geometry.Quadrilateral;

import java.util.Collection;

import static java.lang.Math.abs;

/**
 * jetFlow
 * guillaume
 * 01/03/13 14:45
 */
public class Quadrilateral2D implements Quadrilateral<Point2D, Segment2D> {
    private Point2D a;
    private Point2D b;
    private Point2D c;
    private Point2D d;
    private Points2D points2D;
    private Segments2D segments2D;

    public Quadrilateral2D(Point2D a, Point2D b, Point2D c, Point2D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.points2D = new Points2D();
        this.points2D.add(a);
        this.points2D.add(b);
        this.points2D.add(c);
        this.points2D.add(d);
        this.segments2D = new Segments2D();
        this.segments2D.add(new Segment2D(a, b));
        this.segments2D.add(new Segment2D(b, c));
        this.segments2D.add(new Segment2D(c, d));
        this.segments2D.add(new Segment2D(d, a));
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
    public Point2D d() {
        return d;
    }

    @Override
    public Points2D points() {
        return points2D;
    }

    @Override
    public Collection<Segment2D> segments() {
        return segments2D;
    }

    @Override
    public double surface() {
        return 0.5f * abs((b.coordinates().x() - d.coordinates().x()) * (a.coordinates().y() - c.coordinates().y()) - (a.coordinates().x() - c.coordinates().x()) * (b.coordinates().y() - d.coordinates().y()));
    }
}
