package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

import com.centaurean.jetflow.sim.geometry.Coordinates;
import com.centaurean.jetflow.sim.geometry.Vector;

import static java.lang.Math.sqrt;

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
 * 02/03/13 15:47
 * @author gpnuma
 */
public class Coordinates2D implements Coordinates {
    private double x;
    private double y;

    public Coordinates2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    @Override
    public double distance(Coordinates coordinates) {
        double delta_x = x() - ((Coordinates2D) coordinates).x();
        double delta_y = y() - ((Coordinates2D) coordinates).y();
        return sqrt(delta_x * delta_x + delta_y * delta_y);
    }

    @Override
    public void invert() {
        this.x = -x();
        this.y = -y();
    }

    @Override
    public Coordinates2D add(Coordinates coordinates) {
        return new Coordinates2D(x() + ((Coordinates2D) coordinates).x(), y() + ((Coordinates2D) coordinates).y());
    }

    @Override
    public Coordinates2D multiply(double c) {
        return new Coordinates2D(c * x(), c * y());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (!(object instanceof Coordinates2D))
            return false;
        Coordinates2D coordinates = (Coordinates2D) object;
        return (this.x() == coordinates.x() && this.y() == coordinates.y());
    }

    @Override
    public void translate(Vector vector) {
        x += ((Coordinates2D) vector.getCoordinates()).x();
        y += ((Coordinates2D) vector.getCoordinates()).y();
    }
}
