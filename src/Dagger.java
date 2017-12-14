/**
 * Dagger.java
 * Class to create an object dagger, which extends weapon class 
 * June 14 2017
 * @author Bryen & Michelle
 */

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Dagger extends Weapon{
	private BufferedImage daggerL;
	private BufferedImage daggerR;
	final private int width = 67;
	final private int height = 50;

	/**
	 * Constructor
	 */
	public Dagger() {
		try {
			this.daggerL = ImageIO.read(new File("images/Assassin/Weapon/daggerL.png"));
			this.daggerR = ImageIO.read(new File("images/Assassin/Weapon/daggerR.png"));
		} catch (Exception e) {
			System.out.println("error loading Arrow");
		}
	}

	/**
	 * update
	 * Method to update the position of dagger overtime
	 * @param elapsedTime, elapsed time in milliseconds
	 */
	public void update(double elapsedTime) {
		//only assassin's low attack is a projectile
		if (this.getAtk() == 2) {
			if (this.getDirection() == 'L') {
				this.setX(this.getX() -(int)(this.getSpeed()*elapsedTime*8));
				this.setY(this.getY() +(int)(this.getSpeed()*elapsedTime*2.5));
				this.setBoundingBox(new Rectangle(this.getX(),this.getY(),this.width,this.height));
			} else {
				this.setX(this.getX() +(int)(this.getSpeed()*elapsedTime * 8));
				this.setY(this.getY() +(int)(this.getSpeed()*elapsedTime*2.5));
				this.setBoundingBox(new Rectangle(this.getX(),this.getY(),this.width,this.height));
			}
		}
	}
	
	/**
	 * draw
	 * Method to draw the dagger 
	 */
	public void draw(Graphics g) {
		//only the low attack needs to be drawn separately from the char
		if (this.getDirection() == 'L') {
			if(this.getAtk() ==2) {
				g.drawImage(this.getDaggerL(), this.getX(), this.getY(), null);
			}
		}
		else if(this.getDirection()=='R'){
			if(this.getAtk()==2) {
				g.drawImage(this.getDaggerR(), this.getX(), this.getY(), null);
			}
		}
	}
	
	//getters and setters
	public BufferedImage getDaggerL() {
		return daggerL;
	}
	public void setDaggerL(BufferedImage daggerL) {
		this.daggerL = daggerL;
	}
	public BufferedImage getDaggerR() {
		return daggerR;
	}
	public void setDaggerR(BufferedImage daggerR) {
		this.daggerR = daggerR;
	}

}