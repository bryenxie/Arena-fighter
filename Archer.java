/**
 * Archer.java
 * Class to create an object Archer, which extends character class
 * June 14 2017
 * @author Bryen & Michelle
 */

import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;

public class Archer extends Character {

	/**
	 * Constructor
	 * @param p, 1 for player1, 2 for player2, determines the start position of character
	 */
	public Archer(int p) {
		super();
		this.setType(1);
		try {
			this.icon = ImageIO.read(new File("images/Archer/maoIcon.png"));
			this.idleL[0] = ImageIO.read(new File("images/Archer/Idle/maoIdleL1.png"));
			this.idleL[1] = ImageIO.read(new File("images/Archer/Idle/maoIdleL2.png"));
			this.idleR[0] = ImageIO.read(new File("images/Archer/Idle/maoIdleR1.png"));
			this.idleR[1] = ImageIO.read(new File("images/Archer/Idle/maoIdleR2.png"));
			this.blockL[0] = ImageIO.read(new File("images/Archer/Block/maoBlockL2.png"));
			this.blockL[1] = ImageIO.read(new File("images/Archer/Block/maoBlockL2.png"));
			this.blockR[0] = ImageIO.read(new File("images/Archer/Block/maoBlockR2.png"));
			this.blockR[1] = ImageIO.read(new File("images/Archer/Block/maoBlockR2.png"));
			this.collisionL[0] = ImageIO.read(new File("images/Archer/Collision/maoCollisionL.png"));
			this.collisionL[1] = ImageIO.read(new File("images/Archer/Collision/maoCollisionL.png"));
			this.collisionR[0] = ImageIO.read(new File("images/Archer/Collision/maoCollisionR.png"));
			this.collisionR[1] = ImageIO.read(new File("images/Archer/Collision/maoCollisionR.png"));
			
			int i;
			for (i= 0; i<5; i++){
				this.atkL[i] = ImageIO.read(new File("images/Archer/Atk/maoAtkL"+(i+1)+".png"));
				this.atkR[i] = ImageIO.read(new File("images/Archer/Atk/maoAtkR"+(i+1)+".png"));
				this.jumpL[i] = ImageIO.read(new File("images/Archer/Jump/maoJumpL"+(i+1)+".png"));
				this.jumpR[i] = ImageIO.read(new File("images/Archer/Jump/maoJumpR"+(i+1)+".png"));
				this.lowAtkL[i] =ImageIO.read(new File("images/Archer/Lowatk/maoLowAtkL"+(i+1)+".png"));
				this.lowAtkR[i] =ImageIO.read(new File("images/Archer/Lowatk/maoLowAtkR"+(i+1)+".png"));
			}
			for(i = 0; i< 7; i++){
				this.specialL[i] =ImageIO.read(new File("images/Archer/Special/maoSpecialL"+(i+1)+".png"));
				this.specialR[i] =ImageIO.read(new File("images/Archer/Special/maoSpecialR"+(i+1)+".png"));
			}

			if (p == 1) {
				this.currentImage = this.idleR;
				this.setDirection('R');
				this.setX(30);
				this.setY(230);
				this.setBoundingBox(this.getX()+100,this.getY()+40,100,300);
			} else {
				this.currentImage = this.idleL;
				this.setDirection('L');
				this.setX(930);
				this.setY(230);
				this.setBoundingBox(this.getX()+100,this.getY()+40,100,300);
			}
		} catch(Exception e) { System.out.println("error loading Archer");}
	}

	/**
	 * draw
	 * Method to draw Archer character
	 */
	public void draw(Graphics g){
		g.drawImage(currentImage[this.getPicIndex()], this.getX(), this.getY(), null);
	}

}
