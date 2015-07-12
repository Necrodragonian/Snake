package snakex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.Timer;

import processing.core.PApplet;

public class Snakex extends PApplet {
	public float width = 20;
	public float length = 20;
	public float x = 100;
	public float y = 100;
	public boolean Moveup = false;
	public boolean Movedown = false;
	public boolean Moveright = false;
	public boolean Moveleft = false;
	public boolean gameOver = false;
	public Food f;
	Random rand = new Random();
	int FOODEATEN = 0;
	int score = 0; 
	int GROWTH = 0;
	int adding = 0;
	LinkedList<Square> squareList;
	

	public void setup() {
		size(800, 800);
		background(255, 255, 255);
		f = new Food(rand.nextInt(1801), rand.nextInt(801));
		squareList = new LinkedList<Square>();
		squareList.add(new Square(100, 100));
		
		new Timer(75, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				move();
			}
		}).start();
	}

	public int Snakeup = 100;
	public int Snakeside = 100;

	public void snake() {
		//translate(x, y);
		stroke(0, 0, 0);
		fill(255, 0, 0);
		int count = 0;
		
		
		while (count < squareList.size()) {
			Square s = squareList.get(count);
			rect(s.x, s.y, width, length);
			count = count + 1;
		}
		
	}

	public void draw() {
		if (!gameOver) {
		background(255,255,255);
		//move();
		snake();
		score();
		food();
		
		} else {
			GG();
		}
		
		Square front = squareList.getFirst();
		
		if(  0 > front.x || front.x > 1900 || 0 > front.y || front.y > 900 ){
			gameOver = true;
		}
	}

	public void keyPressed() {
		if (key == 'a') {
			Moveleft = true;
			Moveup = false;
			Moveright = false;
			Movedown = false;
		}
		if (key == 'w') {
			Moveup = true;
			Movedown = false;
			Moveleft = false;
			Moveright = false;
		}
		if (key == 'd') {
			Moveright = true;
			Movedown = false;
			Moveleft = false;
			Moveup = false;
		}
		if (key == 's') {
			Movedown = true;
			Moveright = false;
			Moveleft = false;
			Moveup = false;
		}
		
	}
	public void GG(){
		background(0,0,0);
		fill(255,255,255);
		rect(500,250,950,500);
		fill(0,0,0);
		rect(670,500,600,200);
		textSize(100);
		text("GAME OVER", 680, 400);
		fill(0,0,0);
		fill(255,255,255);
		textSize(46);
		text("Click to try again.", 670, 600);
	}
	public void move() {
		Square back = squareList.get(squareList.size() - 1);
		back.x = squareList.getFirst().x;
		back.y = squareList.getFirst().y;
		
		if(Moveup){
			back.y-= width;
		}
		if (Moveleft){
			back.x-= length;
		}
		if (Moveright){
			back.x += length;
		}
		if (Movedown){
			back.y+=width;
		}
		
		squareList.offerFirst(back);
		squareList.remove(squareList.size() - 1);
	}
	
	public void food(){
		translate(0, 0);
		fill(255,255,0);
		rect(f.x, f.y, 20, 20);
		Square front = squareList.getFirst();
		//.out.println(front.x +  " " + front.y);
		if (f.checkCollision(front.x, front.y)) {
			f = new Food(rand.nextInt(1801), rand.nextInt(801));
			FOODEATEN = FOODEATEN + 1;
			Square back = squareList.getLast();
			int count = 0;
			while (count < 2) {
				squareList.add(new Square(back.x, back.y));
				count = count + 1;
			}
		}
		
	}
	
	public void score(){
		if(!gameOver){
		score = FOODEATEN * 10 ;
		textSize(30);
		fill(0,0,0);
		text( "Score= " + score   ,1700, 900);
		
		}
	}

	public void mouseClicked() {
		System.out.println("mouse");
		System.out.println(mouseX + " " + mouseY + " " + gameOver);
		if (gameOver && (mouseX < 1271 && mouseX > 670 && mouseY > 500 && mouseY < 700)) {
			squareList.clear();
			squareList.add(new Square(100, 100));
			Moveleft = false;
			Moveup = false;
			Moveright = false;
			Movedown = false;
			gameOver = false;
		}
	}
 

	
}