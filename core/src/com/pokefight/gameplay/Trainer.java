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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.pokejava.Move;
import com.pokejava.Pokemon;
import com.pokejava.Sprite;

public class Trainer {
	private ArrayList<Battle_Pokemon> pokemons;
	private Battle_Pokemon pokemonAtivo;
	
	private String nickname;
	private boolean adversario;
	
	private Sprite pokemon;
	private Texture pokemonTexture, pokemonStatus, currentHP;
	private TextureRegion pokemonTextureRegion, pokemonStatusTextureRegion, currentHPTextureRegion;
	
	float x_position_pokemon, y_position_pokemon, x_origin_pokemon, y_origin_pokemon, width_pokemon, height_pokemon, scaleX_pokemon;
	float x_position_status, y_position_status, x_origin_status, y_origin_status, width_status, height_status, scaleX_status;
	
	private TextButtonStyle textButtonStylePokeNameThis; 
	private TextButtonStyle textButtonStylePokeNameAdv; 
	
	BitmapFont font12;
	
	Stage name;
	
	
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
		Pokemon firstPokemon = new Pokemon(2);
		Pokemon secondPokemon = new Pokemon(4);
		
		ArrayList<Move> moves = new ArrayList<Move>();
		moves.add(new Move(1));
		moves.add(new Move(2));
		moves.add(new Move(3));
		moves.add(new Move(4));

		Battle_Pokemon firstBattlePokemon = new Battle_Pokemon(2, firstPokemon.getName(), 1, 1, firstPokemon.getAttack(), firstPokemon.getDefense(), firstPokemon.getHP(), moves);
		Battle_Pokemon secondBattlePokemon = new Battle_Pokemon(4, secondPokemon.getName(), 1, 1, secondPokemon.getAttack(), secondPokemon.getDefense(), secondPokemon.getHP(), moves);		
		
		pokemons.add(firstBattlePokemon);
		pokemons.add(secondBattlePokemon);
		
		name = new Stage();
		textButtonStylePokeNameThis = new TextButtonStyle();
		textButtonStylePokeNameThis.font = font12;
		textButtonStylePokeNameAdv = new TextButtonStyle();
		textButtonStylePokeNameAdv.font = font12;
		final TextButton thisPokeName=new TextButton(this.getPokemon(0).getName(),textButtonStylePokeNameThis);
		thisPokeName.setPosition(298, 72);
		final TextButton thisPokeNameAdv=new TextButton(this.getPokemon(0).getName(),textButtonStylePokeNameAdv);
		thisPokeNameAdv.setPosition(3, 195);
		name.addActor(thisPokeName);
		name.addActor(thisPokeNameAdv);
		
		
		if (adversario) pokemonAtivo = pokemons.get(0);
		else pokemonAtivo = pokemons.get(1);
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
	
	public void render(){
		
	}
	
	public Battle_Pokemon activePokemon(){
		return this.pokemonAtivo;
	}
	
	
public void update(){
		
		if (pokemon == null){
			pokemon = new Sprite(this.activePokemon().getId());
			pokemonTexture = new Texture("." + pokemon.getImage());
			pokemonTextureRegion = new TextureRegion(pokemonTexture);
			if (adversario)
				pokemonStatus = new Texture("media/img/hp-foe.png");
			else pokemonStatus = new Texture("media/img/hp-me.png");
			pokemonStatusTextureRegion = new TextureRegion(pokemonStatus);
			currentHP = new Texture("media/img/"+ lifeBar(this.activePokemon().getCurrentHP(), this.activePokemon().getHp()));
			currentHPTextureRegion = new TextureRegion(currentHP);
		}
		
		if (adversario){
			x_position_pokemon = 240; y_position_pokemon = 100;
			x_origin_pokemon = 130; y_origin_pokemon = 130;
			width_pokemon = 130; height_pokemon = 130;
			scaleX_pokemon = 1;
			// status
			x_position_status = 0; y_position_status = 180;
			x_origin_status = 50; y_origin_status = 50;
			width_status = 122; height_status = 33;
		} else {
			x_position_pokemon = 30; y_position_pokemon = 30;
			x_origin_pokemon = 50; y_origin_pokemon = 50;
			width_pokemon = 100; height_pokemon = 100;
			scaleX_pokemon = -1;
			// status
			x_position_status = 272; y_position_status = 50;
			x_origin_status = 50; y_origin_status = 50;
			width_status = 128; height_status = 42;
		}
		
		
	}
	
	public void draw(SpriteBatch batch){
			batch.begin();
			batch.draw(pokemonTextureRegion, x_position_pokemon, y_position_pokemon, x_origin_pokemon, y_origin_pokemon, width_pokemon, height_pokemon, scaleX_pokemon, 1, 0);
			batch.draw(pokemonStatusTextureRegion, x_position_status, y_position_status, x_origin_status, y_origin_status, width_status, height_status, 1, 1, 0);
			batch.draw(currentHPTextureRegion, 50.f, 188.f, 50, 50, (this.activePokemon().getCurrentHP()/this.activePokemon().getHp())*48, 2, 1, 1, 0);
			name.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
			name.draw();	
			batch.end();
	}
	
	public String lifeBar(float hp, float maxhp){
		if(maxhp<hp) System.out.print("Erro: Vida atual superior a vida total");
		float average = hp/maxhp;
		if(average > .7) return "hp-good.png";
		else if(average > .45)return "hp-low.png";
		else return "hp-crit.png";
	}
}
