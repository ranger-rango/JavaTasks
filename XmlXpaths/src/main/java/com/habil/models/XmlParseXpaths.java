package main.java.com.habil.models;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParseXpaths
{
    public static Stream<Element> stream(NodeList nodeList)
    {
        return IntStream.range(0, nodeList.getLength())
        .mapToObj(nodeList::item)
        .filter(node -> node instanceof Element)
        .map(node -> (Element) node);

    }

    public static void filterApiBased(XPath xPath, Document docs) throws Exception
    {
        NodeList nodeList = (NodeList) xPath.evaluate("//*[@field_type='API_BASED']", docs, XPathConstants.NODESET);
        stream(nodeList)
        .map(el -> el.getAttribute("tag_name"))
        .forEach(System.out::println);
    }

    public static long countTableBasedFileds(XPath xPath, Document doc) throws Exception
    {
        NodeList nodeList = (NodeList) xPath.evaluate("//*[@field_type='TABLE_BASED']", doc, XPathConstants.NODESET);

        long count = stream(nodeList).count();
        return count;
    }

    public static void checkDuplicate(XPath xPath, Document docs) throws Exception
    {
        NodeList nodeList = (NodeList) xPath.evaluate("//item[@check_duplicate]", docs, XPathConstants.NODESET);

        stream(nodeList).forEach(item -> 
        {
            Element checkDup = (Element) item.getElementsByTagName("check_duplicate").item(0);
            String tagName = item.getAttribute("tag_name");
            String associateField = ((Element) checkDup.getElementsByTagName("associated_field").item(0))
            .getAttribute("value");

            System.out.println("Tag: " + tagName + ", Associate Field: " + associateField);
        });
    }

    public static void removeRestrictedNationalitiesTags(XPath xPath, Document docs) throws Exception
    {
        List<String> tagsToRemove = List.of(
            "RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE",
            "MAX_RESTRICTED_ACCESS_NATIONALITIES",
            "RESTRICTED_ACCESS_NATIONALITIES"
        );

        tagsToRemove.forEach(tag -> 
        {
            try
            {
                NodeList nodeList = (NodeList) xPath.evaluate("//item[@tag_name='" + tag + "']", docs, XPathConstants.NODESET);
                stream(nodeList).forEach(node -> 
                {
                    Node parent = node.getParentNode();
                    if (parent != null)
                    {
                        System.out.println("Removed node: " + node.getNodeName());
                        parent.removeChild(node);
                    }
                });
            }
            catch (XPathExpressionException e)
            {
                e.printStackTrace();
            }
        });
    }

    public static void updateMandatoryToOptional(XPath xPath, Document docs) throws Exception
    {
        NodeList nodeList = (NodeList) xPath.evaluate("//*[@use='MANDATORY']", docs, XPathConstants.NODESET);

        stream(nodeList)
        .forEach(el -> el.setAttribute("use", "OPTIONAL"));
    }
}
