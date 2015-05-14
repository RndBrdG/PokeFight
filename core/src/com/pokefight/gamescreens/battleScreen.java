package com.pokefight.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.pokejava.*;

public class battleScreen implements Screen{

	public battleScreen(){
		
	}
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Sprite bulba = new Sprite(1);
		System.out.println(bulba.getImage());
		Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
