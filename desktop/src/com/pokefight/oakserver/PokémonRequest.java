package com.pokefight.oakserver;

public class PokémonRequest extends ResourceRequest {
	private int id;
	
	PokémonRequest(int id) {
		this.id = id;
		apiPath = "pokemon/" + id;
	}
	
	int getId() {
		return id;
	}
}
