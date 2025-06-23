package main.java.com.habil.app;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ReflectionLoader
{
    public static void main(String[] args) throws Exception
    {
        File jarFile = new File("Reflection/ReflectionApp.jar");
        URL jarUrl = jarFile.toURI().toURL();
        System.out.println(jarUrl);
        URLClassLoader loader = new URLClassLoader(new URL[] { jarUrl });

        Class<?> employeeClass = loader.loadClass("main.java.com.habil.models.Employeee");
        Constructor<?> constructor = employeeClass.getDeclaredConstructor(int.class, String.class, String.class, String.class);
        Object object = constructor.newInstance(3131, "1331", "Takeyasu", "Support");

        Method setId = employeeClass.getMethod("setEmployeeId", int.class);
        Method setNumber = employeeClass.getMethod("setEmployeeNumber", String.class);
        Method setName = employeeClass.getMethod("setName", String.class);
        Method setDept = employeeClass.getMethod("setDepartment", String.class);

        setId.invoke(object, 1416);
        setNumber.invoke(object, "8924");
        setName.invoke(object, "Ura");
        setDept.invoke(object, "Technical");

        Constructor<?> constructor2 = employeeClass.getDeclaredConstructor(int.class, String.class);
        Object object2 = constructor2.newInstance(4355, "5242");

        Class<?> utilsClass = loader.loadClass("main.java.com.habil.models.Utils");
        Method printEmployees = utilsClass.getDeclaredMethod("displayEmployeeDetails", employeeClass);
        printEmployees.setAccessible(true);
        printEmployees.invoke(null, object);
        printEmployees.invoke(null, object2);
        
        loader.close();
    }
}
