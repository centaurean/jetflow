package com.centaurean.jetflow.sim.ui;

import com.centaurean.jetflow.JetFlow;
import com.centaurean.jetflow.sim.solver.Particle;
import com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.Particle2D;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.gl2.GLUgl2;

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
 * 14/03/13 02:38
 * @author gpnuma
 */
public class GLSimWindowListener implements GLEventListener {
    private GLUgl2 glu = new GLUgl2();
    private long frames = 0;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glClearDepthf(1.0f);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        glu.gluLookAt(
                0f, 0f, 2f,
                0.5 * GLSimWindow.WIDTH * JetFlow.SCALE, 0.5 * GLSimWindow.HEIGHT * JetFlow.SCALE, 0f,
                0f, 1f, 0f
        );

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0.1, 0.1, 0.1);
        gl.glVertex3d(0.0, 0.0, 0.0);
        gl.glVertex3d(GLSimWindow.WIDTH * JetFlow.SCALE, 0.0, 0.0);
        gl.glVertex3d(GLSimWindow.WIDTH * JetFlow.SCALE, GLSimWindow.HEIGHT * JetFlow.SCALE, 0.0);
        gl.glVertex3d(0.0, GLSimWindow.HEIGHT * JetFlow.SCALE, 0.0);
        gl.glEnd();

        gl.glBegin(GL2.GL_POINTS);

        for (Particle particle : JetFlow.getInstance().getSolver().getParticles()) {
            Particle2D particle2D = (Particle2D) particle;
            RGB colorMap = RGB.map(particle2D.density().getValue() / 1.5, 1024);
            gl.glColor3d(colorMap.r(), colorMap.g(), colorMap.b());
            gl.glVertex3d(particle2D.coordinates().x(), particle2D.coordinates().y(), 0.0);
        }

        gl.glEnd();
        frames++;
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glViewport(x, y, GLSimWindow.WIDTH, GLSimWindow.HEIGHT);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(2.3f, 1.0f/*(float) width / height*/, 1.0f, 10.0f);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    public long frames() {
        return frames;
    }
}
