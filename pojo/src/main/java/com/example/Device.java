package com.example;

public class Device
{
    private String device_id;
    private String model;
    private String firmware_version;
    private String registered_at;
    public Device()
    {}
    public String getDevice_id()
    {
        return device_id;
    }
    public String getModel()
    {
        return model;
    }
    public String getFirmware_version()
    {
        return firmware_version;
    }
    public String getRegistered_at()
    {
        return registered_at;
    }


    public void setDevice_id(String device_id)
    {
        this.device_id = device_id;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public void setFirmware_version(String firmware_version)
    {
        this.firmware_version = firmware_version;
    }
    public void setRegistered_at(String registered_at)
    {
        this.registered_at = registered_at;
    }

    

    
}
