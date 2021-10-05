package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

class CssTests {
    /**
     *  Objects used in the tests 
     */
    Css defaultCss;
    Css nonDefaultCss;
    Css copyTest;

    @BeforeEach
    void setup() {
 
        /**
         *  Initialize a new Css object with a name and a list of strings
         */
        ArrayList<String> testList = new ArrayList<String>() {
            {
                add("String 1"); 
                add("String 2"); 
                add("String 3");
            }
        };

        defaultCss = new Css();
        nonDefaultCss = new Css("./stylesheet.css", "Internal", testList);
        copyTest = new Css(nonDefaultCss); 
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Css> Default Constructor")
    void CSSDefaultConstructor() {

        /**
         *  Make sure the name is empty for the default constructor
         */
        assertEquals("", defaultCss.getName(), 
        "Default constructor should be empty. Returned: " + defaultCss.getName());
        assertEquals(0, defaultCss.getName().length(), 
        "Default constructor should be empty. Returned: " + defaultCss.getName());
        
        /**
         *  Make sure classification is an empty string for the default constructor
         */
        assertEquals("", defaultCss.getClassification());

        /**
         *  Make sure the default constructor contains an empty list
         */
        assertEquals(0, defaultCss.getPages().size(), 
        "Default constructor should have an empty list of pages but returned: "
        + defaultCss.getPages().size());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Css> Parameterized Constructor")

    void CSSParameterizedConstructor() {

        /**
         *  Making sure that the constructor properly initiates the name 
         */
        assertEquals("./stylesheet.css", nonDefaultCss.getName(), 
        "New CSS object should have had the name ./stylesheet.css but returned " + nonDefaultCss.getName());

        assertEquals("Internal", nonDefaultCss.getClassification(),
        "Classification should be 'Internal but returned: " + nonDefaultCss.getClassification());
        
        /**
         *  Checking that the list is stored in the original order
         */
        assertEquals("String 1", nonDefaultCss.getPages().get(0),
        "The first element should be 'String 1' but returned: " + nonDefaultCss.getPages().get(0));

        assertEquals("String 2", nonDefaultCss.getPages().get(1),
        "The list of pages should have contained 'String 2' at the second position but instead returned " 
        + nonDefaultCss.getPages().get(1));

        assertEquals("String 3", nonDefaultCss.getPages().get(nonDefaultCss.getPages().size() - 1),
        "The last element should be 'String 3' but returned: " + nonDefaultCss.getPages().get(nonDefaultCss.getPages().size() - 1));
        
        /**
         *  Verifying that the list contains the exact strings from the list given
         */
        assertTrue(nonDefaultCss.getPages().contains("String 1"), "'String 1' should be contained in the list but it is not");
        assertTrue(nonDefaultCss.getPages().contains("String 2"), "'String 2' should be contained in the list but it is not");
        assertTrue(nonDefaultCss.getPages().contains("String 3"), "'String 3' should be contained in the list but it is not");
        
        /**
         *  Verifying that the # of elements in the list passed in matches the # of elements stored in the new object
         */
        assertEquals(3, nonDefaultCss.getPages().size());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Css> Copy Constructor")
    void CssCopyConstructor() {

        ArrayList<String> testList = new ArrayList<>();
    	
    	Css testCss = new Css();
    	
    	testCss.setName("./stylesheet.css");
    	testCss.setClassification("external");
    	testCss.setPages(testList);
    	
    	Css copy = new Css(testCss);
        copy.addPage("String 1");
        copy.addPage("String 2");
        copy.addPage("String 3");


        assertEquals("./stylesheet.css", copy.getName(), 
        "New CSS object should have had the name './stylesheet.css' but returned " + copy.getName());
        
        /**
         *  Checking that the list is stored in the original order
         */
        assertEquals("String 1", copy.getPages().get(0),
        "The first element should be 'String 1' but returned: " + copy.getPages().get(0));

        assertEquals("String 2", copy.getPages().get(1),
        "The list of pages should have contained 'String 2' at the second position but instead returned " 
        + copy.getPages().get(1));

        assertEquals("String 3", copy.getPages().get(copy.getPages().size() - 1),
        "The last element should be 'String 3' but returned: " + copy.getPages().get(copy.getPages().size() - 1));
        
        /**
         *  Change the new object's name
         */
        copy.setName("./javascript");

        /**
         *  Did the name change?
         */
        assertEquals("./javascript", copy.getName(), 
        "Copied CSS object should have had the name './javascript' but returned " + copy.getName());

        /**
         *  Is the original name unchanged?
         */
        assertEquals("./stylesheet.css", testCss.getName(), 
        "Original CSS object should have had the name './stylesheet' but returned " + testCss.getName());

        /**
         *  Make sure we dont change the original object
         */
        assertFalse(testCss.getPages().contains("String 1"),
        "Original object should have an empty list, but objects were added from copied object.");
        
        /**
         *  Again, original list should be unchanged
         */
        assertEquals(0, testCss.getPages().size(),
        "List of original 'testCss' object should be empty but - List: " + testCss.getPages());
    }

    @Test
    @DisplayName("<Css> Set Pages")
    void TestSetPages() {
        ArrayList<String> testList = new ArrayList<String>() {
            {
                add("String 1"); 
                add("String 2"); 
                add("String 3");
            }
        };

        defaultCss.setPages(testList);
        defaultCss.setName("Jamal");

        assertEquals("String 1", defaultCss.getPages().get(0));
    }

    @Test
    @DisplayName("<Css> Hash Code") 
    void TestHashCode() {
        Css one = new Css();
        Css two = new Css();

        one.setName("Victoria");
        two.setName("Victoria");

        assertEquals(one.hashCode(), two.hashCode(),
         "Two onjects with the same name should have the same hash code, but they do not.");
    }

    @Test
    @DisplayName("<Css> Clone")
    void TestClone() {

        ArrayList<String> testList = new ArrayList<>();
    	Css testCss = new Css();
    	
    	testCss.setName("./stylesheet.css");
    	testCss.setClassification("external");
    	testCss.setPages(testList);
    	
    	Css copy = testCss.clone();
        copy.addPage("String 1");
        copy.addPage("String 2");
        copy.addPage("String 3");

        // Css copy = nonDefaultCss.clone();

        /**
         *  Verify that name copied correctly
         */
        assertEquals("./stylesheet.css", copy.getName(), 
        "New CSS object should have had the name './stylesheet.css' but returned " + copy.getName());
        
        /**
         *  Checking that the list is stored in the original order
         */
        assertEquals("String 1", copy.getPages().get(0),
        "The first element should be 'String 1' but returned: " + copy.getPages().get(0));

        assertEquals("String 2", copy.getPages().get(1),
        "The list of pages should have contained 'String 2' at the second position but instead returned " 
        + copy.getPages().get(1));

        assertEquals("String 3", copy.getPages().get(copy.getPages().size() - 1),
        "The last element should be 'String 3' but returned: " + copy.getPages().get(copy.getPages().size() - 1));
        
        /**
         *  Change the new object's name
         */
        copy.setName("./javascript");

        /**
         *  Did the name change?
         */
        assertEquals("./javascript", copy.getName(), 
        "Copied CSS object should have had the name './javascript' but returned " + copy.getName());

        /**
         *  Is the original name unchanged?
         */
        assertEquals("./stylesheet.css", testCss.getName(), 
        "Original CSS object should have had the name './stylesheet' but returned " + testCss.getName());

        /**
         *  Make sure we dont change the original object
         */
        assertFalse(testCss.getPages().contains("String 1"),
        "Original object should have an empty list, but objects were added from cloned object.");
        
        /**
         *  Again, original list should be unchanged
         */
        assertEquals(0, testCss.getPages().size(),
        "List of original 'testCss' object should be empty but - List: " + testCss.getPages());
        
    }
    
    @Test
    @DisplayName("<Css> Add Page")
    public void testAddPage()
    {
    	ArrayList<String> testList = new ArrayList<>();
    	Css testCss = new Css("./stylesheet.css", "external", testList);
    	
    	testCss.addPage("index.html");
    	
    	assertEquals(testCss.getPages().get(0), "index.html");
    	
    }
    
    @Test
    @DisplayName("<Css> To String")
    public void testToString()
    {
    	String expected = "cssPaths: [./stylesheet.css]" ;
    	
    	assertThat(nonDefaultCss.toString(), equalTo(expected));
    }

    @Test
    @DisplayName("<Css> Equals")
    void cssEquals() {
        Css one = new Css();
        Css two = new Css();

        assertEquals(one, two, "Two default objects should be equal but they are not");

        one.setName("New Name");
        assertNotEquals(one, two,
                "After changing the name of one of two identical objects, " +
                        "they should not be equal");
    }

}
