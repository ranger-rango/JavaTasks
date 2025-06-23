package com.habil.Geometry;

public class Geometry {
    public static void area(int side)
    {
        int area = side * side;
        System.out.println("Square Area: " + area);
    }
    public static void area(double radius)
    {
        final double PI = (double) 22 / 7;
        System.out.println(PI);
        double area = PI * radius * radius;
        System.out.println("Circle Area: " + area);

    }
    public static void area(double length, double breadth)
    {
        double area = length * breadth;
        System.out.println("Rectangle Area: " + area);
    }
    public static void area(double base, double height, boolean isTriangle)
    {
        if(isTriangle)
        {
            double area = ((double) 1 / 2) * base * height;
            System.out.println("Triangle Area: " + area);
        }
    }

    public static void main(String[] args)
    {
        area(7);
        area(8.4);
        area(50, 23);
        area(2.1, 4.8, true);
    }
}
