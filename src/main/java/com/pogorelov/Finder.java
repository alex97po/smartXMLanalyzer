package com.pogorelov;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * This class is responsible for finding logic
 * It finds element and analyze similarity
 */
public class Finder {
    /**
     * Find element on HTML document and return path to it
     *
     * @param htmlFile
     * @param target
     * @return String
     */
    public static String findElement (File htmlFile, Element target) {
        Elements elements = JsoupFindById.getElementsFromHtmlFile(htmlFile).get();
        Element requiredElement = findTheMostSimilarElement(elements, target);
        return getPathToElement(requiredElement);
    }

    /**
     * Find element that has the biggest amount of similar elements
     *
     * @param elements
     * @param target
     * @return Element
     */
    private static Element findTheMostSimilarElement (Elements elements, Element target) {
        int maxAmountOfSimilarAttributes = 0;
        Element mostSimilarElement = elements.first();
        for (Element e : elements) {
            int similarities = countCoincidences(e, target);
            if (similarities > maxAmountOfSimilarAttributes) {
                mostSimilarElement = e;
                maxAmountOfSimilarAttributes = similarities;
            }
        }
        return mostSimilarElement;
    }

    /**
     * Auxiliary method to calculate amount of similarities of focused element and required element
     *
     * @param element
     * @param target
     * @return int
     */
    private static int countCoincidences (Element element, Element target) {
        Attributes elementAttributes = element.attributes();
        Attributes targetAttributes = target.attributes();

        int counter = 0;
        for (Attribute el : elementAttributes) {
            for (Attribute tar : targetAttributes) {
                if (el.equals(tar)){
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * Gather information about element parents and return readable result string
     *
     * @param element
     * @return String
     */

    private static String getPathToElement(Element element) {
        Elements parents = element.parents();
        Collections.reverse(parents);
        return parents.stream()
                .map(el -> el.tagName() + "[" + el.elementSiblingIndex() + "]" + ">")
                .collect(Collectors.joining(" ")) + element.tagName() + element.elementSiblingIndex();
    }
}
