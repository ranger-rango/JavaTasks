package com.habil.JVM;

import java.time.ZoneId;
import java.time.ZonedDateTime;

// -Duser.timezone=Africa/Nairobi -Xms64m -Xmx256m

public class JVMOptions {
    public static void printTimeZone(String[] args)
    {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Current date and time: " + now);
        System.out.println("TimeZone: " + ZoneId.systemDefault());
    }
    
}
