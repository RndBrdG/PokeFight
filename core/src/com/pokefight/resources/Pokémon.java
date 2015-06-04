package com.pokefight.resources;

<<<<<<< HEAD
@SuppressWarnings("unused")
=======
import java.io.UnsupportedEncodingException;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

>>>>>>> origin/master
public class Pokémon {
	private int id;
	private String name;
	private Texture sprite;
	private int maxHp;

	public Pokémon(int id, String name, String sprite, int maxHp) {
		this.id = id;
		this.name = name;
		byte[] spriteByte = null;
		try {
			spriteByte = sprite.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.sprite = new Texture(new Pixmap(spriteByte, 0, spriteByte.length));
		this.maxHp = maxHp;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Texture getSprite() {
		return sprite;
	}
	
	public int getMaxHp() {
		return maxHp;
	}
}
