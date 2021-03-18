package aa;

import java.awt.Color;

//type 3 of food
//color is orange
//for saving code by using inheritance
public class FoodType3 extends Food{
	
	public FoodType3(int x_p, int y_p) {
		super(x_p, y_p, 6, new Color(255, 165, 0));
		//Orange
	}
}
