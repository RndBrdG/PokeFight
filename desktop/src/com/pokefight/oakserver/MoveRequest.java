package com.pokefight.oakserver;

public class MoveRequest extends ResourceRequest {
	private int id;
	
	MoveRequest(int id) {
		this.id = id;
		apiPath = "move/" + id;
	}
	
	public int getId() {
		return id;
	}
}
