package com.pokefight.gameplay;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.pokejava.Move;
import com.pokejava.Pokemon;
import com.pokejava.Sprite;

public class Trainer {
	private ArrayList<Battle_Pokemon> pokemons;
	private Battle_Pokemon pokemonAtivo;
	
	private String nickname;
	private boolean adversario;
	
	private Sprite pokemon;
	private Texture pokemonTexture, currentHP = new Texture("media/img/hp-good.png");
	private TextureRegion pokemonTextureRegion, currentHPTextureRegion;
	
	private float x_position_pokemon, y_position_pokemon, x_origin_pokemon, y_origin_pokemon, width_pokemon, height_pokemon, scaleX_pokemon;
	private float x_position_font, y_position_font;
	private float x_position_barHP, y_position_barHP;
	
	BitmapFont font12;
	
	public Trainer(String nickname, boolean adversario){
		this.nickname = nickname;
		this.adversario = adversario;
		
		//Font Type Pokemon ------------------------------------------------------------------
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Pokemon.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 8;
		parameter.borderColor = Color.GRAY;
		parameter.color = Color.WHITE;
		font12 = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose();
		
		pokemons = new ArrayList<Battle_Pokemon>();
		// GET POKEMONS FROM DATABASE
		
		// GET INFORMATION
		Pokemon firstPokemon = new Pokemon(6);
		Pokemon secondPokemon = new Pokemon(101);
		
		ArrayList<Move> moves = new ArrayList<Move>();
		moves.add(new Move(1));
		moves.add(new Move(2));
		moves.add(new Move(3));
		moves.add(new Move(4));
		
		Battle_Pokemon firstBattlePokemon = new Battle_Pokemon(firstPokemon.getID(), firstPokemon.getName(), 1, 1, firstPokemon.getAttack(), firstPokemon.getDefense(), firstPokemon.getHP(), moves);
		Battle_Pokemon secondBattlePokemon = new Battle_Pokemon(secondPokemon.getID(), secondPokemon.getName(), 1, 1, secondPokemon.getAttack(), secondPokemon.getDefense(), secondPokemon.getHP(), moves);		
		
		pokemons.add(firstBattlePokemon);
		pokemons.add(secondBattlePokemon);
				
		if (!adversario) pokemonAtivo = pokemons.get(0);
		else pokemonAtivo = pokemons.get(1);
	}

	public void setPokemonAtributeNull(){
		this.pokemon = null;
		update();
	}
	
	public String getNickname(){
		return this.nickname;
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
	public Battle_Pokemon firstPokemon_not_fainted(){
		for(int i = 0; i < pokemons.size(); i++){
			if (pokemons.get(i).getCurrentHP() > 0) {System.out.println("Escolho-te a ti " + pokemons.get(i).getName());return pokemons.get(i);}
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
			if (pokemons.get(id).getHp() <= 0) { if (firstPokemon_not_fainted() != null) return firstPokemon_not_fainted(); else return null;}
			else return pokemons.get(id);
		}
	}
	
	public void render(){
		
	}
	
	public Battle_Pokemon activePokemon(){
		return this.pokemonAtivo;
	}
	
	public void setHpTexture(Texture tex){
		currentHP = tex;
		}
	
	public void update(){
		
		if ( pokemon == null){
			System.out.println(this.activePokemon().getName());
			pokemon = new Sprite(this.activePokemon().getId() + 1);
			pokemonTexture = new Texture("." + pokemon.getImage());
			pokemonTextureRegion = new TextureRegion(pokemonTexture);
			currentHPTextureRegion = new TextureRegion(currentHP);
			currentHP = new Texture("media/img/hp-good.png");
		}
		
		if (adversario){
			x_position_pokemon = 240; y_position_pokemon = 100;
			x_origin_pokemon = 130; y_origin_pokemon = 130;
			width_pokemon = 130; height_pokemon = 130;
			scaleX_pokemon = 1;
			// name
			x_position_font = 5; y_position_font = 205;
			// hp
			x_position_barHP = 50; y_position_barHP = 188;
			
		} else {
			x_position_pokemon = 30; y_position_pokemon = 30;
			x_origin_pokemon = 50; y_origin_pokemon = 50;
			width_pokemon = 100; height_pokemon = 100;
			scaleX_pokemon = -1;
			// name
			x_position_font = 300; y_position_font = 83;
			// hp
			x_position_barHP = 344; y_position_barHP = 66;
		}
		
		
	}
	
	public TextureRegion getCurrentHPTextureRegion() {
		return currentHPTextureRegion;
	}

	public void setCurrentHPTextureRegion(TextureRegion currentHPTextureRegion) {
		this.currentHPTextureRegion = currentHPTextureRegion;
	}

	public void draw(SpriteBatch batch){
			batch.begin();
			batch.draw(pokemonTextureRegion, x_position_pokemon, y_position_pokemon, x_origin_pokemon, y_origin_pokemon, width_pokemon, height_pokemon, scaleX_pokemon, 1, 0);
			batch.draw(currentHPTextureRegion, x_position_barHP, y_position_barHP, 50, 50, (this.activePokemon().getCurrentHP()*48)/this.activePokemon().getHp(), 2, 1, 1, 0);
			font12.draw(batch, this.activePokemon().getName(), x_position_font, y_position_font);	
			batch.end();
	}
	
	public String lifeBar(float hp, float maxhp){
		if(maxhp<hp) System.out.print("Erro: Vida atual superior a vida total");
		float average = hp/maxhp;
		if(average > .7) return "hp-good.png";
		else if(average > .45)return "hp-low.png";
		else return "hp-crit.png";
	}
	
	public boolean setCurrentPokemon(Battle_Pokemon new_actual){
		if (new_actual == null) return false;
		else {
			this.pokemonAtivo = new_actual;
			return true;
		}
	}
	
	public boolean setCurrentPokemon(int i){
		if (this.pokemons.get(i).getCurrentHP() <= 0){
			 return false;
		} else {
			this.pokemon = null;
			this.pokemonAtivo = this.pokemons.get(i);
			return true;
		}
	}
	
	public void reverseAdversario(){
		this.adversario = !adversario;
	}
	
	public boolean equals(Object j){
		if (j == null || getClass() != j.getClass()) return false;
		
		Trainer t = (Trainer) j;
		
		return t.getNickname().equals(this.getNickname());
	}
}
