/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbconnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author joseph.habib
 */
public interface DBManipulationDao {

    public void getPersons() throws IOException, ClassNotFoundException, SQLException, FileNotFoundException;

    public abstract boolean addPersons() throws IOException, SQLException, ClassNotFoundException, FileNotFoundException;
}
