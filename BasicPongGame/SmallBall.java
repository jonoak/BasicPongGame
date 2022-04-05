import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class SmallBall extends Rectangle{
	
	Random random;
	int xVelocity;
	int yVelocity; //hoe fast the smallball will move on y
	int initialSpeed = 3;//for the smallball

	
	SmallBall(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if (randomXDirection==0)
			randomXDirection--;
		setXDirction(randomXDirection*initialSpeed);
		int randomYDirection = random.nextInt(2);
		if (randomYDirection==0)
			randomYDirection--;
		setYDirction(randomYDirection*initialSpeed);
		
	}
	
	public void setXDirction(int randomXDirection) {
		xVelocity = randomXDirection;
	}
    public void setYDirction(int randomYDirection) {
    	yVelocity = randomYDirection;
	}
    public void move() {
    	x += xVelocity;
    	y += yVelocity;
    }
    public void draw(Graphics g) {
    	g.setColor(Color.white);
    	g.fillOval(x, y, height, width);
    }
    
}
