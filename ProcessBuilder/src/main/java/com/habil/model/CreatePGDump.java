package main.java.com.habil.model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreatePGDump
{
    private static final String BACKUP_DIR = "ProcessBuilder/backups";
    private static final String PG_DUMP_PATH = "/usr/bin/pg_dump";
    private static final String DB_NAME = "online_exams";

    public static void performBackup() throws IOException, InterruptedException
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String backupFileName = BACKUP_DIR + File.separator + "backups_" + timeStamp + ".sql";

        List<String> command = Arrays.asList(PG_DUMP_PATH, "-Fc", "-f", backupFileName, DB_NAME);
        
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.environment().put("PGUSER", "username");
        processBuilder.environment().put("PGPASSWORD", "password");

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode == 0)
        {
            System.out.println("Backup Created: " + backupFileName);

        }
        else
        {
            System.err.println("Backup Failed with exit code: " + exitCode);
            
        }
    }
}