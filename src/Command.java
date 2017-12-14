/**
 * Command
 * Interface that defines a character
 * @author Bryen
 */
public interface Command {
	void lowAtk(char c);
	void atk(char c);
	void specialAtk(char c, int x);
	void block(char c);
	void jump(char c,double elapsedTime);
	void move(int x);
}