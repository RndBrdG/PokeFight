package com.pokefight.game;

import com.badlogic.gdx.Game;
import com.pokefight.gamescreens.MenuScreen;

public class PokeFight extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen());
	}
	
}
