package com.pokefight.game;

import com.badlogic.gdx.Game;
import com.pokefight.gamescreens.battleScreen;

public class PokeFight extends Game {

	@Override
	public void create() {
		setScreen(new battleScreen());
	}
	
}
