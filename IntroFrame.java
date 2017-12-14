/**
 * IntroFrame.java
 * Class to create the gui for the game, along with the events, actions and animations (drawing)
 * June 14 2017
 * @author Bryen & Michelle
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.JPanel;

class IntroFrame extends JFrame {
	JButton quit = new JButton("Quit");
	JButton instruction = new JButton("Instructions");
	JButton play = new JButton("Play");
	static Frame introFrame;
	static Frame instructionFrame;
	static Frame characterSelectionFrame;
	static Frame charDetailFrame;
	static Frame gameFrame;
	static Frame gameOverFrame;
	Character player1;
	Character player2;
	boolean p1Alive = true;
	boolean p2Alive = true;
	int startBgIndex = -1;
	BufferedImage[] startBg = new BufferedImage[3];

	/**
	 * Constructor
	 */
	IntroFrame() {

		try {
			for(int i =0; i< 3; i++) {
				startBg[i] = ImageIO.read(new File("images/startBG/startBg"+(i+1)+".png"));
			}
		} catch (Exception e) {
			System.out.println("error loading start Bg");
		}

		introFrame = this;
		this.setTitle("fighting Frame");
		this.setPreferredSize(new Dimension(1270, 720));
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		//Creates panel for drawing
		getContentPane().add(new startBgPanel());
	}

	/**
	 * startBgPanel
	 * Class to create a panel to be drawn on
	 * @author Bryen & Michelle
	 */
	class startBgPanel extends JPanel implements ActionListener {
		startBgPanel(){
			//Ringbearer is a font that was downloaded to match the game
			play.setFont(new Font("Ringbearer", Font.BOLD, 55));
			instruction.setFont(new Font("Ringbearer", Font.BOLD, 55));
			quit.setFont(new Font("Ringbearer", Font.BOLD, 55));
			play.setAlignmentX(CENTER_ALIGNMENT);
			instruction.setAlignmentX(CENTER_ALIGNMENT);
			quit.setAlignmentX(CENTER_ALIGNMENT);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

			play.setOpaque(false);
			play.setContentAreaFilled(false);
			play.setBorderPainted(false);
			instruction.setOpaque(false);
			instruction.setContentAreaFilled(false);
			instruction.setBorderPainted(false);
			quit.setOpaque(false);
			quit.setContentAreaFilled(false);
			quit.setBorderPainted(false);

			buttonPanel.add(play);
			buttonPanel.add(instruction);
			buttonPanel.add(quit);
			buttonPanel.setOpaque(false);

			this.setLayout(new BorderLayout());
			this.add(buttonPanel, BorderLayout.SOUTH);

			instruction.addActionListener(this);
			play.addActionListener(this);
			quit.addActionListener(this);
		}

		/**
		 * actionPerformed
		 * Method implemented from ActionListener, responds to button clicks
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == instruction) {
				introFrame.dispose();
				instructionFrame = new instructionFrame();
				instructionFrame.setVisible(true);
			} else if (event.getSource() == quit) {
				introFrame.dispose();
			} else if (event.getSource() == play) {
				introFrame.dispose();
				characterSelectionFrame = new characterSelectionFrame();
				characterSelectionFrame.setVisible(true);
			}
		}

		/** 
		 * paintComponent
		 * Method to paint the animated background
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g); //required
			setDoubleBuffered(true);
			startBgIndex++;
			//Repeats the animation
			try {
				Thread.sleep(300);
			}
			catch(Exception e){
				System.out.println("Sleeping problem for background");
			}
			g.drawImage(startBg[startBgIndex], 0, 0, null);
			if(startBgIndex ==2){
				startBgIndex =-1;
			}
			repaint();
		}
	}

	/**
	 * instructionFrame
	 * Class to create the instructions frame
	 * @author Bryen & Michelle
	 */
	class instructionFrame extends JFrame {
		JButton back = new JButton("Back");
		JPanel backButtonP = new JPanel();
		BufferedImage ins = null;

		instructionFrame() {
			try {
				ins = ImageIO.read(new File("images/ins.png"));
			} catch (Exception e) {
				System.out.println("instructions not found");
			}
			instructionFrame = this;
			backButtonP.add(back);
			this.setTitle("instruction Frame");
			this.setBackground(Color.WHITE);
			this.setPreferredSize(new Dimension(1270, 720));
			this.setResizable(false);
			this.setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();

			this.getContentPane().add(new insPanel());
		}

		/**
		 * insPanel
		 * Class to create a panel for instructions to be drawn on
		 * @author Bryen & Michelle
		 */
		class insPanel extends JPanel implements ActionListener {
			insPanel() {

				back.setFont(new Font("Papyrus", Font.BOLD, 38));
				back.setOpaque(false);
				back.setContentAreaFilled(false);
				back.setBorderPainted(false);
				back.setAlignmentX(CENTER_ALIGNMENT);

				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());
				back.addActionListener(this);
				buttonPanel.add(back);
				buttonPanel.setOpaque(false);

				this.setLayout(new BorderLayout());
				this.add(buttonPanel, BorderLayout.SOUTH);
				this.setOpaque(false);
			}

			/**
			 * actionPerformed
			 * Method implemented from ActionListener, responds to button clicks
			 */
			public void actionPerformed(ActionEvent event) {
				instructionFrame.dispose();
				introFrame = new IntroFrame();
				introFrame.setVisible(true);
			}

			/**
			 * paintComponent
			 * Method to draw the instructions image
			 */
			public void paintComponent(Graphics g) {
				super.paintComponent(g); //required
				setDoubleBuffered(true);
				g.drawImage(ins, 0, 0, null);
				repaint();
			}
		}
	}

	/**
	 * characterSelectionFrame
	 * Class to create frame for char selection
	 * @author Bryen & Michelle
	 *
	 */
	class characterSelectionFrame extends JFrame implements ActionListener {
		JPanel archerPanel = new JPanel();
		JPanel assassinPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel selectPanel = new JPanel();

		//Images for the buttons and locked char
		ImageIcon m = new ImageIcon("images/charSelect/empressMao.png");
		ImageIcon a = new ImageIcon("images/charSelect/pantea.png");
		ImageIcon mush = new ImageIcon("images/charSelect/musashi.png");
		ImageIcon cleo = new ImageIcon("images/charSelect/cleo.png");
		ImageIcon pr = new ImageIcon("images/charSelect/princeRupert.png");

		JButton archer = new JButton(m);
		JButton archerShow = new JButton("Archer Info");
		JButton assassin = new JButton(a);
		JButton assassinShow = new JButton("Assassin Info");
		JButton back = new JButton("Back");

		//Since they are locked, you are unable to press on them
		JLabel mage = new JLabel(cleo);
		JLabel sword = new JLabel(mush);
		JLabel mult = new JLabel(pr);

		JLabel instruction = new JLabel("Player 1: Choose Character");
		Boolean p1chosed = false;

		characterSelectionFrame() {
			characterSelectionFrame = this;
			this.setTitle("character selection Frame");
			this.setPreferredSize(new Dimension(1270, 720));
			this.setResizable(false);
			this.setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();

			instruction.setFont(new Font("Algerian", Font.BOLD, 34));
			instruction.setHorizontalAlignment(JLabel.CENTER);
			back.setFont(new Font("Papyrus", Font.BOLD, 20));
			archerShow.setFont(new Font("Algerian", Font.BOLD, 24));
			assassinShow.setFont(new Font("Algerian", Font.BOLD, 24));

			archerPanel.setLayout(new BoxLayout(archerPanel, BoxLayout.Y_AXIS));
			archerPanel.add(archer);
			archerPanel.add(archerShow);

			assassinPanel.setLayout(new BoxLayout(assassinPanel, BoxLayout.Y_AXIS));
			assassinPanel.add(assassin);
			assassinPanel.add(assassinShow);

			buttonPanel.add(back);

			selectPanel.add(archerPanel);
			selectPanel.add(assassinPanel);
			selectPanel.add(sword);
			selectPanel.add(mage);
			selectPanel.add(mult);

			mainPanel.setLayout(new BorderLayout());

			mainPanel.add(instruction, BorderLayout.NORTH);
			mainPanel.add(selectPanel, BorderLayout.CENTER);
			mainPanel.add(buttonPanel, BorderLayout.SOUTH);

			archer.setOpaque(false);
			archer.setContentAreaFilled(false);
			archer.setBorderPainted(false);
			assassin.setOpaque(false);
			assassin.setContentAreaFilled(false);
			assassin.setBorderPainted(false);
			assassinShow.setOpaque(false);
			assassinShow.setContentAreaFilled(false);
			archerShow.setOpaque(false);
			archerShow.setContentAreaFilled(false);
			back.setOpaque(false);
			back.setContentAreaFilled(false);
			back.setBorderPainted(false);

			archer.addActionListener(this);
			assassin.addActionListener(this);
			assassinShow.addActionListener(this);
			archerShow.addActionListener(this);
			back.addActionListener(this);
			add(mainPanel);
		}

		/**
		 * actionPerformed
		 * Method implemented by ActionListener, responds to button clicks and creates objects accordingly
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == archer) {
				if (p1chosed == false) {
					player1 = new Archer(1);
					instruction.setText("Player 2: Choose Character");
					p1chosed = true;
				} else {
					player2 = new Archer(2);
					characterSelectionFrame.dispose();
					gameFrame = new gameFrame();
				}
			} else if (event.getSource() == archerShow) {
				characterSelectionFrame.dispose();
				charDetailFrame = new charDetailFrame(1);
				charDetailFrame.setVisible(true);
			} else if (event.getSource() == assassin) {
				if (p1chosed == false) {
					player1 = new Assassin(1);
					instruction.setText("Player 2: Choose Character");
					p1chosed = true;
				} else {
					player2 = new Assassin(2);
					characterSelectionFrame.dispose();
					gameFrame = new gameFrame();
				}
			} else if (event.getSource() == assassinShow) {
				characterSelectionFrame.dispose();
				charDetailFrame = new charDetailFrame(2);
				charDetailFrame.setVisible(true);
			} else if (event.getSource() == back) {
				characterSelectionFrame.dispose();
				introFrame = new IntroFrame();
				introFrame.setVisible(true);
			}
		}
	}

	/**
	 * charDetailFrame
	 * Class that creates a frame for char details
	 * @author Michelle
	 */
	class charDetailFrame extends JFrame {
		JButton back = new JButton("Back");
		JPanel button = new JPanel();
		BufferedImage archerDetail;
		BufferedImage assassinDetail;

		//int a - 1 for archer, 2 for assassin
		charDetailFrame(int a) {
			try {
				archerDetail = ImageIO.read(new File("images/archerDetail.png"));
				assassinDetail = ImageIO.read(new File("images/assassinDetail.png"));
			} catch (Exception e) {
				System.out.println("error loading details");
			}

			charDetailFrame = this;
			this.setTitle("Char description");
			this.setPreferredSize(new Dimension(1270, 720));
			this.setResizable(false);
			this.setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
			button.add(back);
			add(button);

			if(a == 1) {
				this.getContentPane().add(new archerDetailPane());
			} else {
				this.getContentPane().add(new assassinDetailPane());
			}
		}

		/**
		 * archerDetailPane
		 * Class that creates a panel for archer info
		 * If more chars better to make one panel and change the image being drawn
		 * @author Michelle
		 */
		class archerDetailPane extends JPanel implements ActionListener {
			archerDetailPane() {

				back.setFont(new Font("Papyrus", Font.BOLD, 38));
				back.setOpaque(false);
				back.setContentAreaFilled(false);
				back.setBorderPainted(false);

				JPanel buttonPanel = new JPanel();
				back.addActionListener(this);
				buttonPanel.setOpaque(false);
				buttonPanel.add(back);

				this.setLayout(new BorderLayout());
				this.add(buttonPanel, BorderLayout.SOUTH);
				this.setOpaque(false);
			}

			/**
			 * actionPerformed
			 * Method implemented by ActionListener, responds to button clicks
			 */
			public void actionPerformed(ActionEvent event) {
				charDetailFrame.dispose();
				characterSelectionFrame = new characterSelectionFrame();	
			}

			/**
			 * paintComponent
			 * Method to paint on the image with the details
			 */
			public void paintComponent(Graphics g) {
				super.paintComponent(g); //required
				setDoubleBuffered(true);
				g.drawImage(archerDetail, 0, 0, null);
				repaint();
			}
		}
		/**
		 * assassinDetailPane
		 * Class that creates a panel for assassin info
		 * @author Michelle
		 */
		class assassinDetailPane extends JPanel implements ActionListener {
			assassinDetailPane() {

				back.setFont(new Font("Papyrus", Font.BOLD, 38));
				back.setOpaque(false);
				back.setContentAreaFilled(false);
				back.setBorderPainted(false);
				back.setAlignmentX(LEFT_ALIGNMENT);

				JPanel buttonPanel = new JPanel();
				back.addActionListener(this);
				buttonPanel.add(back);
				buttonPanel.setOpaque(false);

				this.setLayout(new BorderLayout());
				this.add(buttonPanel, BorderLayout.SOUTH);
				this.setOpaque(false);
			}

			/**
			 * actionPerformed
			 * Method implemented by ActionListener, responds to button clicks
			 */
			public void actionPerformed(ActionEvent event) {
				charDetailFrame.dispose();
				characterSelectionFrame = new characterSelectionFrame();
			}

			/**
			 * paintComponent
			 * Method to paint the assassin detail image
			 */
			public void paintComponent(Graphics g) {
				super.paintComponent(g); //required
				setDoubleBuffered(true);
				g.drawImage(assassinDetail, 0, 0, null);
				repaint();
			}
		}
	}

	/**
	 * gameFrame
	 * Class to create the frame for the main gameplay
	 * @author Bryen & Michelle
	 */
	class gameFrame extends JFrame implements KeyListener{
		Clock clock;
		double elapsedTime;
		ArrayList<Weapon> p1WeaponList = new ArrayList<Weapon>();
		ArrayList<Weapon> p2WeaponList = new ArrayList<Weapon>();

		gameFrame() {
			gameFrame = this;
			this.setTitle("fighting Frame");
			this.setPreferredSize(new Dimension(1280, 720));
			this.setResizable(false);
			this.setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setFocusable(true);
			requestFocusInWindow(); 
			addKeyListener(this);
			setFocusable(true);
			requestFocusInWindow();
			add(new game());
			clock = new Clock();
		}

		/**
		 * game
		 * Class to create a panel for the game to be drawn on
		 * @author Bryen & Michelle
		 */
		class game extends JPanel {
			BufferedImage bg = null;
			
			game() {
				try {
					bg = ImageIO.read(new File("images/ui.png"));
				} catch (Exception e) {
					System.out.println("back groud not found");
				}
				this.setPreferredSize(new Dimension(1280, 720));
				this.setVisible(true);
				setFocusable(true);
				requestFocusInWindow();
				pack(); 
				setFocusable(true);
				requestFocusInWindow();
				this.addMouseListener(new MyMouseListener());
				p1Alive = true;
				p2Alive = true;
				player1.setCombo(100);//testing
				player2.setCombo(100);//testing
			}

			/**
			 * paintComponent
			 * Method to paint the hp bars, combo bars, background, characters and weapons
			 */
			public void paintComponent(Graphics g) {
				super.paintComponent(g); //required
				setDoubleBuffered(true);
				//hp bar
				g.setColor(Color.GREEN);
				g.fillRect(160, 50, (int)(player1.getHp()/100.00*428), 100);
				g.fillRect(683, 50, (int)(player2.getHp()/100.00*428), 100);
				//combo bar
				g.setColor(Color.CYAN);
				g.fillRect(170, 635, (int)(player1.getCombo()/100.00*410), 52);
				g.fillRect(695, 635, (int)(player2.getCombo()/100.00*410), 52);
				//char icons and background
				g.drawImage(player1.icon, 0, 0, null);
				g.drawImage(player2.icon, 1110, 0, null);
				g.drawImage(bg, 0, 0, null);

				//testing
				Rectangle tempBoundBox = player1.getBoundingBox();
				g.drawRect((int)tempBoundBox.getX(),(int)tempBoundBox.getY(),(int)tempBoundBox.getWidth(),(int)tempBoundBox.getHeight());
		//		tempBoundBox = player2.getBoundingBox();
		//		g.drawRect((int)tempBoundBox.getX(),(int)tempBoundBox.getY(),(int)tempBoundBox.getWidth(),(int)tempBoundBox.getHeight());

				clock.update();
				if(clock.getElapsedTime()>0.15) {
					elapsedTime = clock.getElapsedTime();
					player1.update(elapsedTime);
					player2.update(elapsedTime);
				}

				//Check player 1 attacks
				if(player1.isShowArrow() == true) {
					Weapon tempWeapon;
					p1WeaponList = player1.getWeaponList();
					
					//Check for off screen weapons
					for (int i = 0; i < p1WeaponList.size(); i++) {
						tempWeapon = p1WeaponList.get(i);
						if (tempWeapon.getX() > 1400 || tempWeapon.getX() < -200 || tempWeapon.getY() > 580) {
							p1WeaponList.remove(i);
							player1.setWeaponList(p1WeaponList);
						}
					}

					//only for special attack
					if(player1.getWeapon()!=null){
						if(player1.getType()==1) { //archer special
							tempWeapon =  player1.getWeapon();
							player1.getWeaponList().add(tempWeapon); //adding the arrow to the array so it can be drawn
						} //Assassin special has no extra animations to draw
						player1.setWeapon(null);
						player2.setPicIndex(0);
					} 

					//All attack collision detection
					for (int i = 0; i < p1WeaponList.size(); i++) {
						tempWeapon = p1WeaponList.get(i);
					//	tempBoundBox = tempWeapon.getBoundingBox();
					//	g.drawRect((int)tempBoundBox.getX(),(int)tempBoundBox.getY(),(int)tempBoundBox.getWidth(),(int)tempBoundBox.getHeight()); //testing
						if (player2.getBoundingBox().intersects(tempWeapon.getBoundingBox())) {
							if (player2.isBlock() == false && tempWeapon.getAtk() == 1) {
								p2Alive = player2.damage(8);
								//Collision reaction
								if (player2.getDirection() == 'L') {
									player2.setCurrentImage(player2.collisionL);
								} else {
									player2.setCurrentImage(player2.collisionR);
								}
								player1.setCombo(player1.getCombo() + 7);
								player2.setPicIndex(0);
							} else if (player2.isJumping() == false && tempWeapon.getAtk() == 2) {
								p2Alive = player2.damage(4);
								//Collision reaction
								if (player2.getDirection() == 'L') {
									player2.setCurrentImage(player2.collisionL);
								} else {
									player2.setCurrentImage(player2.collisionR);
								}
								player1.setCombo(player1.getCombo() + 5);
								player2.setPicIndex(0);
							} else if (player2.isBlock() == true && tempWeapon.getAtk() == 1) {
								player2.setCombo(player2.getCombo() + 10);
							} else if (tempWeapon.getAtk() == 3) {
								p2Alive = player2.damage(30);
								//Collision reaction
								if (player2.getDirection() == 'L') {
									player2.setCurrentImage(player2.collisionL);
								} else {
									player2.setCurrentImage(player2.collisionR);
								}
							}
							p1WeaponList.remove(i); //Removes weapon after successfully colliding
						}
						//Only applied to archer special attack, going up and back down 
						if(tempWeapon.getY() <= -200) {
							tempWeapon.setX(player2.getX()+80);
							tempWeapon.setY(-100);
							((Arrow)tempWeapon).setDown(1);
						}
						tempWeapon.draw(g);
					}
				}

				//Check player 2 attacks
				if(player2.isShowArrow() == true) {
					Weapon tempWeapon;
					p2WeaponList = player2.getWeaponList();
					
					//Checks for off screen weapons
					for (int i = 0; i < p2WeaponList.size(); i++) {
						tempWeapon = p2WeaponList.get(i);
						if (tempWeapon.getX() > 1400 || tempWeapon.getX() < -200 || tempWeapon.getY() > 580) {
							p2WeaponList.remove(i);
						}
					}
					
					//only for special attacks
					if(player2.getWeapon()!=null){
						if(player2.getType()==1) { //archer special
							tempWeapon =  player2.getWeapon();
							player2.getWeaponList().add(tempWeapon); //adding the arrow to the array so it can be drawn
						} //Assassin special has no extra animations to draw
						player2.setWeapon(null);
						player1.setPicIndex(0);
					} 
					
					//All attacks collision detection
					for (int i = 0; i < p2WeaponList.size(); i++) {
						tempWeapon = p2WeaponList.get(i);
					//	tempBoundBox = tempWeapon.getBoundingBox();
					//	g.drawRect((int)tempBoundBox.getX(),(int)tempBoundBox.getY(),(int)tempBoundBox.getWidth(),(int)tempBoundBox.getHeight());
						if (player1.getBoundingBox().intersects(tempWeapon.getBoundingBox())) {
							if (player1.isBlock() == false && tempWeapon.getAtk() == 1) {
								p1Alive = player1.damage(8);
								//Collision reaction
								if (player1.getDirection() == 'L') {
									player1.setCurrentImage(player1.collisionL);
								} else {
									player1.setCurrentImage(player1.collisionR);
								}
								player2.setCombo(player2.getCombo() + 7);
								player1.setPicIndex(0);
							} else if (player1.isJumping() == false && tempWeapon.getAtk() == 2) {
								p1Alive = player1.damage(4);
								//Collision reaction
								if (player1.getDirection() == 'L') {
									player1.setCurrentImage(player1.collisionL);
								} else {
									player1.setCurrentImage(player1.collisionR);
								}
								player2.setCombo(player2.getCombo() + 5);
								player1.setPicIndex(0);
							} else if (player1.isBlock() == true && tempWeapon.getAtk() == 1) {
								player1.setCombo(player1.getCombo() + 10);
							}else if (tempWeapon.getAtk() == 3) {
								p1Alive = player1.damage(30);
								//Collision reaction
								if (player1.getDirection() == 'L') {
									player1.setCurrentImage(player1.collisionL);
								} else {
									player1.setCurrentImage(player1.collisionR);
								}
							}
							p2WeaponList.remove(i); //Removes weapon after successfully colliding
						}
						//Only applies to archer special attack
						if(tempWeapon.getY() <= -200) {
							tempWeapon.setX(player1.getX()+80);
							tempWeapon.setY(-100);
							((Arrow)tempWeapon).setDown(1);
						}
						tempWeapon.draw(g);
					}
				}

				//Game will stop when one character dies
				if(p1Alive == true && p2Alive == true) {
					player1.draw(g);
					player2.draw(g);
					repaint();
				} else {
					int player, type; //information needed for the end frame
					if(p1Alive == false) {
						player = 2;
						type = player2.getType();
					} else {
						player = 1;
						type = player1.getType();
					}
					p1Alive = true;
					p2Alive = true;
					gameFrame.dispose();
					gameOverFrame = new gameOverFrame(player, type);
					gameOverFrame.setVisible(true);
				}
			}
		}

		//testing purposes
		private class MyMouseListener implements MouseListener {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse Clicked");
				System.out.println("X:" + e.getX() + " y:" + e.getY());
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		} //end of mouselistener

		/**
		 * keyPressed
		 * Method implemented by KeyListener, characters move according to keys
		 */
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			//Player one keys
			if(key==KeyEvent.VK_D){
				if (player1.getX() <= 930 && player1.isIdle() == true) {
					player1.move(30);
				}
			}
			if(key==KeyEvent.VK_A){
				if (player1.getX() >= 30 && player1.isIdle() == true) {
					player1.move(-30);
				}
			}
			if(key==KeyEvent.VK_W){
				if (player1.isIdle() == true) {
					player1.jump(player1.getDirection(),elapsedTime);
				}
			}
			if(key==KeyEvent.VK_S) {
				if (player1.isIdle() == true) {
					player1.block(player1.getDirection());
				}
			}
			if(key==KeyEvent.VK_T) {
				if (player1.isIdle() == true) {
					player1.atk(player1.getDirection());
				}
			}
			if(key==KeyEvent.VK_Y){
				if (player1.isIdle() == true) {
					player1.lowAtk(player1.getDirection());
				}
			}
			if(key==KeyEvent.VK_U) {
				if (player1.isIdle() == true && player1.getCombo() >= 100) {
					player1.specialAtk(player1.getDirection(), player2.getX());
					if(player1.getType() == 2) {
						p2Alive = player2.damage(30);
					}
				}
			}
			//Player 2 keys
			if(key==KeyEvent.VK_DOWN){
				if (player2.isIdle() == true) {
					player2.block(player2.getDirection());
				}
			}
			if(key==KeyEvent.VK_UP){
				if (player2.isIdle() == true) {
					player2.jump(player2.getDirection(),elapsedTime);
				}

			}
			if(key==KeyEvent.VK_RIGHT){
				if (player2.getX() <= 930 && player2.isIdle() == true) {
					player2.move(30);
				}

			}
			if(key==KeyEvent.VK_LEFT){
				if (player2.getX() >= 30 && player2.isIdle() == true) {
					player2.move(-30);
				}
			}
			if(key==KeyEvent.VK_B) {
				if (player2.isIdle() == true) {
					player2.atk(player2.getDirection());
				}
			}
			if (key==KeyEvent.VK_N) {
				if (player2.isIdle() == true) {
					player2.lowAtk(player2.getDirection());
				}
			}
			if (key == KeyEvent.VK_M && player2.getCombo() >= 100) {
				if (player2.isIdle() == true && player2.getAttacking() == false) {
					player2.specialAtk(player2.getDirection(), player1.getX());
					if(player2.getType() == 2) {
						p1Alive = player1.damage(30);
					}
				}
			}
		}

		/**
		 * keyReleased
		 * Method implemented by keyListener, only used for blocking
		 */
		public void keyReleased(KeyEvent e) {
			//Will continue blocking if block button is held down
			if (e.getKeyChar() == 's') {
				player1.setBlock(false);
				player1.setIdle(true);
				player1.update(0.1);
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				player2.setBlock(false);
				player2.setIdle(true);
				player2.update(0.1);
			}
		}
		public void keyTyped(KeyEvent e) {
		}
	}

	/**
	 * gameOverFrame
	 * Class to create a frame when the game is over
	 * @author Michelle
	 */
	class gameOverFrame extends JFrame {
		JButton playAgain = new JButton("Play Again");
		JButton quit = new JButton("Quit");
		JPanel buttonPanel = new JPanel();
		BufferedImage[] endMsgs = new BufferedImage[4];
		BufferedImage bg;

		/**
		 * Constructor
		 * @param player, which player won
		 * @param type, 1 for archer, 2 for assassin
		 */
		gameOverFrame(int player, int type) {
			try {
				endMsgs[0] = ImageIO.read(new File("images/p1ArcherWin.png"));	
				endMsgs[1] = ImageIO.read(new File("images/p1AssassinWin.png"));	
				endMsgs[2] = ImageIO.read(new File("images/p2ArcherWin.png"));	
				endMsgs[3] = ImageIO.read(new File("images/p2AssassinWin.png"));	
			} catch (Exception e) {
				System.out.println("end not found");
			}

			//Sets the background of end screen according to winner and character
			if(player == 1 && type == 1) {
				bg = endMsgs[0];
			} else if (player == 1 && type == 2) {
				bg = endMsgs[1];
			} else if (player == 2 && type == 1) {
				bg = endMsgs[2];
			} else {
				bg = endMsgs[3];
			}

			gameOverFrame = this;
			this.setTitle("Game over Frame");
			this.setPreferredSize(new Dimension(1270, 720));
			this.setResizable(false);
			this.setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
			this.getContentPane().add(new endPane());
		}

		/**
		 * endPane
		 * Class to create a panel to be drawn on
		 * @author Michelle
		 *
		 */
		class endPane extends JPanel implements ActionListener {
			endPane() {
				playAgain.setFont(new Font("Papyrus", Font.BOLD, 38));
				playAgain.setOpaque(false);
				playAgain.setContentAreaFilled(false);
				playAgain.setBorderPainted(false);
				quit.setFont(new Font("Papyrus", Font.BOLD, 38));
				quit.setOpaque(false);
				quit.setContentAreaFilled(false);
				quit.setBorderPainted(false);

				playAgain.setPreferredSize(new Dimension(500, 100));
				quit.setPreferredSize(new Dimension(500, 100));

				JPanel buttonPanel = new JPanel();
				playAgain.addActionListener(this);
				quit.addActionListener(this);
				buttonPanel.setOpaque(false);
				buttonPanel.add(playAgain);
				buttonPanel.add(quit);

				this.setLayout(new BorderLayout());
				this.add(buttonPanel, BorderLayout.SOUTH);
				this.setOpaque(false);
				
			}

			/**
			 * actionPerformed
			 * Method implemented by ActionListener, responds to button clicks
			 */
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == playAgain) {
					gameOverFrame.dispose();
					introFrame = new IntroFrame();
					introFrame.setVisible(true);
				} else if (event.getSource() == quit) {
					gameOverFrame.dispose();
				}
			}

			/**
			 * paintComponent
			 * Method to paint the background
			 */
			public void paintComponent(Graphics g) {
				super.paintComponent(g); //required
				setDoubleBuffered(true);
				g.drawImage(bg, 0, 0, null);
				repaint();
			}
		}
	}
}

