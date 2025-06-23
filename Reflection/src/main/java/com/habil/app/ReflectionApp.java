package main.java.com.habil.app;

import java.lang.reflect.Constructor;
// import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionApp
{
    public static void main(String[] args) throws Exception
    {
        Class<?> employeeReflector = Class.forName("main.java.com.habil.models.Employeee");
        Constructor<?> constructor = employeeReflector.getDeclaredConstructor(int.class, String.class, String.class, String.class);
        Object object = constructor.newInstance(3121, "9013", "Midorifuji", "Marketing");
        
        // Field employeeId = employeeReflector.getDeclaredField("employeeId");
        // employeeId.setAccessible(true);
        // employeeId.set(object, 9123);
        Method setId = employeeReflector.getMethod("setEmployeeId", int.class);
        setId.invoke(object, 9123);

        // Field employeeNumber = employeeReflector.getDeclaredField("employeeNumber");
        // employeeNumber.setAccessible(true);
        // employeeNumber.set(object, "4131");
        Method setNumber = employeeReflector.getMethod("setEmployeeNumber", String.class);
        setNumber.invoke(object, "4131");

        // Field name = employeeReflector.getDeclaredField("name");
        // name.setAccessible(true);
        // name.set(object, "Onosato");
        Method setName = employeeReflector.getMethod("setName", String.class);
        setName.invoke(object, "Onosato");

        // Field department = employeeReflector.getDeclaredField("department");
        // department.setAccessible(true);
        // department.set(object, "Technical");
        Method setDepartment = employeeReflector.getMethod("setDepartment", String.class);
        setDepartment.invoke(object, "Technical");
        
        System.out.println("Using 4 arg constructor ...");
        Class<?> utilsReflector = Class.forName("main.java.com.habil.models.Utils");
        Method utilsMethod = utilsReflector.getDeclaredMethod("displayEmployeeDetails", employeeReflector);
        utilsMethod.setAccessible(true);
        utilsMethod.invoke(null, object);

        System.out.println();
        System.out.println("Using 2arg constructor ...");

        Constructor<?> constructor2 = employeeReflector.getDeclaredConstructor(int.class, String.class);
        Object object2 = constructor2.newInstance(5333, "3131");
        utilsMethod.invoke(null, object2);

    }

    // javac -d bin/ -cp bin/ <... .java files/filepaths> 
    // javac -d bin/ -cp bin/ src/main/java/com/habil/models/* src/main/java/com/habil/app/ReflectionApp.java 

    // jar cfm ReflectionApp.jar manifest.txt -C bin/ . 
}
