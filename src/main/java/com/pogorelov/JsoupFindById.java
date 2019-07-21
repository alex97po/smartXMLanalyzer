package com.pogorelov;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * This class is responsible for HTML parsing
 * Can be considered as an auxiliary class for further finding logic
 */
public class JsoupFindById {

    /**
     * Parse HTML document and find required element by ID
     *
     * @param htmlFile
     * @param targetElementId
     * @return Element
     */
    public static Optional<Element> findElementById (File htmlFile, String targetElementId) {
        try {
            Document document = Jsoup.parse(
                    htmlFile,
                    Util.CHARSET,
                    htmlFile.getAbsolutePath());
            return Optional.of(document.getElementById(targetElementId));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    /**
     * Parse HTML document and return all elements it consists of
     * @param htmlFile
     * @return Elements
     */
    public static Optional <Elements> getElementsFromHtmlFile (File htmlFile) {
        try {
            Document document = Jsoup.parse(
                    htmlFile,
                    Util.CHARSET,
                    htmlFile.getAbsolutePath());
            return Optional.of(document.body().getAllElements());
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
