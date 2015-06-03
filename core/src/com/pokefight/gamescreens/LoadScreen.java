package com.pokefight.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;


public class LoadScreen implements Screen{
		Texture img;
		SpriteBatch batch;
		Animation animation;

		public LoadScreen(){
		
			batch = new SpriteBatch();
			img = new Texture("loading.jpg");

			//((Game)Gdx.app.getApplicationListener()).setScreen(new BattleScreen());
			
		
		Gdx.app.postRunnable(new Runnable() {
		     @Override
		     public void run() {
		         // Do something on the main thread
		    	 ((Game)Gdx.app.getApplicationListener()).setScreen(new BattleScreen());
		     }
		  });
		}

		@Override
		public void show() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void render(float delta) {
			Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			batch.draw(img, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
			batch.end();
			
		}

		@Override
		public void resize(int width, int height) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void pause() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resume() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void hide() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

}