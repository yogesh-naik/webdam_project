package com.webdam.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

import com.web_dam.util.webdamCalls;
import com.webdam.responses.AllAssestsResponse;
import com.webdam.responses.SingleAssetResponse;

public class TestSingleAsset {

	webdamCalls wb = new webdamCalls();
	
	@Test
	public void Test_WrongID() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		String ID = "ABC";
		
		SingleAssetResponse response = wb.getSingleAssest(ID, requestBody);

		assertEquals(response.getStatus(), 400);
		assertEquals(response.getOutput(),"ID Not matching");
	}
}
