package com.centaurean.jetflow.sim.geometry.impl.bidimensional;

import com.centaurean.jetflow.sim.geometry.Coordinates;

import static java.lang.Math.sqrt;

/**
 * jetFlow
 * guillaume
 * 02/03/13 15:47
 */
public class Coordinates2D implements Coordinates<Coordinates2D> {
    private double x;
    private double y;

    public Coordinates2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    @Override
    public double distance(Coordinates2D c) {
        double delta_x = x() - c.x();
        double delta_y = y() - c.y();
        return sqrt(delta_x * delta_x + delta_y * delta_y);
    }

    @Override
    public Coordinates2D add(Coordinates2D coordinates2D) {
        return new Coordinates2D(x() + coordinates2D.x(), y() + coordinates2D.y());
    }

    @Override
    public Coordinates2D multiply(double c) {
        return new Coordinates2D(c * x(), c * y());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (!(object instanceof Coordinates2D))
            return false;
        Coordinates2D coordinates = (Coordinates2D) object;
        return (this.x() == coordinates.x() && this.y() == coordinates.y());
    }
}
