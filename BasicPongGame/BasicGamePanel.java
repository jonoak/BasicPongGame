import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class BasicGamePanel extends JPanel implements Runnable{
	
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	SmallBall smallball;
	TotalScore the_score;
	
	
	BasicGamePanel(){
		newPaddles();
		newSmallBall();
		the_score = new TotalScore(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL()); //AL is short 4 ActionListener
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
   
	public void newSmallBall() {
		//random = new Random();
		smallball = new SmallBall((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),
				(BALL_DIAMETER),(BALL_DIAMETER));
	}
	
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,
				(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g) {
		
		paddle1.draw(g);
		paddle2.draw(g);
		smallball.draw(g);
		the_score.draw(g);
	}
	
	public void move() {
		paddle1.move();
		paddle2.move();
		smallball.move();
	}
	
	public void checkCollision() {
		//bounce smallball off top & bottom window edges
		
		if(smallball.y <=0) {
			smallball.setYDirction(-smallball.yVelocity);
		}
		if (smallball.y >= GAME_HEIGHT - BALL_DIAMETER ) {
			smallball.setYDirction(-smallball.yVelocity);
		}
		
		
		// this bounces smallball off paddles
		
		if(smallball.intersects(paddle1)) {
			smallball.xVelocity = Math.abs(smallball.xVelocity);
			smallball.xVelocity++; //for more speed
			if(smallball.yVelocity>0)
				smallball.yVelocity++; 
			else 
				smallball.yVelocity--;	
			smallball.setXDirction(smallball.xVelocity);
			smallball.setYDirction(smallball.yVelocity);
		
		}
		if(smallball.intersects(paddle2)) {
			smallball.xVelocity = Math.abs(smallball.xVelocity);
			smallball.xVelocity++; //for more speed
			if(smallball.yVelocity>0)
				smallball.yVelocity++; 
			else 
				smallball.yVelocity--;	
			smallball.setXDirction(-smallball.xVelocity);
			smallball.setYDirction(smallball.yVelocity);
		
		}
		
		//stops paddles at window edges
		if(paddle1.y<=0)
			paddle1.y=0;
		if(paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		if(paddle2.y<=0)
			paddle2.y=0;
		if(paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		
		//this gives a player one point and create new paddles & smallball
		if(smallball.x <=0) {
			the_score.person2++;
			newPaddles();
			newSmallBall();
			System.out.println("person2: "+the_score.person2);
		}
		if(smallball.x >=GAME_WIDTH - BALL_DIAMETER) {
			the_score.person1++;
			newPaddles();
			newSmallBall();
			System.out.println("person1: "+the_score.person1);
		}
	}
	
	public void run() {
		//game loop
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now- lastTime) / ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			//	System.out.println("test");
			}
		}
	}
	
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
        public void keyReleassed(KeyEvent e) {
        	paddle1.keyReleassed(e);
			paddle2.keyReleassed(e);
		}
	}
}	 
