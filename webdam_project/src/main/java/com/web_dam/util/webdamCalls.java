package com.web_dam.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.webdam.responses.AllAssestsResponse;
import com.webdam.responses.LoginLogoutResponse;
import com.webdam.responses.SearchReponse;
import com.webdam.responses.SingleAssetResponse;
import com.webdam.responses.TokenResponse;

public class webdamCalls {

	public final String endUrl = "http://interview-testing-api.webdamdb.com/api/v1/";

	public LoginLogoutResponse doLogin(String requestBody,boolean flag) throws InterruptedException {

		String loginUrl = endUrl + "/login";
		LoginLogoutResponse response = null;
		try {

			// Generate Token
			Token_Generator getToken = new Token_Generator();
			TokenResponse token = getToken.tokenGen(requestBody);

			if(flag ==true){
				wait(3600);
			}
			Client client = Client.create();
			WebResource webResource = client.resource(endUrl);
			 response = webResource.accept("application/json").header("Accept", "application/json")
					.header("Authorization", token.getOutput()).get(LoginLogoutResponse.class);

			if (response.getStatus() != 200 || response ==null) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		} catch (NullPointerException e ) {
			e.printStackTrace();
		}
		return response;
	}

	public LoginLogoutResponse doLogout(String requestBody) {

		String loginUrl = endUrl + "/logout";
		LoginLogoutResponse response =  null;
		try {

			// Generate Token
			Token_Generator getToken = new Token_Generator();
			TokenResponse token = getToken.tokenGen(requestBody);

			Client client = Client.create();
			WebResource webResource = client.resource(endUrl);
			 response = webResource.accept("application/json").header("Accept", "application/json")
					.header("Authorization", token.getOutput()).get(LoginLogoutResponse.class);

			if (response.getStatus() != 200 || response ==null) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return response;
	}

	public SearchReponse getSearch(String queryParam, String aut, int limit,String requestBody) {

		String searchUrl = endUrl + "/search?query=" + queryParam + "&sort=" + aut + "&limit=" + limit;

		SearchReponse response = null;
		try {

			// Generate Token
			Token_Generator getToken = new Token_Generator();
			TokenResponse token = getToken.tokenGen(requestBody);

			Client client = Client.create();
			WebResource webResource = client.resource(searchUrl);
		  response = webResource.accept("application/json").header("Accept", "application/json")
					.header("Authorization", token.getOutput()).get(SearchReponse.class);

			if (response.getStatus() != 200 || response ==null) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public AllAssestsResponse getAllAssests(String requestBody) {

		String loginUrl = endUrl + "/asset";
		AllAssestsResponse response = null;
		try {

			// Generate Token
			Token_Generator getToken = new Token_Generator();
			TokenResponse token = getToken.tokenGen(requestBody);

			Client client = Client.create();
			WebResource webResource = client.resource(endUrl);
			 response = webResource.accept("application/json").header("Accept", "application/json")
					.header("Authorization", token.getOutput()).get(AllAssestsResponse.class);

			if (response.getStatus() != 200 || response ==null) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public SingleAssetResponse getSingleAssest(String id,String requestBody) {

		String loginUrl = endUrl + "/asset/"+id;
		SingleAssetResponse response = null;
		try {

			// Generate Token
			Token_Generator getToken = new Token_Generator();
			TokenResponse token = getToken.tokenGen(requestBody);

			Client client = Client.create();
			WebResource webResource = client.resource(endUrl);
			 response = webResource.accept("application/json").header("Accept", "application/json")
					.header("Authorization", token.getOutput()).get(SingleAssetResponse.class);

			if (response.getStatus() != 200 || response ==null) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return response;
	}
}
