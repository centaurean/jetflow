package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

import com.centaurean.jetflow.sim.geometry.Point;
import com.centaurean.jetflow.sim.geometry.Quadrilateral;
import com.centaurean.jetflow.sim.geometry.Segment;

import java.util.Collection;


/*
 * Copyright (c) 2013, Centaurean software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Centaurean software nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Centaurean software BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * jetFlow
 *
 * 01/03/13 14:45
 * @author gpnuma
 */
public class Quadrilateral2D implements Quadrilateral {
    private Point2D a;
    private Point2D b;
    private Point2D c;
    private Point2D d;
    private Points2D points2D;
    private Segments2D segments2D;
    private double area;

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
        this.area = 0.5f * Math.abs((((Coordinates2D) b.coordinates()).x() - ((Coordinates2D) d.coordinates()).x()) * (((Coordinates2D) a.coordinates()).y() - ((Coordinates2D) c.coordinates()).y()) - (((Coordinates2D) a.coordinates()).x() - ((Coordinates2D) c.coordinates()).x()) * (((Coordinates2D) b.coordinates()).y() - ((Coordinates2D) d.coordinates()).y()));
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
    public Collection<Point> points() {
        return points2D;
    }

    @Override
    public Collection<Segment> segments() {
        return segments2D;
    }

    @Override
    public double area() {
        return area;
    }
}
