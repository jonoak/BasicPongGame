import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class TotalScore extends Rectangle{
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int person1;
	int person2;
	
	TotalScore(int GAME_WIDTH, int GAME_HEIGHT){
		TotalScore.GAME_WIDTH = GAME_WIDTH;
		TotalScore.GAME_HEIGHT = GAME_HEIGHT;
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		
		g.drawLine(GAME_WIDTH/2,0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(person1/10)+String.valueOf(person1%10), 
				(GAME_WIDTH/2)-82, 50);
		g.drawString(String.valueOf(person2/10)+String.valueOf(person2%10),
				(GAME_WIDTH/2)+20, 50);
	}

}
