package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class JsonImageTests {
    JsonImage defaultJsonImage;
    JsonImage nonDefault;

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

        /**
         * Creating an ArrayList of Html objects
         */
        ArrayList<Html> htmlList = new ArrayList<>();

        Html testingHtml = new Html("/src/main",
                notLinkedList, cssList, jsList, images, resources);
        Html twoHtml = new Html(testingHtml);
        twoHtml.setLocalPath("/src/main/two");
        Html threeHtml = new Html(testingHtml);
        threeHtml.setLocalPath("/src/main/three");
        Html fourHtml = new Html(testingHtml);
        fourHtml.setLocalPath("/src/main/four");
        Image theChosen = new Image("my_image.jpg", false, 999);

        threeHtml.addImages(theChosen);
        fourHtml.addImages(theChosen);

        htmlList.add(testingHtml);
        htmlList.add(twoHtml);
        htmlList.add(threeHtml);
        htmlList.add(fourHtml);

        /**
         * Initializing both constructors
         */
        defaultJsonImage = new JsonImage();
        nonDefault = new JsonImage("my_image.jpg", htmlList);

    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Testing that the default constructor properly initialized all
     * data members
     */
    @Test
    @DisplayName("<JsonImage> Default Constructor")
    void JsonImageDefaultConstructor() {
        assertEquals("untitled", defaultJsonImage.getFileName(),
                "A default filename should be 'untitled' but returned " +
                defaultJsonImage.getFileName());
        assertEquals(0, defaultJsonImage.getNumPagesDisplayed(),
                "There should be 0 pages this image is displayed on returned " +
                defaultJsonImage.getNumPagesDisplayed());
        assertEquals(0, defaultJsonImage.getPageList().size(),
                "The pageList should be empty but returned a size of " +
                defaultJsonImage.getPageList().size());
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Testing the 'parameterized' constructor.
     *  - verifying the name is set correctly
     *  - verifying numPagesDisplayed is correctly set
     *  - verifying the pageList is correctly filled
     */
    @Test
    @DisplayName("<JsonImage> Constructor With Parameters")
    void JsonImageNonDefaultConstructor() {

        assertEquals("my_image.jpg", nonDefault.getFileName(),
                "Filename should be 'my_image.jpg' but returned " +
                nonDefault.getFileName());
        assertEquals(2, nonDefault.getNumPagesDisplayed(),
                "Should have 2 pages where this image is located returned " +
                nonDefault.getNumPagesDisplayed());
        assertTrue(nonDefault.getPageList().contains("/src/main/four"),
                "List should contain '/src/main/four'");
        assertTrue(nonDefault.getPageList().contains("/src/main/three"),
                "List should contain '/src/main/three'");
        assertEquals(2, nonDefault.getPageList().size(),
                "Should have 2 elements in the pageList but returned " +
                nonDefault.getPageList().size());

    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Testing all setters to make sure all values can be set accurately
     */
    @Test
    @DisplayName("<JsonImage> Setters")
    void JsonImageSetters() {
        defaultJsonImage.setFileName("new_name.png");
        assertEquals("new_name.png", defaultJsonImage.getFileName(),
                "Filename should be new_name.png but returned " +
                defaultJsonImage.getFileName());

        // Setting pageList to a new list
        ArrayList<String> randoList = new ArrayList<>();
        randoList.add("One");
        randoList.add("Two");
        defaultJsonImage.setPageList(randoList);
        assertEquals(2, defaultJsonImage.getPageList().size(),
                "Should have 2 items in pageList but returned " +
                defaultJsonImage.getPageList().size());
        assertTrue(defaultJsonImage.getPageList().contains("One"),
                "'One' should be in the page list");
        assertTrue(defaultJsonImage.getPageList().contains("Two"),
                "'Two' should be in the page list");

        defaultJsonImage.setNumPagesDisplayed(2);
        assertEquals(2, defaultJsonImage.getNumPagesDisplayed(),
                "Number of pages displayed on should be 2 but returned " +
                defaultJsonImage.getNumPagesDisplayed());

    }

}
