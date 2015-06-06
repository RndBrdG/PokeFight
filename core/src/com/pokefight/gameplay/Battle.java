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
	private float initialTime, nextAttack;
	private Trainer actualPlayer;
	
	public Battle(int maxWaitTime) {
		jogador1 = new Trainer("ASH", false);
		jogador2 = new Trainer("GARY", true);
		actualPlayer = jogador1;
		anyAttack = false;
		this.maxWaitTime = maxWaitTime;
	}
	
	public void setNextAttack(float value){
		this.nextAttack = value;
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
		if (((initialTime + delta)) > maxWaitTime){
			System.out.println(actualPlayer.getNickname() + " não jogou e, como tal, perdeu!");
			((Game)Gdx.app.getApplicationListener()).setScreen(new LoadScreen());
		} else {
			initialTime += delta;
			if (anyAttack){
				initialTime = 0;
				if (actualPlayer.equals(jogador1)){
					if (nextAttack*1.5 - jogador2.activePokemon().getDefense() < 0)
						nextMove(jogador2, nextAttack*0.75);
					else nextMove(jogador2, nextAttack*1.5 - jogador2.activePokemon().getDefense());
					tradePlayer();
				} else {
					if (nextAttack*1.5 - jogador1.activePokemon().getDefense() < 0)
						nextMove(jogador1, nextAttack*0.75);
					else nextMove(jogador1, nextAttack*1.5 - jogador1.activePokemon().getDefense());
					tradePlayer();
				}
			}
		}
		this.anyAttack = false;
	}
	
	public void nextMove(Trainer to, double damage){
		to.activePokemon().descreaseActualHP(damage);
		to.setCurrentHPTextureRegion(new TextureRegion(new Texture("media/img/"+ to.lifeBar(to.activePokemon().getCurrentHP(), to.activePokemon().getHp()))));
		if ( to.activePokemon().getCurrentHP() <= 0){
			if (!to.setCurrentPokemon(to.firstPokemon_not_fainted())){
				System.out.println("Exiting...");
				Gdx.app.exit();
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
	
	public void tradePlayer(){
		if (jogador1.equals(this.actualPlayer)){
			jogador1.reverseAdversario();
			jogador2.reverseAdversario();
			actualPlayer = jogador2;
		}else {
			jogador1.reverseAdversario();
			jogador2.reverseAdversario();
			actualPlayer = jogador1;
		}
	}
	
	public Trainer getActualPlayer(){
		return this.actualPlayer;
	}
}
