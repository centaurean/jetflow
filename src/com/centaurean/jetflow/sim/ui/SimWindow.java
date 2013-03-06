package com.centaurean.jetflow.sim.ui;

import com.centaurean.jetflow.JetFlow;
import com.centaurean.jetflow.sim.solver.Particle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

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
public class SimWindow extends JFrame {
    private static SimWindow instance = new SimWindow();

    private JPanel simPanel;
    private BufferStrategy bufferStrategy;

    public static SimWindow getInstance() {
        return instance;
    }

    public SimWindow() {
        super();
        setBounds(200, 200, 0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container content = getContentPane();
        simPanel = new JPanel(true);
        simPanel.setPreferredSize(new Dimension(960, 960));
        simPanel.setBackground(Color.white);
        content.add(simPanel);

        setIgnoreRepaint(true);
        pack();
        setResizable(false);
        setVisible(true);

        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
    }

    public void update() {
        Graphics2D graphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 960, 960);

        // todo draw
        for (Particle particle : JetFlow.getInstance().getSolver().getParticles())

            graphics2D.dispose();
        bufferStrategy.show();

        JetFlow.getInstance().getSolver().step();
    }
}