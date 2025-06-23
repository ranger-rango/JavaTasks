package com.habil.app;
import java.util.Map;
import com.habil.model.DataProcessor;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLProcessor implements DataProcessor
{
    public void process(String data)
    {
        try
        {
            XmlMapper xmlMapper = new XmlMapper();
            Map<String, Object> map = xmlMapper.readValue(data, Map.class);

            for (Map.Entry<String, Object> entry : map.entrySet())
            {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }
        catch (Exception e)
        {
            System.err.println("Failed to parse XML: " + e.getMessage());
        }
    }
}
