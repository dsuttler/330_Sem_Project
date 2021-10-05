package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LinkTest {
    /**
     *  Objects used in the tests 
     */
    Link defaultLink;
    Link nonDefaultLink;

    @BeforeEach
    void setup() {
    	String testName= "testNameLink";
    	String testClassification= "testClassificationLink";
    	
        defaultLink = new Link(); 
        nonDefaultLink = new Link(testName, testClassification); 
        
    }

    @Test
    @DisplayName("<Link> Default Constructor")
    void LinkDefaultConstructor() {

        /**
         *  Make sure the name is empty for the default constructor
         */
        assertEquals("", defaultLink.getName(), 
        "Default constructor should be empty. Returned: " + defaultLink.getName());
        
        /**
         *  Make sure the classification is empty for the default constructor
         */
        assertEquals("", defaultLink.getClassification(), 
        "Default constructor should be empty. Returned: " + defaultLink.getClassification());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<Link> Parameterized Constructor")
    void LinkParameterizedConstructor() {

        /**
         *  Make sure the name is testNameLink for the parameterized constructor
         */
        assertEquals("testNameLink", nonDefaultLink.getName(), 
        "Parameterized constructor should be testNameLink. Returned: " + nonDefaultLink.getName());
        
        /**
         *  Make sure the classification is testClassificationLink for the parameterized constructor
         */
        assertEquals("testClassificationLink", nonDefaultLink.getClassification(), 
        "Parameterized constructor should be testClassificationLink. Returned: " + nonDefaultLink.getClassification());
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<Link> SetName")
    void LinkSetName() {
    	defaultLink.setName(nonDefaultLink.getName());
    	assertEquals(defaultLink.getName(), nonDefaultLink.getName(), 
        "default constructor name should be testNameLink. Returned: " + defaultLink.getName());
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<Link> SetClassification")
    void LinkSetClassification() {
    	defaultLink.setClassification(nonDefaultLink.getClassification());
    	assertEquals(defaultLink.getClassification(), nonDefaultLink.getClassification(), 
        "default constructor classification should be " + nonDefaultLink.getClassification() + " Returned: " + defaultLink.getClassification());
    }

}