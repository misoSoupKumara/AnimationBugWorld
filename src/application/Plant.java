package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Plant extends Circle {
	float x = 100, y = 100, dx = -1.5f, dy = -1.5f;

	public Plant(float x, float y, float dx, float dy, String fileName) {
		super(x, y, 50);
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		try {
			this.setFill(new ImagePattern(new Image(new FileInputStream(fileName))));
		} catch (FileNotFoundException e) {
			this.setFill(Color.GREEN);
			e.printStackTrace();
		}
	}
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

}
