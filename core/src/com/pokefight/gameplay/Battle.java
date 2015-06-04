package com.pokefight.gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Battle {
	
	private Trainer jogador1;
	private Trainer jogador2;
	
	public Battle() {
		jogador1 = new Trainer("ASH", false);
		jogador2 = new Trainer("Gary", true);
	}
	
	public Trainer getTrainer(boolean adversario){
		return adversario ? jogador2 : jogador1;
	}
	
	public void draw(SpriteBatch batch){
		getTrainer(true).update();
		getTrainer(true).draw(batch);
		getTrainer(false).update();
		getTrainer(false).draw(batch);
	}
}
