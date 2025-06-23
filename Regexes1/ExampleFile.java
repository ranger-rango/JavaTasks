package Regexes1;

public class ExampleFile
{
    private final double PI = 22.0 / 7.0;
    private static double radius;
    public String name;
    public int age;

    private double getArea (double rad)
    {
        return PI * radius * radius;
    }

    public void displayArea()
    {
        radius = 21.21;
        System.out.println(getArea(radius));
    }
}
