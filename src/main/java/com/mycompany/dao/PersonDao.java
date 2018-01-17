/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dbconnection.*;
import com.mycompany.mainpackage.MainClass;
import com.mycompany.parse.*;
import java.io.*;
import java.sql.*;
import org.apache.log4j.Logger;

/**
 *
 * @author joseph.habib
 */
public class PersonDao implements DBManipulationDao {

    public Logger logger = Logger.getLogger(PersonDao.class);
    DBConnectionManager db = new DBConnectionManager();

    @Override
    public void getPersons() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        String sql = "Select * from PERSON ";

        try {
            Statement stm = db.connection().createStatement();
            ResultSet rs = stm.executeQuery(sql);
        } finally {
            db.connection().close();
        }
    }

    @Override
    public boolean addPersons() throws IOException, SQLException, ClassNotFoundException, FileNotFoundException {

        String queryFather = "INSERT INTO PERSON(P_FIRSTNAME,P_LASTNAME,P_ADDRESS,P_AGE) VALUES (?,?,?,?)";
        try {
            PreparedStatement psFather = db.connection().prepareStatement(queryFather); // close connection

            int countFatherRow = psFather.getUpdateCount();// to get count of rows affected in PERSON table

            String attributes[] = null;
            String fileName = ParsingFactory.getFileName();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

            if (fileType.equals("xml")) {
                attributes = XML.parsePerson();
            } else if (fileType.equals("csv")) {
                attributes = CSV.parsePerson();
            }
            for (String res : attributes) {
                int pAge = Integer.parseInt(attributes[3]);
                psFather.setString(1, attributes[0]);
                psFather.setString(2, attributes[1]);
                psFather.setString(3, attributes[2]);
                psFather.setInt(4, pAge);
            }
            psFather.addBatch();
            psFather.executeBatch();
            countFatherRow++;
            logger.info("Number of rows added in PERSON table: " + Integer.toString(countFatherRow));
        } finally {
            if (db.connection() != null) {
                db.connection().close();
            }
        }
        return true;
    }
}
