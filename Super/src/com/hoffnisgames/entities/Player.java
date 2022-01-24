package com.hoffnisgames.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.Camera;
import com.hoffnisgames.world.World;

public class Player extends Entity{


	public boolean right, left;
	private double grav = 2;
	public int dir = 1;
	public boolean jump = false;
	public boolean jumping = false;
	public int pulo = 44;
	public int jumpframes = 0;
	private int anima = 0;
	private int maxAnima = 6;
	private int maxsprite = 4;
	private int cursprite = 0;
	private boolean moved = false;
	public static int coinAtual = 0;
	public static int coinMax = 100;
	public int vida = 100;

	public Player(int x, int y, int width, int heigth, double speed, BufferedImage sprite) {
		super(x, y, width, heigth, speed, sprite);
	
		
	}
	public void tick() {
	
		depth = 2;
	if(World.isFree((int)x, (int)(y+grav)) && jumping == false) {
		y+=grav;
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
		if(e instanceof Enemy1) {
		if(Entity.isCollinding(this, e)) {
			
			
	
				jumping = true;
				pulo = 28;
				((Enemy1) e).ivida--;
				if(((Enemy1) e).ivida <= 0) {
					Game.entities.remove(i);
					break;
				}
			
			
		}
		}
		}
		}
	
	
	if(right && World.isFree((int)(x+speed), (int)y)) {
		x+=speed;
		dir=1;
		moved = true;
		
	} else if(left && World.isFree((int)(x-speed), (int)y)) {
		x-=speed;
		dir=0;
		moved = true;
	} else {
		moved=false;
	}
	
	if(jump) {
		if(!World.isFree(this.getX(), this.getY()+1)){
			jumping = true;
		}else {
			jump = false;
		}
	}
	
	if(jumping) {
		if(World.isFree(this.getX(), this.getY()-2)) {
			y-=2;
			jumpframes+=2;
			if(jumpframes == pulo) {
				jumping = false;
				jump = false;
				jumpframes = 0;
			}
		}else {
			jumping = false;
			jump = false;
			jumpframes = 0;
		}
	}
	
	
	for(int i = 0; i < Game.entities.size() ; i++) {
		Entity e = Game.entities.get(i);
		if(e instanceof Enemy1) {
			if(Entity.isCollinding(this, e)) {
				vida--;
			}
		}
		
	}
	
	for(int i = 0; i < Game.entities.size() ; i++) {
		Entity e = Game.entities.get(i);
		if(e instanceof Moeda) {
			if(Entity.isCollinding(this, e)) {
				Game.entities.remove(i);
				Player.coinAtual++;
				
				break;
			}
		}
		
	}
	
	
	Camera.x = Camera.clamp((int)x - Game.WIDTH / 2, 0, World.WIDTH * 16 - Game.WIDTH);
	Camera.y = Camera.clamp((int)y - Game.HEIGHT / 2, 0, World.HEIGTH * 16 - Game.HEIGHT);
	
	}
	
	
	
	public void render(Graphics g) {
		
		if(moved) {
		anima++;
		if(anima == maxAnima) {
			cursprite++;
			anima = 0;
			if(cursprite == maxsprite) {
				cursprite = 0;
			}
		}
		} else {
			cursprite = 0;
		}
		
		if(dir == 1) {
			sprite = Entity.PLAYER_RIGHT[cursprite];
		}
		else if (dir == 0) {
			sprite = Entity.PLAYER_LEFT[cursprite];
		}
		
		super.render(g);
	}
	
	
	
	
	
	


}
