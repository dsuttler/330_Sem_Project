package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonHtmlTests {

    JsonHtml defaultJsonHtml;
    JsonHtml nonDefault;

    /** 
     * Setting up for the tests here to improve readability
     */
    @BeforeEach
    void setup() {
        /**
         * Creating an ArrayList of links for the HTML object
         */
        ArrayList<Link> notLinkedList = new ArrayList<>();
        Link linkOne = new Link("Link 1", "intra");
        Link linkTwo = new Link("Link 2", "inter");
        Link linkThree = new Link("Link 3", "external");
        Link linkFour = new Link("Link 4", "intra");
        Link linkFive = new Link("Link 5", "external");
        notLinkedList.add(linkOne);
        notLinkedList.add(linkTwo);
        notLinkedList.add(linkThree);
        notLinkedList.add(linkFour);
        notLinkedList.add(linkFive);

        /**
         * Creating the CSS ArrayList
         */
        ArrayList<Css> cssList = new ArrayList<>();
        ArrayList<String> cssDisplayed = new ArrayList<>();
        cssDisplayed.add("About");
        cssDisplayed.add("Home");
        cssDisplayed.add("Contact Us");
        Css cssOne = new Css("Css One", "internal", cssDisplayed);
        Css cssTwo = new Css("Css Two", "internal", cssDisplayed);
        Css cssThree = new Css("Css Three", "external", cssDisplayed);
        cssList.add(cssOne);
        cssList.add(cssTwo);
        cssList.add(cssThree);

        /**
         * Creating the JS ArrayList
         */
        ArrayList<Javascript> jsList = new ArrayList<>();
        Javascript jsOne = new Javascript("JS One", "internal", cssDisplayed);
        Javascript jsTwo = new Javascript("JS Two", "external", cssDisplayed);
        Javascript jsThree = new Javascript("JS Three", "internal", cssDisplayed);
        jsList.add(jsOne);
        jsList.add(jsTwo);
        jsList.add(jsThree);

        /**
         * Creating the Image ArrayList
         */
        ArrayList<Image> images = new ArrayList<>();
        Image imageOne = new Image("Image 1", true, 128);
        Image imageTwo = new Image("Image 2", true, 512);
        Image imageThree = new Image("Image 3", false, 9001);
        images.add(imageOne);
        images.add(imageTwo);
        images.add(imageThree);

        /**
         * Creating the File Resource ArrayList
         */
        ArrayList<FileResource> resources = new ArrayList<>();
        FileResource frOne = new FileResource("FR 1",
                "/src/main", 3, "Archive");
        FileResource frTwo = new FileResource("FR 1",
                "/src/main/home", 56, "Video");
        FileResource frThree = new FileResource("FR 1",
                "/src/main/about", 6334, "Audio");
        resources.add(frOne);
        resources.add(frTwo);
        resources.add(frThree);

        Html testingHtml = new Html("/src/main",
                notLinkedList, cssList, jsList, images, resources);

        /**
         * Constructors used for testing
         */
        defaultJsonHtml = new JsonHtml();
        nonDefault = new JsonHtml(testingHtml);


    }


    /** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * 
     * Testing the default constructor
     * - Verify that all lists are empty 
     * - Verify that all int variables are 0
     */
    @Test
    @DisplayName("<JsonHtml> Default Constructor")
    void DefaultConstructorTest() {
        // number of local images is 0
        assertEquals(0, defaultJsonHtml.getNumLocalImages(),
        "Number of local images should be 0. Returned: " +
        defaultJsonHtml.getNumLocalImages());

        // number of scripts referenced should be 0
        assertEquals(0, defaultJsonHtml.getNumScriptsRef(),
        "Number of scripts referenced should be 0. Returned: " +
        defaultJsonHtml.getNumScriptsRef());

        // number of intra page links should be 0
        assertEquals(0, defaultJsonHtml.getNumIntraPageLinks(),
        "Number of intra page links should be 0. Returned: " +
        defaultJsonHtml.getNumIntraPageLinks());

        // number of inter site links should be 0
        assertEquals(0, defaultJsonHtml.getNumInterSiteLinks(),
        "Number of inter site links should be 0. Returned: " +
        defaultJsonHtml.getNumInterSiteLinks());

        // number of external site links should be 0
        assertEquals(0, defaultJsonHtml.getNumExternalLinks(),
        "Number of external site links should be 0. Returned: " +
        defaultJsonHtml.getNumExternalLinks());

        // number of CSS Scripts should be 0
        assertEquals(0, defaultJsonHtml.getCssUtilized().size(),
        "Should not have any CSS scripts but returned: " +
        defaultJsonHtml.getCssUtilized().size());

        // list of Images should be empty
        assertEquals(0, defaultJsonHtml.getAllImages().size(),
        "Should not have any images yet but returned: " +
        defaultJsonHtml.getAllImages().size());

        // list of scripts should be empty
        assertEquals(0, defaultJsonHtml.getScriptsUtilized().size(),
        "Should not have any scripts in the list. Returned: " +
        defaultJsonHtml.getScriptsUtilized().size());


    }

    /** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     * Testing the non-default constructor
     * - Verify all data members are correctly initialized
     */
    @Test
    @DisplayName("<JsonHtml> Non-Default Constructor & Getters")
    void nonDefaultConstructorTest() {

        // check to make sure all elements were utilized
        assertEquals("/src/main", nonDefault.getFileName(),
                "Filename should be '/src/main but returned " +
                nonDefault.getFileName());
        assertEquals(3, nonDefault.getCssUtilized().size(),
                "Should have 3 list items returned " +
                        nonDefault.getCssUtilized().size());
        assertEquals(3, nonDefault.getAllImages().size(),
                "Should have 3 list items returned " +
                        nonDefault.getAllImages().size());
        assertEquals(3, nonDefault.getScriptsUtilized().size(),
                "Should have 3 list items returned " +
                        nonDefault.getScriptsUtilized().size());
        assertEquals(2, nonDefault.getNumLocalImages(),
                "Should have 2 local images but returned " +
                nonDefault.getNumLocalImages());
        assertEquals(1, nonDefault.getNumExternalImages(),
                "Should have 1 external image but returned " +
                nonDefault.getNumExternalImages());
        assertEquals(3, nonDefault.getNumScriptsRef(),
                "Should have 3 scripts but returned " +
                nonDefault.getNumScriptsRef());
        assertEquals(2, nonDefault.getNumIntraPageLinks(),
                "Should have 2 intra-page links but returned " +
                nonDefault.getNumIntraPageLinks());
        assertEquals(1, nonDefault.getNumInterSiteLinks(),
                "Should have 1 inter-site link but returned " +
                nonDefault.getNumInterSiteLinks());
        assertEquals(2, nonDefault.getNumExternalLinks(),
                "Should have 2 external site links but returned " +
                nonDefault.getNumExternalLinks());

    }

    /** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     * Testing all Setters in the JsonHtml Class
     * - Verify we can change each value
     */
    @Test
    @DisplayName("<JsonHtml> All Setters")
    void gettersTest() {
        ArrayList<String> testingArrayList = new ArrayList<>();
        testingArrayList.add("THE ONE");
        testingArrayList.add("NUMBER TWO");

        // Set list of CSS file names
        nonDefault.setCssUtilized(testingArrayList);
        assertEquals(2, nonDefault.getCssUtilized().size(),
                "Size of the CSS list should be 2 but was " +
                nonDefault.getCssUtilized().size());
        assertTrue(nonDefault.getCssUtilized().contains("NUMBER TWO"),
                "The CSS list should contain the string 'NUMBER TWO' but does not");

        // Set the list of Images
        nonDefault.setAllImages(testingArrayList);
        assertEquals(2, nonDefault.getAllImages().size(),
                "Size of the Image list should be 2 but was " +
                        nonDefault.getAllImages().size());
        assertTrue(nonDefault.getAllImages().contains("NUMBER TWO"),
                "The Image list should contain the string 'NUMBER TWO' but does not");

        // Set the list of JS scripts
        nonDefault.setScriptsUtilized(testingArrayList);
        assertEquals(2, nonDefault.getScriptsUtilized().size(),
                "Size of the JS list should be 2 but was " +
                        nonDefault.getScriptsUtilized().size());
        assertTrue(nonDefault.getScriptsUtilized().contains("NUMBER TWO"),
                "The JS list should contain the string 'NUMBER TWO' but does not");

        // Set the number of local images
        nonDefault.setNumLocalImages(5);
        assertEquals(5, nonDefault.getNumLocalImages(),
                "Number of local images should have changed to 5 but returned " +
                nonDefault.getNumLocalImages());

        // Set the number of JS scripts referenced
        nonDefault.setNumScriptsRef(7);
        assertEquals(7, nonDefault.getNumScriptsRef(),
                "Number of JS scripts referenced should have changed to 7 but returned " +
                nonDefault.getNumScriptsRef());

        // Set the number of Intra-Page links
        nonDefault.setNumIntraPageLinks(19);
        assertEquals(19, nonDefault.getNumIntraPageLinks(),
                "Number of Intra-Site links should have changed to 19 but returned " +
                nonDefault.getNumIntraPageLinks());

        // Set the number of Inter-Site links
        nonDefault.setNumInterSiteLinks(90);
        assertEquals(90, nonDefault.getNumInterSiteLinks(),
                "Number of Inter-Site links should have changed to 90 but returned " +
                nonDefault.getNumInterSiteLinks());

        // Set the number of External links
        nonDefault.setNumExternalLinks(999);
        assertEquals(999, nonDefault.getNumExternalLinks(),
                "Number of external links should have changed to 999 but returned " +
                nonDefault.getNumExternalLinks());

        // Change the filename
        nonDefault.setFileName("New Name");
        assertEquals("New Name", nonDefault.getFileName(),
                "Name should have changed to 'New Name' but returned " +
                nonDefault.getFileName());

        // change the number of external images
        nonDefault.setNumExternalImages(56);
        assertEquals(56, nonDefault.getNumExternalImages(),
                "Number of external images should have changed to 56 but returned " +
                nonDefault.getNumExternalImages());
    }
}
