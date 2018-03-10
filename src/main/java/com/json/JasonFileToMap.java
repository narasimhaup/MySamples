package com.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JasonFileToMap {
	public static void main(String[] args) {

		try {

			ObjectMapper mapper = new ObjectMapper();

			// read JSON from a file
			Map<String, Object> map = mapper.readValue(
					new File("c:\\Users\\nupparap\\user.json"),
					new TypeReference<Map<String, Object>>() {
			});

			System.out.println(map.get("name"));
			System.out.println(map.get("age"));

			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) map.get("messages");

			for (String msg : list) {
				System.out.println(msg);
			}

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
