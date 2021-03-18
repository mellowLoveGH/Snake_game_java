package test;

import java.util.Random;
import java.util.Scanner;

import aa.Board;

//this command test
public class Tester {
	
	public static void main(String[] args) {
		
		Board.initMap();
		int number = 50;
		Board.addWall(number);
		Board.addFood();
		Board.addSnake();
		Board.print1();
		Board.print2();
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int N = 0;
		
		while(true) {
			Random ran = new Random();
			N = ran.nextInt(4);
			
			N = scan.nextInt();
			
			if(N == 1) {
				System.out.println("turn left");
				Board.snake.turnLeft();
			}else if(N == 2){
				System.out.println("turn right");
				Board.snake.turnRight();
			}else {
				Board.snake.moveForward();
				System.out.println("move forward");
			}
			
			
			Board.print2();
			
			try {
				Thread.sleep(2*1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
}
