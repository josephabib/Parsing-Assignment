/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parse;

import com.mycompany.archive.FileArchiver;
import com.mycompany.dao.ChildDao;
import com.mycompany.dao.PersonDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author joseph.habib
 */
public class XML implements Parser {

    static String[] persons = new String[4];
    static String[] children = new String[2];
    String fileName;

    public XML(String fileName) throws ClassNotFoundException, SAXException, ParserConfigurationException, SQLException, IOException {
        this.fileName = fileName;
        parse(fileName);
    }
    boolean excutedCompletely = false;

    @Override
    public final String[] parse(String fileName) throws ParserConfigurationException, IOException, SQLException, ClassNotFoundException, FileNotFoundException, SAXException {
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document document = (Document) dBuilder.parse(fileName);

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        String childName = null;
        String c_Age = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == node.ELEMENT_NODE) {
                Element elem = (Element) node;
                persons[0] = elem.getElementsByTagName("Firstname").item(0).getChildNodes().item(0).getNodeValue();
                persons[1] = elem.getElementsByTagName("Lastname").item(0).getChildNodes().item(0).getNodeValue();
                persons[2] = elem.getElementsByTagName("Address").item(0).getChildNodes().item(0).getNodeValue();
                persons[3] = elem.getElementsByTagName("Age").item(0).getChildNodes().item(0).getNodeValue();

                int elements_Length = elem.getElementsByTagName("Children").item(0).getChildNodes().getLength();
                for (int j = 0; j < elements_Length; j++) {
                    if (node.getNodeType() == node.ELEMENT_NODE) {
                        Element el = (Element) node;
                        children[0] = el.getElementsByTagName("Childname").item(0).getChildNodes().item(0).getNodeValue();
                        children[1] = el.getElementsByTagName("Childage").item(0).getChildNodes().item(0).getNodeValue();
                    }
                }
                PersonDao pd = new PersonDao();
                pd.addPersons();
                ChildDao cd = new ChildDao();
                cd.addPersons();
            }
        }
        excutedCompletely = true;
        if (excutedCompletely) {
            FileArchiver archiver = new FileArchiver();
            archiver.archive(fileName);
        }

        return null;
    }

    public static String[] parsePerson() {
        String pAge = persons[3];
        int age = Integer.parseInt(pAge);
        return persons;
    }

    public static String[] parseChild() {
        String cAge = children[1];
        int age = Integer.parseInt(cAge);
        return children;
    }
}
