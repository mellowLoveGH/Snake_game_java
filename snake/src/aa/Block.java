package aa;

//Board is 2D blocks
//namely, a chess board
//every board has a position of (x, y)
public class Block{
	private int x;
	private int y;
	
	public Block(int x, int y, int idx) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
