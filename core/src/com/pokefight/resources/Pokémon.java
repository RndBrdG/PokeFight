package com.pokefight.resources;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.pokejava.Move;

public class Pokémon { // TODO: Lista de ataques
	private final int id;
	private final String name;
	private final Texture sprite;
	private final int maxHp;

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
