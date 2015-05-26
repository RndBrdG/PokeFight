package com.pokefight.gamescreens;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pokefight.gameplay.Trainer;
import com.pokejava.Sprite;

public class BattleScreen implements Screen{
	private Trainer treinador1;
	private Trainer treinador2;
	
	private double enemy_x_position = 240;
	private double enemy_y_position = 100;
	private int our_x_position = 30;
	private int our_y_position = 30;

	private int attack = 0;
	
	private SpriteBatch batch;
	private Sprite pokeenemy;
	private Sprite pokeme;
	// ENEMY PLAYER POKEMON
	private Texture enemy_Pokemon_image;
	private TextureRegion enemy_tr_Pokemon_image;
	// THIS PLAYER POKEMON
	private Texture my_pokemon_image;
	private TextureRegion my_pokemon_tr_image;
	// CURRENT STATUS [ HP / EXP ] OF THIS PLAYER POKEMON
	private Texture enemy_pokemonStatus;
	private TextureRegion enemy_tr_pokemonStatus;
	// CURRENT STATUS [ HP / EXP ] OF THIS PLAYER POKEMON
	private final Texture my_pokemonStatus;
	private final TextureRegion my_tr_pokemonStatus;
	// CURRENT HP OF ENEMY
	private final Texture enemy_currentHP;
	private final TextureRegion enemy_tr_currentHP;
	// CURRENT HP OF THIS PLAYER
	private final Texture my_currentHP;
	private final TextureRegion my_tr_currentHP;
	// DIALOG BOX
	private final Texture dialogbox;
	private final TextureRegion dialogboxtextreg;
	
	private final Texture battlefield;
	//MENUATTACK
	 TextButtonStyle textButtonStyleFight, textButtonStylePokemon, textButtonStyleQuit;
	 Skin skins;
	 TextureAtlas buttonAtlas;
	 private Skin skin;
	 Stage stages;
	 BitmapFont font;
	 private Stage stage;
	 private Texture img;
	 //FLOAT TEST LIFE
	 float HP1 = 210, HP2=100, MAXHP=200;
	 
	public BattleScreen(){
		Random gerador = new Random();
		int mapa = gerador.nextInt(11) + 1;
		
		treinador1 = new Trainer("us");
		treinador2 = new Trainer("enemy");
		
		batch = new SpriteBatch();

		pokeenemy = new Sprite(treinador1.getPokemon(0).getId());
		pokeme = new Sprite(treinador2.getPokemon(1).getId());
		
		enemy_Pokemon_image = new Texture("." + pokeenemy.getImage());
		enemy_tr_Pokemon_image = new TextureRegion(enemy_Pokemon_image);
		
		my_pokemon_image = new Texture("." + pokeme.getImage());
		my_pokemon_tr_image = new TextureRegion(my_pokemon_image);
		
		enemy_pokemonStatus = new Texture("media/img/hp-foe.png");
		enemy_tr_pokemonStatus = new TextureRegion(enemy_pokemonStatus);
		
		my_pokemonStatus = new Texture("media/img/hp-me.png");
		my_tr_pokemonStatus = new TextureRegion(my_pokemonStatus);
		
		enemy_currentHP = new Texture("media/img/"+ lifeBar(HP1, MAXHP));
		enemy_tr_currentHP = new TextureRegion(enemy_currentHP);
		
		my_currentHP = new Texture("media/img/"+ lifeBar(HP2, MAXHP));
		my_tr_currentHP = new TextureRegion(my_currentHP);
		
		dialogbox = new Texture("media/img/dialog-box.png");
		dialogboxtextreg = new TextureRegion(dialogbox);
		
		battlefield = new Texture("battlefields/" + mapa + ".png");
		
		menuattack();
		
	}
	
