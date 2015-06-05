package com.pokefight.resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.pokefight.oakserver.MoveRequest;
import com.pokefight.oakserver.PokémonMoveRequest;
import com.pokefight.oakserver.PokémonRequest;
import com.pokejava.Move;

public class Pokémon {
	private int id;
	private String name;
	private Texture sprite;
	private int attack;
	private int defense;
	private int maxHp;
	private int currentHp;
	private ArrayList<Move> moves;

	public Pokémon(final int id) {
		Client client = new Client();
		
		Kryo kryo = client.getKryo();
		kryo.register(PokémonRequest.class);
	    kryo.register(Pokémon.class);
		kryo.register(MoveRequest.class);
	    kryo.register(com.pokefight.resources.Move.class);
		kryo.register(PokémonMoveRequest.class);
		kryo.register(ArrayList.class);

		client.addListener(new Listener() {
			@Override
			public void connected(Connection conn) {
				System.out.println("[Pokémon] Ligado!");
				
				PokémonRequest req = new PokémonRequest(id);
				conn.sendUDP(req);
			}
			@Override
			public void disconnected(Connection conn) {
				System.out.println("[Pokémon] Desligado.");
			}
			@Override
			public void received(Connection conn, Object obj) {
				System.out.println("[Pokémon] Recebeu " + obj.getClass());
				
				if (obj instanceof Pokémon) {
					Pokémon response = (Pokémon) obj;

					Pokémon.this.id = response.getId();
					Pokémon.this.name = response.getName();
					Pokémon.this.sprite = response.getSprite();
					Pokémon.this.attack = response.getAttack();
					Pokémon.this.defense = response.getDefense();
					Pokémon.this.maxHp = response.getMaxHp();
					Pokémon.this.currentHp = maxHp;
				}
			}
		});
		
		try {
			client.start();
			client.connect(5000, "127.0.0.1", 54555, 54777); // TODO: mudar host
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//constructMoves();
	}

	public Pokémon(int id, String name, String sprite, int attack, int defense, int maxHp) {
		this.id = id;
		this.name = name;
		byte[] spriteByte = null;
		try {
			spriteByte = sprite.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.sprite = new Texture(new Pixmap(spriteByte, 0, spriteByte.length));
		this.attack = attack;
		this.defense = defense;
		this.maxHp = maxHp;
		currentHp = maxHp;
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

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void decreaseCurrentHp(int deltaHp) {
		currentHp -= deltaHp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}
}
