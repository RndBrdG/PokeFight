package com.pokefight.gameplay;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pokefight.gamescreens.LoadScreen;

public class Battle {
	
	private Trainer jogador1;
	private Trainer jogador2;
	
	private boolean anyAttack;
	public int damageInt, maxWaitTime;
	private float initialTime;
	private Trainer actualPlayer;
	
	public Battle(int maxWaitTime) {
		jogador1 = new Trainer("ASH", false);
		jogador2 = new Trainer("Gary", true);
		actualPlayer = jogador1;
		anyAttack = false;
		this.maxWaitTime = maxWaitTime;
	}
	
	public Trainer getTrainer(boolean adversario){
		return adversario ? jogador2 : jogador1;
	}
	
	public void setActualTrainer(Boolean adversario){
		if (adversario) actualPlayer = jogador2;
		else actualPlayer = jogador1;
	}
	
	public void draw(SpriteBatch batch){
		getTrainer(true).update();
		getTrainer(true).draw(batch);
		getTrainer(false).update();
		getTrainer(false).draw(batch);
	}
	
	public void match(float delta){
		//System.out.println("ELAPSED TIME: " + ((initialTime + delta)));
		//System.out.println("Name: " + jogador2.activePokemon().getName() + " > " + jogador2.activePokemon().getCurrentHP());
		if (((initialTime + delta)) > maxWaitTime){
			System.out.println(actualPlayer.getNickname() + " não jogou e, como tal, perdeu!");
			((Game)Gdx.app.getApplicationListener()).setScreen(new LoadScreen());
		} else {
			initialTime += delta;
			if (anyAttack){
				initialTime = 0;
				if (actualPlayer.equals(jogador1)){
					// GET THE ATTACK MOVE
					//System.out.println("Atacar Jogador 2");
					nextMove(jogador2, 20);
				} else {
					// GET THE ATTACK MOVE
					System.out.println("Atacar Jogador 1");
					nextMove(jogador1, 20);
				}
			}
		}
		this.anyAttack = false;
	}
	
	public void nextMove(Trainer to, double damage){
		to.activePokemon().descreaseActualHP(damage);
		to.setCurrentHPTextureRegion(new TextureRegion(new Texture("media/img/"+ to.lifeBar(to.activePokemon().getCurrentHP(), to.activePokemon().getHp()))));
		System.out.println(to.activePokemon().getCurrentHP());
		if ( to.activePokemon().getCurrentHP() <= 0){
			if (!to.setCurrentPokemon(to.firstPokemon_not_fainted())){
				System.out.println("Exiting");
				Gdx.app.exit();  // TODO: Fim de jogo em condições.
			}
			else {to.setPokemonAtributeNull();
			}
		}
	}
	
	public boolean getAnyAttack(){
		return this.anyAttack;
	}
	
	public void changeAnyAttack(){
		this.anyAttack = !anyAttack;
	}

	public void setAnyAttack(){
		if (actualPlayer.equals(jogador1) && anyAttack == false) anyAttack = true;
	}
	
	public Trainer getJogadorAlvo(){
		if (jogador1.equals(actualPlayer)) return jogador2;
		else return jogador1;
	}
}
