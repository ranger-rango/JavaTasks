package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String json = new String(Files.readAllBytes(Paths.get("Reg-Payload.json")));

        ObjectMapper mapper = new ObjectMapper();

        try
        {
            Device device = mapper.readValue(json, Device.class);
            System.out.println("Device Id: " + device.getDevice_id());
            System.out.println("Model: " + device.getModel());
            System.out.println("Firmware Version: " + device.getFirmware_version());
            System.out.println("Registered At: " + device.getRegistered_at());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}