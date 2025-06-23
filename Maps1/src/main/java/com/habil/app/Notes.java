package main.java.com.habil.app;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class Notes
{
    public static void main(String[] args)
    {
        // do not have any ordering during insertion/display
        Map <String, Integer> ageMap = new HashMap<>();

        ageMap.put("Habil", 24);
        ageMap.put("Tesfaye", 30);

        System.out.println("Habil's Age: " + ageMap.get("Habil"));
        System.out.println("Contains 30: " + ageMap.containsValue(30));

        // simple/concise easy to read
        ageMap.forEach((key, value) -> System.out.println(key + " : " + value));

        // most efficient for large datasets
        for (Map.Entry<String, Integer> entry : ageMap.entrySet())
        {
            System.out.println(entry.getKey() + " is " + entry.getValue() + " years old");
        }
        // efficient if values are accessed ocassionaly
        for (String key : ageMap.keySet())
        {
            System.out.println("Key: " + key + ", Value: " + ageMap.get(key));
        }

        // automatically sorted by keys 
        Map <String, Integer> sortedMap = new TreeMap<>();
        sortedMap.put("Mango", 1);
        sortedMap.put("Orange", 2);
        sortedMap.put("Apple", 3);

        for (Map.Entry<String, Integer> entry: sortedMap.entrySet())
        {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("TreeMap:");
        System.out.println(sortedMap);

        Map <String, String> linkedMap = new LinkedHashMap<>();
        linkedMap.put("name", "Habil");
        linkedMap.put("occupation", "Software Engineer");

        for (String key : linkedMap.keySet())
        {
            System.out.println(key + " : " + linkedMap.get(key));
        }

        Map <String, Integer> concurrentMap = new ConcurrentHashMap<>();

        concurrentMap.put("Mango", 1);
        System.out.println(concurrentMap);
    }


}
