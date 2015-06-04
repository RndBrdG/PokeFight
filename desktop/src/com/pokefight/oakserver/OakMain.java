package com.pokefight.oakserver;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageIO;

import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.json.JSONException;
import org.json.JSONObject;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.pokefight.resources.Pokémon;
import com.pokejava.Move;
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

						id = jsonResp.getInt("pkmnId");
						name = jsonResp.getString("name");
						sprite = jsonResp.getString("sprite");
						maxHp = jsonResp.getInt("hp");
					} catch (OakServerException e) {
						id = req.getId();
						Pokemon newPokemon = new Pokemon(id);
						name = newPokemon.getName();
						Sprite newPokemonSprite = new Sprite(id);
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
						
						Map<String, String> jsonParameters = new HashMap<String, String>();
						jsonParameters.put("pkmnId", new Integer(id).toString());
						jsonParameters.put("name", name);
						jsonParameters.put("sprite", sprite);
						jsonParameters.put("hp", new Integer(maxHp).toString());
						JSONObject newPokemonJson = new JSONObject(jsonParameters);
						
						postToHttp("pokemon/" + id, newPokemonJson);
					} catch (Exception e) {
						e.printStackTrace();
					}

					conn.sendUDP(new Pokémon(id, name, sprite, maxHp));
				}
			}
		});
		
		server.addListener(new Listener() {
			public void receive(Connection conn, Object obj) {
				if (obj instanceof MoveRequest) {
					int id = 0;
					String name = "";
					int power = 0;

					MoveRequest req = (MoveRequest) obj;
					ResourceResponse resp = new ResourceResponse(req);

					try {
						JSONObject jsonResp = resp.getResponse();

						id = jsonResp.getInt("moveid");
						name = jsonResp.getString("name");
						power = jsonResp.getInt("power");
					} catch (OakServerException e) {
						Move newMove = new com.pokejava.Move(req.getId());
						name = newMove.getName();
						power = newMove.getPower();
						
						Map<String, String> jsonParameters = new HashMap<String, String>();
						jsonParameters.put("moveid", new Integer(id).toString());
						jsonParameters.put("name", name);
						jsonParameters.put("power", new Integer(power).toString());
						JSONObject newMoveJson = new JSONObject(jsonParameters);
						
						postToHttp("move/" + id, newMoveJson);
					
					} catch (Exception e) {
						e.printStackTrace();
					}
					conn.sendUDP(new Move(id, name, power));
				}
			}
		});
	}

	private static void postToHttp(String apiUrl, JSONObject postArguments) {
		try {
			Form toSend = Form.form();
			for (String key : JSONObject.getNames(postArguments)) {
				try {
					toSend.add(key, postArguments.getString(key));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}			

			Response serverResponse = Request.Post("localhost/api/" + apiUrl)
					.bodyForm(toSend.build())
					.execute();
			if (serverResponse.returnResponse().getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				try {
					throw new OakServerException();
				} catch (OakServerException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
