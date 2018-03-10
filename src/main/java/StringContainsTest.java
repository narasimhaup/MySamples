
public class StringContainsTest {

	public static void main(String[] args) {
		String str = "http://cisco.com/myfile.mov.zip";
		String fileExt = "";
		String lblockedFileformats = ".zip,.swf,.flv,.txt,.rtf,.arf,.mov";
		if(str.lastIndexOf(".") > 0 )
			fileExt = str.substring(str.lastIndexOf("."));
		if(lblockedFileformats.contains(fileExt))
			System.out.println(" Its NOT ALLOWED");
		String jsonString = "Select Partner (Choose this option if you are publishing competitive content that should not be accessible by Cisco competitors)";
		String st = "Partner (Choose this option if you are publishing content that should be accessible by all partners)";
		String s1 = "";
		String cellVal = "Select Partner";
		if(jsonString != null && jsonString.indexOf("(") > 0)
			jsonString = jsonString.substring(0,jsonString.indexOf("(")).trim();

		System.out.println(jsonString);
		System.out.println(cellVal.equals(jsonString));

	}

}
