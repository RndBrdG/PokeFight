package com.pokefight.oakserver;

public class MoveRequest extends ResourceRequest {
	private final int id;
	
	MoveRequest(int id) {
		super("move/" + id);
		this.id = id;
	}
	
	int getId() {
		return id;
	}
}
