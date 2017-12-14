/**
 * Clock.java
 * Class with values to keep track of the time in the game, mainly used for animation
 * June 14 2017
 * @author Bryen
 * 
 */

class Clock {
	long elapsedTime;
	long lastTimeCheck;

	/**
	 * Constructor
	 */
	public Clock() {
		lastTimeCheck=System.nanoTime();
		elapsedTime=0;
	}
	
	/**
	 * update
	 * Method to update the currentTime and lastTimeCheck variables
	 */
	public void update() {
		long currentTime = System.nanoTime();  //if the computer is fast you need more precision
		elapsedTime=currentTime - lastTimeCheck;
		if((elapsedTime/1.0E9)>0.15) {
			lastTimeCheck = currentTime;
		}
	}

	/**
	 * getElapsedTime
	 * Method to get the elapsed time in milliseconds
	 * @return double, time in ms
	 */
	public double getElapsedTime() {
		return elapsedTime/1.0E9;
	}
}