
public class HtmlTools
{
	public static String fixUrl(String inUrl, Domain domain)
	{
		String url = ""; 
		
		///url does not begin with domain
		if(!inUrl.startsWith(domain.getDomainUrl()))
		{ 
			//if does not start with http - fix
			if(!inUrl.startsWith("http"))
			{ 
				if(inUrl.startsWith("/"))
				{
					url = domain.getDomainUrl().concat(inUrl);
				}
				else
				{
					url = domain.getDomainUrl().concat("/" + inUrl);
				}
			}
			else
			{
				//probably an external URL leave for now
				url = inUrl;
			}
		}
		
		if(inUrl.endsWith("/"))
		{
			url = url.substring(0, url.length());
		}
		
		//remove bookmarks
		if(url.contains("#"))
		{
			url = url.substring(0, url.indexOf("#"));
		}
		return url; 
	}
}
