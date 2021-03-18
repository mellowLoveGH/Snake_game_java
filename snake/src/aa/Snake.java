package aa;

import java.util.LinkedList;
import java.util.List;

public class Snake {
	
	//used for singleton design pattern
	private static Snake snake = null;
	
	//record the blocks of snake
	//when initialized, only one block that is the head of snake
	private List<Block> block_list = null;
	
	//count how many blocks the snake has
	private static int index = 0;
	
	//record the score gained
	private int score = 0;
	
	//used for moving every time
	//used to insure there is only one kind of move
	//forward, turn left or turn right
	private boolean state = true;
	
	//show the current state of snake
	//for example, eat a food, gain scores
	private String message = ""; 
	
	//check whether snake is alive or not
	//if it is, continue the game
	//else, record score
	private boolean alive = true;
	
	//direction, means the current direction where the snake is heading
	//0, UP
	//1, DOWN
	//2, LEFT
	//3, RIGHT
	private int direction = 0;
	
	//every time move 1 block by default
	private int speed;
	
	
	//singleton design pattern
	private Snake() {
		block_list = new LinkedList<Block>();
		Block head = new Block(0, 0, index);
		index++;
		block_list.add(head);
		speed = 1;
	}
	
	//used to get the singleton reference of snake
	public static Snake getInstance() {
		if(snake == null) {
			snake = new Snake();
		}
		return snake;
	}
	
	//set initialized position when starting game
	public void setInitPtn(int x, int y) {
		Block head = block_list.get(0);
		head.setX(x);
		head.setY(y);
	}
	
	//set initialized direction for snake to move
	public void setInitDtn(int dir) {
		direction = dir;
		if(dir == 0) {
			System.out.println("initialize direction: UP");
		}else if(dir == 1) {
			System.out.println("initialize direction: DOWN");
		}else if(dir == 2) {
			System.out.println("initialize direction: LEFT");
		}else {
			System.out.println("initialize direction: RIGHT");
		}
	}
	
	//if there is no keyboard event to change direction
	//the snake keeps forward
	public void moveForward() {
		
		if(!state) {
			return;
		}
		
		//calculate the position the head of snake is going to move
		//there are 4 types of situations
		//up, down, left and right
		Block head = block_list.get(0);
		int nx = head.getX();
		int ny = head.getY();
		
		//0, UP
		if(direction == 0) {
			nx = nx - speed;
		}
		//1, DOWN
		else if(direction == 1) {
			nx = nx + speed;
		}
		//2, LEFT
		else if(direction == 2) {
			ny = ny - 1;
		}
		//3, RIGHT
		else if(direction == 3) {
			ny = ny + 1;
		}
		
		if(nx < 0) {
			nx = nx + Board.WIDTH;
		}
		if(ny < 0) {
			ny = ny + Board.HEIGHT;
		}
		
		//here module arithmetic is used
		//because snake could only move inside the board
		nx = nx % Board.WIDTH;
		ny = ny % Board.HEIGHT;
		
		//change the position of blocks of snake
		nextStep(nx, ny); 
		
		//
		state = false;
	}
	
	//here speed could be set for one move
	//this method may be used to accelerate the moving of snake if necessary
	public void moveForward(int spd) {
		speed = spd;
		moveForward();
		speed = 1;
	}
	
	//when pressing keyboard to change direction
	public void turnLeft() {
		
		//calculate the position the head of snake is going to move
		//there are 4 types of situations
		//up, down, left and right
		Block head = block_list.get(0);
		int nx = head.getX();
		int ny = head.getY();
		
		//0, UP
		if(direction == 0) {
			ny = ny - speed;
			direction = 2;
		}
		//1, DOWN
		else if(direction == 1) {
			ny = ny + speed;
			direction = 3;
		}
		//2, LEFT
		else if(direction == 2) {
			nx = nx + speed;
			direction = 1;
		}
		//3, RIGHT
		else if(direction == 3) {
			nx = nx - speed;
			direction = 0;
		}
		
		if(nx < 0) {
			nx = nx + Board.WIDTH;
		}
		if(ny < 0) {
			ny = ny + Board.HEIGHT;
		}
		
		//here module arithmetic is used
		//because snake could only move inside the board
		nx = nx % Board.WIDTH;
		ny = ny % Board.HEIGHT;
		
		//change the position of blocks of snake
		nextStep(nx, ny);
		
		//
		state = false;
	}
	
