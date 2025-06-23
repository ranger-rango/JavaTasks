package main.java.com.habil.app;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
// import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
// import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import main.java.com.habil.models.XmlParseNoXpaths;
import main.java.com.habil.models.XmlParseXpaths;

public class XmlXpathsParser
{
    public static void main(String[] args) throws IOException, Exception
    {
        String xml = new String(Files.readAllBytes(Paths.get("XmlXpaths/signatories_model_info_copy.xml")));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputSource is = new InputSource(new StringReader(xml)); // create and input source
        Document doc = builder.parse(is);
        doc.getDocumentElement().normalize();

        // Not using Xpath 
        NodeList allElements = doc.getElementsByTagName("*");
        // Stream<Element> elements = XmlParseNoXpaths.streamElements(allElements);

        XmlParseNoXpaths.filterApiBased(XmlParseNoXpaths.streamElements(allElements));

        long tableBasedFiledsCount = XmlParseNoXpaths.countTableBasedFileds(XmlParseNoXpaths.streamElements(allElements));
        System.out.println(tableBasedFiledsCount);

        XmlParseNoXpaths.checkDuplicate(XmlParseNoXpaths.streamElements(allElements));

        XmlParseNoXpaths.removeRestrictedNationalitiesTags(doc);

        XmlParseNoXpaths.updateMandatoryToOptional(XmlParseNoXpaths.streamElements(allElements));

        // using Xpaths 
        System.out.println("With Xpaths");
        DocumentBuilderFactory fac  = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = fac.newDocumentBuilder();
        Document docs = build.parse(new File("XmlXpaths/signatories_model_info_copy.xml"));

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        XmlParseXpaths.filterApiBased(xPath, docs);

        long countXpaths = XmlParseXpaths.countTableBasedFileds(xPath, docs);
        System.out.println(countXpaths);

        XmlParseXpaths.checkDuplicate(xPath, docs);

        XmlParseXpaths.removeRestrictedNationalitiesTags(xPath, docs);

        XmlParseXpaths.updateMandatoryToOptional(xPath, docs);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(docs), new StreamResult(new File("updated.xml")));


    }
}
