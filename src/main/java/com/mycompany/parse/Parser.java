/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parse;

import java.io.*;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author joseph.habib
 */
public interface Parser {

    String[] parse(String fileName) throws SAXException, ParserConfigurationException, IOException, SQLException, ClassNotFoundException, FileNotFoundException;
}
