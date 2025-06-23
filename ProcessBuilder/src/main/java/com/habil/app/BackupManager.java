package main.java.com.habil.app;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import main.java.com.habil.model.CreatePGDump;
import main.java.com.habil.model.RotateBackups;

public class BackupManager 
{
    private static final String BACKUP_DIR = "ProcessBuilder/backups";
    private static final int INTERVAL_SECONDS = 2;
    public static void main(String[] args)
    {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        new File(BACKUP_DIR).mkdirs();

        Runnable backuptask = () -> 
        {
            try
            {
                CreatePGDump.performBackup();
                RotateBackups.rotateBackups();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        };

        System.out.println("Backup job started. Taking backup every " + INTERVAL_SECONDS);
        scheduler.scheduleAtFixedRate(backuptask, 0, INTERVAL_SECONDS, TimeUnit.SECONDS);
    }
}
