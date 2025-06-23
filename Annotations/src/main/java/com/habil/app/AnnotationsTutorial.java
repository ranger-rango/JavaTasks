package main.java.com.habil.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Table
{
    String name();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column
{
    String name();
}

@Table(name = "users")
class UserModel
{
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String userName;
    
}


public class AnnotationsTutorial //SQLGenerator
{
    static String mapJavaTypeToSql (Class<?> type)
    {
        if (type == int.class) return "INTEGER";
        if (type == String.class) return "VARCHAR(255)";
        return "TEXT";
    }
    public static void main(String[] args)
    {
        Class<?> class1 = UserModel.class;

        if (class1.isAnnotationPresent(Table.class))
        {
            Table table = class1.getAnnotation(Table.class);
            StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");
            stringBuilder.append(table.name()).append(" (\n");

            Field[] fields = class1.getDeclaredFields();

            for (Field field : fields)
            {
                if (field.isAnnotationPresent(Column.class))
                {
                    Column column = field.getAnnotation(Column.class);
                    stringBuilder.append("  ").append(column.name()).append(" ")
                    .append(mapJavaTypeToSql(field.getType())).append(",\n");
                }
            }
            
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
            stringBuilder.append(");");

            System.out.println(stringBuilder);
        }
    }   
}

// annotations provide metadata about code, that is used by the compiler, frameworkd, 
// and app-logic, reflections ... : @Override, @Deprecated, @SuppressWarnings("unchecked"), ...

// Retention policies 
// @Retention(RetentionPolicy.SOURCE) - discarded by compiler 
// @Retention(RetentionPolicy.CLASS) - store in .class file, not in runtime 
// @Retention(RetentionPolicy.RUNTIME) - available at runtime, and useful if reflections are used 

// Targets 
// @Target(ElementType.FIELD) - //only fields
// @Target(ElementType.METHOD) - only mtds 
// @Target(ElementType.TYPE) - classes, interfaces, enums 
// @Target({ElementType.TYPE, ElementType.FIELD}) - multiple 

