package com.webdam.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.web_dam.util.Token_Generator;
import com.webdam.responses.TokenResponse;

public class TestTokenGeneratorService {

	@Test
	public void Test_emptyTokenBody(){
		String requestBody = "";  //empty input
		
		Token_Generator tk = new Token_Generator();
		TokenResponse response = tk.tokenGen(requestBody);
		
		
		assertEquals(response.getStatus(), 400,"Error code not matching");
		assertEquals(response.getOutput(), "Token Body empty","Expected error msg "
				+ "not matching with actual response msg.");
	}
	
	@Test
	public void Test_EmptygrantType(){
		String requestBody = "{\"grant_type\":\" ",\"client_id\":4,"\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\","
				+ "\"scope\":\"POST-ORDER\"}"; 
		
		Token_Generator tk = new Token_Generator();
		TokenResponse response = tk.tokenGen(requestBody);
		
		
		assertEquals(response.getStatus(), 401,"Error code not matching");
		assertEquals(response.getOutput(), "grant type is needed","Expected error msg "
				+ "not matching with actual response msg.");
	}
	
	@Test
	public void Test_EmptygrantType(){
		String requestBody = "{\"grant_type\":\"username/password",\"client_id\":,"\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\","
				+ "\"scope\":\"POST-ORDER\"}"; 
		
		Token_Generator tk = new Token_Generator();
		TokenResponse response = tk.tokenGen(requestBody);
		
		
		assertEquals(response.getStatus(), 401,"Error code not matching");
		assertEquals(response.getOutput(), "Client ID is missing","Expected error msg "
				+ "not matching with actual response msg.");
	}
	
	@Test
	public void Test_EmptygrantClientSecret(){
		String requestBody = "{\"grant_type\":\"username/password",\"client_id\":4,"\"client_secret\":\" ","
				+ "\"scope\":\"POST-ORDER\"}"; 
		
		Token_Generator tk = new Token_Generator();
		TokenResponse response = tk.tokenGen(requestBody);
		
		
		assertEquals(response.getStatus(), 401,"Error code not matching");
		assertEquals(response.getOutput(), "Client secret is missing","Expected error msg "
				+ "not matching with actual response msg.");
	}
	
	@Test
	public void Test_EmptyScope(){
		String requestBody = "{\"grant_type\":\"username/password",\"client_id\":4,"\"client_secret\":\" lfkdnfkldnfkl","
				+ "\"scope\":\"\"}"; 
		
		Token_Generator tk = new Token_Generator();
		TokenResponse response = tk.tokenGen(requestBody);
		
		
		assertEquals(response.getStatus(), 401,"Error code not matching");
		assertEquals(response.getOutput(), " Scope is missing","Expected error msg "
				+ "not matching with actual response msg.");
	}
	
}
