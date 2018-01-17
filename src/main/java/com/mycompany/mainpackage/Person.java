/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mainpackage;

import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author joseph.habib
 */
public class Person {

    private String firstName;
    private String lastName;
    private String address;
    private int age;
    String childName;
    int c_Age;
    //private List<Person> Children;

    final static Logger logger = Logger.getLogger(MainClass.class);

    public Person(String firstName, String lastName, String address, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        if (age < 0 || age > 100) {
            logger.warn("Age is of miniumum 1 and maximum of 99");
            throw new IllegalArgumentException("Age is of miniumum 1 and maximum of 99");
        } else {
            this.age = age;
        }
    }

    //for Child
    public Person(String childName, int c_Age) {
        this.childName = childName;
        if (c_Age < 0 || c_Age > 50) {
            logger.warn("Age is of miniumum 1 and maximum of 99");
            throw new IllegalArgumentException("Age is of miniumum 1 and maximum of 99");

        } else {
            this.age = age;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getParentAge() {
        return age;
    }

    public void setFirstName() {
        this.firstName = firstName;
    }

    public void setLastName() {
        this.lastName = lastName;
    }

    public void setAddress() {
        this.address = address;
    }

    public void setParentAge() {
        this.age = age;
    }

    public static Person createPerson(String[] metadata) {
        String firstName = metadata[0];
        String lastName = metadata[1];
        String address = metadata[2];
        int age = Integer.parseInt(metadata[3]);
        return new Person(firstName, lastName, address, age);
    }

    @Override
    public String toString() {
        return "First name: " + firstName + ", Last name: " + lastName
                + " ,address: " + address + ", age: " + age;
    }

}
