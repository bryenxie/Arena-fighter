/**
 * Character.java
 * Abstract class to hold various info for different characters
 * June 14 2017
 * @author Bryen & Michelle
 */

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

abstract class Character implements Command {
	
	BufferedImage icon;
	BufferedImage[] currentImage;
	BufferedImage[] idleL = new BufferedImage[2];
	BufferedImage[] idleR = new BufferedImage[2];
	BufferedImage[] blockL = new BufferedImage[2];
	BufferedImage[] blockR = new BufferedImage[2];
	BufferedImage[] atkL = new BufferedImage[5];
	BufferedImage[] atkR = new BufferedImage[5];
	BufferedImage[] jumpL = new BufferedImage[5];
	BufferedImage[] jumpR = new BufferedImage[5];
	BufferedImage[] lowAtkL = new BufferedImage[5];
	BufferedImage[] lowAtkR = new BufferedImage[5];
	BufferedImage[] specialL = new BufferedImage[7];
	BufferedImage[] specialR = new BufferedImage[7];
	BufferedImage[] collisionL = new BufferedImage[2];
	BufferedImage[] collisionR = new BufferedImage[2];

	private int hp;
	private int type;
	private int combo;
	private boolean block;
	private boolean attacking;
	private boolean alive;
	private boolean idle;
	private boolean jumping;
	private char direction;
	private int x,y;
	private int picIndex;
	private boolean showArrow;

	private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
	private Weapon weapon; //only for special

	private Rectangle boundingBox;

	/**
	 * Constructor
	 * All variables are the same at beginning of a game
	 */
	Character(){
		this.setHp(100);
		this.setAlive(true);
		this.setBlock(false);
		this.setAttacking(false);
		this.setShowArrow(false);
		this.setIdle(true);
		this.setPicIndex(0);
		this.setCombo(0);
		this.setJumping(false);
		this.setWeapon(null);
	}

