/**
 * Arrow.java
 * Class to create an object arrow, which extends weapon class
 * June 14 2017
 * @author Bryen & Michelle
 */

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Arrow extends Weapon {
	private BufferedImage arrowL;
	private BufferedImage arrowR;
	private BufferedImage arrowUp;
	private BufferedImage arrowDown;
	private BufferedImage arrowLowL;
	private BufferedImage arrowLowR;
	private int down = 0; //if 0, arrow moving up, if 1 moving down

	/**
	 * Constructor
	 * Reads all images for an arrow
	 */
	public Arrow() {
		try {
			arrowL = ImageIO.read(new File("images/Archer/Weapon/arrowL.png"));
			arrowR = ImageIO.read(new File("images/Archer/Weapon/arrowR.png"));
			arrowUp = ImageIO.read(new File("images/Archer/Weapon/arrowsUp.png"));
			arrowDown = ImageIO.read(new File("images/Archer/Weapon/arrowsDown.png"));
			arrowLowL = ImageIO.read(new File("images/Archer/Weapon/arrowLowL.png"));
			arrowLowR = ImageIO.read(new File("images/Archer/Weapon/arrowLowR.png"));
		} catch (Exception e) {
			System.out.println("error loading Arrow");
		}
	}

	/**
	 * update
	 * Method to update the position of an arrow after the elapsed time
	 * @param elapsedTime, elapsed time in milliseconds
	 */
	public void update(double elapsedTime) {
		//High attack moves horizontally
		if (this.getAtk() == 1) {
			if (this.getDirection() == 'L') {
				this.setX(this.getX() -(int)(this.getSpeed()*elapsedTime*10));
				this.setBoundingBox(new Rectangle(this.getX(),this.getY(),133,11));
			} else {
				this.setX(this.getX() +(int)(this.getSpeed()*elapsedTime * 10));
				this.setBoundingBox(new Rectangle(this.getX(),this.getY(),133,11));
			}
		} else if (this.getAtk() == 2) { //Low attack moves towards the ground
			if (this.getDirection() == 'L') {
				this.setX(this.getX() -(int)(this.getSpeed()*elapsedTime*8));
				this.setY(this.getY() +(int)(this.getSpeed()*elapsedTime*2.5));
				this.setBoundingBox(new Rectangle(this.getX(),this.getY(),133,45));
			} else {
				this.setX(this.getX() +(int)(this.getSpeed()*elapsedTime*8));
				this.setY(this.getY() +(int)(this.getSpeed()*2.5*elapsedTime));
				this.setBoundingBox(new Rectangle(this.getX(), this.getY(),133,45));
			}
		} else if (this.getAtk() == 3) { //Special attack moves up and then down on the opponent
			this.setBoundingBox(new Rectangle(this.getX(), this.getY(),100,150));
			if(this.getDown() == 0) {
				this.setY(this.getY() - (int) (this.getSpeed() * 10 * elapsedTime));
			} else {
				this.setDown(1);
				this.setY(this.getY() + (int) (this.getSpeed() * 10 * elapsedTime));
			}
		}
	}

	/**
	 * draw
	 * Method to decide which image will be drawn
	 */
	public void draw(Graphics g) {
		if (this.getDirection() == 'L') {
			if(this.getAtk() ==1) {
				g.drawImage(this.getArrowL(), this.getX(), this.getY(), null);
			}
			else if(this.getAtk() == 2){
				g.drawImage(this.getArrowLowL(),this.getX(), this.getY(), null);
			}
		}
		else if(this.getDirection()=='R'){
			if(this.getAtk()==1) {
				g.drawImage(this.getArrowR(), this.getX(), this.getY(), null);
			}
			else if(this.getAtk()==2){
				g.drawImage(this.getArrowLowR(),this.getX(), this.getY(), null);
			}
			else if(this.getAtk()==3&&this.getDown()==0){
				g.drawImage(this.getArrowUp(),this.getX(),this.getY(),null);
			}
			else{
				g.drawImage(this.getArrowDown(),this.getX(),this.getY(),null);
			}
		}
	}

	//Getters and setters
	public BufferedImage getArrowUp() {
		return arrowUp;
	}
	public void setArrowUp(BufferedImage arrowUp) {
		this.arrowUp = arrowUp;
	}
	public BufferedImage getArrowDown() {
		return arrowDown;
	}
	public void setArrowDown(BufferedImage arrowDown) {
		this.arrowDown = arrowDown;
	}
	public BufferedImage getArrowLowL() {
		return arrowLowL;
	}
	public void setArrowLowL(BufferedImage arrowLowL) {
		this.arrowLowL = arrowLowL;
	}
	public BufferedImage getArrowLowR() {
		return arrowLowR;
	}
	public void setArrowLowR(BufferedImage arrowLowR) {
		this.arrowLowR = arrowLowR;
	}
	public BufferedImage getArrowL() {
		return arrowL;
	}
	public void setArrowL(BufferedImage arrowL) {
		this.arrowL = arrowL;
	}
	public BufferedImage getArrowR() {
		return arrowR;
	}
	public void setArrowR(BufferedImage arrowR) {
		this.arrowR = arrowR;
	}
	public void setDown(int i){
		this.down=i;
	}
	public int getDown(){
		return this.down;
	}
}

