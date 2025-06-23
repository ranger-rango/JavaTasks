package com.habil.app;

import java.nio.file.Files;
import java.nio.file.Paths;

public class InterfaceApp {
    public static void main(String[] args) throws Exception
    {

        String jsonContent = new String(Files.readAllBytes(Paths.get("data.json")));
        String xmlContent = new String(Files.readAllBytes(Paths.get("data.xml")));

        System.out.println("Processing JSON:");
        JsonProcessor jsonProcessor = new JsonProcessor();
        jsonProcessor.process(jsonContent);

        System.out.println("Processing XML:");
        XMLProcessor xmlProcessor = new XMLProcessor();
        xmlProcessor.process(xmlContent);
    }
}

// mvn compile
// mvn exec:java -Dexec.mainClass="main.java.com.habil.app.App"