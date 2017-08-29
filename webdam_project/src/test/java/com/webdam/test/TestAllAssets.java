package com.webdam.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.web_dam.util.webdamCalls;
import com.webdam.responses.AllAssestsResponse;
import com.webdam.responses.SearchReponse;

public class TestAllAssets {

	webdamCalls wb = new webdamCalls();
	
	@Test
	public void Test_AllAsset() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		AllAssestsResponse response = wb.getAllAssests(requestBody);

		assertEquals(response.getStatus(), 200);
		assertEquals(response.getOutput(),"Expected data output", "Results are not matching");
	}
}
