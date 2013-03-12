package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

import com.centaurean.jetflow.sim.geometry.Coordinates;
import com.centaurean.jetflow.sim.geometry.Triangle;
import com.centaurean.jetflow.sim.geometry.Triplet;

import static java.lang.Math.abs;

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
public class Triangle2D extends Triplet<Point2D> implements Triangle {
    private Segment2D ab;
    private Segment2D bc;
    private Segment2D ca;
    private Points2D points2D;
    private Segments2D segments2D;
    private Point2D centerOfGravity;
    private double area;

    public Triangle2D(Point2D a, Point2D b, Point2D c) {
        super(a, b, c);
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
        this.area = 0.5 * abs(a().coordinates().x() * (b.coordinates().y() - c.coordinates().y()) + b.coordinates().x() * (c.coordinates().y() - a.coordinates().y()) + c.coordinates().x() * (a.coordinates().y() - b.coordinates().y()));
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
    public double area() {
        return area;
    }

    @Override
    public Point2D centerOfGravity() {
        return centerOfGravity;
    }

    /**
     * Determine if getCoordinates are included in the triangle
     *
     * @param coordinates a given set of 2D getCoordinates
     * @return true if the triangle contains the given getCoordinates, false otherwise
     */
    @Override
    public boolean includes(Coordinates coordinates) {
        Vector2D v0 = new Vector2D(a().coordinates());
        Vector2D v1 = new Vector2D(b().coordinates().add(a().coordinates().multiply(-1.0)));
        Vector2D v2 = new Vector2D(c().coordinates().add(a().coordinates().multiply(-1.0)));

        double multiplier = 1.0 / (v1.getCoordinates().y() * v2.getCoordinates().x() - v1.getCoordinates().x() * v2.getCoordinates().y());
        double a = (v0.getCoordinates().y() * v2.getCoordinates().x() - v0.getCoordinates().x() * v2.getCoordinates().y() + v2.getCoordinates().y() * ((Coordinates2D) coordinates).x() - v2.getCoordinates().x() * ((Coordinates2D) coordinates).y()) * (-multiplier);
        double b = (v0.getCoordinates().y() * v1.getCoordinates().x() - v0.getCoordinates().x() * v1.getCoordinates().y() + v1.getCoordinates().y() * ((Coordinates2D) coordinates).x() - v1.getCoordinates().x() * ((Coordinates2D) coordinates).y()) * multiplier;

        return (a > 0.0) && (b > 0.0) && (a + b < 1.0);
    }
}
