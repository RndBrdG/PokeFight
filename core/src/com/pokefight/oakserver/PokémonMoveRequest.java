package com.pokefight.oakserver;

public class PokémonMoveRequest extends ResourceRequest {
	private final int pokemonId;
	
	PokémonMoveRequest(int pokemonId) {
		super("pokemon/" + pokemonId + "/moves");
		this.pokemonId = pokemonId;
	}
	
	public int getPokemonId() {
		return pokemonId;
	}
}
