package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

import com.centaurean.jetflow.sim.geometry.Point;
import com.centaurean.jetflow.sim.geometry.Segment;

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
public class Segment2D implements Segment {
    private Point2D a;
    private Point2D b;
    private Point2D midPoint;

    public Segment2D(Point2D a, Point2D b) {
        this.a = a;
        this.b = b;
        this.midPoint = new Point2D(0.5 * (((Coordinates2D) a().coordinates()).x() + ((Coordinates2D) b().coordinates()).x()), 0.5 * (((Coordinates2D) a().coordinates()).y() + ((Coordinates2D) b().coordinates()).y()));
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
    public double position(Point c) {
        return (((Coordinates2D) b().coordinates()).x() - ((Coordinates2D) a().coordinates()).x()) * (((Coordinates2D) c.coordinates()).y() - ((Coordinates2D) a().coordinates()).y()) - (((Coordinates2D) b().coordinates()).y() - ((Coordinates2D) a().coordinates()).y()) * (((Coordinates2D) c.coordinates()).x() - ((Coordinates2D) a().coordinates()).x());
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
