package main.java.com.habil.app;

import java.io.IOException;

public class ProcessBuilderFuncs
{

    public static void osVersion() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder("uname", "-a");
        processBuilder.inheritIO();
        processBuilder.start();
    }

    public static void osRunningProcesses() throws IOException, InterruptedException
    {
        ProcessBuilder runningProcesses = new ProcessBuilder("ps", "aux");
        runningProcesses.inheritIO();
        runningProcesses.start();

        ProcessBuilder mkdirCdEcho = new ProcessBuilder("bash", "-c", "mkdir ProcessBuilder/procBuilder && cd ProcessBuilder/procBuilder && echo \"$(ps aux)\" > running_processes.txt");
        Process mkdirCdEchoProcess = mkdirCdEcho.start();
        mkdirCdEchoProcess.waitFor();
    }

    public static void diskInfo() throws IOException
    {
        ProcessBuilder dskInfo = new ProcessBuilder("lsblk");
        dskInfo.inheritIO();
        dskInfo.start();
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        osVersion();
        osRunningProcesses();
        diskInfo();
    }
}
