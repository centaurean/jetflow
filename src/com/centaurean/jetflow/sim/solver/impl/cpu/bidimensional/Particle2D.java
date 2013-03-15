package com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional;

import com.centaurean.jetflow.sim.geometry.Vector;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Vector2D;
import com.centaurean.jetflow.sim.solver.*;

import static com.centaurean.jetflow.JetFlow.*;

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
    public static final double K = 330.0 * 330.0 * SCALE; // k = Cs^2
    public static final double K_BOUNCE = 0.95;

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
        Coordinates2D current = coordinates();
        Coordinates2D next = coordinates().add(speed().multiply(SCALE * TIME_STEP));

        if (next.x() < 0.0) {
            next.setX(-next.x());
            speed().getCoordinates().setX(-K_BOUNCE * speed().getCoordinates().x());
        }
        if (next.x() > Solver2D.WIDTH) {
            next.setX(Solver2D.WIDTH - (next.x() - Solver2D.WIDTH));
            speed().getCoordinates().setX(-K_BOUNCE * speed().getCoordinates().x());
        }
        if (next.y() < 0.0) {
            next.setY(-next.y());
            speed().getCoordinates().setY(-K_BOUNCE * speed().getCoordinates().y());
        }
        if (next.y() > Solver2D.HEIGHT) {
            next.setY(Solver2D.HEIGHT - (next.y() - Solver2D.HEIGHT));
            speed().getCoordinates().setY(-K_BOUNCE * speed().getCoordinates().y());
        }

        if (getInstance().getSolver().getGrid().gridChange(current, next)) {
            getInstance().getSolver().getGrid().remove(this);
            coordinates = next;
            getInstance().getSolver().getGrid().add(this);
        } else {
            coordinates = next;
        }
    }

    @Override
    public Speed2D speed() {
        return speed;
    }

    @Override
    public void updateSpeed(SmoothingKernel smoothingKernel) {
        // Gravity
        speed().getCoordinates().translate(new Vector2D(0.0, -9.8).multiply(TIME_STEP));

        for (Particle particle : getInstance().getSolver().getParticles()) {
            if (!particle.equals(this)) {
                // Pressure forces
                Vector2D r = new Vector2D(coordinates(), (Coordinates2D) particle.coordinates());
                double Pij = particle.mass().kilograms() * (pressure().getPascals() / (density().getValue() * density().getValue()) + particle.pressure().getPascals() / (particle.density().getValue() * particle.density().getValue()));
                Vector delta = smoothingKernel.valueDerivative(r);
                speed().getCoordinates().translate(delta.multiply(Pij * TIME_STEP));

                // Surface tension

                // Viscosity
            }
        }
    }

    @Override
    public Density density() {
        return density;
    }

    @Override
    public Mass mass() {
        return mass;
    }

    @Override
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

    @Override
    public int hashCode() {
        return coordinates().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (!(object instanceof Particle2D))
            return false;
        Particle2D particle = (Particle2D) object;
        return (this.coordinates().equals(particle.coordinates()));
    }
}
