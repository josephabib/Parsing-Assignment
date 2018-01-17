/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parse;

import com.mycompany.mainpackage.Person;
import com.mycompany.mainpackage.*;
import com.mycompany.dao.*;
import com.mycompany.archive.FileArchiver;
import java.io.*;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author joseph.habib
 */
public class CSV implements Parser {

    static String attributes[] = new String[6];
    Logger logger = Logger.getLogger(CSV.class);

    String fileName;

    public CSV(String fileName) throws IOException, SQLException, ClassNotFoundException, FileNotFoundException, ParserConfigurationException, SAXException {
        this.fileName = fileName;
        parse(fileName);
    }

    @Override
    public final String[] parse(String fileName) throws SAXException, ParserConfigurationException, IOException, SQLException, ClassNotFoundException, FileNotFoundException {
        boolean executedCompletely = false;
        List<Person> persons = new ArrayList<>();
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);

        String line = "";
        while ((line = br.readLine()) != null) {
            attributes = line.split(",");
            String[] personArr = Arrays.copyOfRange(attributes, 0, 4);
            String[] childArr = Arrays.copyOfRange(attributes, 4, 6);

            if (attributes.length < 5) {
                if (containsDigit(personArr[0]) == true) {
                    logger.warn("First name contains integer");
                    continue;
                } else if (containsDigit(personArr[1]) == true) {
                    logger.warn("Last name contains integer");
                    continue;
                } else if (containsDigit(personArr[3]) == false) {
                    logger.warn("Age contains string");
                    continue;
                }
                childArr[0] = null;
                childArr[1] = null;
//                parsePerson();
                PersonDao pd = new PersonDao();
                pd.addPersons();

//                parseChild();
                continue;
            } else {
                // Performs check on array elements.
                if (containsDigit(personArr[0]) == true) {
                    logger.warn("First name contains integer");
                    continue;
                } else if (containsDigit(personArr[1]) == true) {
                    logger.warn("Last name contains integer");
                    continue;
                } else if (containsDigit(personArr[3]) == false) {
                    logger.warn("Age contains string");
                    continue;
                } else if (containsDigit(childArr[0]) == true) {
                    logger.warn("Child's name contains integer");
                    continue;
                } else if (containsDigit(childArr[1]) == false) {
                    logger.warn("Child's age contains integer");
                    continue;
                }

                PersonDao person = new PersonDao();
                person.addPersons();

                ChildDao child = new ChildDao();
                child.addPersons();
            }
        }

        br.close();

        executedCompletely = true;
        if (executedCompletely) {
            FileArchiver archiver = new FileArchiver();
            archiver.archive(fileName);
        }

        return null;
    }

    public static String[] parsePerson() {
        String[] personArr = Arrays.copyOfRange(attributes, 0, 4);
        String pAge = personArr[3];
        int age = Integer.parseInt(pAge);
        Person p = new Person(personArr[0], personArr[1], personArr[2], age);
        return personArr;
    }

    public static String[] parseChild() {
        String[] childArr = Arrays.copyOfRange(attributes, 4, 6);
        String cAge = childArr[1];
        int age = Integer.parseInt(cAge);
        Child c = new Child(childArr[0], age);
        return childArr;
    }

    public static boolean containsDigit(String s) {
        boolean containsDigit = false;
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }
        return containsDigit;
    }
}
