package com.webdam.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.web_dam.util.Token_Generator;
import com.web_dam.util.webdamCalls;
import com.webdam.responses.LoginLogoutResponse;
import com.webdam.responses.TokenResponse;

public class TestLoginService {

	webdamCalls wb = new webdamCalls();

	// Test case with invalid client ID =5
	@Test
	public void Test_invalidToken() {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":5,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";


		LoginLogoutResponse output = wb.doLogin(requestBody,false);

		assertEquals(output.getStatus(), 409, "Error code not matching");
		assertEquals(output.getOutput(), "User not authorised to login.Check user credentials");

	}

	// Test case with invalid granttype
	@Test
	public void Test_invalidGrantype() {
		String requestBody = "{\"grant_type\":\"User2/pwd\",\"client_id\":5,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		LoginLogoutResponse output = wb.doLogin(requestBody,false);

		assertEquals(output.getStatus(), 409, "Error code not matching");
		assertEquals(output.getOutput(), "User not authorised to login.Check user credentials");

	}

	// Test case with invalid secretType
	@Test
	public void Test_invalidSecretType() {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":5,"
				+ "\"client_secret\":\"ABCDERFC\"," + "\"scope\":\"POST-ORDER\"}";

		LoginLogoutResponse output = wb.doLogin(requestBody,false);

		assertEquals(output.getStatus(), 409, "Error code not matching");
		assertEquals(output.getOutput(), "User not authorised to login.Check user credentials");

	}

	// Test case with invalid Scope
	@Test
	public void Test_invalidScope() {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":5,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"PRE\"}";

		LoginLogoutResponse output = wb.doLogin(requestBody,false);

		assertEquals(output.getStatus(), 409, "Error code not matching");
		assertEquals(output.getOutput(), "User not authorised to login.Check user credentials");

	}

	// Test case with expired token after 1 hr(configurable param)
	@Test
	public void Test_expiredToken() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		LoginLogoutResponse output = wb.doLogin(requestBody,true);

		assertEquals(output.getStatus(), 401, "Error code not matching");
		assertEquals(output.getOutput(), "Usertoken expired or token not matching");
	}

	// Positive test case
	@Test
	public void Test_LoginSucess() throws InterruptedException {
		String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
				+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\"," + "\"scope\":\"POST-ORDER\"}";

		LoginLogoutResponse output = wb.doLogin(requestBody,false);

		assertEquals(output.getStatus(), 200, "Error code not matching");
		assertEquals(output.getOutput(), "Expected Data","Actual and expected data not matching");

	}

}
