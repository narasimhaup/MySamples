import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringEscapeUtils;

public class CharcterEncoding {

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String s = "एक गाव में एक किसान";
		String out = new String(s.getBytes("UTF-8"), "ISO-8859-1");

		  String dec = "The session aims to de-mystify the changes that occurred in the IOx "
			  		+ "architecture in its evolution to a containerised system from pure PaaS, "
			  		+ "it will introduce the various types of container and the different  capabilities of each"
			  		+ "ie simple PaaS or modified kernel with LXC Docker IOx supports a"
			  		+ "ie simple PaaS or modified kernel with LXC /Docker).IOx supports a variety of 'languages' namely Python Java and 'C' -"
			  		+ " the session will contain details on the status of the language pack support and which interfaces are supported "
			  		+ "from the containerised architecture. The supported IoX platforms and their capabilities will be highlighted  "
			  		+ "and current limitations will be called out - for example USB interface support for containers. "
			  		+ "In addition you will be shown where to go for help resources and proof of concepts and advice on "
			  		+ "how your partners can work with DevNet for IOx support.";
		  //dec = "drive Ciscoâ€™s response";
		  //System.out.println("ENCODEC DECS:: " + dec);
		  //dec = "VCE™ TECHNOLOGY EXTENSION FOR EMC® ISILON® STORAGE";
		  dec = "AIâ€™s â€“ ESO Team ";
		  dec = "Ciscoâ€™s";
		  System.out.println("ORIGINAL DECS:: " + dec);
			  System.out.println("ESCAPED DECS:: " + StringEscapeUtils.escapeHtml3(dec));
			  System.out.println("ENCODEC DECS:: " + StringEscapeUtils.escapeJson(dec));
			  System.out.println("ENCODEC DECS:: " + StringEscapeUtils.escapeJava(dec));
			
			  //  System.out.println("Decoded DECS:: " + decode(dec));
			  System.out.println(charEmcode());

	}
	private static String decode(String val)
	{
		// To clean out %2F and replace with / for SFDC - WEM requires it to %2F
		// To clean out %2C and replace with , for SFDC - WEM requires it to %2C
		if( val.contains("%2F") || val.contains("%2C") ) {
			try {
				val = URLDecoder.decode(val, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return val;
		
	}
	
	static String charEmcode(){
		
		CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
		  String dec = "different  capabilities of";
		  System.out.println(StringEscapeUtils.escapeHtml3(dec));
		  System.out.println(StringEscapeUtils.escapeJava(dec));
		  System.out.println(StringEscapeUtils.escapeJson(dec));
		String text = dec;
		CharBuffer cb = CharBuffer.wrap(text.toCharArray());
		ByteBuffer bb = null;
		try {
			bb = encoder.encode(cb);
		} catch (CharacterCodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bb.remaining());
		System.out.println(bb.array().length);
		return new String(bb.array());
	}
	public static String encode(String str){
		String value="";
		try {
			//log.info("BEFORE >>> DESCRIPTION:" + str);
			value = new String(str.getBytes("UTF-8"));
			//log.info("AFTER >>> DESCRIPTION:" + value);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return value;
	}	
}