	@Override
	public void show() {
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(battlefield, 0, 44, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
		batch.draw(dialogboxtextreg, 0.f, 0.f, 50, 50, 400, 44, 1, 1, 0);

		batch.draw(enemy_tr_Pokemon_image, (int)enemy_x_position, (int)enemy_y_position, 245, 150, 130, 130, 1, 1, 0);
		batch.draw(my_pokemon_tr_image, our_x_position, our_y_position, 50, 50, 100, 100, -1, 1, 0);
		
		batch.draw(enemy_tr_pokemonStatus, 0.f, 180.f, 50, 50, 122, 33, 1, 1, 0);
		batch.draw(my_tr_pokemonStatus, 272.f, 50.f, 50, 50, 128, 42, 1, 1,0);
		
		batch.draw(enemy_tr_currentHP, 50.f, 188.f, 50, 50, (HP1/MAXHP)*48, 2, 1, 1,0);
		batch.draw(my_tr_currentHP, 344.f, 66.f, 50, 50, (HP2/MAXHP)*48, 2, 1, 1,0);
		//menuattackbackground
		batch.draw(img, 282, 0, 118, 46);
		batch.end();
		
		//Menuattack
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		
		/*
		if(attack == 0){
			if ( enemy_x_position <= 270){
				enemy_x_position += 0.5;
				enemy_y_position += 0.2;
			}else attack++;
		}
		else if (attack == 100){
			if (enemy_x_position > 240){
				enemy_x_position -= 0.5;
				enemy_y_position -= 0.2;
			}else if (enemy_x_position <= 240) {
				attack = 0;
				//enemy_x_position = 240;
				//enemy_y_position = 100;
			}
		}
		else {
			attack++;
		}
		*/
		/*
		if(count2 > 47.0f)
			count2 = 0.0f;
			else
			count2 ++;
		
		if(count1 < 0.0f)
			count1 = 130.0f;
			else
			count1 --;
		
		if(count < 0.0f)
			count = 360.0f;
			else
			count --;*/
	
	}
	//CALC COLOR OF LIFE BAR (CRITIC, LOW, GOOD)
	public String lifeBar(float hp, float maxhp){
		if(maxhp<hp) System.out.print("Erro: Vida atual superior à vida total");
		float average = hp/maxhp;
		if(average > .7) return "hp-good.png";
		else if(average > .45)return "hp-low.png";
		else return "hp-crit.png";
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
	
	public void menuattack() {
 	stages = new Stage();
    Gdx.input.setInputProcessor(stages);
    font = new BitmapFont();
    skins = new Skin();
    buttonAtlas = new TextureAtlas(Gdx.files.internal("menu.pack"));
    skins.addRegions(buttonAtlas);
    textButtonStyleFight = new TextButtonStyle();
    textButtonStyleFight.font = font;
    textButtonStyleFight.up = skins.getDrawable("fight");
    textButtonStyleFight.down = skins.getDrawable("fight");
    textButtonStyleFight.checked = skins.getDrawable("fight");
	textButtonStyleFight.over = skins.getDrawable("fightselec");
	
    textButtonStylePokemon = new TextButtonStyle();
    textButtonStylePokemon.font = font;
    textButtonStylePokemon.up = skins.getDrawable("poke");
    textButtonStylePokemon.down = skins.getDrawable("poke");
    textButtonStylePokemon.checked = skins.getDrawable("poke");
    textButtonStylePokemon.over = skins.getDrawable("pokeselec");
    
    textButtonStyleQuit = new TextButtonStyle();
    textButtonStyleQuit.font = font;
    textButtonStyleQuit.up = skins.getDrawable("quit");
    textButtonStyleQuit.down = skins.getDrawable("quit");
    textButtonStyleQuit.checked = skins.getDrawable("quit");
    textButtonStyleQuit.over = skins.getDrawable("quitselec");
	
	skin = new Skin();
	stage = new Stage();
	batch = new SpriteBatch();
	img = new Texture("menubackground.png");
	
	Gdx.input.setInputProcessor(stage);

	

	// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
	final TextButton fightButton=new TextButton("",textButtonStyleFight);
	fightButton.setPosition(296, 24);
	fightButton.setHeight(13);
	fightButton.setWidth(40);
	stage.addActor(fightButton);
	
	final TextButton pokeButton = new TextButton("",textButtonStylePokemon);
	pokeButton.setPosition(296,8);
	pokeButton.setHeight(13);
	pokeButton.setWidth(51);
	stage.addActor(pokeButton);
	
	final TextButton quitButton = new TextButton("",textButtonStyleQuit);
	quitButton.setPosition(357,8);
	quitButton.setHeight(13);
	quitButton.setWidth(34);
	stage.addActor(quitButton);
	
	fightButton.addListener( new ClickListener() {              
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
	    	//((Game)Gdx.app.getApplicationListener()).setScreen(new BattleScreen());
	    };
	});
	pokeButton.addListener( new ClickListener() {              
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
	    	//Gdx.app.exit();
	    };
	});
	quitButton.addListener( new ClickListener() {              
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
	    	//Gdx.app.exit();
	    };
	});
	
	}

}
