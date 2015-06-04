package com.pokefight.resources;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pokefight.gameplay.Battle;

public class PokeListener extends ClickListener{
	Battle battle;
    public PokeListener(Battle battle){
        this.battle = battle;
    }
	public void clicked(InputEvent event, float x, float y) {
	}
}