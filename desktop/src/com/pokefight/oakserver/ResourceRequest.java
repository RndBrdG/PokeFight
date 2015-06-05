package com.pokefight.oakserver;

abstract class ResourceRequest {
	protected String apiPath;
	
	ResourceRequest(String apiPath) {
		this.apiPath = apiPath;
	}
	
	String getApiPath() {
		return apiPath;
	}
}
