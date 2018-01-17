/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.archive;

import java.io.*;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author joseph.habib
 */
public class FileArchiver {
    
    static final Logger logger = Logger.getLogger(FileArchiver.class.getName());
    
    public void archive(String fileName) {
        File source = new File(fileName);
        File destination = new File("D:/Checked/");
        try {
            FileUtils.copyFileToDirectory(source, destination);
            source.delete();
            logger.info("File moved from source destination to D:/checked");
        } catch (IOException e) {
            logger.warning("Directory not found");
        }
    }
}
