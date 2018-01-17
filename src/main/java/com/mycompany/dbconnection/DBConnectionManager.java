/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbconnection;

import java.io.*;
import java.sql.*;

/**
 *
 * @author joseph.habib
 */
public class DBConnectionManager {

    public static Connection connection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        String url = "jdbc:oracle:thin:@10.0.20.35:1521:orcl";
        String username = "JOE";
        String password = "JOE";
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }

}
