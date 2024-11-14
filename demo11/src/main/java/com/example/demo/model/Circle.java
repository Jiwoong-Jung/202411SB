package com.example.demo.model;

/**
 *
 * @author U51199
 *
 */
public class Circle {

    private int radius;

    public Circle() {
        radius = 1;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}
