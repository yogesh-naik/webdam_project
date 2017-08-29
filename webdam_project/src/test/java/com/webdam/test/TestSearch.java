package com.webdam.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.web_dam.util.Token_Generator;
import com.web_dam.util.webdamCalls;
import com.webdam.responses.SearchReponse;
import com.webdam.responses.TokenResponse;

public class TestSearch {

	webdamCalls wb = new webdamCalls();

	@Test
	public void Test_NullQueryParam() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		String queryparam = null;
		SearchReponse response = wb.getSearch("iphone", "AUT", 11, requestBody);

		assertEquals(response.getStatus(), 400, "Error code not matching");
		assertEquals(response.getOutput(), " Null query param.Pl. enter valid string like 'iphone'");
	}
	
	@Test
	public void Test_AlphanumericQueryParam() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		String queryparam = "iphone523.23sd";
		SearchReponse response = wb.getSearch("iphone", "AUT", 11, requestBody);

		assertEquals(response.getStatus(), 400, "Error code not matching");
		assertEquals(response.getOutput(), " Query param should be valid keyword");
	}
	
	@Test
	public void Test_Special_char_QueryParam() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		String queryparam = "@#$#%$^*&*&(*(())($#$";
		SearchReponse response = wb.getSearch("iphone", "AUT", 11, requestBody);

		assertEquals(response.getStatus(), 400, "Error code not matching");
		assertEquals(response.getOutput(), " Query param should be not contain special char");
	}

	@Test
	public void Test_EmptyQueryParam() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		SearchReponse response = wb.getSearch("", "aut", 10, requestBody);

		assertEquals(response.getStatus(), 400, "Error code not matching");
		assertEquals(response.getOutput(), "Missing mandatory Query param");
	}

	// Query param > 50 char
	@Test
	public void Test_MaxQueryParam() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		String queryparam = "almlkfmdlkmfkldmflkdmflkdmflkmvnmcnvmcnvmnmnmnmcnvmncnmnm";
		SearchReponse response = wb.getSearch(queryparam, "aut", 10, requestBody);

		assertEquals(response.getStatus(), 400, "Error code not matching");
		assertEquals(response.getOutput(), "QueryParam param exceeds Char limit");
	}

	@Test
	public void Test_EmptysortParam() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		SearchReponse response = wb.getSearch("iphone", "", 10, requestBody);

		assertEquals(response.getStatus(), 400, "Error code not matching");
		assertEquals(response.getOutput(), " Sort Param missing");
	}

	// Sort param > 4 char
	@Test
	public void Test_MaxsortParam() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		SearchReponse response = wb.getSearch("iphone", "AUTTT", 10, requestBody);

		assertEquals(response.getStatus(), 400, "Error code not matching");
		assertEquals(response.getOutput(), " Sort Param exceeds 4 char");
	}

	@Test
	public void Test_MaxLimitParam() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		SearchReponse response = wb.getSearch("iphone", "autr", 100, requestBody);

		assertEquals(response.getStatus(), 400, "Error code not matching");
		assertEquals(response.getOutput(), "Limit exceeds than 10 as a max valid value");
	}
}
