package com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.kernels;

import com.centaurean.jetflow.sim.geometry.Vector;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Vector2D;
import com.centaurean.jetflow.sim.solver.impl.cpu.CPUKernel;

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
 * 10/03/13 15:16
 * @author gpnuma
 */
public class CubicSplineKernel extends CPUKernel {
    private static final double SIGMA = 10.0 / (7.0 * Math.PI);

    private double cD;

    public CubicSplineKernel(double h) {
        super(h);
        cD = SIGMA / (h * h);
    }

    @Override
    public double value(Vector variation) {
        double r = variation.getLength();
        double q = r / h;
        if (q <= 1.0)
            return cD * (1.0 - (3.0 / 2.0) * q * q + (3.0 / 4.0) * q * q * q);
        if (q < 2.0)
            return cD * (1.0 / 4.0) * (2.0 - q) * (2.0 - q) * (2.0 - q);
        return 0.0;
    }

    @Override
    public Vector valueDerivative(Vector variation) {
        double r = variation.getLength();
        double q = r / h;
        if (q <= 1.0)
            return variation.toUnit().multiply(cD * (1.0 / 4.0) * (-12.0 * q + 9.0 * q * q));
        if (q < 2.0)
            return variation.toUnit().multiply(cD * -(3.0 / 4.0) * (-2.0 + q) * (-2.0 + q));
        return new Vector2D(0.0, 0.0);
    }
}
