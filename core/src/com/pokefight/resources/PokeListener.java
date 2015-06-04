package com.pokefight.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.pokefight.gameplay.Battle;

public class PokeListener extends InputListener{
	Battle battle;
    public PokeListener(Battle battle){
        this.battle = battle;
    }
    
    public void clicked(InputEvent event, float x, float y) {
        Gdx.app.log("button","clicked");
        battle.setAnyAttack();
    };
}