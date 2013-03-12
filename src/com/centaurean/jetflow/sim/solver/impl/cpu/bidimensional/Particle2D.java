package com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional;

import com.centaurean.jetflow.JetFlow;
import com.centaurean.jetflow.sim.geometry.Vector;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Vector2D;
import com.centaurean.jetflow.sim.solver.*;
import com.centaurean.jetflow.sim.ui.RGB;

import java.awt.image.WritableRaster;

import static com.centaurean.jetflow.JetFlow.SCALE;
import static com.centaurean.jetflow.JetFlow.getInstance;
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
    public static final double REST_DENSITY = 1.0;
    public static final double K = 330.0 * 330.0; // k = Cs^2

    private Coordinates2D coordinates;
    private Speed2D speed;
    private Density density;
    private Mass mass;
    private Pressure pressure;
    private Viscosity viscosity;

    public Particle2D(Mass mass, Coordinates2D coordinates2D) {
        this.mass = mass;
        this.coordinates = coordinates2D;
        this.speed = new Speed2D(0.0, 0.0);//Math.random() - 0.5, Math.random() - 0.5);
        this.density = new Density(0.0);
        this.pressure = new Pressure(0.0);
    }

    @Override
    public Coordinates2D coordinates() {
        return coordinates;
    }

    @Override
    public void updateCoordinates() {
        translate(speed().multiply(JetFlow.TIME_STEP));
        if (coordinates().x() > 798.0 * SCALE)
            speed().getCoordinates().invert();
        if (coordinates().y() > 798.0 * SCALE)
            speed().getCoordinates().invert();
        if (coordinates().x() < 2.0 * SCALE)
            speed().getCoordinates().invert();
        if (coordinates().y() < 2.0 * SCALE)
            speed().getCoordinates().invert();
    }

    @Override
    public Speed2D speed() {
        return speed;
    }

    @Override
    public void updateSpeed(SmoothingKernel smoothingKernel) {
        Vector2D acceleration = new Vector2D(0.0, 0.0);
        for (Particle particle : getInstance().getSolver().getParticles())
            if (!particle.equals(this)) {
                Vector2D vectorTo = new Vector2D(coordinates(), (Coordinates2D) particle.coordinates());
                double Pij = -particle.mass().kilograms() * (pressure().getPascals() / (density().getValue() * density().getValue()) + particle.pressure().getPascals() / (particle.density().getValue() * particle.density().getValue()));
                Vector delta = smoothingKernel.valueDerivative(vectorTo);
                acceleration.getCoordinates().translate(delta.multiply(Pij));
            }
        speed().getCoordinates().translate(acceleration.multiply(JetFlow.TIME_STEP));
    }

    @Override
    public Density density() {
        return density;
    }

    @Override
    public void updateDensity(SmoothingKernel smoothingKernel) {
        double newDensity = 0.0;
        for (Particle particle : getInstance().getSolver().getParticles())
            newDensity += mass().kilograms() * smoothingKernel.value(new Vector2D(coordinates(), (Coordinates2D) particle.coordinates()));
        density().setValue(newDensity);
    }

    public Mass mass() {
        return mass;
    }

    public Pressure pressure() {
        return pressure;
    }

    @Override
    public void updatePressure() {
        pressure().setPascals(K * (density().getValue() - REST_DENSITY));
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
        RGB rgb = RGB.map(0.75 * density().getValue(), 1024);
        int x = (int) floor(coordinates().x() / SCALE);
        int y = (int) floor(coordinates().y() / SCALE);
        try {
            writableRaster.setPixel(x, y, new int[]{0, 0, 255});//rgb.toIntArray(255));
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(coordinates().x() + ", " + coordinates().y());
        }
    }
}
