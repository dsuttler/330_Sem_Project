package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
/**
 * These tests assume the ExampleWebsite folder is located directly in 
 * the project repository folder.
 */
class ParserTest {

	String emptpyOfflineTest;
	String singlePageOfflineTest;
	String afewPagesOffileTest;
	String CourseSiteOffileTest;	
	String pageWithImage;
	Parser p;
	File input;
	Document doc;
	
	@BeforeEach
    void setup() throws IOException {
    	emptpyOfflineTest= "/ExampleWebsite/0-empty";
    	singlePageOfflineTest= "./ExampleWebsite/1-singlePage/Summer 2021.html";
    	afewPagesOffileTest= "/ExampleWebsite/2-aFewPages";
    	CourseSiteOffileTest= "/ExampleWebsite/3-410-course-site";
    	pageWithImage = "./ExampleWebsite/3-410-course-site/Public/schedule/schedule__scroll.html";
    	
    	p = new Parser();
//    	input = new File("/home/justus/git/AvocadoPhase4/ExampleWebsite/1-singlePage/Summer 2021.html");
    	input = new File(singlePageOfflineTest);
    	doc = Jsoup.parse(input, "UTF-8" );
    }
    
    /**
     * this will be used to test parseWebsite
     */
    @Test
    @DisplayName("<Parser> parseWebsite")
    void parseWebsiteTest() throws IOException
    {
    	
    	String[] htmlDocs = new String[3];
    	htmlDocs[0] = "/a.html";
    	htmlDocs[1] = "/b.html";
    	htmlDocs[2] = "/c.html";
    	ArrayList<Html> testAllPages = new ArrayList<Html>();
    	ArrayList<String> testSiteURLs = new ArrayList<String>();
    	
    	Website w = new Website();
    	w.setLocalPath("/2-aFewPages");
    	w.setSiteURLs(testSiteURLs);
    	w.setAllPages(testAllPages);
    	
    	w = p.parseWebsite("./ExampleWebsite/2-aFewPages", htmlDocs);
    	
    	
    	assertEquals("./ExampleWebsite/2-aFewPages", w.getLocalPath());
    }

    /**
     * this will be used to test parseHtml
     * @throws IOException 
     */
    @Test
    @DisplayName("<Parser> parseHtml")
    void parseHtmlTest() throws IOException
    {
    
    	ArrayList<String> test = new ArrayList<String>();
    	Html h = new Html (p.parseHtml("./ExampleWebsite/1-singlePage/Summer 2021.html", "./ExampleWebsite/1-singlePage"));	
    	
    	assertEquals("/Summer 2021.html", h.getLocalPath());
    }
    
	/**
     * this will be used to test parseLink
     */
    @Test
    @DisplayName("<Parser> parseLink")
    void parseLinkTest() throws IOException
    {
  

//    	Elements links = doc.select("a");
//    	ArrayList<String> test = new ArrayList<String>();
//    	Link link = new Link();
//    	link = p.parseLink(links.get(0), "./ExampleWebsite/1-singlePage", "/Summer 2021.html", test);
//    	
//    	Link newLink = new Link("/https://www.google.com/calendar/selfsched?sstoken=UVA2dEh5VUkwakdJfGRlZmF1bHR8MWVjY2E1YjJkZDJiODk2ZTY4N2I4MDUwNDVjMjNlMTY", "External");
//    	
//    	assertEquals(newLink.getName(), link.getName());
//    	assertEquals(newLink.getClassification(), link.getClassification());
    	
    	input = new File("./ExampleWebsite/2-aFewPages/a.html");
    	doc = Jsoup.parse(input, "UTF-8");
    	
    	Elements links = doc.select("a");
    	ArrayList<String> test = new ArrayList<String>();
    	Link link = new Link();
    	link = p.parseLink(links.get(0), "./ExampleWebsite/2-aFewPages", "/a.html");
    	
    	Link newLink = new Link("/b.html", "Internal");
    	
    	assertEquals(newLink.getName(), link.getName());
    	assertEquals(newLink.getClassification(), link.getClassification());
    }

