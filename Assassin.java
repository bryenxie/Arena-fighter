/**
 * Assassin.java
 * Class with to create object Assassin which extends character class
 * June 14 2017
 * @author Bryen & Michelle
 */

import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;

public class Assassin extends Character {

	/**
	 * Constructor
	 * @param p, 1 for player1, 2 for player2 - determines the start position of character
	 */
	public Assassin(int p) {
		super();
		this.setType(2);
		try {
			this.icon = ImageIO.read(new File("images/Assassin/panIcon.png"));
			this.idleL[0] = ImageIO.read(new File("images/Assassin/Idle/panIdleL1.png"));
			this.idleL[1] = ImageIO.read(new File("images/Assassin/Idle/panIdleL2.png"));
			this.idleR[0] = ImageIO.read(new File("images/Assassin/Idle/panIdleR1.png"));
			this.idleR[1] = ImageIO.read(new File("images/Assassin/Idle/panIdleR2.png"));
			this.blockL[0] = ImageIO.read(new File("images/Assassin/Block/panBlockL.png"));
			this.blockL[1] = ImageIO.read(new File("images/Assassin/Block/panBlockL.png"));
			this.blockR[0] = ImageIO.read(new File("images/Assassin/Block/panBlockR.png"));
			this.blockR[1] = ImageIO.read(new File("images/Assassin/Block/panBlockR.png"));
			this.collisionL[0] = ImageIO.read(new File("images/Assassin/Collision/panCollisionL.png"));
			this.collisionL[1] = ImageIO.read(new File("images/Assassin/Collision/panCollisionL.png"));
			this.collisionR[0] = ImageIO.read(new File("images/Assassin/Collision/panCollisionR.png"));
			this.collisionR[1] = ImageIO.read(new File("images/Assassin/Collision/panCollisionR.png"));

			int i;
			for (i= 0; i<5; i++){
				this.atkL[i] = ImageIO.read(new File("images/Assassin/Atk/panAtkL"+(i+1)+".png"));
				this.atkR[i] = ImageIO.read(new File("images/Assassin/Atk/panAtkR"+(i+1)+".png"));
				this.jumpL[i] = ImageIO.read(new File("images/Assassin/Jump/panJumpL"+(i+1)+".png"));
				this.jumpR[i] = ImageIO.read(new File("images/Assassin/Jump/panJumpR"+(i+1)+".png"));
				this.lowAtkL[i] =ImageIO.read(new File("images/Assassin/Lowatk/panLowAtkL"+(i+1)+".png"));
				this.lowAtkR[i] =ImageIO.read(new File("images/Assassin/Lowatk/panLowAtkR"+(i+1)+".png"));
			}
			for( i = 0; i< 7; i++){
				this.specialL[i] =ImageIO.read(new File("images/Assassin/Special/panSpecialL"+(i+1)+".png"));
				this.specialR[i] =ImageIO.read(new File("images/Assassin/Special/panSpecialR"+(i+1)+".png"));
			}

			if (p == 1) {
				this.currentImage = this.idleR;
				this.setDirection('R');
				this.setX(30);
				this.setY(230);
				this.setBoundingBox(this.getX()+90,this.getY()+60,100,300);
			} else {
				this.currentImage = this.idleL;
				this.setDirection('L');
				this.setX(930);
				this.setY(230);
				this.setBoundingBox(this.getX()+100,this.getY()+60,100,300);
			}

		} catch(Exception e) { 
			System.out.println("error loading Dagger");
		}
	}

	/**
	 * draw
	 * Method to draw Assassin character
	 */
	public void draw(Graphics g){
		g.drawImage(currentImage[this.getPicIndex()], this.getX(), this.getY(), null);
	}

}
