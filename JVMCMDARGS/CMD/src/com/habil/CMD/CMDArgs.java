package com.habil.CMD;

public class CMDArgs {
    public static String argParser(String[] args, String flag)
    {
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals(flag))
            {
                return args[i + 1];
            }
        }

        return null;
    }
}
