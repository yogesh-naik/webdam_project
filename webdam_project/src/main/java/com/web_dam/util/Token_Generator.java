package com.web_dam.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.webdam.responses.TokenResponse;

public class Token_Generator {

//	private final String requestBody = "{\"grant_type\":\"Username/Password\",\"client_id\":4,"
//			+ "\"client_secret\":\"4ovGa5yXfHnWR47wGRVUfKlDTBxC3WQtnkmO5sgs\","
//			+ "\"scope\":\"POST-ORDER\"}";

	private final String endpoint = "http://your-app.com/oauth/token";

	public TokenResponse tokenGen(String requestBody) {
		TokenResponse tokenResponse = null;

		try {
			Client client = Client.create();
			WebResource webResource = client.resource(endpoint);
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, requestBody);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			System.out.println("Token Response .... \n");
			 tokenResponse = response.getEntity(TokenResponse.class);
			System.out.println(tokenResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenResponse;
	}

}
