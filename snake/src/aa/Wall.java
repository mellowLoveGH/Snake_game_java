package aa;

//wall is 2D
//every wall has a position of (x, y)
public class Wall {

	private int x;
	private int y;

	public Wall(int x_p, int y_p) {
		x = x_p;
		y = y_p;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
