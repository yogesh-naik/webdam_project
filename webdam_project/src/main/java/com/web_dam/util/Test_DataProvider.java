package com.web_dam.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.codehaus.jettison.*;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.testng.annotations.DataProvider;

public class Test_DataProvider {

	@DataProvider(name = "getItemScenario", parallel = false)
	public static Iterator<Object[]> getItemScenario() throws JSONException {
		Set<Object[]> testFilterSet = new HashSet<Object[]>();

		String line = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("src//api/tests/util/test_getItemScenario.json")));
			StringBuilder jsonString = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}

			JSONObject jsonObject = new JSONObject(jsonString.toString());

			JSONArray jsonArray = jsonObject.getJSONArray("data");

			for (int i = 0; i < jsonArray.length(); i++) {
				Object[] objects = new Object[5];
				objects[0] = (String) ((JSONObject) jsonArray.get(i)).get("ItemId");
				objects[1] = (String) ((JSONObject) jsonArray.get(i)).get("Site");
				objects[2] = (String) ((JSONObject) jsonArray.get(i)).get("Scenario");
				objects[3] = (String) ((JSONObject) jsonArray.get(i)).get("Variation");
				objects[4] = (String) ((JSONObject) jsonArray.get(i)).get("isContextualLocationOn");
				testFilterSet.add(objects);
				
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testFilterSet.iterator();
	}
}