	/**
	 * atk
	 * Method implemented from Command interface
	 * Adjusts character images and parameters for a high atk
	 */
	public void atk(char c){
		Weapon tempWeapon = null;
		//1-Archer 2-Assassin
		if(this.getType()==1) {
			tempWeapon = new Arrow();
			tempWeapon.setAtk(1);
			if (c == 'L') {
				this.setCurrentImage(atkL);
				tempWeapon.setX(this.getX());
				tempWeapon.setY(this.getY() + 130);
				tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(),tempWeapon.getY(),133,11));
				tempWeapon.setDirection('L');
			} else {
				this.setCurrentImage(atkR);
				tempWeapon.setX(this.getX() + 110);
				tempWeapon.setY(this.getY() + 130);
				tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(),tempWeapon.getY(),133,11));
				tempWeapon.setDirection('R');
			}
		} else {
			tempWeapon = new Dagger();
			tempWeapon.setAtk(1);
			if(c=='R') {
				tempWeapon.setX(this.getX() + 205);
				tempWeapon.setY(this.getY() + 140);
				tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(), tempWeapon.getY(), 100, 15));
				this.setCurrentImage(atkR);
			}
			else {
				tempWeapon.setX(this.getX()+ 15);
				tempWeapon.setY(this.getY() + 140);
				tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(), tempWeapon.getY(), 100, 15));
				this.setCurrentImage(atkL);
			}
		}
		this.weaponList.add(tempWeapon);
		this.setShowArrow(true);
		this.setAttacking(true);
		this.setIdle(false);
	}
	
	/**
	 * lowAtk
	 * Method implemented from Command interface
	 * Adjusts character images and parameters for a low atk
	 */
	public void lowAtk(char c){
		Weapon tempWeapon = null;
		//1-Archer 2-Assassin
		if(this.getType()==1){
			tempWeapon = new Arrow();
			tempWeapon.setAtk(2);
			if(c=='L'){
				this.setCurrentImage(lowAtkL);
				tempWeapon.setX(this.getX());
				tempWeapon.setY(this.getY()+130);
				tempWeapon.setDirection(this.getDirection());
				tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(),tempWeapon.getY(),133,45));
			} else {
				this.setCurrentImage(lowAtkR);
				tempWeapon.setX(this.getX()+110);
				tempWeapon.setY(this.getY()+130);
				tempWeapon.setDirection(this.getDirection());
				tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(),tempWeapon.getY(),133,45));
			}
		} else {
			tempWeapon = new Dagger();
			tempWeapon.setAtk(2);
			if (c == 'L') {
				this.setCurrentImage(lowAtkL);
				tempWeapon.setX(this.getX());
				tempWeapon.setY(this.getY()+130);
				tempWeapon.setDirection(this.getDirection());
				tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(),tempWeapon.getY(),133,45));
			} else {
				this.setCurrentImage(lowAtkR);
				tempWeapon.setX(this.getX()+110);
				tempWeapon.setY(this.getY()+130);
				tempWeapon.setDirection(this.getDirection());
				tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(),tempWeapon.getY(),133,45));
			}
		}
		this.weaponList.add(tempWeapon);
		this.setShowArrow(true);
		this.setAttacking(true);
		this.setIdle(false);
	}
	
	/**
	 * specialAtk
	 * Method implemented from Command interface
	 * Adjusts character images and parameters for a special attack
	 */
	public void specialAtk(char c, int xPos){
		this.setCombo(0);
		this.setShowArrow(true);
		Weapon tempWeapon;
		if(this.type == 1) {
			tempWeapon = new Arrow();
			tempWeapon.setX(this.getX()+140);
			tempWeapon.setY(this.getY()+100);
			((Arrow)tempWeapon).setDown(0); //sets arrow moving up
			tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(),tempWeapon.getY()-100,100,160));
			tempWeapon.setDirection('R');
			tempWeapon.setAtk(3);
			if (c == 'L') {
				this.setCurrentImage(specialL);
			} else {
				this.setCurrentImage(specialR);
			}
		} else {
			tempWeapon = new Dagger();
			tempWeapon.setAtk(3);
			if(this.getX() < xPos) {
				this.setX(xPos - 120);
				this.setCurrentImage(specialR);
			} else {
				this.setX(xPos + 150);
				this.setCurrentImage(specialL);
			}
			this.setBoundingBox(this.getX()+100, this.getY()+60, 100, 300);
		}
		this.setWeapon(tempWeapon);
		this.setAttacking(true);
		this.setIdle(false);
	}
	
	/**
	 * block
	 * Method implemented from Command interface
	 * Adjusts character images and parameters for a block
	 */
	public void block(char c){
		if (c == 'L') {
			this.setCurrentImage(blockL);
		} else {
			this.setCurrentImage(blockR);
		}
		this.setBlock(true);
		this.setIdle(false);
	}
	
	/**
	 * jump
	 * Method implemented from Command interface
	 * Adjusts character images and parameters for a jump
	 */
	public void jump(char c,double elapsedTime){
		if (c == 'L') {
			this.setCurrentImage(jumpL);
		} else {
			this.setCurrentImage(jumpR);
		}
		this.setIdle(false);
		this.setAttacking(true);
		this.setJumping(true);
	}
	
	/**
	 * move
	 * Method implemented from Command interface
	 * Adjusts character images and parameters to move left/right
	 */
	public void move(int x){
		this.setX(this.x + x);
		this.setIdle(true);
		this.setBoundingBox(this.getX()+100,this.getY()+60, 100, 300);
		//Changes image every time player moves
		if (this.getPicIndex() == 1) {
			this.setPicIndex(0);
		} else {
			this.setPicIndex(this.getPicIndex()+1);
		}
		if(x>0) {
			this.setDirection('R');
			this.setCurrentImage(idleR);
		} else {
			this.setDirection('L');
			this.setCurrentImage(idleL);
		}
	}

	/**
	 * update
	 * Method that updates a character's images to create animation
	 * Also updates the character's weapon's images
	 */
	public void update(double elapsedTime) {
		//Make the jump frames move up and down accordingly
		if (this.isJumping() == true) {
			if (this.getPicIndex() < 2) {
				this.setY(this.getY()-50);
				this.setBoundingBox(this.getX()+90, this.getY()+60, 100, 300);
			} else if (this.getPicIndex() > 2){
				this.setY(this.getY()+50);
				this.setBoundingBox(this.getX()+90, this.getY()+60, 100, 300);
			}
		}
		//Shuffles through entire array for each group of images
		if(this.getAttacking() == true) {
			if(this.getPicIndex() == currentImage.length-1){
				this.setPicIndex(0);
				if(this.isJumping() == true) {
					this.setY(230);
					this.setBoundingBox(this.getX()+90, this.getY()+60, 100, 300);
				}
				//removes bounding box for assassin high attack after attack finishes
				if(this.type == 2) {
					for(int i = 0; i<this.getWeaponList().size(); i++) {
						if(this.getWeaponList().get(i).getAtk() == 1) {
							this.getWeaponList().remove(i);
						}
					}
				}
				if(this.getDirection() == 'L') {
					this.setCurrentImage(idleL);
					this.setIdle(true);
					this.setAttacking(false);
					this.setJumping(false);
				} else {
					this.setCurrentImage(idleR);
					this.setAttacking(false);
					this.setIdle(true);
					this.setJumping(false);
				}
			} else {
				this.setPicIndex(this.getPicIndex()+1);

			}
		}
		else if (this.isBlock() == false) {
			if(this.getDirection() == 'L') {
				this.setCurrentImage(idleL);
			} else {
				this.setCurrentImage(idleR);
			}
		}
		//Updates weaponlist
		ArrayList<Weapon> tempList= this.getWeaponList();
		Weapon tempWeapon;
		for(int i=0; i<tempList.size(); i++){
			tempWeapon = tempList.get(i);
			if(this.getType() ==1) {
				((Arrow)tempWeapon).update(elapsedTime);
			} else {
				((Dagger)tempWeapon).update(elapsedTime);
			}
		}
	}

	//Getters and setters
	public void setCombo(int combo) {
		//To make sure the combo bar does not overflow
		if(combo>100) {
			combo = 100;
		}
		this.combo = combo;
	}
	public boolean damage(int damage){
		this.setHp(this.getHp()-damage);
		return checkAlive();
	}
	public boolean checkAlive(){
		if (this.hp <= 0) {
			alive = false;
		}
		return getAlive();
	}
	public BufferedImage[] getBlockL() {
		return blockL;
	}
	public BufferedImage[] getCollisionL() {
		return collisionL;
	}
	public void setCollisionL(BufferedImage[] collisionL) {
		this.collisionL = collisionL;
	}
	public BufferedImage[] getCollisionR() {
		return collisionR;
	}
	public void setCollisionR(BufferedImage[] collisionR) {
		this.collisionR = collisionR;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public ArrayList<Weapon> getWeaponList() {
		return weaponList;
	}
	public void setWeaponList(ArrayList<Weapon> weaponList) {
		this.weaponList = weaponList;
	}
	public boolean isIdle() {
		return idle;
	}
	public void setIdle(boolean idle) {
		this.idle = idle;
	}
	public BufferedImage getIcon() {
		return icon;
	}
	public void setIcon(BufferedImage icon) {
		this.icon = icon;
	}
	public int getCombo() {
		return combo;
	}
	public boolean isShowArrow() {
		return showArrow;
	}
	public void setShowArrow(boolean showArrow) {
		this.showArrow = showArrow;
	}
	public void setBlockL(BufferedImage[] blockL) {
		this.blockL = blockL;
	}
	public BufferedImage[] getBlockR() {
		return blockR;
	}
	public void setBlockR(BufferedImage[] blockR) {
		this.blockR = blockR;
	}
	public BufferedImage[] getAtkL() {
		return atkL;
	}
	public void setAtkL(BufferedImage[] atkL) {
		this.atkL = atkL;
	}
	public BufferedImage[] getAtkR() {
		return atkR;
	}
	public void setAtkR(BufferedImage[] atkR) {
		this.atkR = atkR;
	}
	public BufferedImage[] getJumpL() {
		return jumpL;
	}
	public void setJumpL(BufferedImage[] jumpL) {
		this.jumpL = jumpL;
	}
	public BufferedImage[] getJumpR() {
		return jumpR;
	}
	public void setJumpR(BufferedImage[] jumpR) {
		this.jumpR = jumpR;
	}
	public BufferedImage[] getLowAtkL() {
		return lowAtkL;
	}
	public void setLowAtkL(BufferedImage[] lowAtkL) {
		this.lowAtkL = lowAtkL;
	}
	public BufferedImage[] getLowAtkR() {
		return lowAtkR;
	}
	public void setLowAtkR(BufferedImage[] lowAtkR) {
		this.lowAtkR = lowAtkR;
	}
	public BufferedImage[] getSpecialL() {
		return specialL;
	}
	public void setSpecialL(BufferedImage[] specialL) {
		this.specialL = specialL;
	}
	public BufferedImage[] getSpecialR() {
		return specialR;
	}
	public void setSpecialR(BufferedImage[] specialR) {
		this.specialR = specialR;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	} 
	public boolean isBlock() {
		return block;
	}
	public void setBlock(boolean block) {
		this.block = block;
	}
	public int getPicIndex() {
		return picIndex;
	}
	public void setPicIndex(int picIndex) {
		this.picIndex = picIndex;
	}
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public BufferedImage[] getIdleL() {
		return idleL;
	}
	public void setIdleL(BufferedImage[] idleL) {
		this.idleL = idleL;
	}
	public BufferedImage[] getIdleR() {
		return idleR;
	}
	public void setIdleR(BufferedImage[] idleR) {
		this.idleR = idleR;
	}
	public BufferedImage[] getCurrentImage() {
		return currentImage;
	}
	public void setCurrentImage(BufferedImage[] currentImage) {
		this.currentImage = currentImage;
	}
	public void setDirection (char direction){
		this.direction = direction;
	}
	public char getDirection(){
		return this.direction;
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setAttacking(boolean attacking){
		this.attacking = attacking;
	}
	public boolean getAttacking(){
		return this.attacking;
	}

	boolean isJumping(){
		return  this.jumping;
	}
	void setJumping(boolean jumping){
		this.jumping = jumping;
	}
	boolean getAlive(){
		return this.alive;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public void setBoundingBox(int x,int y, int width,int height){
		this.boundingBox = new Rectangle(x,y,width,height);
	}
	public void draw(Graphics g) {
	}

}


