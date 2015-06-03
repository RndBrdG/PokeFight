package com.pokefight.oakserver;

public class PokémonRequest extends ResourceRequest {
	private int id;
	
	PokémonRequest(int id) {
		this.id = id;
		apiPath = "pokemon/" + id;
	}
	
	public int getId() {
		return id;
	}
}
