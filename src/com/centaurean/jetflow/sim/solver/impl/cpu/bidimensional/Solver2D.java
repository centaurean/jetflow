package com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional;


import com.centaurean.jetflow.JetFlow;
import com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles.Obstacles2D;
import com.centaurean.jetflow.sim.environment.obstacles.Obstacles;
import com.centaurean.jetflow.sim.solver.Particle;
import com.centaurean.jetflow.sim.solver.Particles;
import com.centaurean.jetflow.sim.solver.SmoothingKernel;
import com.centaurean.jetflow.sim.solver.Solver;
import com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.kernels.Poly6Kernel;
import com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.kernels.SpikyKernel;
import com.centaurean.jetflow.sim.ui.GLSimWindow;

import static com.centaurean.jetflow.JetFlow.SCALE;
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
 * 06/03/13 15:05
 * @author gpnuma
 */
public class Solver2D implements Solver {
    public static final double WIDTH = GLSimWindow.WIDTH * SCALE;
    public static final double HEIGHT = GLSimWindow.HEIGHT * SCALE;

    public static final double USEFUL_PARTICLES = 50.0;
    public static final double R_USEFUL = sqrt((WIDTH * HEIGHT * USEFUL_PARTICLES) / (Math.PI * JetFlow.PARTICLES));

    private static Solver2D instance = new Solver2D();

    private Obstacles2D obstacles2D;
    private Particles2D particles2D;
    private SmoothingKernel densityKernel = new Poly6Kernel(R_USEFUL);
    private SmoothingKernel pressureKernel = new SpikyKernel(R_USEFUL);
    private long time;

    public static Solver2D getInstance() {
        return instance;
    }

    private Solver2D() {
        time = 0;
    }

    @Override
    public void initialize() {
        for (Particle particle : particles2D) {
            particle.updateDensity(densityKernel);
            particle.updatePressure();
        }
    }

    @Override
    public void setObstacles(Obstacles obstacles2D) {
        this.obstacles2D = (Obstacles2D) obstacles2D;
    }

    @Override
    public void setParticles(Particles particles2D) {
        this.particles2D = (Particles2D) particles2D;
    }

    @Override
    public Obstacles2D getObstacles() {
        return obstacles2D;
    }

    @Override
    public Particles2D getParticles() {
        return particles2D;
    }

    @Override
    public void step() {
        for (Particle particle : particles2D)
            particle.updateDensity(densityKernel);
        for (Particle particle : particles2D)
            particle.updatePressure();
        for (Particle particle : particles2D)
            particle.updateSpeed(pressureKernel);
        for (Particle particle : particles2D)
            particle.updateCoordinates();

        // todo manage bounce

        time++;
    }

    @Override
    public long time() {
        return time;
    }
}
