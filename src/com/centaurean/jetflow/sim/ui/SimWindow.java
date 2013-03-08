package com.centaurean.jetflow.sim.ui;

import com.centaurean.jetflow.JetFlow;
import com.centaurean.jetflow.sim.environment.obstacles.Obstacle;
import com.centaurean.jetflow.sim.solver.Particle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

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
 * 06/03/13 14:56
 * @author gpnuma
 */
public class SimWindow extends javax.swing.JPanel {
    private static final String FPS = "FPS = ";
    private static final String SPS = "SPS = ";

    private static SimWindow instance = new SimWindow();

    private Image imageSource = null;
    private String fpsText = FPS + "0";
    private String spsText = SPS + "0";

    public static SimWindow getInstance() {
        return instance;
    }

    private SimWindow() {
        new Worker().execute();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imageSource != null)
            g.drawImage(imageSource, 0, 0, this);
    }

    private class Worker extends SwingWorker<Void, Image> {
        protected void process(java.util.List<Image> chunks) {
            for (Image bufferedImage : chunks) {
                imageSource = bufferedImage;
                repaint();
            }
        }

        protected Void doInBackground() throws Exception {
            long frames = 0;
            long timeStart = System.nanoTime();
            try {
                while (true) {
                    BufferedImage bufferedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics2D = bufferedImage.createGraphics();
                    //graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

                    graphics2D.setColor(Color.white);
                    graphics2D.fillRect(0, 0, 800, 800);

                    WritableRaster raster = bufferedImage.getRaster();
                    for (Particle particle : JetFlow.getInstance().getSolver().getParticles())
                        particle.draw(raster);

                    graphics2D.setColor(Color.darkGray);
                    for (Obstacle obstacle : JetFlow.getInstance().getSolver().getObstacles())
                        obstacle.draw(graphics2D);

                    graphics2D.setColor(Color.white);
                    graphics2D.fillRect(5, 5, 75, 40);
                    graphics2D.setColor(Color.red);
                    graphics2D.drawString(fpsText, 10, 20);
                    graphics2D.drawString(spsText, 10, 40);

                    graphics2D.dispose();
                    publish(bufferedImage);

                    frames++;
                    if (frames % 100 == 0)
                        fpsText = FPS + Math.round((frames * 1000000000.0) / (System.nanoTime() - timeStart));
                    if (JetFlow.getInstance().getSolver().time() % 100 == 0)
                        spsText = SPS + Math.round((JetFlow.getInstance().getSolver().time() * 1000000000.0) / (System.nanoTime() - timeStart));
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                throw exception;
            }
        }
    }

}