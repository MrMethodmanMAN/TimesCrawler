import java.security.MessageDigest;


public class Hasher
{
	public static String toSha256(String inString)
	{ 
		try
		{ 
			MessageDigest md = MessageDigest.getInstance("SHA-256"); 
			String text = inString.toLowerCase(); 
			md.update(text.getBytes("ASCII"));//change this to "UTF-16" if required
			byte[] hash = md.digest();
			
			StringBuilder sb = new StringBuilder(); 
			for(byte b : hash)
			{
				sb.append(String.format("%02x", b)); 
			}
			return sb.toString().toUpperCase();
		}
		catch (Exception e)
		{ 
			return e.toString();
		}
	}
}
