package com.pokefight.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen,TextInputListener{
	private Stage stage;
	private Texture img;
	private Skin skin;
	private SpriteBatch batch;
	Stage stages;
	TextButton button;
	TextButtonStyle textButtonStylePlay, textButtonStyleExit;
	BitmapFont font;
	Skin skins;
	TextureAtlas buttonAtlas;


	public MenuScreen() {
		stages = new Stage();
		Gdx.input.setInputProcessor(stages);
		font = new BitmapFont();
		skins = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons.pack"));
		skins.addRegions(buttonAtlas);
		textButtonStylePlay = new TextButtonStyle();
		textButtonStylePlay.font = font;
		textButtonStylePlay.up = skins.getDrawable("play");
		textButtonStylePlay.down = skins.getDrawable("play");
		textButtonStylePlay.checked = skins.getDrawable("play");
		textButtonStylePlay.over = skins.getDrawable("play2");

		textButtonStyleExit = new TextButtonStyle();
		textButtonStyleExit.font = font;
		textButtonStyleExit.up = skins.getDrawable("exit");
		textButtonStyleExit.down = skins.getDrawable("exit");
		textButtonStyleExit.checked = skins.getDrawable("exit");
		textButtonStyleExit.over = skins.getDrawable("exit2");

		skin = new Skin();
		stage = new Stage();
		batch = new SpriteBatch();
		img = new Texture("pokefightback_v2.png");

		Gdx.input.setInputProcessor(stage);

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		/*	TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.WHITE);

		textButtonStyle.font = skin.getFont("default");*/

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton playButton=new TextButton("",textButtonStylePlay);
		playButton.setPosition(10, 5);
		playButton.setHeight(35);
		playButton.setWidth(102);
		stage.addActor(playButton);

		final TextButton exitButton = new TextButton("",textButtonStyleExit);
		exitButton.setPosition(135,5);
		exitButton.setHeight(35);
		exitButton.setWidth(91);
		stage.addActor(exitButton);

		playButton.addListener( new ClickListener() {              
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new LoadScreen());
			};
		});
		exitButton.addListener( new ClickListener() {              
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			};
		});
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
		batch.end();
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		//stage.setDebugAll(true);
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
		stage.dispose();
		skin.dispose();
	}

	@Override
	public void input(String text) {
		String iP = text;
		System.out.println(iP);
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}

}
