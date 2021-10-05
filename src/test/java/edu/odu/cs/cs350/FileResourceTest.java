package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileResourceTest {
    /**
     *  Objects used in the tests 
     */
    FileResource defaultFileResource;
    FileResource nonDefaultFileResource;
    FileResource copyTest;

    @BeforeEach
    void setup() {
    	String testName= "testNameFileResource";
    	String testLocalPath= "testLocalPathFileResource";
    	int testSize = 10;
    	String testCategory= "testCategoryFileResource";
    	
        defaultFileResource = new FileResource(); // default constructor testing onject
        nonDefaultFileResource = new FileResource(testName, testLocalPath, testSize, testCategory); // Parameterized constructor testing object
        copyTest= new FileResource(); 
        
    }

    @Test
    @DisplayName("<FileResource> Default Constructor")
    void FileResourceDefaultConstructor() {

        /**
         *  Make sure the name is empty for the default constructor
         */
        assertEquals("", defaultFileResource.getName(), 
        "Default constructor should be empty. Returned: " + defaultFileResource.getName());
        
        /**
         *  Make sure the category is empty for the default constructor
         */
        assertEquals("", defaultFileResource.getCategory(), 
        "Default constructor should be empty. Returned: " + defaultFileResource.getCategory());
        
        /**
         *  Make sure localpath is an empty string for the default constructor
         */
        assertEquals("", defaultFileResource.getLocalPath());
        
        /**
         *  Make sure the default constructor contains a 0 for size
         */
        assertEquals(0, defaultFileResource.getSize(), 
        "Default constructor should have a 0 for size but returned: "
        + defaultFileResource.getSize());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<FileResource> Parameterized Constructor")
    void FileResourceParameterizedConstructor() {

        /**
         *  Make sure the name is testNameFileResource for the parameterized constructor
         */
        assertEquals("testNameFileResource", nonDefaultFileResource.getName(), 
        "Parameterized constructor should be testNameFileResouce. Returned: " + nonDefaultFileResource.getName());
        
        /**
         *  Make sure the category is testCategoryFileResource for the parameterized constructor
         */
        assertEquals("testCategoryFileResource", nonDefaultFileResource.getCategory(), 
        "Parameterized constructor should be testCategoryFileResource. Returned: " + nonDefaultFileResource.getCategory());
        
        /**
         *  Make sure localpath is an testLocalPathFileResource string for the parameterized constructor
         */
        assertEquals("testLocalPathFileResource", nonDefaultFileResource.getLocalPath(),
        "Parameterized constructor should be testLocalPathFileResource. Returned: " + nonDefaultFileResource.getLocalPath());
        
        /**
         *  Make sure the parameterized constructor contains a 0 for size
         */
        assertEquals(10, nonDefaultFileResource.getSize(), 
        "Default constructor should have a 10 for size but returned: "
        + nonDefaultFileResource.getSize());
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<FileResource> SetName")
    void FileResourceSetName() {
    	defaultFileResource.setName(nonDefaultFileResource.getName());
    	assertEquals(defaultFileResource.getName(), nonDefaultFileResource.getName(), 
        "default constructor name should be "+ nonDefaultFileResource.getName() + " Returned: " + defaultFileResource.getName());
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<FileResource> SetLocalPath")
    void FileResourceSetLocalPath() {
    	defaultFileResource.setLocalPath(nonDefaultFileResource.getLocalPath());
    	assertEquals(defaultFileResource.getLocalPath(), nonDefaultFileResource.getLocalPath(), 
        "default constructor localPath should be "+ nonDefaultFileResource.getLocalPath() + " Returned: " + defaultFileResource.getLocalPath());
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<FileResource> SetSize")
    void FileResourceSetSize() {
    	defaultFileResource.setSize(nonDefaultFileResource.getSize());
    	assertEquals(defaultFileResource.getSize(), nonDefaultFileResource.getSize(), 
        "default constructor size should be "+ nonDefaultFileResource.getSize() + " Returned: " + defaultFileResource.getSize());
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<FileResource> SetCategory")
    void FileResourceSetCategory() {
    	defaultFileResource.setCategory(nonDefaultFileResource.getCategory());
    	assertEquals(defaultFileResource.getCategory(), nonDefaultFileResource.getCategory(), 
        "default constructor category should be "+ nonDefaultFileResource.getCategory() + " Returned: " + defaultFileResource.getCategory());
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<FileResource> CopyConstructor")
    void FileResourceCopyConstructor() {
    	copyTest.CopyFileResource(nonDefaultFileResource);
    	assertEquals(copyTest.getName(), nonDefaultFileResource.getName(), 
        "copy constructor name should be "+ nonDefaultFileResource.getName() + " Returned: " + copyTest.getName());
    	assertEquals(copyTest.getLocalPath(), nonDefaultFileResource.getLocalPath(), 
        "copy constructor localPath should be "+ nonDefaultFileResource.getLocalPath() + " Returned: " + copyTest.getLocalPath());
    	assertEquals(copyTest.getSize(), nonDefaultFileResource.getSize(), 
        "copy constructor size should be "+ nonDefaultFileResource.getSize() + " Returned: " + copyTest.getSize());
    	assertEquals(copyTest.getCategory(), nonDefaultFileResource.getCategory(), 
        "copy constructor category should be "+ nonDefaultFileResource.getCategory() + " Returned: " + copyTest.getCategory());
    }
 
    
}