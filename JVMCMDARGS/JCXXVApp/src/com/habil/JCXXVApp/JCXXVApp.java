package com.habil.JCXXVApp;

import com.habil.CMD.CMDArgs;
import com.habil.JVM.JVMOptions;
import com.habil.XmlXsdValidator.XmlXsdValidator;

public class JCXXVApp {
    public static void main(String[] args)
    {
        JVMOptions.printTimeZone(args);

        String xmlPath = CMDArgs.argParser(args, "-xml");
        String xsdPath = CMDArgs.argParser(args, "-xsd");

        if (xmlPath == null || xsdPath == null)
        {
            System.err.println("Missing -xml or -xsd argument.");
            System.exit(1);
        }

        boolean isXmlValid = XmlXsdValidator.xXValidator(xmlPath, xsdPath);
        if (isXmlValid)
        {
            System.out.println("The Xml is valid.");
        }


    }
}
