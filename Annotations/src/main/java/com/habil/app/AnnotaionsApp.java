package main.java.com.habil.app;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import main.java.com.habil.models.Description;
import main.java.com.habil.models.Employeee;
import main.java.com.habil.models.EntityInfo;
import main.java.com.habil.models.ImportantMethod;
import main.java.com.habil.models.PaymentProcessor;
import main.java.com.habil.models.LibraryManagement.Authors;
import main.java.com.habil.models.LibraryManagement.Librarians;
import main.java.com.habil.models.LibraryManagement.Libraries;

public class AnnotaionsApp
{
    public static void main(String[] args) throws Exception
    {
        Class<?> classEmployee = Employeee.class;

        Field[] employeeFields = classEmployee.getDeclaredFields();
        System.out.println("Employee Fields with Description annotation: ... ");
        System.out.printf("%-20s %-50s%n", "Field Name", "Field Description");
        for (Field field: employeeFields)
        {
            if (field.isAnnotationPresent(Description.class))
            {
                Description description = field.getAnnotation(Description.class);

                System.out.printf("%-20s %-50s%n", field.getName(), description.info());
            }
        }
        System.out.println();

        Class<?> classPaymentProocessor = PaymentProcessor.class;
        Method[] paymentProcessorMethods = classPaymentProocessor.getMethods();
        System.out.println("Executing Critical PaymentProcessor Methods: ...");

        for (Method method: paymentProcessorMethods)
        {
            if (method.isAnnotationPresent(ImportantMethod.class))
            {
                System.out.println("Executing: " + method.getName() + "()");
                method.invoke(null);
                System.out.println();
            }
        }
        System.out.println();

        Class<?> classLibraries = Libraries.class;
        if (classLibraries.isAnnotationPresent(EntityInfo.class))
        {
            EntityInfo librariesEntityInfo = classLibraries.getAnnotation(EntityInfo.class);
            System.out.println(librariesEntityInfo.entityType() + " " + librariesEntityInfo.label());
        }
        Field[] librariesFields = classLibraries.getDeclaredFields();
        System.out.printf("%-20s %-20s %-20s%n", "label", "Entity Type", "Data Type");
        for (Field field: librariesFields)
        {
            if (field.isAnnotationPresent(EntityInfo.class))
            {
                EntityInfo entityInfo = field.getAnnotation(EntityInfo.class);
                System.out.printf("%-20s %-20s %-20s%n", entityInfo.label(), entityInfo.entityType(), entityInfo.dataType());
            }
        }
        System.out.println();

        Class<?> classAuthors = Authors.class;
        if (classAuthors.isAnnotationPresent(EntityInfo.class))
        {
            EntityInfo authorsEntityInfo = classAuthors.getAnnotation(EntityInfo.class);
            System.out.println(authorsEntityInfo.entityType() + " " + authorsEntityInfo.label());
        }
        Field[] authorsFields = classAuthors.getDeclaredFields();
        System.out.printf("%-20s %-20s %-20s%n", "label", "Entity Type", "Data Type");
        for (Field field: authorsFields)
        {
            if (field.isAnnotationPresent(EntityInfo.class))
            {
                EntityInfo entityInfo = field.getAnnotation(EntityInfo.class);
                System.out.printf("%-20s %-20s %-20s%n", entityInfo.label(), entityInfo.entityType(), entityInfo.dataType());
            }
        }
        System.out.println();

        Class<?> classLibrarians = Librarians.class;
        if (classLibrarians.isAnnotationPresent(EntityInfo.class))
        {
            EntityInfo librariansEntityInfo = classLibrarians.getAnnotation(EntityInfo.class);
            System.out.println(librariansEntityInfo.entityType() + " " + librariansEntityInfo.label());
        }
        Field[] librariansFields = classLibrarians.getDeclaredFields();
        System.out.printf("%-20s %-20s  %-20s%n", "label", "Entity Type", "Data Type");
        for (Field field : librariansFields)
        {
            if (field.isAnnotationPresent(EntityInfo.class))
            {
                EntityInfo entityInfo = field.getAnnotation(EntityInfo.class);
                System.out.printf("%-20s %-20s %-20s%n", entityInfo.label(), entityInfo.entityType(), entityInfo.dataType());
            }
        }

    }
}
