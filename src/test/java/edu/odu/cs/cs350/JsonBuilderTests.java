package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class JsonBuilderTests {
    JsonBuilder defaultJsonBuilder;
    JsonBuilder nonDefault;
    Website testSite;

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
        Image theChosen = new Image("my_image.jpg", false, 999);
        images.add(imageOne);
        images.add(imageTwo);
        images.add(imageThree);

        /**
         * Creating the File Resource ArrayList
         */
        ArrayList<FileResource> resources = new ArrayList<>();
        FileResource frOne = new FileResource("FR 1",
                "/src/main", 3, "Archive");
        FileResource frTwo = new FileResource("FR 2",
                "/src/main/home", 56, "Video");
        FileResource frThree = new FileResource("FR 3",
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

        threeHtml.addImages(theChosen);
        fourHtml.addImages(theChosen);

        htmlList.add(testingHtml);
        htmlList.add(twoHtml);
        htmlList.add(threeHtml);
        htmlList.add(fourHtml);

        ArrayList<String> urls = new ArrayList<>();
        urls.add("Home/");
        urls.add("About/");
        urls.add("Contact Us/");

        testSite = new Website("src/", urls, htmlList);
        defaultJsonBuilder = new JsonBuilder();
        nonDefault = new JsonBuilder(testSite);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Default Constructor Test
     * <p>
     * Verifying all 3 lists are initialized to empty lists
     */
    @Test
    @DisplayName("<JsonBuilder> Default Constructor")
    void JsonBuilderDefaultConstructor() {
        assertEquals(0, defaultJsonBuilder.getHtmlList().size(),
                "Html List should be empty but returned a size of " +
                        defaultJsonBuilder.getHtmlList().size());
        assertEquals(0, defaultJsonBuilder.getImageList().size(),
                "Image list should be empty but returned " +
                        defaultJsonBuilder.getImageList().size());
        assertEquals(0, defaultJsonBuilder.getFileResourceList().size(),
                "File Resource list should be empty but returned " +
                        defaultJsonBuilder.getFileResourceList().size());
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Parameterized Constructor
     * <p>
     * The parameterized constructor is fed a Website object.
     * This test will verify that all 3 lists are properly created
     */
    @Test
    @DisplayName("<JsonBuilder> Parameterized Constructor")
    void JsonBuilderParameterizedConstructor() {
        assertEquals(4, nonDefault.getImageList().size(),
                "Image list size should be 4 returned " +
                        nonDefault.getImageList().size());
        assertEquals(4, nonDefault.getHtmlList().size(),
                "Html list size should be 5 returned " +
                        nonDefault.getHtmlList());
        assertEquals(3, nonDefault.getFileResourceList().size(),
                "FileResource list should contain 3 elements returned " +
                        nonDefault.getFileResourceList().size());
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Verifying that the Image list was correctly created and with no
     * duplicates
     */
    @Test
    @DisplayName("<JsonBuilder> Generate Image List")
    void JsonBuilderGenerateImageList() {
        assertEquals("Image 1", nonDefault.getImageList().get(0).getFileName(),
                "Should have 'Image 1' in the ImageList");
        assertEquals("Image 2", nonDefault.getImageList().get(1).getFileName(),
                "Should have 'Image 2' in the ImageList");
        assertEquals("Image 3", nonDefault.getImageList().get(2).getFileName(),
                "Should have 'Image 3' in the ImageList");
        assertEquals("my_image.jpg", nonDefault.getImageList().get(3).getFileName(),
                "Should have 'my_image.jpg' in the ImageList");
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Verifying that the File Resource list was correctly created and
     * with no duplicates
     */
    @Test
    @DisplayName("<JsonBuilder> Generate FileResource List")
    void JsonBuilderGenerateFRList() {
        assertEquals("FR 1", nonDefault.getFileResourceList().get(0).getName(),
                "'FR 1' should be in the FRList");
        assertEquals("FR 2", nonDefault.getFileResourceList().get(1).getName(),
                "'FR 2' should be in the FRList");
        assertEquals("FR 3", nonDefault.getFileResourceList().get(2).getName(),
                "'FR 3' should be in the FRList");
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Verifying all setters correctly initialize new lists
     */
    @Test
    @DisplayName("<JsonBuilder> Setters")
    void JsonBuilderSetters() {
        /**
         * Create a new jsonHtml list and make it the new
         * HtmlList for defaultJsonBuilder
         */
        ArrayList<JsonHtml> newHtmlList = new ArrayList<>();
        JsonHtml testOne = new JsonHtml();
        JsonHtml testTwo = new JsonHtml();
        newHtmlList.add(testOne);
        newHtmlList.add(testTwo);
        defaultJsonBuilder.setHtmlList(newHtmlList);

        assertEquals(2, defaultJsonBuilder.getHtmlList().size(),
                "Html List should have 2 items but returned " +
                        defaultJsonBuilder.getHtmlList().size());
        assertTrue(defaultJsonBuilder.getHtmlList().contains(testOne),
                "The HtmlList should contain the 'testOne' object");

        /**
         * Create a new jsonImage list and make it the new
         * ImageList for defaultJsonBuilder
         */
        ArrayList<JsonImage> testingJsonImgList = new ArrayList<>();
        JsonImage OneJI = new JsonImage();
        JsonImage TwoJI = new JsonImage();
        JsonImage ThreeJI = new JsonImage();
        testingJsonImgList.add(OneJI);
        testingJsonImgList.add(TwoJI);
        testingJsonImgList.add(ThreeJI);
        defaultJsonBuilder.setImageList(testingJsonImgList);

        assertEquals(3, defaultJsonBuilder.getImageList().size(),
                "The new ImageList should have 3 items but returned " +
                        defaultJsonBuilder.getImageList().size());
        assertTrue(defaultJsonBuilder.getImageList().contains(TwoJI),
                "The imageList should contain the object TwoJI");

        /**
         * Create a new FileResource list and make it the new
         * FRList for defaultJsonBuilder
         */
        ArrayList<FileResource> fr = new ArrayList<>();
        FileResource frOne = new FileResource();
        FileResource frTwo = new FileResource();
        FileResource frThree = new FileResource();
        fr.add(frOne);
        fr.add(frTwo);
        fr.add(frThree);
        defaultJsonBuilder.setFileResourceList(fr);

        assertEquals(3, defaultJsonBuilder.getFileResourceList().size(),
                "FRList should have 3 items but returned " +
                        defaultJsonBuilder.getFileResourceList().size());
        assertTrue(defaultJsonBuilder.getFileResourceList().contains(frThree),
                "The new FRList should have the frThree object");


    }

}
