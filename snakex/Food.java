package snakex;

public class Food {
	public float x;
	public float y;
	int tolerance = 16;
	public Food(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean checkCollision(float x, float y) {
		return Math.abs(this.x -x) < tolerance && Math.abs(this.y - y) < tolerance;
		
	}
}