package com.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		String mdsValue = "{\"targetRegion\": [\"APJ\",\"APJC\",\"Americas\",\"EMEAR\",\"GC\"],\"aName\": \"targetRegion\",\"cdcBrowse\": \"N\",\"mandatory\": \"CDT\",\"multiple\": \"CDT\",\"nodeName\": \"targetRegion\",\"searchable\": \"Y\",\"source\": \"MDS\",\"type\": \"structuredHead\",\"status\": \"success\",\"message\": \"ok\"}";

		String mdsValue1 = "{\"targetTheatre\": {\"APJ\": [\"ANZ\",\"As/ia\",\"India\",\"Japan\"],\"APJC\": [\"ANZ\",\"Asia\",\"Greater China\",\"India\",\"Japan\"],\"GC\": [\"Greater China\"],\"Americas\": [\"United States\",\"Canada\",\"LATAM\"],\"EMEAR\": [\"Central\",\"North\",\"South\",\"UK & Ireland\",\"Emerging\"]},\"aName\": \"targetTheatre\",\"cdcBrowse\": \"N\",\"isFilterSet\": \"Y\",\"mandatory\": \"CDT\",\"multiple\": \"CDT\",\"nodeName\": \"targetTheatre\",\"searchable\": \"Y\",\"source\": \"MDS\",\"type\": \"structuredHead\",\"status\": \"success\",\"message\": \"ok\"}";
		
		String json = "{\"contentCategory\": [\"Collateral\",\"Demo\",\"Training\",\"Proposal\"],\"aName\": \"contentCategory\",\"cdcBrowse\": \"N\",\"cdcR1Func\": \"Y\",\"mandatory\": \"CDT\",\"nodeName\": \"contentCategory\",\"searchable\": \"N\",\"source\": \"MDS\",\"type\": \"structuredHead\",\"status\": \"success\",\"message\": \"ok\"}";
	
		System.out.println(alterJsonText(json, null));
		//System.out.println(JsonTest.validateMetadata("targetRegion", "APJ", null, mdsValue));
		//System.out.println(JsonTest.validateMetadata("targetTheatre", "As/ia", "APJ", mdsValue1));
	}

	public static String alterJsonText(String jsonData, String pTagName) {
		JSONObject obj = new JSONObject(jsonData);
		JSONArray jsonArray = new JSONObject(jsonData).getJSONArray("contentCategory");
		JSONArray newJa = new JSONArray();
		//jsonArray.put(value)
		if (jsonArray != null && jsonArray.length() > 0) {
			for (int i = 0; i < jsonArray.length(); i++) {
				System.out.println(jsonArray.optString(i));
				newJa.put(jsonArray.optString(i));
			}
		}
		newJa.put("Marketing");
		newJa.put("Sales");
		newJa.put("SC Ops");
		newJa.put("WWPO (WorldWide Partner Organization)");
		newJa.put("Business Unit");
		newJa.put("Other");
		 
		 obj.put("contentCategory", newJa);
		 System.out.println(newJa);
		 return obj.toString();
	}
	
	/**
	 * Generic Method to validate given value with specifiec MDS tagname dataset
	 * values
	 * 
	 * @param pTagName
	 * @param pValue
	 * @return
	 */
	public static boolean validateMetadata(String pTagName, String pValue, String pJsonKeyValue, String mdsValue) {
		// String mdsValue = mdsIntegrator.getProperty(pTagName);
		System.out.println("TagName[" + pTagName + "] Values: " + mdsValue);
		JSONObject jsonObject;
		boolean isValid = false;
		try {
			jsonObject = new JSONObject(mdsValue);
			if (jsonObject.has(pTagName)) {
				Object json = jsonObject.get(pTagName);

				if (json instanceof JSONObject) {

					System.out.println("ITs JSON Object");
					if (pJsonKeyValue != null) {
						JSONObject json1 = (JSONObject) jsonObject.get(pTagName);
						JSONArray jsonArray = json1.getJSONArray(pJsonKeyValue);

						if (jsonArray != null && jsonArray.length() > 0) {
							for (int i = 0; i < jsonArray.length(); i++) {
								System.out.println(jsonArray.optString(i));
								if (pValue.equalsIgnoreCase(jsonArray.optString(i))) {
									System.out.println("Value MATCH");
									isValid = true;
									break;
								} else
									System.out.println("Value NOT MATCH");
							}
						}
					}

				} else if (json instanceof JSONArray) {
					System.out.println("ITs JSON Array");
					JSONArray jsonArray = jsonObject.getJSONArray(pTagName);

					if (jsonArray != null && jsonArray.length() > 0) {
						for (int i = 0; i < jsonArray.length(); i++) {
							System.out.println(jsonArray.optString(i));
							if (pValue.equalsIgnoreCase(jsonArray.optString(i))) {
								System.out.println("Value MATCH");
								isValid = true;
								break;
							} else
								System.out.println("Value NOT MATCH");

						}
					}

				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isValid;
	}

}
