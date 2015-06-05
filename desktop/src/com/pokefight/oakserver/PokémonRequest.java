package com.pokefight.oakserver;

public class PokémonRequest extends ResourceRequest {
	private final int id;
	
	PokémonRequest(int id) {
		super("pokemon/" + id);
		this.id = id;
	}
	
	int getId() {
		return id;
	}
}
