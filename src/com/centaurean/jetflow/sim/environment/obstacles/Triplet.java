package com.centaurean.jetflow.sim.environment.obstacles;

/**
 * jetFlow
 * guillaume
 * 04/03/13 21:08
 */
public class Triplet<E> {
    private E a;
    private E b;
    private E c;

    public Triplet(E a, E b, E c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public E a() {
        return a;
    }

    public E b() {
        return a;
    }

    public E c() {
        return a;
    }
}
