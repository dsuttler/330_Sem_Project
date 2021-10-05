package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

class JSONWriterTest {
	JSONWriter defaultWriter;
	JSONWriter testWriter;
	Website testSite;
	
	@BeforeEach
	void setup()
	{
		/**
		 * Creating an ArrayList of links for the HTML object
		 */
		ArrayList<Link> notLinkedList = new ArrayList<>();
		Link linkOne = new Link("home/", "intra");
		Link linkTwo = new Link("#content", "inter");
		Link linkThree = new Link("www.linkedin.com", "external");
		Link linkFour = new Link("about/", "intra");
		Link linkFive = new Link("www.instagram.com", "external");
		notLinkedList.add(linkOne);
		notLinkedList.add(linkTwo);
		notLinkedList.add(linkThree);
		notLinkedList.add(linkFour);
		notLinkedList.add(linkFive);

		/**
		 * Creating the CSS ArrayList
		 */
		ArrayList<Css> homeCssList = new ArrayList<>();
		ArrayList<Css> loginCssList = new ArrayList<>();
		ArrayList<Css> aboutCssList = new ArrayList<>();
		ArrayList<Css> contactCssList = new ArrayList<>();
		ArrayList<String> cssDisplayed = new ArrayList<>();
		cssDisplayed.add("About");
		cssDisplayed.add("Home");
		cssDisplayed.add("Contact Us");
		Css cssOne = new Css("main.css", "internal", cssDisplayed);
		Css cssTwo = new Css("navbar.css", "internal", cssDisplayed);
		Css cssThree = new Css("footer.css", "external", cssDisplayed);
		Css cssFour = new Css("about.css", "internal", cssDisplayed);
		homeCssList.add(cssOne);
		homeCssList.add(cssTwo);
		homeCssList.add(cssThree);
		loginCssList.add(cssThree);
		aboutCssList.add(cssOne);
		aboutCssList.add(cssTwo);
		aboutCssList.add(cssThree);
		aboutCssList.add(cssFour);
		contactCssList.add(cssTwo);
		contactCssList.add(cssThree);


		/**
		 * Creating the JS ArrayList
		 */
		ArrayList<Javascript> jsList = new ArrayList<>();
		ArrayList<Javascript> loginJsList = new ArrayList<>();
		ArrayList<Javascript> aboutJsList = new ArrayList<>();
		ArrayList<Javascript> contactJsList = new ArrayList<>();
		Javascript jsOne = new Javascript("main.js", "internal", cssDisplayed);
		Javascript jsTwo = new Javascript("bootstrap4.js", "external", cssDisplayed);
		Javascript jsThree = new Javascript("login.js", "internal", cssDisplayed);
		Javascript jsFour = new Javascript("yet-again.js", "internal", cssDisplayed);
		Javascript jsFive = new Javascript("charts.js", "external", cssDisplayed);
		jsList.add(jsOne);
		jsList.add(jsTwo);
		jsList.add(jsThree);
		jsList.add(jsFour);
		jsList.add(jsFive);
		loginJsList.add(jsThree);
		aboutJsList.add(jsTwo);
		aboutJsList.add(jsFour);
		contactJsList.add(jsTwo);
		contactJsList.add(jsOne);
		contactJsList.add(jsFour);

		/**
		 * Creating the Image ArrayList
		 */
		ArrayList<Image> images = new ArrayList<>();
		ArrayList<Image> aboutImages = new ArrayList<>();
		ArrayList<Image> contactImages = new ArrayList<>();
		ArrayList<Image> loginImages = new ArrayList<>();
		Image imageOne = new Image("logo.jpg", true, 128);
		Image imageTwo = new Image("puppies.png", true, 512);
		Image imageThree = new Image("apex-legends.jpg", false, 9001);
		Image imageFour = new Image("dinos.jpg", false, 9001);
		Image imageFive = new Image("olympus.jpg", true, 564);
		Image imageSix = new Image("bierGood.jpg", false, 1024);
		Image imageSeven = new Image("snowboarding.jpg", true, 16);
		Image theChosen = new Image("my_image.png", false, 999);
		images.add(imageOne);
		images.add(imageTwo);
		images.add(imageThree);
		images.add(imageFour);
		images.add(imageFive);
		aboutImages.add(imageSeven);
		aboutImages.add(imageSix);
		aboutImages.add(imageOne);
		contactImages.add(imageOne);
		contactImages.add(imageTwo);
		contactImages.add(imageThree);
		contactImages.add(imageSix);
		contactImages.add(theChosen);
		loginImages.add(imageOne);

		/**
		 * Creating the File Resource ArrayList
		 */
		ArrayList<FileResource> resources = new ArrayList<>();
		FileResource frOne = new FileResource("allFiles.zip",
				"/src/about", 3, "Archive");
		FileResource frTwo = new FileResource("welcomeVideo.mp4",
				"/src/home", 56, "Video");
		FileResource frThree = new FileResource("music.mp3",
				"/src/login", 6334, "Audio");
		resources.add(frOne);
		resources.add(frTwo);
		resources.add(frThree);

		/**
		 * Creating an ArrayList of Html objects
		 */
		ArrayList<Html> htmlList = new ArrayList<>();

		Html home = new Html("/src/home",
				notLinkedList, homeCssList, jsList, images, resources);
		Html about = new Html("/src/home",
				notLinkedList, homeCssList, aboutJsList, aboutImages, resources);
		about.setLocalPath("/src/about");
		Html contactUs = new Html("/src/home",
				notLinkedList, homeCssList, contactJsList, contactImages, resources);
		contactUs.setLocalPath("/src/contact-us");
		Html login = new Html("/src/home",
				notLinkedList, homeCssList, loginJsList, loginImages, resources);
		login.setLocalPath("/src/login");

		htmlList.add(home);
		htmlList.add(about);
		htmlList.add(contactUs);
		htmlList.add(login);

		ArrayList<String> urls = new ArrayList<>();
		urls.add("Home/");
		urls.add("About/");
		urls.add("login");
		urls.add("Contact-Us/");

		testSite = new Website("src/", urls, htmlList);
		testWriter = new JSONWriter(testSite);
		defaultWriter = new JSONWriter();
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Defauolt Constructor Test
	 *
	 * Verifying the jsonBuilder object is initialized to be empty
	 */
	@Test
	@DisplayName("<Json Writer> Default Constructor")
	void JsonWriterDefaultConstructor() {
		assertEquals(0, defaultWriter.getObjectToWrite().getHtmlList().size(),
				"Should have an empty JsonBuilder object but html list is not empty");
		assertEquals(0, defaultWriter.getObjectToWrite().getFileResourceList().size(),
				"Should have an empty JsonBuilder object but file resource list is not empty");
		assertEquals(0, defaultWriter.getObjectToWrite().getImageList().size(),
				"Should have an empty JsonBuilder object but image list is not empty");
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Parameterized Constructor Test
	 *
	 * Verifying that all members of 'objectToWrite' are initialized
	 * properly
	 */
	@Test
	@DisplayName(("<Json Writer> Parameterized Constructor"))
	void JsonWriterParameterizedConstructor() {
		assertEquals(4, testWriter.getObjectToWrite().getHtmlList().size(),
				"Should have 4 items in JsonBuilder object html list but returned " +
						testWriter.getObjectToWrite().getHtmlList().size());
		assertEquals(3, testWriter.getObjectToWrite().getFileResourceList().size(),
				"Should have 3 items in JsonBuilder object file resource list but returned " +
						testWriter.getObjectToWrite().getFileResourceList().size());
		assertEquals(8, testWriter.getObjectToWrite().getImageList().size(),
				"Should have 8 items in JsonBuilder object image list but returned " +
						testWriter.getObjectToWrite().getImageList().size());
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Testing that the website was accurately written to JSON format
	 * and the file was created
	 * TODO - Figure out how to test the file
	 */
	@Test
	@DisplayName("<JsonWriter> Write to Json")
	void JsonWriterWriteToJson() {
		testWriter.WriteToJson();
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Verify that we can properly set a new JsonBuilder object
	 */
	@Test
	@DisplayName("<JsonWriter> Set Object(JsonBuilder) to Write")
	void JsonWriterSetObjectToWrite() {
		defaultWriter.setObjectToWrite(testWriter.getObjectToWrite());
		assertEquals(4, defaultWriter.getObjectToWrite().getHtmlList().size(),
				"Should have 4 items in JsonBuilder object html list but returned " +
						defaultWriter.getObjectToWrite().getHtmlList().size());
		assertEquals(3, defaultWriter.getObjectToWrite().getFileResourceList().size(),
				"Should have 3 items in JsonBuilder object file resource list but returned " +
						defaultWriter.getObjectToWrite().getFileResourceList().size());
		assertEquals(8, defaultWriter.getObjectToWrite().getImageList().size(),
				"Should have 8 items in JsonBuilder object image list but returned " +
						defaultWriter.getObjectToWrite().getImageList().size());
	}

}
