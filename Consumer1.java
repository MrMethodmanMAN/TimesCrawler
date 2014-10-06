import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;

import javax.lang.model.util.Elements;





import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Consumer1 
{
	public void Start()
	{ 
		String crawlDomains = "http://jsoup.org/";
		Domain domain = new Domain(Hasher.toSha256(crawlDomains), crawlDomains);
		DomainUrl  domainUrl = new DomainUrl(domain.getDomainHash(), domain.getDomainUrl(), domain);
		HashSet<String> hrefs = new HashSet<String>(); 
		
		try
		{
			Document doc = Jsoup.connect(domainUrl.getDomainUrl()).get();
			
		//	HtmlPage page = new HtmlPage(doc.html(), domainUrl, new Date());
			
			
		
			//get all anchors
			org.jsoup.select.Elements anchors = doc.select("a"); 
			for(Element anchor : anchors)
			{
				String href = anchor.attr("href"); 
				href = HtmlTools.fixUrl(href, domain);
				hrefs.add(href);
			}
			
		} 
		catch(IOException ex)
		{
			System.out.println("pokemon, got to catch them all!");
		}
		
		for(String href :hrefs)
		{
			System.out.println(href);
		}
		
	}
}
