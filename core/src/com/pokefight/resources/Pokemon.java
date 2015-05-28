package com.pokefight.resources;

import org.json.JSONObject;

public class Pokemon {
	private int id;
	private String name;
	private byte[] sprite;
	private int maxHp;
	
	public Pokemon(int id) {
		try {
			JSONObject pkmnInfo = OakServer.getResource("pokemon/" + id);
		} catch (OakServerException e) {
			com.pokejava.Pokemon newPokemon = new com.pokejava.Pokemon(id);
		}
	}
}
