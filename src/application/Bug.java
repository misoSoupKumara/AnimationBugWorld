package application;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.paint.*;

//import com.sun.prism.paint.Color;

public class Bug extends Circle {
	double x = 100;
	double y = 100, dx = -1.5f, dy = -1.5f;
	
	public Bug(float x, float y, float dx, float dy, String fileName) {
		super(x, y, 40);
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		try {
			this.setFill(new ImagePattern(new Image(new FileInputStream(fileName))));
		} catch (FileNotFoundException e) {
			this.setFill(Color.RED);
			e.printStackTrace();
		}	
	}

	public double getX() {
		return x;
	}

	public void setX(double d) {
		this.x = d;
	}

	public double getY() {
		return y;
	}

	public void setY(double d) {
		this.y = d;
	}

	public double getDx() {
		return dx;
	}
	
	public void setDx(double d) {
		this.dx = d;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double d) {
		this.dy = d;
	}

	
}

