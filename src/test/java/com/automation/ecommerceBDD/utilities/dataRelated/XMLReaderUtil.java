package com.automation.ecommerceBDD.utilities.dataRelated;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

public class XMLReaderUtil {

    public Document getData(String filePath) {
        File inputFile = new File(System.getProperty("user.dir") + filePath);
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(inputFile);
        } catch (DocumentException e) {
            e.printStackTrace();

        }
        return document;
    }
}
