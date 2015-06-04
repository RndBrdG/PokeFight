package com.pokefight.oakserver;

public class MoveRequest extends ResourceRequest {
	private int id;
	
	MoveRequest(int id) {
		this.id = id;
		apiPath = "move/" + id;
	}
	
	int getId() {
		return id;
	}
}
