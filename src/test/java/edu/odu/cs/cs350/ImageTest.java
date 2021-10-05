package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageTest {
    /**
     *  Objects used in the tests 
     */
    Image defaultImage;
    Image nonDefaultImage;
    Image copyTest;

    @BeforeEach
    void setup() {
 
        
    	String testName= "testNameImage";
    	boolean testInternal= true;
/*    	int testNumberOfPages = 3;
 *       ArrayList<String> testPagesDisplayedOn = new ArrayList<String>() {
 *           {
 *               add("String 1Images"); 
 *               add("String 2Images"); 
 *               add("String 3Images");
 *           }
 *       };
 */       
    	int testSize= 9;
    	
        defaultImage = new Image(); 
        nonDefaultImage = new Image(testName, testInternal, testSize); 
        // copyTest.copyImage(nonDefaultImage); 
        copyTest = new Image();
    }

    @Test
    @DisplayName("<Image> Default Constructor")
    void ImageDefaultConstructor() {

        /**
         *  Make sure the name is empty for the default constructor
         */
        assertEquals("", defaultImage.getName(), 
        "Default constructor should be empty. Returned: " + defaultImage.getName());
        
        /**
         *  Make sure the internal is false for the default constructor
         */
        assertEquals(false , defaultImage.getInternal(), 
        "Default constructor should be false. Returned: " + defaultImage.getInternal());
        
/*        // Make sure numberofpages is a 0 for the default constructor
 *       assertEquals(0, defaultImages.getNumberOfPages(),
 *       "Default constructor should be 0. Returned: " + defaultImages.getNumberOfPages());
 */      // Make sure the default constructor pagesdisplayedOnSize is 0
        assertEquals(0, defaultImage.getSize(), 
        "Default constructor should have a 0 for PagesDisplayedOn size but returned: "
        + defaultImage.getSize());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<Image> Parameterized Constructor")
    void ImageParameterizedConstructor() {

        /**
         *  Make sure the name is populated for the parameterized constructor
         */
        assertEquals("testNameImage", nonDefaultImage.getName(), 
        "Parameterized constructor should be testNameImages. Returned: " + nonDefaultImage.getName());
        
        /**
         *  Make sure the internal is true for the parameterized constructor
         */
        assertEquals(true , nonDefaultImage.getInternal(), 
        "Parameterized constructor should be true. Returned: " + nonDefaultImage.getInternal());
        
/*        // Make sure NumberOfPages is 3 for the parameterized constructor
        assertEquals( 3 , nonDefaultImage.getNumberOfPages(),
        "Parameterized constructor should be 3. Returned: " + nonDefaultImages.getNumberOfPages());
        // Make sure the parameterized constructor contains a 3 for pages displayed on size
        assertEquals( 3 , nonDefaultImages.getPagesDisplayedOn().size(), 
        "Default constructor should have a 3 for size of pagesDisplayedOn but returned: "
        + nonDefaultImages.getPagesDisplayedOn().size());
*/        
        /**
         *  Make sure the parameterized constructor contains a 9 for size
         */
        assertEquals( 9 , nonDefaultImage.getSize(), 
        "Default constructor should have a 9 for size but returned: "
        + nonDefaultImage.getSize());
        
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Image> SetName")
    void ImageSetName() {
    	
    	defaultImage.setName(nonDefaultImage.getName());
    	assertEquals(nonDefaultImage.getName(), defaultImage.getName(), "defaultImage name should be set to " + nonDefaultImage.getName() + " but instead returned: " + defaultImage.getName());
    }

    @Test
    @DisplayName("<Image> SetInternal")
    void ImageSetInternal() {
    	
    	defaultImage.setInternal(nonDefaultImage.getInternal());
    	assertEquals(nonDefaultImage.getInternal(), defaultImage.getInternal(), "defaultImage internal should be set to " + nonDefaultImage.getInternal() + " but instead returned: " + defaultImage.getInternal());
    }
    
    @Test
    @DisplayName("<Image> SetSize")
    void ImageSetSize() {
    	
    	defaultImage.setSize(nonDefaultImage.getSize());
    	assertEquals(nonDefaultImage.getSize(), defaultImage.getSize(), "defaultImage size should be set to " + nonDefaultImage.getSize() + " but instead returned: " + defaultImage.getSize());
    }
    
    @Test
    @DisplayName("<Image> CopyConstructor")
    void ImageCopyConstructor() {
    	
    	copyTest.copyImage(nonDefaultImage);
    	assertEquals(nonDefaultImage.getName(), copyTest.getName(), "copyTest name should be "+ nonDefaultImage.getName() + " but it returned " + copyTest.getName());
    	assertEquals(nonDefaultImage.getInternal(), copyTest.getInternal(), "copyTest internal should be "+ nonDefaultImage.getInternal() + " but it returned " + copyTest.getInternal());
    	assertEquals(nonDefaultImage.getSize(), copyTest.getSize(), "copyTest size should be "+ nonDefaultImage.getSize() + " but it returned " + copyTest.getSize());

    }
}