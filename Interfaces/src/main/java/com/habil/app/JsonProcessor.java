package com.habil.app; 
import java.util.Map;
import com.habil.model.DataProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProcessor implements DataProcessor
{
    @Override
    public void process(String data)
    {
        try
        {
            ObjectMapper jsonMapper = new ObjectMapper();
            Map<String, Object> map = jsonMapper.readValue(data, Map.class);

            for (Map.Entry<String, Object> entry : map.entrySet())
            {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }
        catch (Exception e)
        {
            System.err.println("Failed to parse JSON: " + e.getMessage());
        }

    }
}
