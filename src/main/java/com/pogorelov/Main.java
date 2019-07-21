package com.pogorelov;

import org.jsoup.nodes.Element;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String TARGET_ELEMENT_ID = args[0];
        File originCase = new File(args[1]);
        File testCase = new File(args[2]);

        Element targetElement = JsoupFindById.findElementById(originCase, TARGET_ELEMENT_ID).get();
        System.out.println(Finder.findElement(testCase, targetElement));
    }
}
