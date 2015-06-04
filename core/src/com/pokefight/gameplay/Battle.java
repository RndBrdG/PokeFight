package com.pokefight.gameplay;

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
}
