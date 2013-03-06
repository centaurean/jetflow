package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

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
 * 01/03/13 14:45
 * @author gpnuma
 */
public class Vector2D implements Vector {
    private Coordinates2D coordinates;

    public Vector2D(double x, double y) {
        coordinates = new Coordinates2D(x, y);
    }

    public Vector2D(Coordinates2D coordinates) {
        this(coordinates.x(), coordinates.y());
    }

    public Vector2D(Coordinates2D c1, Coordinates2D c2) {
        this(c2.x() - c1.x(), c2.y() - c1.y());
    }

    @Override
    public Coordinates2D coordinates() {
        return coordinates;
    }

    public double length() {
        return sqrt(coordinates().x() * coordinates().x() + coordinates().y() * coordinates().y());
    }

    @Override
    public Vector2D add(Vector vector) {
        return new Vector2D(coordinates().x() + ((Coordinates2D) vector.coordinates()).x(), coordinates().y() + ((Coordinates2D) vector.coordinates()).y());
    }

    @Override
    public Vector2D multiply(double coefficient) {
        return new Vector2D(coordinates().x() * coefficient, coordinates().y() * coefficient);
    }

    @Override
    public double dotProduct(Vector vector) {
        return coordinates().x() * ((Coordinates2D) vector.coordinates()).x() + coordinates().y() * ((Coordinates2D) vector.coordinates()).y();
    }

    @Override
    public String toString() {
        return "{" + coordinates().x() + ", " + coordinates().y() + "}";
    }
}
