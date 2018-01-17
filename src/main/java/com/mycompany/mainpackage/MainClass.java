/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mainpackage;

import com.mycompany.parse.ParsingFactory;
import java.io.*;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author joseph.habib
 */
public class MainClass {

    final static Logger logger = Logger.getLogger(com.mycompany.mainpackage.MainClass.class);
    //static String fileName = "D:/person.xml";
    static File path = new File("D:/Checker");

    public static void main(String[] args) throws SAXException, ParserConfigurationException, SQLException, IOException, FileNotFoundException, ClassNotFoundException {
        long startTime = System.currentTimeMillis();
        ParsingFactory parsingFactory = new ParsingFactory();
        parsingFactory.directoryIterator(path);
        long totalTime = System.currentTimeMillis() - startTime;
        logger.info("Success parsing file in " + totalTime + " milliseconds");
    }
}
