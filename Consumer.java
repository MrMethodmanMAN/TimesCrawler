import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Consumer
{
	public void Start() throws IOException
	{
		crawl();
	}

	
	private void crawl() throws IOException
	{
		String crawlUrl = this.getCrawlerUrl(); 
		
		HashSet<String> anchors = new HashSet<String>(); 
		
		
		Document doc = Jsoup.connect(crawlUrl).get(); 	    	
    	Elements hrefs = doc.select("a");	    		
    	for(Element e : hrefs)
    	{
    		String anchor = e.attr("href").trim();
    		anchors.add(anchor);
    		System.out.println(anchor);
    	}	
    	System.out.println("---------------");
    	
    	for(String s: anchors)
    	{
    		System.out.println(s);
    	}
    	
	}
	
	
	
	private String getCrawlerUrl()
	{
		String url = "http://danu2.it.nuigalway.ie/steviebsgaf/html/"; 
		return url;
	}
}
