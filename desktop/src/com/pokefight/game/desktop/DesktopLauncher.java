package com.pokefight.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pokefight.game.PokeFight;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "PokeFight";
	    config.width = 255;
	    config.height = 143 + 44;
		new LwjglApplication(new PokeFight(), config);
	}
}
