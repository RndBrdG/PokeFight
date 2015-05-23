package com.pokefight.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pokejava.Sprite;

public class BattleScreen implements Screen{
	SpriteBatch batch = new SpriteBatch();
	Sprite pokeenemy = new Sprite(6); // seleção do pokemon 
	Sprite pokeme = new Sprite(2);
	Texture pokeenemytext = new Texture("." + pokeenemy.getImage());
	TextureRegion pokeenemytextreg = new TextureRegion(pokeenemytext);
	Texture pokemetext = new Texture("." + pokeme.getImage());
	TextureRegion pokemetextreg = new TextureRegion(pokemetext);
	
	Texture hpenemy = new Texture("media/img/hp-foe.png");
	TextureRegion hpenemytextreg = new TextureRegion(hpenemy);
	
	Texture hpme = new Texture("media/img/hp-me.png");
	TextureRegion hpmetextreg = new TextureRegion(hpme);
	
	Texture lifered = new Texture("media/img/hp-low.png");
	TextureRegion lifered1 = new TextureRegion(lifered);
	
	Texture dialogbox = new Texture("media/img/dialog-box.png");
	TextureRegion dialogboxtextreg = new TextureRegion(dialogbox);
	

	
	Texture battlefield = new Texture("battlefields/1.png");
	private float count =360.0f;
	private float count1 =130.0f;
	private float count2 =0.0f;
	private float count3 =48.0f;

	public BattleScreen(){

		
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
		batch.draw(pokeenemytextreg, (float)250, (float)100,(float) 245,(float) 150,(float) count1, (float)count1, (float)1, (float)1,(float)0);
		batch.draw(pokemetextreg, 16.f, 20.f,(float) 50,(float) 50,(float) 130, (float)130, (float)-1, (float)1,(float)0);
		batch.draw(hpenemytextreg, 0.f, 170.f,(float) 50,(float) 50,(float) 122, (float)33, (float)1, (float)1,(float)0);
		batch.draw(hpmetextreg, 272.f, 50.f,(float) 50,(float) 50,(float) 128, (float)42, (float)1, (float)1,(float)0);
		batch.draw(lifered1, 50.f, 178.f,(float) 50,(float) 50,(float) count2, (float)2, (float)1, (float)1,(float)0);
		batch.draw(lifered1, 344.f, 66.f,(float) 50,(float) 50,(float) count2, (float)2, (float)1, (float)1,(float)0);
		batch.draw(dialogboxtextreg, 0.f, 0.f,(float) 50,(float) 50,(float) 400, (float)44, (float)1, (float)1,(float)0);
		batch.end();
		
		if(count3 < 0.0f)
			count3 = 48.0f;
			else
			count3 --;
		
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
			count --;

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
	

}
