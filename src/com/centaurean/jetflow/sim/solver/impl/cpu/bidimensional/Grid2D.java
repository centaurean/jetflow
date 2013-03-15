package com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional;

import com.centaurean.jetflow.sim.geometry.Coordinates;
import com.centaurean.jetflow.sim.geometry.Vector;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Vector2D;
import com.centaurean.jetflow.sim.solver.Grid;
import com.centaurean.jetflow.sim.solver.Particle;
import com.centaurean.jetflow.sim.solver.SmoothingKernel;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.centaurean.jetflow.JetFlow.*;
import static com.centaurean.jetflow.JetFlow.getInstance;
import static com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.Particle2D.K;
import static com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.Particle2D.REST_DENSITY;
import static com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.Solver2D.*;

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
 * 14/03/13 23:45
 * @author gpnuma
 * todo study HashSet<Particle2D> for speed
 */
public class Grid2D implements Grid {
    public static final double S_USEFUL = Math.sqrt(WIDTH * HEIGHT * USEFUL_PARTICLES / PARTICLES) / 3.0;
    ArrayList<LinkedList<Particle>> array;
    private int arrayWidth;
    private int arrayHeight;

    public Grid2D() {
        arrayWidth = (int) Math.ceil(WIDTH / S_USEFUL) + 2;
        arrayHeight = (int) Math.ceil(HEIGHT / S_USEFUL) + 2;
        System.out.println("Grid " + arrayWidth + ", " + arrayHeight);

        array = new ArrayList<LinkedList<Particle>>();
        for (int count = 0; count < arrayWidth * arrayHeight; count++)
            array.add(new LinkedList<Particle>());

    }

    @Override
    public int translate(Coordinates coordinates) {
        Coordinates2D coordinates2D = (Coordinates2D) coordinates;
        return (int) (Math.floor(coordinates2D.x() / S_USEFUL) + 1 + (Math.floor(coordinates2D.y() / S_USEFUL) + 1) * arrayWidth);
    }

    @Override
    public LinkedList<Particle> inGridCell(int index) {
        return array.get(index);
    }

    @Override
    public void add(Particle particle) {
        Particle2D particle2D = (Particle2D) particle;
        array.get(translate(particle2D.coordinates())).add(particle2D);
    }

    @Override
    public void remove(Particle particle) {
        Particle2D particle2D = (Particle2D) particle;
        array.get(translate(particle2D.coordinates())).remove(particle2D);
    }

    @Override
    public boolean gridChange(Coordinates previous, Coordinates current) {
        Coordinates2D previous2D = (Coordinates2D) previous;
        Coordinates2D current2D = (Coordinates2D) current;
        return translate(previous2D) != translate(current2D);
    }

    @Override
    public LinkedList<Particle> nearParticles(int index) {
        LinkedList<Particle> particlesFound = new LinkedList<Particle>();
        particlesFound.addAll(array.get(index - 1 + arrayWidth));
        particlesFound.addAll(array.get(index + arrayWidth));
        particlesFound.addAll(array.get(index + 1 + arrayWidth));
        particlesFound.addAll(array.get(index - 1));
        particlesFound.addAll(array.get(index));
        particlesFound.addAll(array.get(index + 1));
        particlesFound.addAll(array.get(index - 1 - arrayWidth));
        particlesFound.addAll(array.get(index - arrayWidth));
        particlesFound.addAll(array.get(index + 1 - arrayWidth));
        return particlesFound;
    }

    @Override
    public int getWidth() {
        return arrayWidth;
    }

    @Override
    public int getHeight() {
        return arrayHeight;
    }

    @Override
    public void updatePressures(SmoothingKernel smoothingKernel) {
        for (int x = 1; x < getInstance().getSolver().getGrid().getWidth() - 1; x++)
            for (int y = 1; y < getInstance().getSolver().getGrid().getHeight() - 1; y++) {
                int index = x + y * getInstance().getSolver().getGrid().getWidth();
                LinkedList<Particle> particles = getInstance().getSolver().getGrid().inGridCell(index);
                LinkedList<Particle> nearParticles = getInstance().getSolver().getGrid().nearParticles(index);
                for (Particle particle : particles) {
                    double newDensity = 0.0;
                    for (Particle nearParticle : nearParticles) {
                        Vector2D r = new Vector2D((Coordinates2D) particle.coordinates(), (Coordinates2D) nearParticle.coordinates());
                        newDensity += particle.mass().kilograms() * smoothingKernel.value(r);
                    }
                    particle.density().setValue(newDensity);
                    particle.pressure().setPascals(K * (newDensity - REST_DENSITY));
                }
            }
    }

    @Override
    public void updateSpeeds(SmoothingKernel smoothingKernel) {
        for (int x = 1; x < getInstance().getSolver().getGrid().getWidth() - 1; x++)
            for (int y = 1; y < getInstance().getSolver().getGrid().getHeight() - 1; y++) {
                int index = x + y * getInstance().getSolver().getGrid().getWidth();
                LinkedList<Particle> particles = getInstance().getSolver().getGrid().inGridCell(index);
                LinkedList<Particle> nearParticles = getInstance().getSolver().getGrid().nearParticles(index);
                for (Particle particle : particles) {
                    // Gravity
                    particle.speed().getCoordinates().translate(new Vector2D(0.0, -9.8).multiply(TIME_STEP));

                    for (Particle nearParticle : nearParticles) {
                        if (!particle.equals(nearParticle)) {
                            // Pressure forces
                            Vector2D r = new Vector2D((Coordinates2D) particle.coordinates(), (Coordinates2D) nearParticle.coordinates());
                            double Pij = nearParticle.mass().kilograms() * (particle.pressure().getPascals() / (particle.density().getValue() * particle.density().getValue()) + nearParticle.pressure().getPascals() / (nearParticle.density().getValue() * nearParticle.density().getValue()));
                            Vector delta = smoothingKernel.valueDerivative(r);
                            particle.speed().getCoordinates().translate(delta.multiply(Pij * TIME_STEP));

                            // Surface tension

                            // Viscosity
                        }
                    }
                }
            }
    }
}
