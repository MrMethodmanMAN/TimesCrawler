import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.logging.Level;











import javax.swing.text.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup; 
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.helper.Validate;
 
/**
 * 
 * 1. createUrlList(String url1):
 * Produces hashset of the Domain Urls from which to crawl
 * 
 * 2. extractString(HashSet<String> anchors, String url)
 * Processes hashset of urls to plain text
 *
 */
public class Crawler
{
	    public static void main(String[] args) throws IOException 
	    {	
	    	//hashset for holding the links to follow
	    	HashSet<String> anchors = new HashSet<String>(); 
	    	
	    	//singular string variable for holding individual urls
	    	String result = "";
	    	
	    	String url = "http://www.irishtimes.com";	    	    	
	    	anchors = createUrlList(url);
	    	
	    	extractString(anchors, url);
	    	
	    }
	    	    
	    public static HashSet<String> createUrlList(String url1) throws IOException
	    {
	    	//provide url you want to start the crawl from 
	    	String url = url1;
	    	
	    	//open a connnection to the provided URl
	    	//document contains sum of page.
	    	Document doc = Jsoup.connect(url).get(); 
	    	
	    	//hashset for holding the links to follow
	    	HashSet<String> anchors = new HashSet<String>();  
	    	
	    	//separate out the links through anchors ("a")
	    	Elements hrefs = doc.select("a");
	    	//a loop that runs through the entire selection of links
	    	
	    	/*
	    	 * first loop adds all links to the hashset
	    	 * second loop removes the absolute urls(they are 99% adds or internal links
	    	 */
	    	
	    	int capturedUrls = 0;
	    	int completeAnchors = 0; 
	    	int countAnchors = 0; 
	    	int nonNewsUrlsCount = 0;
	    	for (Element e : hrefs)
	    	{ 
	    		if(e.hasAttr("href"))
	    		{
	    			//count variable to determine number of anchors
	    			countAnchors++;
					String anchor = e.attr("href").trim();
					anchors.add(anchor);
					if(anchor.startsWith("http"))
					{
						anchors.remove(anchor);
						completeAnchors++;
						//System.out.println(anchor);
					}	
					//remove obvious ads missed by more general sweep
					if(anchor.contains("adserver"))
					{
						anchors.remove(anchor);
					}
					if(!anchor.contains("."))
					{
						nonNewsUrlsCount++;
						anchors.remove(anchor);
					}
	    		}	    		   		
	    	}
	    	
	    	/*
	    	//print method for HashSet
	    	for (String s : anchors) 
	    	{
	    		capturedUrls++;
	    	   // System.out.println(s);
	    	}
	    	*/
	    	
	    	return anchors;    	
	    }
	    
	    static void extractString(HashSet<String> anchors, String url) throws IOException
    	{
	    	String result = "";
	    	
	    	//quick and dirty link for extracting individual links
	    	//title of the article can be extracted by printing S
	    	int i = 0;
	    	for(String s : anchors)
	    	{
	    		if(i < 1)
	    		{
	    			result = url + s;	
	    			System.out.println(s);
	    			i++;    			
	    		}   		
	    	}	
	    	
    		Document doc = Jsoup.connect(result).get();
	    	
	    	Elements hrefs = doc.select("section"); 
	    	for(Element e : hrefs)
	    	{
	    		if(e.hasAttr("property"))
	    		{	    			
	    			String anchor = e.text(); 
	    			System.out.println(anchor);
	    		}
	    	}
    	}
}
		
