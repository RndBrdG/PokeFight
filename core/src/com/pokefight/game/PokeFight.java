package com.pokefight.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.pokefight.gamescreens.MenuScreen;

public class PokeFight extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen());
		MenuScreen listener = new MenuScreen();
		Gdx.input.getTextInput(listener, "Estabelecer ligação", "Valor do IP", null);
	}
	
}
