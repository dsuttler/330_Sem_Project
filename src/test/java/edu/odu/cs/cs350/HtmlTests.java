package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

class HtmlTests {
	/**
	 *  Objects used in tests
	 */
	Html defaultHtml;
	Html nonDefaultHtml;
	Html copyHtml;
	Link link;
	Css css;
	Javascript js;
	Image image;
	FileResource fr;
	
	@BeforeEach
	void setup() {
	/**
	 * Tests ArrayLists
	 */
	ArrayList<Link> linkList = new ArrayList<Link>() {
		{
			add(link);
		}
	};
	
	ArrayList<Css> cssList = new ArrayList<Css>() {
		{
			add(css);
		}
	};
	
	ArrayList<Javascript> jsList = new ArrayList<Javascript>() {
		{
			add(js);
		}
	};
	
	ArrayList<Image> imageList = new ArrayList<Image>() {
		{
			add(image);
		}
	};
	
	ArrayList<FileResource> frList = new ArrayList<FileResource>() {
		{
			add(fr);
		}
	};
	
	defaultHtml = new Html();
	nonDefaultHtml = new Html("./a/path/to/file.html", linkList, cssList, jsList, imageList,frList);
	copyHtml = new Html(nonDefaultHtml);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Html> Default Constructor")
	public void HtmlDefaultConstructor()
	{
		/**
		 *  Verify path is an empty string
		 */
		assertEquals("", defaultHtml.getLocalPath());
		
		/**
		 *  Verify ArrayLists are empty (size == 0)
		 */
		assertEquals(0, defaultHtml.getLinks().size());
		assertEquals(0, defaultHtml.getStyleSheets().size());
		assertEquals(0, defaultHtml.getJavascripts().size());
		assertEquals(0, defaultHtml.getImages().size());
		assertEquals(0, defaultHtml.getFileResouces().size());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Html> NonDefault Constructor")
	public void HtmlNonDefaultConstructor()
	{
		/**
		 *  Verify path is initialized
		 */
		assertEquals("./a/path/to/file.html", nonDefaultHtml.getLocalPath());
		
		/**
		 * Verify ArrayLists are initialized
		 */
		assertEquals(link, nonDefaultHtml.getLinks().get(0));
		assertEquals(css, nonDefaultHtml.getStyleSheets().get(0));
		assertEquals(js, nonDefaultHtml.getJavascripts().get(0));
		assertEquals(image, nonDefaultHtml.getImages().get(0));
		assertEquals(fr, nonDefaultHtml.getFileResouces().get(0));
		

	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Html> Copy Constructor")
	public void HtmlCopyConstructor()
	{
		/**
		 *   Verify new Html object has the same localPath name
		 */
		assertEquals(nonDefaultHtml.getLocalPath(), copyHtml.getLocalPath());
		
		
		/**
		 *   Verify ArrayLists contents successfully copied
		 */
		assertEquals(nonDefaultHtml.getLinks(), copyHtml.getLinks());
		assertEquals(nonDefaultHtml.getStyleSheets(), copyHtml.getStyleSheets());
		assertEquals(nonDefaultHtml.getJavascripts(), copyHtml.getJavascripts());
		assertEquals(nonDefaultHtml.getImages(), copyHtml.getImages());
		assertEquals(nonDefaultHtml.getFileResouces(), copyHtml.getFileResouces());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Html> addStyleSheet")
	public void HtmlAddStyleSheet()
	{
		/**
		 *   Setup Html and Css objects
		 */
		Html newHtml = new Html();
		ArrayList<String> pages = new ArrayList<>();
		Css sSHeet = new Css("./styles.css", "internal", pages);
		
		newHtml.addStyleSheet(sSHeet);
		
		/**
		 * Verify newHtml object contains added Css object (stylesheet)
		 */
		assertEquals("./styles.css", newHtml.getStyleSheets().get(0).getName());
		assertEquals("internal", newHtml.getStyleSheets().get(0).getClassification());
		assertEquals(pages, newHtml.getStyleSheets().get(0).getPages());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Html> addJavaScript")
	public void HtmlAddJavaScript()
	{
		/**
		 *   Setup Html and Javascript objects
		 */
		Html newHtml = new Html();
		ArrayList<String> pages = new ArrayList<>();
		Javascript jScript = new Javascript("./script.js", "external", pages);
		
		newHtml.addJavaScript(jScript);
		
		/**
		 *   Verify newHtml object contains added Javascript object (script)
		 */
		assertEquals("./script.js", newHtml.getJavascripts().get(0).getName());
		assertEquals("external", newHtml.getJavascripts().get(0).getClassification());
		assertEquals(pages, newHtml.getJavascripts().get(0).getPages());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Html> addImage")
	public void HtmlAddImage()
	{
		/**
		 *  TODO - will implement when Image class is fixed
		 */
	
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Html> Set Images")
	void HtmlSetImages() {
		Html testHtml = new Html();
		ArrayList<Image> images = new ArrayList<>();
		Image one = new Image();
		Image two = new Image();
		one.setName("One");
		two.setName("Two");
		images.add(one);
		images.add(two);
		testHtml.setImages(images);

		assertEquals(2, testHtml.getImages().size(),
				"Should have 2 items in the list of images but returned " +
						testHtml.getImages().size());
		assertEquals("One", testHtml.getImages().get(0).getName(),
				"First element should be 'One' but returned " +
						testHtml.getImages().get(0).getName());
		assertEquals("Two", testHtml.getImages().get(1).getName(),
				"Last element should be 'Two' but returned " +
						testHtml.getImages().get(1).getName());

	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Html> Set Links")
	void HtmlAddLinks() {
		Html testHtml = new Html();
		ArrayList<Link> triForceList = new ArrayList<>();
		Link one = new Link();
		Link two = new Link();
		one.setName("One");
		two.setName("Two");
		triForceList.add(one);
		triForceList.add(two);

		testHtml.setLinks(triForceList);

		assertEquals(2, testHtml.getLinks().size(),
		"Should have 2 items in the list of links but returned " +
				testHtml.getLinks().size());
		assertEquals("One", testHtml.getLinks().get(0).getName(),
				"First element should be 'One' but returned " +
						testHtml.getLinks().get(0).getName());
		assertEquals("Two", testHtml.getLinks().get(1).getName(),
				"Last element should be 'Two' but returned " +
						testHtml.getLinks().get(1).getName());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Html> Set Stylesheets")
	void HtmlSetStylesheets() {
		Html testHtml = new Html();
		ArrayList<Css> testingList = new ArrayList<>();
		Css one = new Css();
		Css two = new Css();
		one.setName("One");
		two.setName("Two");
		testingList.add(one);
		testingList.add(two);
		testHtml.setStyleSheets(testingList);

		assertEquals(2, testHtml.getStyleSheets().size(),
				"Should have 2 items in the list of stylesheets but returned " +
						testHtml.getStyleSheets().size());
		assertEquals("One", testHtml.getStyleSheets().get(0).getName(),
				"First element should be 'One' but returned " +
						testHtml.getStyleSheets().get(0).getName());
		assertEquals("Two", testHtml.getStyleSheets().get(1).getName(),
				"Last element should be 'Two' but returned " +
						testHtml.getStyleSheets().get(1).getName());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Html> Set JavaScript")
	void HtmlSetJavascript() {
		Html testHtml = new Html();
		ArrayList<Javascript> scripts = new ArrayList<>();
		Javascript one = new Javascript();
		Javascript two = new Javascript();
		one.setName("One");
		two.setName("Two");
		scripts.add(one);
		scripts.add(two);
		testHtml.setJavaScripts(scripts);

		assertEquals(2, testHtml.getJavascripts().size(),
				"Should have 2 items in the list of scripts but returned " +
						testHtml.getJavascripts().size());
		assertEquals("One", testHtml.getJavascripts().get(0).getName(),
				"First element should be 'One' but returned " +
						testHtml.getJavascripts().get(0).getName());
		assertEquals("Two", testHtml.getJavascripts().get(1).getName(),
				"Last element should be 'Two' but returned " +
						testHtml.getJavascripts().get(1).getName());

	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Html> Set File Resources")
	void HtmlSetFileResources() {
		Html testHtml = new Html();
		ArrayList<FileResource> fr = new ArrayList<>();
		FileResource one = new FileResource();
		FileResource two = new FileResource();
		one.setName("One");
		two.setName("Two");
		fr.add(one);
		fr.add(two);
		testHtml.setFileResouces(fr);

		assertEquals(2, testHtml.getFileResouces().size(),
				"Should have 2 items in the list of file resources but returned " +
						testHtml.getFileResouces().size());
		assertEquals("One", testHtml.getFileResouces().get(0).getName(),
				"First element should be 'One' but returned " +
						testHtml.getFileResouces().get(0).getName());
		assertEquals("Two", testHtml.getFileResouces().get(1).getName(),
				"Last element should be 'Two' but returned " +
						testHtml.getFileResouces().get(1).getName());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Html> addFileResource")
	public void HtmlAddFileResource()
	{
		/**
		 *  Setup Html and FileResource objects
		 */
		
		Html newHtml = new Html();
		FileResource fr = new FileResource("test", "./a/path/to/file.html", 10, "external");
		
		newHtml.addFileResource(fr);
		
		/**
		 *  Verify newHtml object contains added FileResource object
		 */
		assertEquals("test", newHtml.getFileResouces().get(0).getName());
		assertEquals("./a/path/to/file.html", newHtml.getFileResouces().get(0).getLocalPath());
		assertEquals(10, newHtml.getFileResouces().get(0).getSize());
		assertEquals("external", newHtml.getFileResouces().get(0).getCategory());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Html> clone")
	public void HtmlClone()
	{
		/**
		 *  Clone nonDefaultHtml object
		 */
		Html newHtml = nonDefaultHtml.clone();
		
		assertEquals(nonDefaultHtml.getLocalPath(), newHtml.getLocalPath());
		assertEquals(nonDefaultHtml.getLinks(), newHtml.getLinks());
		assertEquals(nonDefaultHtml.getStyleSheets(), newHtml.getStyleSheets());
		assertEquals(nonDefaultHtml.getJavascripts(), newHtml.getJavascripts());
		assertEquals(nonDefaultHtml.getImages(), newHtml.getImages());
		assertEquals(nonDefaultHtml.getFileResouces(), newHtml.getFileResouces());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Html> hashCode")
	public void HtmlHashCode()
	{
		/**
		 *   Create two Html objects and set the same localPath for both
		 */
		Html html1 = new Html();
		Html html2 = new Html();
		
		html1.setLocalPath("./a/path/to/file.html");
		html2.setLocalPath("./a/path/to/file.html");
		
		/**
		 *  Both objects with the same localPath should have equal hashcodes
		 */
		assertEquals(html1.hashCode(), html2.hashCode());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Html> toString")
	public void HtmlToString()
	{
		String expected = "path: ./a/path/to/file.html";
		
		assertEquals(expected, nonDefaultHtml.toString());
	}
}
