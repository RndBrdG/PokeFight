package com.pokefight.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pokejava.Sprite;

public class BattleScreen implements Screen{
	SpriteBatch batch = new SpriteBatch();
	Sprite charmander = new Sprite(5);
	Sprite charizard = new Sprite(2);
	Texture charmPoke = new Texture("." + charmander.getImage());
	Texture chariPoke = new Texture("." + charizard.getImage());
	Texture battlefield = new Texture("battlefields/1.png");
	
	public BattleScreen(){

		
	}
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(battlefield, 0, 44);
		batch.draw(charmPoke, 135, 70);
		batch.draw(chariPoke, 6, 20);
		batch.end();
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
