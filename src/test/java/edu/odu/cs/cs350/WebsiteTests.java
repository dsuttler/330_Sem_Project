package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

class WebsiteTests {
    /*
     * Objects used in the tests 
     */
    Website defaultWebsite;
    Website parameterizedWebsite;
    Website copyWebsite;
    Html testPage;
    
    
    @BeforeEach
    void setup() {
 
        /*
         * Initialize array list of test URLs (can add more URLs if I need to)
         */
        ArrayList<String> testURLs = new ArrayList<String>() {
            {
                add("URL"); 

            }
        };
        
        /*
         * Initialize array list of Html page (can add more pages if I need to)
         */
        ArrayList<Html> testHtml = new ArrayList<Html>() {
        	{
        		add(testPage);
        		
        	}
        	
        };
    
        defaultWebsite = new Website();
        parameterizedWebsite = new Website("./testPath", testURLs, testHtml);
        
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<Website> Default Constructor")
    void WebsiteDefaultConstructor() {

        /*
         * Make sure the local path is empty for the default constructor
         */
        assertEquals("", defaultWebsite.getLocalPath(), 
        "Default constructor should be empty. Returned: " + defaultWebsite.getLocalPath());
        

        
        /*
         * Make sure the default constructor contains an empty list of URLs
         */
        assertEquals(0, defaultWebsite.getSiteURLs().size(), 
        "Default constructor should have an empty list of URLs but returned: " + defaultWebsite.getSiteURLs().size());
        
        /*
         * Make sure the default constructor contains an empty list of pages
         */
        assertEquals(0, defaultWebsite.getAllPages().size(), 
        "Default constructor should have an empty list of URLs but returned: " + defaultWebsite.getAllPages().size());
        
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    
    @Test
    @DisplayName("<Website> Parameterized Constructor")
    void WebsiteParameterizedConstructor() {

        /*
         * Make sure the local path is initialized for parameterized constructor
         */
        assertEquals("./testPath", parameterizedWebsite.getLocalPath(), 
        "Parameterized constructor should return testPath. Returned: " + parameterizedWebsite.getLocalPath());
        

        
        /*
         * Make sure the non default constructor contains list of URL
         */
        assertEquals("URL", parameterizedWebsite.getSiteURLs().get(0), 
        "Parameterized constructor should return URL but returned: " + parameterizedWebsite.getSiteURLs().get(0));
        
        /*
         * Make sure the non default constructor contains list of Page
         */
        assertEquals(testPage, parameterizedWebsite.getAllPages().get(0), 
        "Parameterized constructor should return html page but returned: " + parameterizedWebsite.getAllPages().size());
        
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */



    
    
    @Test
    @DisplayName("<Website> Set Local Path")
    void websiteSetLocalPath() {
    	
    	
    	defaultWebsite.setLocalPath("./testPath");
    	
    	assertEquals("./testPath", defaultWebsite.getLocalPath(), 
    	"Website should contain testPath but returned: " + defaultWebsite.getLocalPath());
    	
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    
    @Test
    @DisplayName("<Website> Set Site URL")
    void websiteSetSiteURLs() {
    	
    	/*
    	 * sets up array list of test URLs
    	 * I did not think I needed this here since it is in the setup() that is supposed to run
    	 * before each but this function would not recognize testURLs without initializing here 
    	 */
        ArrayList<String> testURLs = new ArrayList<String>() {
            {
                add("URL"); 
            }
        };
        
    	defaultWebsite.setSiteURLs(testURLs);
    	
    	assertEquals("URL", defaultWebsite.getSiteURLs().get(0), 
    	"Website should contail URL but contains: " + defaultWebsite.getSiteURLs().get(0));
    
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Website> Add Site Url")
    void websiteAddSiteUrl() {
        Website testingSite = new Website();
        testingSite.addSiteURL("One");
        testingSite.addSiteURL("Two");

        assertEquals(2, testingSite.getSiteURLs().size());
        assertEquals("One", testingSite.getSiteURLs().get(0));
        assertEquals("Two", testingSite.getSiteURLs().get(1));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Website> Copy Website")
    void WebsiteCopyConstructor() {
        Website testingSite = new Website();
        testingSite.addSiteURL("/src/main/home");
        Website copyTest = new Website(testingSite);
        copyTest.addSiteURL("/src/main/about");

        assertTrue(copyTest.getSiteURLs().contains("/src/main/home"));
        assertTrue(copyTest.getSiteURLs().contains("/src/main/about"));
        assertFalse(testingSite.getSiteURLs().contains("/src/main/about"));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    
    @Test
    @DisplayName("<Website> Set All Pages")
    void websiteSetAllPages() {
    	
    	
    	/*
    	 *  Initialize array list of Html page 
    	 *  same as above, not sure why setup() not being seen in this function 
    	 */
    	
        ArrayList<Html> testHtml = new ArrayList<Html>() {
        	{
        		add(testPage);
        		
        	}
        	
        };
    	    	
    	defaultWebsite.setAllPages(testHtml);
    	
    	assertEquals(testPage, defaultWebsite.getAllPages().get(0),
    	"Website should contail Html but contains: " + defaultWebsite.getAllPages().get(0));
    	
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<Website> Copy Website")
    void websiteCopyWebsite() { 
    	 
    	
    	Website copyTest = parameterizedWebsite;
    	
    	
    	/*
    	 * Make sure new Website object has same path
    	 */
        assertEquals("./testPath", copyTest.getLocalPath(), 
        "Copy constructor should return testPath. Returned: " + copyTest.getLocalPath());
        
        
        /*
         * Make sure the copy default constructor contains list of URL
         */
        assertEquals("URL", copyTest.getSiteURLs().get(0), 
        "Copy constructor should return URL but returned: " + copyTest.getSiteURLs().get(0));
        
        /*
         * Make sure the copy default constructor contains list of Page
         */
        assertEquals(testPage, copyTest.getAllPages().get(0), 
        "Copy constructor should return html page but returned: " + copyTest.getAllPages().size());
		
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

}