	public void turnRight() {
		
		//calculate the position the head of snake is going to move
		//there are 4 types of situations
		//up, down, left and right
		Block head = block_list.get(0);
		int nx = head.getX();
		int ny = head.getY();
		
		//0, UP
		if(direction == 0) {
			ny = ny + speed;
			direction = 3;
		}
		//1, DOWN
		else if(direction == 1) {
			ny = ny - speed;
			direction = 2;
		}
		//2, LEFT
		else if(direction == 2) {
			nx = nx - speed;
			direction = 0;
		}
		//3, RIGHT
		else if(direction == 3) {
			nx = nx + speed;
			direction = 1;
		}
		
		if(nx < 0) {
			nx = nx + Board.WIDTH;
		}
		if(ny < 0) {
			ny = ny + Board.HEIGHT;
		}
		
		//here module arithmetic is used
		//because snake could only move inside the board
		nx = nx % Board.WIDTH;
		ny = ny % Board.HEIGHT;
		
		//change the position of blocks of snake
		nextStep(nx, ny);
		
		//
		state = false;
	}
	
	
	//check the next position the head of snake is going to move
	//if it is wall, then snake will die and GAME OVER
	//if it is the body the itself, then snake will die and GAME OVER
	//if it is food, eat the food and obtain score, generate a new food
	//if it is free position, update the position information of snake
	public void nextStep(int nx, int ny) {
		System.out.println(nx + ", " + ny);
		
		//(nx, ny) means the position the head of the snake is going to move 
		if(hitWall(nx, ny) || hitItself(nx, ny)) {
			//if hitting wall, game over
			alive = false;
			System.out.println("GAME OVER");
		}else if(hitFood(nx, ny) != null) {
			//if hitting food, snake will grow
			Block nh = new Block(nx, ny, 0);
			block_list.add(0, nh);
			score = score + hitFood(nx, ny).reward;
			index++;
			
			Board.eatFood();
			System.out.println("score: " + score);
		}else {
			//move head, the following blocks move to the position of previous one
			Block nh = new Block(nx, ny, 0);
			block_list.add(0, nh);
			block_list.remove(block_list.size() - 1);
		}
	}
	
	//check whether hitting food
	public Food hitFood(int x, int y) {
		
		if(Board.MAP[x][y] == 2) {
			Food food = Board.food;
			
			message = "eat food, score plus " + food.reward;
			
			return food;
		}
		
		return null;
	}
	
	//check whether hitting wall
	public boolean hitWall(int x, int y) {
		
		if(Board.MAP[x][y] == 1) {
			
			message = "hit the wall, GAME OVER";
			
			return true;
		}
		
		return false;
	}
	
	//check whether hitting itself
	public boolean hitItself(int x, int y) {
		int[][] copy = updateBoard();
		if(copy[x][y] == 3) {
			
			message = "hit yourself, GAME OVER";
			
			return true;
		}
		return false;
	}
	
	//get the current score
	public int getScore() {
		return score;
	}
	
	//get the blocks of snake that is recorded by using a list
	public List<Block> getList(){
		return block_list;
	}
	
	//get current direction
	public int getDirection() {
		return direction;
	}
	
	//every move, set the state
	public void setState(boolean st) {
		state = st;
	}
	
	//get state
	public boolean getState() {
		return state;
	}
	
	//get current message
	public String getMessage() {
		return message;
	}
	
	//check whether snake is alive or not
	public boolean getAlive() {
		return alive;
	}
	
	//get the copied 2D array from class Board
	//add the position information of snake
	//get the full info of the current playing
	public int[][] updateBoard() {
		int[][] copy = Board.copyMap();
		for (int i = 0; i < block_list.size(); i++) {
			Block blk = block_list.get(i);
			copy[blk.getX()][blk.getY()] = 3; 
		}
		
		return copy;
	}
	
}
