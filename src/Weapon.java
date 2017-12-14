/**
 * Weapon.java
 * Abstract class to hold variables for different weapons used
 * June 14 2017
 * @author Bryen & Michelle
 * 
 */

import java.awt.*;

abstract public class Weapon {
	private int x, y;
	private int speed = 50;
	private Rectangle boundingBox;
	private int atk; // 1-high 2-low 3- special
	private char direction;

	//Constructor
	Weapon() {
	}

	//Getters and setters
	public char getDirection() {
		return direction;
	}
	public void setDirection(char direction) {
		this.direction = direction;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public void update(){
	}
	void setX(int x) {
		this.x = x;
	}
	void setY(int y) {
		this.y = y;
	}
	void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	void setSpeed(int speed) {
		this.speed = speed;
	}
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getSpeed() {
		return speed;
	}
	public void draw(Graphics g) {
	}
}
