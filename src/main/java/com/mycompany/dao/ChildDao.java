/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dbconnection.*;
import com.mycompany.parse.*;
import java.io.*;
import java.sql.*;
import org.apache.log4j.Logger;
import com.mycompany.parse.CSV;
import com.mycompany.mainpackage.*;

/**
 *
 * @author joseph.habib
 */
public class ChildDao implements DBManipulationDao {

    public Logger logger = Logger.getLogger(ChildDao.class);
    DBConnectionManager db = new DBConnectionManager();
    boolean executedCompletely = false;

    @Override
    public void getPersons() throws IOException, ClassNotFoundException, SQLException, FileNotFoundException {
        String sql = "Select * from CHILD";

        try {
            Statement stm = db.connection().createStatement();
            ResultSet rs = stm.executeQuery(sql);
        } finally {
            db.connection().close();
        }
    }

    @Override
    public boolean addPersons() throws IOException, SQLException, ClassNotFoundException, FileNotFoundException {

        String queryChild = "INSERT INTO CHILD(C_NAME,C_AGE) VALUES(?,?)";
        try {
            PreparedStatement psChild = db.connection().prepareCall(queryChild);

            int countChildRow = psChild.getUpdateCount();
            String attributes[] = null;
            String fileName = ParsingFactory.getFileName();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (fileType.equals("xml")) {
                attributes = XML.parseChild();
            } else if (fileType.equals("csv")) {
                attributes = CSV.parseChild();
            }

            for (String res : attributes) {

                int cAge;
                if (attributes[1] == null) {
                    cAge = 0;
                } else {
                    cAge = Integer.parseInt(attributes[1]);
                }

                logger.info(res);

                //Moving data to table CHILD in database
                psChild.setString(1, attributes[0]);
                psChild.setInt(2, cAge);
            }
            psChild.addBatch();
            psChild.executeBatch();
            countChildRow++;

            logger.info("Number of rows added in CHILD table: " + Integer.toString(countChildRow));

        } finally {
            if (db.connection() != null) {
                db.connection().close();
            }
        }
        return true;
    }
}
