package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

class JavascriptTests {
    @Test
    @DisplayName("JUnit Integration Verification")
    void TestJUnitIntegration() {
        assertEquals(1, 1, "Test Failed");
    }

    @Test
    @DisplayName("<Javascript> Default Constructor")
    void JavascriptDefaultConstructor() {
        Javascript testJavascript = new Javascript(); 

        /**
         *  Make sure the name is empty for the default constructor
         */
        assertEquals("", testJavascript.getName());
        
        /**
         *  Make sure classification is an empty string for the default constructor
         */
        assertEquals("", testJavascript.getClassification());

        /**
         *  Make sure the default constructor contains an empty list
         */
        assertEquals(0, testJavascript.getPages().size());
    }
    
    @Test
    public void testJavascriptNonDefaultConstructor()
    {
    	ArrayList<String> testList = new ArrayList<>();
    	
    	Javascript testJavascript = new Javascript("./script.js", "external", testList);
    	
    	assertEquals("./script.js", testJavascript.getName());
    	assertEquals("external", testJavascript.getClassification());
    	assertEquals(testList, testJavascript.getPages());
    }
    
    @Test
    public void testJavascriptCopyConstructor()
    {
    	ArrayList<String> testList = new ArrayList<>();
    	
    	Javascript testJavascript = new Javascript();
    	
    	testJavascript.setName("./script.js");
    	testJavascript.setClassification("external");
    	testJavascript.setPages(testList);
    	
    	Javascript copy = new Javascript(testJavascript);
    	
    	assertEquals(copy.getName(), testJavascript.getName());
    	assertEquals(copy.getClassification(), testJavascript.getClassification());
    	assertEquals(copy.getPages(), testJavascript.getPages()); 
    }
    
    @Test
    public void testClone()
    {
    	ArrayList<String> testList = new ArrayList<>();
    	Javascript testJavascript = new Javascript();
    	
    	testJavascript.setName("./script.js");
    	testJavascript.setClassification("external");
    	testJavascript.setPages(testList);
    	
    	Javascript copy = testJavascript.clone();
    	
    	assertEquals(copy.getName(), testJavascript.getName());
    	assertEquals(copy.getClassification(), testJavascript.getClassification());
    	assertEquals(copy.getPages(), testJavascript.getPages()); 
    }

    @Test
    void testEquals()
    {
        Javascript one = new Javascript();
        Javascript two = new Javascript();

        one.setName("Equal");
        two.setName("Equal");

        assertTrue(one.equals(two), "Both default objects should be equal");
    }

    @Test
    void testHashCode()
    {
        Javascript one = new Javascript();
        Javascript two = new Javascript();

        one.setName("Equal");
        two.setName("Equal");

        assertEquals(one.hashCode(), two.hashCode());
    }
    
    @Test
    public void testAddPage()
    {
    	ArrayList<String> testList = new ArrayList<>();
    	Javascript testJavascript = new Javascript("./script.js", "external", testList);
    	
    	testJavascript.addPage("index.html");
    	
    	assertEquals(testJavascript.getPages().get(0), "index.html");
    }
    
    @Test
    public void testToString()
    {
    	ArrayList<String> testList = new ArrayList<>();
    	Javascript testJavascript = new Javascript("./script.js", "external", testList);
    	String expected = "scriptPaths: [./script.js]" ;
    	
    	assertThat(testJavascript.toString(), equalTo(expected));
    }
    
}
