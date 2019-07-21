package com.pogorelov;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

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
}
