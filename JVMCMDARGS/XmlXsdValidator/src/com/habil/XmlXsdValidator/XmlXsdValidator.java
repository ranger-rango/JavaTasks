package com.habil.XmlXsdValidator;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XmlXsdValidator {
    public static boolean xXValidator(String xmlPath, String xsdPath)
    {
        try
        {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            return true;
        }
        catch (SAXException e)
        {
            System.err.println("XML Validation error: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }

        return false;
    }
}
