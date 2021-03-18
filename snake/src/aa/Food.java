package aa;

import java.awt.Color;

//here use inheritance to generate different kinds of foo
//Food is the base class that has 4 types of attributes
//x,y means the position in the board
//reward is how many scores will add when snake eating
//color, in order to distinguish different food
//for GUI, when drawing, get the color of the food
public class Food {
	public int x;
	public int y;
	public int reward;
	public Color color;
	
	public Food(int x_p, int y_p, int re, Color clr) {
		x = x_p;
		y = y_p;
		this.reward = re;
		this.color = clr;
	}
	
}
