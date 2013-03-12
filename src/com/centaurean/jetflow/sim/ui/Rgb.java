package com.centaurean.jetflow.sim.ui;

import static java.lang.Math.round;

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
 * 10/03/13 16:50
 * @author gpnuma
 */
public class RGB {
    public double r;
    public double g;
    public double b;

    public RGB(double r, double g, double b) {
        setR(r);
        setG(g);
        setB(b);
    }

    public static RGB map(double value, int precision) {
        if (value >= 1.0f || value < 0.0f)
            return new RGB(1.0f, 0.0f, 0.0f);
        RGB rgb = new RGB(0.0f, 0.0f, 0.0f);
        int index = (int) ((precision << 2) * value);
        float coefficient = 1.0f / (float) precision;
        if (index < precision) {
            rgb.setG(coefficient * index);
            rgb.setB(1.0f);
            return rgb;
        }
        if (index < (precision << 1)) {
            rgb.setG(1.0f);
            rgb.setB(1.0f - coefficient * (index - precision));
            return rgb;
        }
        if (index < (precision * 3)) {
            rgb.setR(coefficient * (index - (precision << 1)));
            rgb.setG(1.0f);
            return rgb;
        }
        if (index < (precision << 2)) {
            rgb.setR(1.0f);
            rgb.setG(1.0f - coefficient * (index - precision * 3));
            return rgb;
        }
        return rgb;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double r() {
        return r;
    }

    public double g() {
        return g;
    }

    public double b() {
        return b;
    }

    public int[] toIntArray(int maxValue) {
        return new int[]{(int) round(r() * maxValue), (int) round(g() * maxValue), (int) round(b() * maxValue)};
    }
}
