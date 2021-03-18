package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import aa.Block;
import aa.Board;
import aa.Record;


//this class is used for GUI frame
//mainly use class Board
//top 5 ranking records are also displayed in the frame

@SuppressWarnings("serial")
public class WindowShow extends JFrame{
	
	//run the game
	//enter in a name then launch
	//after playing, name and score will be recorded
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		//enter in the name of your playing
		String name = "";
		name = scan.nextLine();
		
		//start playing
		new WindowShow(name).running();
		
	}
	
	//the width and height size of the frme
	private static int WSIZE = 800;
	private static int HSIZE = 800;
	
	//the Board is 2D array, every value of the 2D array corresponds to a grid
	//a grid is 30*30 wide and high
	private static int gridW = 30;
	private static int gridH = 30;
//	private JFrame frame = null;
	private boolean update = false;
//	private JLabel scoreL;
	
	//where the left-up corner of the board lies
	//default value is (100, 150)
	private static int x_offset = 100;
	private static int y_offset = 150;
	
	//registration number is the name entered in before playing
	//default value is noName
	private String registrationNo = "noName";
	
	
	//constructor with a name assigning to registrationNo
	public WindowShow(String name) {
		
		this.setTitle(registrationNo);
		this.setSize(WSIZE, HSIZE);
		
		//initialize board
		//add wall, snake and food
		Board.initMap();
		int number = 5;
		Board.addWall(number);
		Board.addFood();
		Board.addSnake();
		
		//those 2 printout info used for testing and debugging
		Board.print1();
		Board.print2();
		
		//set frame visible
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//
		registrationNo = name;
	}
	
	//keep running the game
	public void running() {
		while(Board.snake.getAlive()) {
			
			//every time, set state true to move
			Board.snake.setState(true);
			
			
			//add keyboard event listener
			this.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					int dir = Board.snake.getDirection();
					switch(e.getKeyCode()) {
					case KeyEvent.VK_UP:
						if(dir == 0) {
//							Board.snake.moveForward();
//							repaint();
						}else if(dir == 1) {
							
						}else if(dir == 2) {
							Board.snake.turnRight();
							repaint();
						}else if(dir == 3) {
							Board.snake.turnLeft();
							repaint();
						}
						System.out.println("direction: " + dir);
		                System.out.println("Up");
		                break;
		            case KeyEvent.VK_DOWN:
						if(dir == 0) {
							
						}else if(dir == 1) {
//							Board.snake.moveForward();
//							repaint();
						}else if(dir == 2) {
							Board.snake.turnLeft();
							repaint();
						}else if(dir == 3) {
							Board.snake.turnRight();
							repaint();
						}
						System.out.println("direction: " + dir);
						System.out.println("Down");
		                break;
		            case KeyEvent.VK_LEFT:
		            	if(dir == 0) {
							Board.snake.turnLeft();
							repaint();
						}else if(dir == 1) {
							Board.snake.turnRight();
							repaint();
						}else if(dir == 2) {
//							Board.snake.moveForward();
//							repaint();
						}else if(dir == 3) {
							
						}
		            	System.out.println("direction: " + dir);
		                System.out.println("Left");
		                break;
		            case KeyEvent.VK_RIGHT:
		            	if(dir == 0) {
		            		Board.snake.turnRight();
		            		repaint();
						}else if(dir == 1) {
							Board.snake.turnLeft();
							repaint();
						}else if(dir == 2) {
							
						}else if(dir == 3) {
//							Board.snake.moveForward();
//							repaint();
						}
		            	System.out.println("direction: " + dir);
		                System.out.println("Right");
		                break;
					}
				}
			});
			
			if(Board.snake.getState()) {
				Board.snake.moveForward();
				this.repaint();
			}
			try {
				Thread.sleep(1*1000 / (Board.snake.getScore()/20 + 2));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//when GAME OVER
		//write name and score to file
		String nLine = registrationNo + ": " + Board.snake.getScore();
		String path = "ranks.txt";
		boolean append = false;
//		Record.writeToFile(path, nLine, append);
		String str = Record.readFromFile(path);
		str = str + nLine;
		Record.writeToFile(path, str, append);
		
		update = true;
		repaint();
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//draw board outline
		drawBoard(g);
		
		//draw walls
		drawWall(g);
		
		//draw food
		drawFood(g);
		
		//draw snake
		drawSnake(g);
		
		//draw current score
		drawScore(g);
		
		if(update) {
			//draw top 5 rankings
			drawRanks(g);
		}
	}
	
	public void drawBoard(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(y_offset,x_offset,gridW * Board.WIDTH, gridH * Board.HEIGHT);//»­Ïß¿ò
		
		
		//here every grid of board could be drawn if you like
		//or left the free block as empty
		
		//vertical lines
//		for (int i = 1; i < Board.HEIGHT; i++) {
//			int x1 = x_offset + gridW * i;
//			int y1 = y_offset;
//			int x2 = x1;
//			int y2 = y1 + gridH * Board.HEIGHT;
//			g.drawLine(x1, y1, x2, y2);
//		}
		
		//horizontal lines
//		for (int i = 1; i < Board.WIDTH; i++) {
//			int x1 = x_offset;
//			int y1 = y_offset  + gridH * i;
//			int x2 = x1  + gridW * Board.WIDTH;
//			int y2 = y1;
//			g.drawLine(x1, y1, x2, y2);
//		}
	}
	
	//iterate the MAP in board
	//if 1, then draw the wall that is yellow
	public void drawWall(Graphics g) {
		int[][] map = Board.MAP;
		g.setColor(new Color(255, 255, 0));
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] == 1) {
					int x = i * gridW + x_offset;
					int y = j * gridH + y_offset;
//					g.fillRect(y, x, gridW, gridH);
					g.fillRect(y + 1, x + 1, gridW - 3, gridH - 3);
				}
			}
		}
	}
	
	
	//draw food
	//there 3 types of food
	//each one has different possibilities to appear and different colors
	public void drawFood(Graphics g) {
		int i = Board.food.x;
		int j = Board.food.y;
		g.setColor(Board.food.color);
		
		int x = i * gridW + x_offset;
		int y = j * gridH + y_offset;
//		g.fillRect(y, x, gridW, gridH);
		g.fillRect(y + 1, x + 1, gridW - 3, gridH - 3);
	}
	
	
	//draw snake
	//iterate the list of class Snake, which records all blocks of the snake
	//the head is pink and others are purple if more than one blocks
	public void drawSnake(Graphics g) {
		
		List<Block> list = Board.snake.getList();
		
		for (int i = 0; i < list.size(); i++) {
			
			if(i == 0) {
				g.setColor(new Color(255, 105, 180));
			}else {
				g.setColor(new Color(138, 43, 226));
			}
			
			Block blk = list.get(i);
			int x = blk.getX() * gridW + x_offset;
			int y = blk.getY() * gridH + y_offset;
//			g.fillRect(y, x, gridW, gridH);
			g.fillRect(y + 1, x + 1, gridW - 3, gridH - 3);
		}
	}
	
	
	//draw score and the current message of snake
	//score, message are attributes of snake
	public void drawScore(Graphics g) {
		g.setColor(Color.BLACK);
		Font f=new Font(null,Font.PLAIN,20);
		g.setFont(f);
		g.drawString("score: " + Board.snake.getScore(), y_offset, x_offset - 20);
		g.drawString(Board.snake.getMessage(), y_offset + 300, x_offset - 20);
	}
	
	//read from ext file
	//sorting those records by score
	//show the top 5
	public void drawRanks(Graphics g) {
		String path = "ranks.txt";
		String str = Record.readFromFile(path).trim();
		int number = 5;
		String[] ranks = Record.topInfo(str, number);
		
		g.drawString("TOP 5: ", 30, 160);
		
		g.setColor(Color.RED);
		Font f=new Font(null,Font.PLAIN,16);
		g.setFont(f);
		
		int N = 5;
		if(N > ranks.length) {
			N = ranks.length;
		}
		
		for (int i = 0; i < N; i++) {
			g.drawString(ranks[i], 30, 200 + i * 30);
		}
		
	}
	
}
