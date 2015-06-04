package com.pokefight.resources;

public class Move {
	private final int id;
	private final String name;
	private final int power;
	
	public Move(int id, String name, int power) {
		this.id = id;
		this.name = name;
		this.power = power;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPower() {
		return power;
	}
}
