package com.pokefight.gameplay;

import java.util.ArrayList;

import com.pokejava.Pokemon;

public class Trainer {
	private ArrayList<Battle_Pokemon> pokemons;
	
	
	public Trainer(String nickname){
		pokemons = new ArrayList<Battle_Pokemon>();
		// GET POKEMONS FROM DATABASE
		
		// GET INFORMATION
		Pokemon firstPokemon = new Pokemon(2);
		Pokemon secondPokemon = new Pokemon(4);
		
		Battle_Pokemon firstBattlePokemon = new Battle_Pokemon(2, firstPokemon.getName(), 1, 1, firstPokemon.getAttack(), firstPokemon.getDefense(), firstPokemon.getHP(), firstPokemon.getAbilities());
		Battle_Pokemon secondBattlePokemon = new Battle_Pokemon(4, secondPokemon.getName(), 1, 1, secondPokemon.getAttack(), secondPokemon.getDefense(), secondPokemon.getHP(), secondPokemon.getAbilities());		

		pokemons.add(firstBattlePokemon);
		pokemons.add(secondBattlePokemon);
	}
	
	/*
	 *  Update win or loss column on database.
	 *  Returns true of successful, or false otherwise.
	 */
	public boolean refreshData(){
		
		return false;
	}
	
	/*
	 * Returns first pokemon which hp is not 0;
	 */
	private Battle_Pokemon firstPokemon_not_fainted(){
		for(int i = 0; i < pokemons.size(); i++){
			if (pokemons.get(i).getHp() > 0) return pokemons.get(i);
			else continue;
		}
		return null;
	}
	
	/*
	 * Returns the pokemon in 'id' position. If this pokemon is incapable of fighting [ hp = 0], returns the first one who is capable of , or null. 
	 */
	public Battle_Pokemon getPokemon(int id){
		if (id < 0 || id > 5) { if (firstPokemon_not_fainted() != null) return firstPokemon_not_fainted(); else return null;}
		else {
			if (pokemons.get(id).getHp() == 0) { if (firstPokemon_not_fainted() != null) return firstPokemon_not_fainted(); else return null;}
			else return pokemons.get(id);
		}
	}
	
	
}
