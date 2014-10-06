import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.swing.text.DefaultFormatter;


public class CompressionUtils
{
	public byte[] Compress(String inputString) throws Exception
	{ 
		//encode a String into bytes
		byte[] input = inputString.getBytes("UTF-8");
		//compress the bytes
		byte[] output = inputString.getBytes(); 
		
		Deflater compresser = new Deflater(); 
		compresser.setInput(input); 
		compresser.finish();  
		int compressedDataLength = compresser.deflate(output); 
				
		return output; 
	}
	
	public String Decompresser(byte[] inBytes) throws Exception
	{
		
		Inflater decompresser = new Inflater(); 
		decompresser.setInput(inBytes, 0, inBytes.length);
		byte[] result = new byte[inBytes.length];
		int resultLength = decompresser.inflate(result);
		
		//decode the bytes into a String
		return new String(result, 0, resultLength, "UTF-8");
		
	}
}