    /**
     * WORKS NOW
     * this will be used to test parseCss
     * @throws IOException 
     */
    @Test
    @DisplayName("<Parser> parseCss")
    void parseCssTest() throws IOException
    {

    	Elements css= doc.select("link");
    	Css c = new Css();
    	c= p.parseCss(css.get(0), "./ExampleWebsite/1-singlePage", "/Summer 2021.html");
    	ArrayList<String> test = new ArrayList<String>();
    	Css newCss = new Css("/Summer%202021_files/test-layout.css", "internal", test);
    	assertEquals(newCss.getName(), c.getName(), "c name= " + c.getName());
    }

    /**
     * WORKS NOW
     * this will be used to test parseJavascript
     */
    @Test
    @DisplayName("<Parser> parseJavascript")
    void parseJavascriptTest()
    {	
//    //still needs to be implemented
    	
    	Elements javascripts = doc.select("script");
    	ArrayList<String> jsList = new ArrayList<String>();
    	
//    	String test = javascripts.get(0).attr("abs:src");
//    	System.out.println(test);
    	Javascript js = new Javascript();
    	js= p.parseJavascript(javascripts.get(0), "./ExampleWebsite/1-singlePage", "/Summer 2021.html");
    	Javascript newJs = new Javascript("/Summer%202021_files/jquery.js", "internal", jsList);
    	assertEquals(newJs.getName(), js.getName());
    }

    /**
     * this will be used to test parseImage
     * @throws IOException 
     */
    @Test
    @DisplayName("<Parser> parseImage") 
    void parseImageTest() throws IOException
    {
    	input = new File("./ExampleWebsite/3-410-course-site/Public/schedule/schedule__scroll.html");
    	doc = Jsoup.parse(input, "UTF-8");
    	Elements images = doc.select("img");
    	
    	Image i = new Image();
    	i = p.parseImage(images.get(0), pageWithImage, "/schedule__scroll.html");
    	
    	Image newImage = new Image("../../graphics/home.png", false, 0);
    	File img = new File("../../graphics/home.png");
    	newImage.setSize((int)(img.length() / 1024));
    	
    	assertEquals(newImage.getName(), i.getName());
    	assertEquals(newImage.getInternal(), i.getInternal());
    	assertEquals(newImage.getSize(), i.getSize());
    }
    
    /**
     * this will be used to test parseFileResource
     */
    @Test
    @DisplayName("<Parser> parseFileResource")
    void parseFileResourceTest()
    {
    	Elements fileResources = doc.select("source");
    	FileResource newFr = new FileResource();
    	
    	FileResource fr = new FileResource();
    	
    	/**
    	 *  Implemented asserts this way because I couldn't find a file that had FileResource
    	 *  elements.  Can adjust this later.
    	 */
    	if(fileResources.size() > 0)
    	{
    		fr = p.parseFileResource(fileResources.get(0), "./ExampleWebsite/1-singlePage", "/Summer 2021.html");
    		assertEquals(newFr.getName(), fr.getName());
    	}
    	else
    		assertEquals("", fr.getName()); 	
    }
    
    @Test
    @DisplayName("<Parser> SearchArrayListStrings")
    void searchArrayListStringsTest()
    {
    	ArrayList<String> webpages= new ArrayList<String>() {
    		{
    			add("test.html");
    			add("anotherTest.html");
    		}
    	};
    	
    	boolean isTrue = p.SearchArrayListStrings("anotherTest.html", webpages);
    	boolean isFalse = p.SearchArrayListStrings("random.html", webpages);
    	
    	assertEquals(true, isTrue);
    	assertEquals(false, isFalse);
    }

    @Test
    @DisplayName("<Parser> ReturnAbsolutePath")
    void returnAbsolutePathTest()
    {
    	Elements javascripts = doc.select("script");
    	Elements css = doc.select("link");
    			
    	String jsAttr = javascripts.get(0).attr("src");
    	String cssAttr = css.get(0).attr("href");
    	String localDir = "./ExampleWebsite/1-singlePage";
    	String html = "/Summer 2021.html";
    	
    	assertEquals("./ExampleWebsite/1-singlePage/Summer%202021_files/jquery.js", p.ReturnAbsolutePath(jsAttr, localDir, html));
    	assertEquals("./ExampleWebsite/1-singlePage/Summer%202021_files/test-layout.css", p.ReturnAbsolutePath(cssAttr, localDir, html));
    }
    
    @Test
    @DisplayName("<Parser> RemovePath")	
    void removePathTest()
    {
    	String testHtml = "Test1/test2/test3.html";
    	
    	assertEquals("Test1/test2", p.RemovePath(testHtml));
    }
    
}