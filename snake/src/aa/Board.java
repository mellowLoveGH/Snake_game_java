package aa;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//board provide a place for snake to move, eat
//board is the class where several components are collected and gathered here
//Wall, Food, and Snake
//here static methods are used to insure the consistency of this game
//every time of playing, only one board is generated and used 
//for GUI, methods in this class will be invoked you will see
//
public class Board {
	
	//the width, and height of this 2D board
	public static int WIDTH = 20;
	public static int HEIGHT = 20;
	
	//all value initialized as 0
	//if there is a wall, set to 1
	//if there is food, set to 2
	//if it is one block of snake, set to 3
	public static int[][] MAP = null;
	
	//there is always a food on board, if eaten by snake, food will re-generate
	public static Food food = null;
	
	//there is only one snake moving and eating on board
	//therefore, singleton design pattern is used here
	public static Snake snake = null;
	
	//initialize board as 2D array
	public static void initMap() {
		MAP = new int[WIDTH][HEIGHT];
		for (int i = 0; i < MAP.length; i++) {
			for (int j = 0; j < MAP[0].length; j++) {
				MAP[i][j] = 0;
			}
		}
	}
	
	//after initializing board
	//add wall
	//the block taken by wall will be set to 1
	//here are two methods for adding wall
	
	//this one is to generate automatically
	public static List<Wall> addWall(int number){
		List<Wall> wall_list = new LinkedList<Wall>();
		for (int i = 0; i < number; i++) {
			int[] ptn = getFreePtn();
			Wall w = new Wall(ptn[0], ptn[1]);
			wall_list.add(w);
			MAP[ptn[0]][ptn[1]] = 1;
		}
		
		return wall_list;
	}
	
	//this one is to set by user
	public static void addWall(List<Wall> wall_list) {
		for (int i = 0; i < wall_list.size(); i++) {
			Wall w = wall_list.get(i);
			int x = w.getX();
			int y = w.getY();
			MAP[x][y] = 1;
		}
	}
	
	//after adding walls
	//add food
	//there are 3 kinds of food to be added
	//each one has different possibilities to generate
	//each one has different scores
	public static void addFood() {
		//
		Random ran = new Random();
		int number = ran.nextInt(10);
		int[] ptn = getFreePtn();
		MAP[ptn[0]][ptn[1]] = 2;
		
		if(number < 5) {
			food = new FoodType1(ptn[0], ptn[1]);
		}else if(number >= 5 && number < 8) {
			food = new FoodType2(ptn[0], ptn[1]);
		}else {
			food = new FoodType3(ptn[0], ptn[1]);
		}
	}
	
	//when the head of snake hit the food
	//the food is to be eaten
	//therefore, remove the current one and generate a new one
	public static void eatFood() {
		MAP[food.x][food.y] = 0;
		food = null;
		addFood();
	}
	
	//get free position
	//for the board which is a 2D array
	//all position is set to be 0 at the beginning
	//after setting the wall and food, some blocks are taken
	//apart from those occupied blocks
	//get a random and free position from the board
	//the blocks of snake will also be excluded
	public static int[] getFreePtn(){
		Random ran = new Random();
		int[] ptn = new int[2];
		
		ptn[0] = ran.nextInt(WIDTH);
		ptn[1] = ran.nextInt(HEIGHT);
		
		//
		if(snake == null) {
			while(MAP[ptn[0]][ptn[1]] != 0) {
				ptn[0] = ran.nextInt(WIDTH);
				ptn[1] = ran.nextInt(HEIGHT);
			}
		}else {
			int[][] mat = snake.updateBoard();
			while(mat[ptn[0]][ptn[1]] != 0) {
				ptn[0] = ran.nextInt(WIDTH);
				ptn[1] = ran.nextInt(HEIGHT);
			}
		}
		return ptn;
	}
	
	//after adding wall and food
	//add the snake
	//at the beginning
	//snake only consists of one block that is the head
	//where the snake starts, the beginning position is randomized
	//for snake to move, starting direction is initialized
	public static void addSnake() {
		snake = Snake.getInstance();
		int[] ptn = getFreePtn();
		snake.setInitPtn(ptn[0], ptn[1]);
		Random ran = new Random();
		int dir = ran.nextInt(4);
		System.out.println("initialized direction: " + dir);
		snake.setInitDtn(dir);
	}
	
	
	//get a copy of the current board that is 2D array
	//the copied array only records the position of food and walls
	//this copied map is used for GUI
	//in GUI, add this copied map with the position of snake to draw frame
	//because snake may be several blocks long
	//the positions of blocks taken by snake is recorded by the list inside class Snake
	public static int[][] copyMap(){
		int[][] copy = new int[WIDTH][HEIGHT];
		for (int i = 0; i < MAP.length; i++) {
			for (int j = 0; j < MAP[0].length; j++) {
				copy[i][j] = MAP[i][j];
			}
		}
		
		return copy;
	}
	
	//for testing, print out the board
	//0 means free blocks
	//1 means walls
	//2 means food
	//the positions occupied by snake is not included here
	public static void print1() {
		for (int i = 0; i < MAP.length; i++) {
			for (int j = 0; j < MAP[0].length; j++) {
				System.out.print(MAP[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}
	
	//for testing, print out the board
	//0 means free blocks
	//1 means walls
	//2 means food
	//3 means the blocks taken by snake
	public static void print2() {
		int[][] mat = snake.updateBoard();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}
	
}