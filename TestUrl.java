import java.io.*;
import java.net.*;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TestUrl 
{
	int c; 
	
	public TestUrl() throws URISyntaxException
	{ 
		try
		{ 
			//A url object is created containing the URL of a site
			URL url = new URL("http://www.irishtimes.com/"); 
			String url1 = "http://www.irishtimes.com/sport/rugby/pro12/o-mahony-back-in-the-fold-for-munster-v-leinster-1.1950986";
			
			//a connection is made to the website
			URLConnection con = url.openConnection(); 
			
			//information about the docchanges can be accessed 
			//System.out.println("File name: " + url.getFile());
			//gets name of website
		//	System.out.println("1 : " + url.getAuthority());
		//	System.out.println("2 : " + url.getHost());
		//	System.out.println("3 : " + url.toExternalForm());
			
			//gets protocol details
		//	System.out.println("4 : " + url.getProtocol());
			HashSet<String> anchors = new HashSet<String>(); 
			
			Document doc = Jsoup.connect(url1).get();
			//add img to get images
			int count2 = 0;
			//select identifies the part of the tag to zero in on 
			Elements hrefs = doc.select("section"); 
			for(Element e : hrefs)
			{ 
				//hasAttribute does a quick check to identify the part of the 'section' to print
				if(e.hasAttr("property"))
				{
					//String anchor = e.attr("p").trim(); 
					//String anchor = e.attr("articleBody").trim(); 
					System.out.println(e);
				}
			//	System.out.println(count2++);
				//add 'title here to get tittle of phot if img is fed
				//src to get the source of the img
				String anchor = e.attr("articleBody").trim(); 
				//anchors.add(anchor); 
				//System.out.println(anchor); 
			//	System.out.println(e);
				
			}
			//variable for counting links
			int count = 0;
			//variable for eliminating advertising
			int secondCount = 0; 
			//ending in html - useless
			int thirdCount = 0;
			//contains "." in title, indicating an internal link to article
			int fourthCount = 0; 
	    	for(String s: anchors)
	    	{
	    		count++;
	    		
	    		url = new URL(s); 
	    		
	    		//clear descernable links
	    		//eliminate as they are all for advertising
	    		if(s.startsWith("http"))
	    		{
	    			//System.out.println(s);
	    			secondCount++;
	    		}
	    		else if(s.endsWith(".html"))
	    		{ 
	    			thirdCount++;
	    			System.out.println(s);	    			
	    		}
	    		else if(s.contains("."))
	    		{
	    			
	    			fourthCount++;
	    			System.out.println(s);
	    			//url1 = url1.concat(s);
	    			System.out.println(fourthCount);
	    			//create a new instance of URL and concatinate the destination to it to have complete links
	    			//String newURL = url1.concat(s);
	    			//System.out.println(newURL);
	    		}
	    	
	    		
	    	}
	    	
	    //	System.out.println(url1);
	    //	System.out.println("links :" + count);
	    //	System.out.println("advertising :" + secondCount);
	    //	System.out.println("ends in html :" + thirdCount);
	    //	System.out.println("contains . :" + fourthCount);
	    	
	    			
			//an input stream is opened and the contents of the html page are printed
			InputStream in = url.openStream(); 
			
			/*
			while((c = in.read())!= -1)
			{ 
				System.out.print((char) c); 
			}
			
			in.close();
			*/
		}
		catch(MalformedURLException me)
		{  
			System.out.println("error: " + me); 
		}
		catch(IOException e)
		{ 
			System.out.println("EroR : " + e); 
		}
	}
	
	
	public static void main(String args[]) throws URISyntaxException
	{
		 TestUrl tu = new TestUrl();
	}
	
}
  