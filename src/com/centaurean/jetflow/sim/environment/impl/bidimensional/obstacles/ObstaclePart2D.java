package com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles;

import com.centaurean.jetflow.sim.environment.obstacles.ObstaclePart;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Point2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Triangle2D;

import java.awt.*;
import java.awt.geom.Path2D;


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
 * 06/03/13 17:48
 * @author gpnuma
 */
public class ObstaclePart2D extends Triangle2D implements ObstaclePart {
    private static final Path2D path2D = new Path2D.Double();

    public ObstaclePart2D(Point2D a, Point2D b, Point2D c) {
        super(a, b, c);
    }

    /**
     * Draw a triangle
     * This method is not thread safe for performance reasons
     *
     * @param graphics2D the graphics environment
     */
    @Override
    public void draw(Graphics2D graphics2D) {
        path2D.reset();
        path2D.moveTo(a().coordinates().x(), a().coordinates().y());
        path2D.lineTo(b().coordinates().x(), b().coordinates().y());
        path2D.lineTo(c().coordinates().x(), c().coordinates().y());
        path2D.closePath();
        graphics2D.fill(path2D);
    }
}
