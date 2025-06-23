package main.java.com.habil.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PBTutorial
{
    public static void getOutputErrors() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder("ls", "-la");
        Process process = processBuilder.start();

        BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        while ((line = outputReader.readLine()) != null)
        {
            System.out.println("OUTPUT: " + line);
        }

        BufferedReader erroReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        while ((line = erroReader.readLine()) != null)
        {
            System.out.println("ERROR: " + line);
        }
    }

    public static void writeToProcessInput() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder("cat");
        Process process = processBuilder.start();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        writer.write("Hello, ProcessBuilder input!\n");
        writer.flush();
        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null)
        {
            System.out.println("RESPONSE: " + line);
        }
    }

    public static void redirectOutputToFile() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder("echo", "This output was redirected by the ProcessBuilder\n");
        processBuilder.redirectOutput(new File("output.txt"));
        processBuilder.start();

    }

    public static void redirectInputToFile() throws IOException
    {
        File inputFile = new File("input.txt");
        ProcessBuilder processBuilder = new ProcessBuilder("cat");
        processBuilder.redirectInput(inputFile);
        processBuilder.inheritIO();
        processBuilder.start();
    }

    public static void getExitCode() throws IOException, InterruptedException
    {
        ProcessBuilder processBuilder = new ProcessBuilder("ls", "-la");
        processBuilder.inheritIO(); // show directly in terminal, useful in running cmds
        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        System.out.println("Process exited with code: " + exitCode);
    }
    public static void main(String[] args) throws IOException, InterruptedException
    {
        // getOutputErrors();
        // writeToProcessInput();
        // redirectOutputToFile();
        // redirectInputToFile();
        getExitCode();
    }
}
