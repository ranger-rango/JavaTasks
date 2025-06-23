package main.java.com.habil.model;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class RotateBackups
{
    private static final String BACKUP_DIR = "backups";
    private static final int MAX_BACKUPS = 10;
    public static void rotateBackups() throws IOException
    {
        File dir = new File(BACKUP_DIR);
        File[] backupFiles = dir.listFiles((d, name) -> name.endsWith(".sql"));

        if (backupFiles == null) return;

        Arrays.sort(backupFiles, Comparator.comparingLong(File::lastModified));

        if (backupFiles.length > MAX_BACKUPS)
        {
            for (int i = 0; i < backupFiles.length - MAX_BACKUPS; i++)
            {
                System.out.println("Deleting old backup: " + backupFiles[i].getName());
                backupFiles[i].delete();
            }
        }
    }
}