import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class BallLauncher extends GraphicsProgram{
	public static final int PROGRAM_HEIGHT = 600;
	public static final int PROGRAM_WIDTH = 800;
	public static final int SIZE = 25;
	
	private static final int SPEED = 2;
	private static final int MS = 50;
	private Timer t;
	private ArrayList<GOval> balls;
	
	
	public void actionPerformed(ActionEvent e) {
		for (GOval ball : balls) {
			ball.move(SPEED, 0);
		}
	}
	
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		t = new Timer(MS,this);
		addMouseListeners();
		t.start();
		balls = new ArrayList<GOval>();
	}
	
	private boolean canSpawnBall() {
        // If no balls exist, we can always spawn
        if (balls.isEmpty()) return true;

        // Otherwise, all must have x >= 100
        for (GOval b : balls) {
            if (b.getX() < 100) return false;
        }
        return true;
    }
	
	public void mousePressed(MouseEvent e) {
		if (canSpawnBall()) {
			GOval ball = makeBall(SIZE/2, e.getY());
			add(ball);
			balls.add(ball);
		}
	}
	
	public GOval makeBall(double x, double y) {
		GOval temp = new GOval(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
		temp.setColor(Color.RED);
		temp.setFilled(true);
		return temp;
	}
	
	public static void main(String[] args) {
		new BallLauncher().start();
	}

}
