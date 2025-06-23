package com.habil.StringFormatter;

public class StringFormatter {
    public static String format(String input)
    {
        
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String format(String input, int repeat)
    {
        StringBuilder repeatedInput = new StringBuilder();
        for (int i = 0; i < repeat; i++)
        {
            repeatedInput.append(input);
        }
        return repeatedInput.toString();
    }

    public static String format(String input, String prefix, String suffix)
    {
        prefix += input;
        prefix += suffix;
        return prefix;

    }

    public static void main(String[] args)
    {
        System.out.println(format("overloading"));
        System.out.println(format(" Lorem Ipsum", 5));
        System.out.println(format("quick", "the ", "brown fox"));

    }
}
