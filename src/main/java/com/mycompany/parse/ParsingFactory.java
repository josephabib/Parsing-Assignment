/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parse;

import com.mycompany.dao.ChildDao;
import com.mycompany.archive.FileArchiver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author joseph.habib
 */
public class ParsingFactory {

    public Logger logger = Logger.getLogger(ParsingFactory.class);
    ChildDao cd = new ChildDao();
    FileArchiver fa = new FileArchiver();
    static String fileName;

    public Parser directoryIterator(File file) throws IOException, SQLException, ClassNotFoundException, FileNotFoundException, ParserConfigurationException, SAXException {
        File path = new File(file.toURI());
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                fileName = files[i].getPath();
                parserIdentifier(fileName);
            }
        }
        return null;
    }

    public Parser parserIdentifier(String fileName) throws IOException, SQLException, ClassNotFoundException, FileNotFoundException, ParserConfigurationException, SAXException {
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        logger.info("File type: " + fileType);
        if (fileType.equals("csv")) {
            return new CSV(fileName);
        } else if (fileType.equals("xml")) {
            return new XML(fileName);
        } else {
            logger.fatal("Error parsing " + fileName);
            logger.fatal(fileType + " is unsupported");
            return null;
        }
    }

    public static String getFileName() {
        return fileName;
    }
}
