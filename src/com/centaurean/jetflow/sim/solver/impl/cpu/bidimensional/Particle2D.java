package com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional;

import com.centaurean.jetflow.sim.geometry.Vector;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Vector2D;
import com.centaurean.jetflow.sim.solver.Mass;
import com.centaurean.jetflow.sim.solver.Particle;
import com.centaurean.jetflow.sim.solver.Pressure;
import com.centaurean.jetflow.sim.solver.Viscosity;

import java.awt.image.WritableRaster;

import static java.lang.Math.floor;

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
 * 03/03/13 20:13
 * @author gpnuma
 */
public class Particle2D implements Particle {
    private static final int[] particleColor = {0, 0, 255};

    private Coordinates2D coordinates;
    private Speed2D speed;
    private Mass mass;
    private Pressure pressure;
    private Viscosity viscosity;

    private Vector2D translation;

    public Particle2D(Coordinates2D coordinates2D) {
        this.coordinates = coordinates2D;
        this.translation = new Vector2D(Math.random() - 0.5, Math.random() - 0.5);
    }

    @Override
    public Coordinates2D coordinates() {
        return coordinates;
    }

    @Override
    public Speed2D speed() {
        return speed;
    }

    public Mass mass() {
        return mass;
    }

    public Pressure pressure() {
        return pressure;
    }

    public Viscosity viscosity() {
        return viscosity;
    }

    @Override
    public void translate(Vector vector) {
        coordinates().translate(vector);
    }

    /**
     * Draws a particle as a pixel
     *
     * @param writableRaster a raster to draw a pixel to
     */
    @Override
    public void draw(WritableRaster writableRaster) {
        writableRaster.setPixel((int) floor(coordinates().x()), (int) floor(coordinates().y()), particleColor);
    }

    public Vector2D translation() {
        return translation;
    }

    public void invertTranslation() {
        translation = translation().multiply(-1.0);
    }
}
