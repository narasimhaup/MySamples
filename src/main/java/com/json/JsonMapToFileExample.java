package com.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonMapToFileExample {
	public static void main(String[] args) {

		try {

			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "mkyong");
			map.put("age", 29);

			List<Object> list = new ArrayList();
			list.add("msg 1");
			list.add("msg 2");
			list.add("msg 3");

			map.put("messages", list);

			// write JSON to a file
			mapper.writeValue(new File("c:\\Users\\nupparap\\user.json"), map);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
