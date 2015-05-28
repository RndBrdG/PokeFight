package com.pokefight.resources;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.json.JSONObject;

public class OakServer {
	static JSONObject getResource(String path) throws OakServerException {
		Response serverResponse;

		try {
			serverResponse = Request.Get("localhost/api/" + path).execute();
			if (serverResponse.returnResponse().getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND)
				throw new OakServerException();
			
			return parse(serverResponse.returnContent().asString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Fonte: https://github.com/mickeyjk/PokeJava/blob/master/src/com/pokejava/ModelClass.java */
	private static JSONObject parse(String data) {
		JSONObject root;
		try {
		root = new JSONObject(data);		
		return root;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
