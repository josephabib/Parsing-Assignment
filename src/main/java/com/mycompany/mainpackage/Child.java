/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mainpackage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseph.habib
 */
public class Child extends Person {

    List<Object> child = new ArrayList<>();

    public Child(String childName, int c_Age) {
        super(childName, c_Age);
        child.add(childName);
        child.add(c_Age);
    }

    public static Child createChild(String[] metadata) {
        String childName = metadata[0];
        int c_Age = Integer.parseInt(metadata[1]);
        return new Child(childName, c_Age);
    }
    public String getChildName()
    {
        return childName;
    }
    
    public int getChildAge()
    {
        return c_Age;
    }
}
