package aa;

import java.awt.Color;

//type 1 of food
//color is light blue
//for saving code by using inheritance
public class FoodType1 extends Food{
	
	public FoodType1(int x_p, int y_p) {
		super(x_p, y_p, 1, new Color(0, 255, 255));
		//Aqua
	}
}
