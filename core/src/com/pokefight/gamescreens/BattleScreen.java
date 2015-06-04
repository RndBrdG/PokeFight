package com.pokefight.gamescreens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pokefight.gameplay.Battle;
import com.pokefight.resources.PokeListener;

public class BattleScreen implements Screen{

	private Battle battle;
	private Music music;
	private SpriteBatch batch;
	// DIALOG BOX
	private final Texture dialogbox;
	private final TextureRegion dialogboxtextreg;
	BitmapFont font12;
	private final Texture battlefield;
	//MENUATTACK

	private TextButtonStyle textButtonStyleMove1, textButtonStyleMove2, textButtonStyleMove3, textButtonStyleMove4, textButtonStylePoke1, textButtonStylePoke2, textButtonStylePoke3, textButtonStylePoke4, textButtonStyleFight,textButtonStylePoke,textButtonStyleQuit;
	private Skin skins;
	private TextureAtlas buttonAtlas;
	private Stage stages;
	private BitmapFont font;
	private Stage stageMenuLeft, stageMenuRight;
	private Texture img;
	//MOVES
	ArrayList <String> moves;

	public BattleScreen(){
		Random gerador = new Random();
		int mapa = gerador.nextInt(11) + 1;

		battle = new Battle();
		batch = new SpriteBatch();

		dialogbox = new Texture("media/img/dialog-box.png");
		dialogboxtextreg = new TextureRegion(dialogbox);

		battlefield = new Texture("battlefields/" + mapa + ".png");

		//Font Type Pokemon ------------------------------------------------------------------
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Pokemon.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 10;
		parameter.borderColor = Color.GRAY;
		parameter.color = Color.BLACK;
		font12 = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose();

		music = Gdx.audio.newMusic(Gdx.files.internal("music/battle.mp3"));
		music.play();
		music.setLooping(true); 

		menuButtons();

	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.disableBlending();
		batch.begin();
		batch.draw(battlefield, 0, 44, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
		batch.draw(dialogboxtextreg, 0.f, 0.f, 50, 50, 400, 44, 1, 1, 0);
		batch.draw(img, 282, 0, 118, 46);
		batch.end();

		batch.enableBlending();
		battle.draw(batch);

		stageMenuLeft.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stageMenuLeft.draw();	

		stageMenuRight.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stageMenuRight.draw();	

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
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		music.play();
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		music.stop();
		this.music.dispose();
	}

	public void menuButtons() {
		stages = new Stage();
		Gdx.input.setInputProcessor(stages);
		font = new BitmapFont();
		skins = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("menu.pack"));
		skins.addRegions(buttonAtlas);

		//Menu Options Styles-----------------------------------------------------
		textButtonStyleFight = new TextButtonStyle();
		textButtonStyleFight.font = font;
		textButtonStyleFight.up = skins.getDrawable("fight");
		textButtonStyleFight.down = skins.getDrawable("fight");
		textButtonStyleFight.checked = skins.getDrawable("fight");
		textButtonStyleFight.over = skins.getDrawable("fightselec");

		textButtonStylePoke = new TextButtonStyle();
		textButtonStylePoke.font = font;
		textButtonStylePoke.up = skins.getDrawable("poke");
		textButtonStylePoke.down = skins.getDrawable("poke");
		textButtonStylePoke.checked = skins.getDrawable("poke");
		textButtonStylePoke.over = skins.getDrawable("pokeselec");

		textButtonStyleQuit = new TextButtonStyle();
		textButtonStyleQuit.font = font;
		textButtonStyleQuit.up = skins.getDrawable("quit");
		textButtonStyleQuit.down = skins.getDrawable("quit");
		textButtonStyleQuit.checked = skins.getDrawable("quit");
		textButtonStyleQuit.over = skins.getDrawable("quitselec");


		stageMenuLeft = new Stage();
		stageMenuRight = new Stage();
		batch = new SpriteBatch();
		img = new Texture("menubackground.png");

		InputMultiplexer multi = new InputMultiplexer();
		multi.addProcessor(stageMenuLeft);
		multi.addProcessor(stageMenuRight);

		Gdx.input.setInputProcessor(multi);

		//Menu Options Buttons--------------------------------------------------------
		final TextButton fightButton=new TextButton("",textButtonStyleFight);
		fightButton.setPosition(296, 24);
		fightButton.setHeight(13);
		fightButton.setWidth(40);
		stageMenuRight.addActor(fightButton);

		final TextButton pokeButton = new TextButton("",textButtonStylePoke);
		pokeButton.setPosition(296,8);
		pokeButton.setHeight(13);
		pokeButton.setWidth(51);
		stageMenuRight.addActor(pokeButton);

		final TextButton quitButton = new TextButton("",textButtonStyleQuit);
		quitButton.setPosition(357,8);
		quitButton.setHeight(13);
		quitButton.setWidth(34);
		stageMenuRight.addActor(quitButton);

		fightButton.addListener( new ClickListener() {              
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//((Game)Gdx.app.getApplicationListener()).setScreen(new BattleScreen());
				stageMenuLeft.clear();
				moves();
			};
		});
		pokeButton.addListener( new ClickListener() {              
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//Gdx.app.exit();
				stageMenuLeft.clear();
				pokeList();
			};
		});
		quitButton.addListener( new ClickListener() {              
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dispose();
				((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
			};
		});
	}

	void moves(){
		//Moves Styles------------------------------------------------------------------------
		textButtonStyleMove1 = new TextButtonStyle();
		textButtonStyleMove1.font = font12;

		textButtonStyleMove2 = new TextButtonStyle();
		textButtonStyleMove2.font = font12;

		textButtonStyleMove3 = new TextButtonStyle();
		textButtonStyleMove3.font = font12;

		textButtonStyleMove4 = new TextButtonStyle();
		textButtonStyleMove4.font = font12;



		//Moves Buttons--------------------------------------------------------------------------
		final TextButton move1Button=new TextButton(battle.getTrainer(true).getPokemon(0).getMoves().get(0).getName().trim(),textButtonStyleMove1);
		move1Button.setPosition(15, 24);
		stageMenuLeft.addActor(move1Button);

		final TextButton move2Button = new TextButton(battle.getTrainer(true).getPokemon(0).getMoves().get(0).getName().trim(),textButtonStyleMove2);
		move2Button.setPosition(130,24);
		stageMenuLeft.addActor(move2Button);

		final TextButton move3Button = new TextButton(battle.getTrainer(true).getPokemon(0).getMoves().get(0).getName().trim(),textButtonStyleMove3);
		move3Button.setPosition(15,8);
		stageMenuLeft.addActor(move3Button);

		final TextButton move4Button = new TextButton(battle.getTrainer(true).getPokemon(0).getMoves().get(0).getName().trim(),textButtonStyleMove4);
		move4Button.setPosition(130,8);
		stageMenuLeft.addActor(move4Button);
		move1Button.addListener(new PokeListener(battle) {
			@Override public void clicked(InputEvent event, float x, float y) {
				// When you click the button it will print this value you assign.
				// That way you will know 'which' button was clicked and can perform
				// the correct action based on it.
				Gdx.app.log("button", "clicked ");  
			};
		});
		move2Button.addListener(new PokeListener(battle) {
			@Override public void clicked(InputEvent event, float x, float y) {
				// When you click the button it will print this value you assign.
				// That way you will know 'which' button was clicked and can perform
				// the correct action based on it.
				Gdx.app.log("button", "clicked ");  
			};
		});
		move3Button.addListener(new PokeListener(battle) {
			@Override public void clicked(InputEvent event, float x, float y) {
				// When you click the button it will print this value you assign.
				// That way you will know 'which' button was clicked and can perform
				// the correct action based on it.
				Gdx.app.log("button", "clicked ");  
			};
		});
		move4Button.addListener(new PokeListener(battle) {
			@Override public void clicked(InputEvent event, float x, float y) {
				// When you click the button it will print this value you assign.
				// That way you will know 'which' button was clicked and can perform
				// the correct action based on it.
				Gdx.app.log("button", "clicked ");  
			};
		});

	}

	void pokeList(){

		//Poke Styles------------------------------------------------------------------------
		textButtonStylePoke1 = new TextButtonStyle();
		textButtonStylePoke1.font = font12;

		textButtonStylePoke2 = new TextButtonStyle();
		textButtonStylePoke2.font = font12;

		textButtonStylePoke3 = new TextButtonStyle();
		textButtonStylePoke3.font = font12;

		textButtonStylePoke4 = new TextButtonStyle();
		textButtonStylePoke4.font = font12;


		//Poke Buttons--------------------------------------------------------------------------
		final TextButton poke1Button=new TextButton(battle.getTrainer(true).getPokemon(0).getName().trim(),textButtonStylePoke1);
		poke1Button.setPosition(15, 24);
		stageMenuLeft.addActor(poke1Button);

		final TextButton poke2Button = new TextButton(battle.getTrainer(true).getPokemon(0).getName().trim(),textButtonStylePoke2);
		poke2Button.setPosition(130,24);
		stageMenuLeft.addActor(poke2Button);

		final TextButton poke3Button = new TextButton(battle.getTrainer(true).getPokemon(0).getName().trim(),textButtonStylePoke3);
		poke3Button.setPosition(15,8);
		stageMenuLeft.addActor(poke3Button);

		final TextButton poke4Button = new TextButton(battle.getTrainer(true).getPokemon(0).getName().trim(),textButtonStylePoke4);
		poke4Button.setPosition(130,8);
		stageMenuLeft.addActor(poke4Button);

		poke1Button.addListener( new ClickListener() {              
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//((Game)Gdx.app.getApplicationListener()).setScreen(new BattleScreen());
			};
		});
		poke2Button.addListener( new ClickListener() {              
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//Gdx.app.exit();
			};
		});
		poke3Button.addListener( new ClickListener() {              
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//dispose();
				//((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
			};
		});
		poke4Button.addListener( new ClickListener() {              
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//dispose();
				//((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
			};
		});
	}
}
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
