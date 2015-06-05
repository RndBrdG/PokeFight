package com.pokefight.oakserver;

public class PokemonMoveRequest extends ResourceRequest {
	private final int pokemonId;
	
	PokemonMoveRequest(int pokemonId) {
		super("pokemon/" + pokemonId + "/moves");
		this.pokemonId = pokemonId;
	}
	
	public int getPokemonId() {
		return pokemonId;
	}
}
