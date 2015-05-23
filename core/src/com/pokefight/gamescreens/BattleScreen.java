package com.pokefight.gamescreens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pokefight.gameplay.Trainer;
import com.pokejava.Sprite;

public class BattleScreen implements Screen{
	private Trainer treinador1;
	private Trainer treinador2;
	
	private SpriteBatch batch;
	private Sprite pokeenemy;
	private Sprite pokeme;
	// ENEMY PLAYER POKEMON
	private Texture enemy_Pokemon_image;
	private TextureRegion enemy_tr_Pokemon_image;
	// THIS PLAYER POKEMON
	private Texture my_pokemon_image;
	private TextureRegion my_pokemon_tr_image;
	// CURRENT STATUS [ HP / EXP ] OF THIS PLAYER POKEMON
	private Texture enemy_pokemonStatus;
	private TextureRegion enemy_tr_pokemonStatus;
	// CURRENT STATUS [ HP / EXP ] OF THIS PLAYER POKEMON
	private final Texture my_pokemonStatus;
	private final TextureRegion my_tr_pokemonStatus;
	// CURRENT HP OF ENEMY
	private final Texture enemy_currentHP;
	private final TextureRegion enemy_tr_currentHP;
	// CURRENT HP OF THIS PLAYER
	private final Texture my_currentHP;
	private final TextureRegion my_tr_currentHP;
	// DIALOG BOX
	private final Texture dialogbox;
	private final TextureRegion dialogboxtextreg;
	
	private final Texture battlefield;
	private float count =360.0f;
	private float count1 =130.0f;
	private float count2 =0.0f;

	public BattleScreen(){
		Random gerador = new Random();
		int mapa = gerador.nextInt(11) + 1;
		
		treinador1 = new Trainer("us");
		treinador2 = new Trainer("enemy");
		
		batch = new SpriteBatch();

		pokeenemy = new Sprite(treinador1.getPokemon(0).getId());
		pokeme = new Sprite(treinador2.getPokemon(1).getId());
		
		System.out.println(pokeenemy.getImage());
		enemy_Pokemon_image = new Texture("." + pokeenemy.getImage());
		enemy_tr_Pokemon_image = new TextureRegion(enemy_Pokemon_image);
		
		my_pokemon_image = new Texture("." + pokeme.getImage());
		my_pokemon_tr_image = new TextureRegion(my_pokemon_image);
		
		enemy_pokemonStatus = new Texture("media/img/hp-foe.png");
		enemy_tr_pokemonStatus = new TextureRegion(enemy_pokemonStatus);
		
		my_pokemonStatus = new Texture("media/img/hp-me.png");
		my_tr_pokemonStatus = new TextureRegion(my_pokemonStatus);
		
		enemy_currentHP = new Texture("media/img/hp-low.png");
		enemy_tr_currentHP = new TextureRegion(enemy_currentHP);
		
		my_currentHP = new Texture("media/img/hp-low.png");
		my_tr_currentHP = new TextureRegion(my_currentHP);
		
		dialogbox = new Texture("media/img/dialog-box.png");
		dialogboxtextreg = new TextureRegion(dialogbox);
		
		battlefield = new Texture("battlefields/" + mapa + ".png");
		
	}
	@Override
	public void show() {
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(battlefield, 0, 44, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());

		batch.draw(enemy_tr_Pokemon_image, (float)200, (float)70,(float) 245,(float) 150,(float) count1, (float)count1, (float)1, (float)1,(float)0);
		batch.draw(my_pokemon_tr_image, 6.f, 20.f,(float) 50,(float) 50,(float) 100, (float)100, (float)-1, (float)1,(float)0);
		batch.draw(enemy_tr_pokemonStatus, 0.f, 180.f,(float) 50,(float) 50,(float) 122, (float)33, (float)1, (float)1,(float)0);
		batch.draw(my_tr_pokemonStatus, 272.f, 50.f,(float) 50,(float) 50,(float) 128, (float)42, (float)1, (float)1,(float)0);
		batch.draw(enemy_tr_currentHP, 50.f, 188.f,(float) 50,(float) 50,(float) count2, (float)2, (float)1, (float)1,(float)0);
		batch.draw(my_tr_currentHP, 344.f, 66.f,(float) 50,(float) 50,(float) count2, (float)2, (float)1, (float)1,(float)0);
		batch.draw(dialogboxtextreg, 0.f, 0.f,(float) 50,(float) 50,(float) 400, (float)44, (float)1, (float)1,(float)0);
		batch.end();
		
		/*
		if(count2 > 47.0f)
			count2 = 0.0f;
			else
			count2 ++;
		
		if(count1 < 0.0f)
			count1 = 130.0f;
			else
			count1 --;
		
		if(count < 0.0f)
			count = 360.0f;
			else
			count --;
	*/
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}
	

}
