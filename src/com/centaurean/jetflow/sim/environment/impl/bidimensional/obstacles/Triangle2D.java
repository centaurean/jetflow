package com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles;

import com.centaurean.jetflow.sim.environment.obstacles.Triangle;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Vector2D;

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
 * 04/03/13 21:27
 * @author guillaume
 */
public class Triangle2D extends Triangle<Anchors2D, Coordinates2D> {
    public Triangle2D(Integer a, Integer b, Integer c) {
        super(a, b, c);
    }

    /**
     * Determine if coordinates are included in the triangle
     *
     * @param anchors2D     the list of points in the environment
     * @param coordinates2D a given set of 2D coordinates
     * @return true if the triangle contains the given coordinates, false otherwise
     */
    @Override
    public boolean includes(Anchors2D anchors2D, Coordinates2D coordinates2D) {
        Vector2D v0 = new Vector2D(anchors2D.get(a()).coordinates());
        Vector2D v1 = new Vector2D(anchors2D.get(b()).coordinates().add(anchors2D.get(a()).coordinates().multiply(-1.0)));
        Vector2D v2 = new Vector2D(anchors2D.get(c()).coordinates().add(anchors2D.get(a()).coordinates().multiply(-1.0)));

        double multiplier = 1.0 / (v1.coordinates().y() * v2.coordinates().x() - v1.coordinates().x() * v2.coordinates().y());
        double a = (v0.coordinates().y() * v2.coordinates().x() - v0.coordinates().x() * v2.coordinates().y() + v2.coordinates().y() * coordinates2D.x() - v2.coordinates().x() * coordinates2D.y()) * (-multiplier);
        double b = (v0.coordinates().y() * v1.coordinates().x() - v0.coordinates().x() * v1.coordinates().y() + v1.coordinates().y() * coordinates2D.x() - v1.coordinates().x() * coordinates2D.y()) * multiplier;

        return (a > 0.0) && (b > 0.0) && (a + b < 1.0);
    }
}
