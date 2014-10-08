import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
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
	
	/*
	 * Method separates out "href anchors" and returns a hash set of all articles
	 */
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

			
				result = url + s;
				//System.out.println("title" + s);  
				//System.out.println("url" + url); 
				//System.out.println(title);
				//method commented out calls the print method +++ print to file
				printHtml(result, s);
			
		}	
	}

	/*
	 * method accepts complete URL and returns content
	 */
	static void printHtml(String url, String title) throws IOException
	{
		//System.out.println("title :" + title);
		
		Document doc = Jsoup.connect(url).get();
		 title = EditTitle(url);
		 //System.out.println(title);
		 
		Elements hrefs = doc.select("section"); 
		for(Element e : hrefs)
		{
			if(e.hasAttr("property"))
			{	    			
				String anchor = e.text(); 
				//System.out.println(anchor);
				
				//method commented out saves the content as text and title as title for doc
				//saveToFile(title, anchor);
			}
		}
		
	}

	/*
	 * Method saves string to file with title as filename and html processed text as content
	 */
	static void saveToFile(String title, String  text) throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter("articles\\" + title + ".txt");
		out.println(text);
		out.close();	
	}


	static String EditTitle(String title)
	{
		StringTokenizer st = new StringTokenizer(title, "/"); 
		int count = st.countTokens();
		// String Str = new String(title);
		String str = title;
		String delim = "/";

		//title = Str.replace("-", " ");
		//System.out.println(title);
		StringTokenizer tok = new StringTokenizer(str, delim, true);
		String[] result = new String[tok.countTokens()];
		String[] titleArray = new String[tok.countTokens()];
		boolean expectDelim = false;
		while (tok.hasMoreTokens()) 
		{

			String token = tok.nextToken();
			//System.out.println(token);
			int i = 0;  
			if (delim.equals(token))
			{
				if (expectDelim) 
				{
					expectDelim = false;
					result[i] = token;

					continue;
				} else 
				{
					// unexpected delim means empty token
					token = null;
				}
			}

			titleArray[i] =  token;
			expectDelim = true;
			i++;

		}
		String ftitle = "";
		for (int x=0; x<titleArray.length; x++)
			if(titleArray[x] != null)
			{
				ftitle = titleArray[x];
				//System.out.println(ftitle);
			}

		delim = "-";	
		tok = new StringTokenizer(ftitle, delim);
		int x  = tok.countTokens()-1;
		String Finalt = "";
		while(tok.hasMoreElements())
		{
			String token = tok.nextToken();
			if(tok.countTokens() >=1)
			{
				Finalt = Finalt + " " +  token;
			}			
		}
		return(Finalt);
	}
	
	
}


