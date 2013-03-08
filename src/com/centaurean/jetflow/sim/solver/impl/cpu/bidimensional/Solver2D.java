package com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional;


import com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles.Obstacles2D;
import com.centaurean.jetflow.sim.environment.obstacles.Obstacle;
import com.centaurean.jetflow.sim.environment.obstacles.Obstacles;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Vector2D;
import com.centaurean.jetflow.sim.solver.Particle;
import com.centaurean.jetflow.sim.solver.Particles;
import com.centaurean.jetflow.sim.solver.Solver;

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
    private static Solver2D instance = new Solver2D();

    private Obstacles2D obstacles2D;
    private Particles2D particles2D;

    public static Solver2D getInstance() {
        return instance;
    }

    private Solver2D() {
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
        for (Particle particle2D : particles2D) {
            // Step 1 : obstacles
            for (Obstacle obstacle2D : obstacles2D)
                if (obstacle2D.includes(particle2D.coordinates())) ;
            // todo manage bounce

            // Step 2 : smoothed kernel
            if (((Coordinates2D) particle2D.coordinates()).x() < 799 && ((Coordinates2D) particle2D.coordinates()).y() < 799)
                if (((Coordinates2D) particle2D.coordinates()).x() > 1.0 && ((Coordinates2D) particle2D.coordinates()).y() > 1.0)
                    particle2D.translate(new Vector2D(Math.random() - 0.5, Math.random() - 0.5));
        }
    }
}
