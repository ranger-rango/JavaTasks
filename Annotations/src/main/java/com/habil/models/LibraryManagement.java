package main.java.com.habil.models;

public class LibraryManagement
{
    @EntityInfo(label = "libraries", entityType = EntityType.TABLE, dataType = DataType.UNDEFINED)
    public class Libraries
    {
        @EntityInfo(label = "library_id", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
        long libraryId;
        @EntityInfo(label = "librarey_name", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String librareyName;
        @EntityInfo(label = "library_email", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String libraryEmail;
        @EntityInfo(label = "created_at", entityType = EntityType.COLUMN, dataType = DataType.UNDEFINED)
        String createdAt;
        @EntityInfo(label = "updated_at", entityType = EntityType.COLUMN, dataType = DataType.UNDEFINED)
        String updatedAt;
    }

    @EntityInfo(label = "authors", entityType = EntityType.TABLE, dataType = DataType.UNDEFINED)
    public class Authors
    {
        @EntityInfo(label = "author_id", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
        long authorId;
        @EntityInfo(label = "surname", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String surname;
        @EntityInfo(label = "first_name", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String firstName;
        @EntityInfo(label = "middle_name", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String middleName;
        @EntityInfo(label = "about_author", entityType = EntityType.COLUMN, dataType = DataType.TEXT)
        String aboutAuthor;
        @EntityInfo(label = "created_at", entityType = EntityType.COLUMN, dataType = DataType.UNDEFINED)
        String createdAt;
        @EntityInfo(label = "updated_at", entityType = EntityType.COLUMN, dataType = DataType.UNDEFINED)
        String updatedAt;
    }

    @EntityInfo(label = "librarians", entityType = EntityType.TABLE, dataType = DataType.UNDEFINED)
    public class Librarians
    {
        @EntityInfo(label = "librarian_id", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
        long librarianId;
        @EntityInfo(label = "library_id", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
        long libraryId;
        @EntityInfo(label = "surname", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String surname;
        @EntityInfo(label = "first_name", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String firstName;
        @EntityInfo(label = "middle_name", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String middleName;
        @EntityInfo(label = "librarian_email", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String librarianEmail;
        @EntityInfo(label = "password_hash", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
        String passwordHash;
        @EntityInfo(label = "created_at", entityType = EntityType.COLUMN, dataType = DataType.UNDEFINED)
        String createdAt;
        @EntityInfo(label = "updated_at", entityType = EntityType.COLUMN, dataType = DataType.UNDEFINED)
        String updatedAt;
    }
}
