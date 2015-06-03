package com.pokefight.gameplay;

import java.util.ArrayList;

import com.pokejava.Ability;
import com.pokejava.Move;

public class Battle_Pokemon {
	private int id;
	private String name;
	private int level;
	private int experience;
	private int attack;
	private int defense;
	private int hp;
	private ArrayList<Move> moves;
	
	public Battle_Pokemon(int id, String name, int level, int experience, int attack, int defense, int hp, ArrayList<Move> moves){
		this.id = id;
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
		this.moves = moves;
	}
	
	public int getId() {
		return id;
	}

	public String getName(){
		return this.name;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getHp() {
		return hp;
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}
	
}
