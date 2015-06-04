package com.pokefight.oakserver;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.json.JSONObject;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.pokefight.resources.Pokémon;
import com.pokejava.Pokemon;
import com.pokejava.Sprite;

public class OakMain {
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
		try {
			server.bind(54555, 54777);
		} catch (IOException e) {
			e.printStackTrace();
		}

		server.addListener(new Listener() {
			public void receive(Connection conn, Object obj) {
				if (obj instanceof PokémonRequest) {
					int id = 0;
					String name = "";
					String sprite = "";
					int maxHp = 0;

					PokémonRequest req = (PokémonRequest) obj;
					ResourceResponse resp = new ResourceResponse(req);

					try {
						JSONObject jsonResp = resp.getResponse();

						id = jsonResp.getInt("id");
						name = jsonResp.getString("name");
						sprite = jsonResp.getString("sprite");
						maxHp = jsonResp.getInt("maxHp");
					} catch (OakServerException e) {
						Pokemon newPokemon = new com.pokejava.Pokemon(req.getId());
						name = newPokemon.getName();
						Sprite newPokemonSprite = new Sprite(req.getId());
						maxHp = newPokemon.getHP();

						BufferedImage spriteImg = null;
						try {
							URL url = new URL("http://www.pokeapi.co" + newPokemonSprite.getImage());
							spriteImg = ImageIO.read(url);
						} catch (IOException i) {
							i.printStackTrace();
						}
						
						try {
							sprite = new String(((DataBufferByte) spriteImg.getData().getDataBuffer()).getData(), "ISO-8859-1");
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					conn.sendUDP(new Pokémon(id, name, sprite, maxHp));
				}
			}
		});
	}
}
