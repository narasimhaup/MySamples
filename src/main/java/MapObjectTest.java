import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapObjectTest {

	public static void main(String[] args) {
		
		Map<String, Object> fieldsMap = new HashMap();
		

		fieldsMap.put("offeringCategory", new JSONArray());
		fieldsMap.put("offering", new TreeSet(Arrays.asList("hello")));
		System.out.println(fieldsMap);
		fieldsMap = removeEmptyField(fieldsMap);

		System.out.println(fieldsMap);
		
	}

    private static Map<String, Object> removeEmptyField(Map<String, Object> fieldsMap) {
		
    	Map<String, Object> newMap = new HashMap();
    	String key = null;
    	Object val = null;
    	for (Map.Entry<String, Object> entry : fieldsMap.entrySet()) {
    	    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    	    key = entry.getKey();
    	    val = entry.getValue();
    	    if(val instanceof Set){
    	    	if(((Set) val).isEmpty())
    	    		System.out.println("EMPTY");
    	    	else {
    	    		System.out.println("ADD TO MAP");
    	    		newMap.put(key, val);
    	    	}
    	    } else if (val instanceof JSONArray){
    	    	if(((JSONArray) val).length() == 0)
    	    		System.out.println("EMPTY");
    	    	else {
    	    		System.out.println("ADD TO MAP");
    	    		newMap.put(key, val);
    	    		
    	    	}
    	    		
    	    } else if(val instanceof JSONObject){
    	    	
    	    } else if(val instanceof JSONObject){
    	    	
    	    }
    	   
    	}
    	return newMap;	
	}
     
}
