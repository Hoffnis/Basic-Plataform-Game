package com.hoffnisgames.entities;

import java.awt.image.BufferedImage;

import com.hoffnisgames.world.World;

public class Enemy1 extends Entity {
	
	public boolean rigth = true, left = false;
	public int ivida = 2;

	public Enemy1(double x, double y, int width, int heigth, double speed, BufferedImage sprite) {
		super(x, y, width, heigth, speed, sprite);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		if(World.isFree((int)x, (int)(y+1))) {
			y+=1;
		}
		
		if(rigth) {
			if(World.isFree((int)(x+speed), (int)y)) {
			x+=speed;
			if(World.isFree((int)x+16, (int)y+1)) {
				rigth = false;
				left = true;	
			}
			}else {
				rigth = false;
				left = true;
			}
		}
		
		
		if(left){
			if(World.isFree((int)(x-speed), (int)y)) {
			x-=speed;
			if(World.isFree((int)x-16, (int)y+1)) {
				rigth = true;
				left = false;
			}
		}else {
			rigth = true;
			left = false;
		}
			}
		
	}
}
