import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class htmlParser 
{
	
	public static void main(String arg[]) throws IOException
	{
		int c; 
		
		URL url = new URL("http://www.irishtimes.com/sport/rugby/pro12/o-mahony-back-in-the-fold-for-munster-v-leinster-1.1950986");
		String url1 =("http://www.irishtimes.com/sport/rugby/pro12/o-mahony-back-in-the-fold-for-munster-v-leinster-1.1950986");
		URLConnection con = url.openConnection(); 
		InputStream in = url.openStream(); 
		System.out.println(download(url1));
		 
	}	
		static String download(String urlToRead) {
		    java.net.CookieManager cm = new java.net.CookieManager();
		    java.net.CookieHandler.setDefault(cm);
		    String result = "";
		    try {
		        URL url = new URL(urlToRead);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Cookie", "onlineCampusSelection=C");

		        BufferedReader rd = new BufferedReader(new InputStreamReader(
		                conn.getInputStream()));
		        String line;
		        while ((line = rd.readLine()) != null) {
		            result += line + "\n";
		        }
		        rd.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return result;
		
		 /*
		while((c = in.read())!= -1)
		{ 
			System.out.print((char) c); 
		}
		
		in.close();
		*/
	
	
		}}
