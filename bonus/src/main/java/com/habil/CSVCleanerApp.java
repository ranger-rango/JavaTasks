package com.habil;

import java.io.IOException;

public class CSVCleanerApp
{
    public static void main(String[] args) throws IOException {
        CleanCSV.cleanCSV();
    }
}

// time MAVEN_OPTS="-Xmx512m" mvn exec:java -Dexec.mainClass="com.habil.CSVCleanerApp"
