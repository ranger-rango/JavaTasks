package main.java.com.habil.models;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class XmlParseNoXpaths
{
    public static Stream<Element> streamElements(NodeList nodeList)
    {
        return IntStream.range(0, nodeList.getLength())
        .mapToObj(nodeList::item)
        .filter(node -> node instanceof Element)
        .map(node -> (Element) node);
    }

    public static void filterApiBased(Stream<Element> elements)
    {
        elements.filter(e -> "API_BASED".equals(e.getAttribute("field_type")))
        .map(e -> e.getAttribute("tag_name"))
        .forEach(System.out::println);
    }

    public static long countTableBasedFileds(Stream<Element> elements)
    {
        long count = elements
        .filter(e -> "TABLE_BASED".equals(e.getAttribute("field_type")))
        .count();

        return count;
    }

    public static void checkDuplicate(Stream<Element> elements)
    {
        elements.filter(e -> "true".equals(e.getAttribute("check_duplicate")))
        .forEach(e -> System.out.println("Tag: " + e.getTagName() + " Field: " + e.getAttribute("tag_name")));
    }

    public static void removeRestrictedNationalitiesTags(Document docs)
    {
        List<String> toRemove = List.of(
            "RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE",
            "MAX_RESTRICTED_ACCESS_NATIONALITIES",
            "RESTRICTED_ACCESS_NATIONALITIES"
        );

        toRemove.forEach(tag -> 
        {
            NodeList nodes = docs.getElementsByTagName(tag);
            streamElements(nodes).forEach(node -> 
            {
                Node parent = node.getParentNode();
                if (parent != null) parent.removeChild(node);
            });
        });
    }

    public static void updateMandatoryToOptional (Stream<Element> elements)
    {
        elements.filter(e -> "MANDATORY".equals(e.getAttribute("use")))
        .forEach(e -> e.setAttribute("use", "OPTIONAL"));
    }
    
}
