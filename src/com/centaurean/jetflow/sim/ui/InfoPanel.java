package com.centaurean.jetflow.sim.ui;

import com.centaurean.jetflow.JetFlow;

import javax.swing.*;

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
 * 14/03/13 15:47
 * @author gpnuma
 */
public class InfoPanel extends JLabel {
    private static final String FPS = "FPS = ";
    private static final String SPS = "SPS = ";
    private static InfoPanel instance = new InfoPanel();

    private String fpsText = FPS + "0";
    private String spsText = SPS + "0";

    private InfoPanel() {
        new Worker().execute();
    }

    public static InfoPanel getInstance() {
        return instance;
    }

    private class Worker extends SwingWorker {
        protected Void doInBackground() throws Exception {
            long timeStart = System.nanoTime();
            try {
                while (true) {
                    fpsText = FPS + Math.round((GLSimWindow.getInstance().getGlSimWindowListener().frames() * 1000000000.0) / (System.nanoTime() - timeStart));
                    spsText = SPS + Math.round((JetFlow.getInstance().getSolver().time() * 1000000000.0) / (System.nanoTime() - timeStart));
                    setText(fpsText + ", " + spsText);
                    Thread.sleep(500);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                throw exception;
            }
        }
    }
}
