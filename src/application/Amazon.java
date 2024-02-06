package application;
import java.util.ArrayList;
import java.util.Random;
//import clickableBugs.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Amazon extends Application {
	int width = 1000, height = 600;
	float x = 100, y = 100;
	static Label l = new Label("Welcome to my Bug World!    ");

	ArrayList<Bug> bugs = new ArrayList<Bug>();
	ArrayList<Plant> plants = new ArrayList<Plant>();

	@Override
	public void start(Stage primaryStage) throws Exception {

		Bug grasshopper = new Bug(60, 100, -1.5f, -1.5f, "grasshopper.png");
		Bug bee = new Bug(120, 200, -1.5f, -1.5f, "bee.png");
		Plant flower = new Plant(60, 50, -1.5f, -1.5f, "flower.png");
		Plant grass = new Plant(300, 300, -1.5f, -1.5f, "grass.png");
		Plant appleTree = new Plant(600, 500, -1.5f, -1.5f, "appletree.png");

		bugs.add(grasshopper);
		bugs.add(bee);
		plants.add(flower);
		plants.add(grass);
		plants.add(appleTree);

		Timeline timeline = new Timeline();

		Button btn1 = new Button();
		btn1.setText("Start");
		btn1.setOnAction(e -> timeline.play());

		Button btn2 = new Button();
		btn2.setText("Pause");
		btn2.setOnAction(e -> timeline.pause());

		Button btn3 = new Button();
		btn3.setText("Stop");
		btn3.setOnAction(e -> timeline.stop());

		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		hb1.setTranslateX(250);
		hb2.setTranslateY(15);
		BorderPane bp = new BorderPane();
		bp.setTop(hb1);
		bp.setCenter(hb2);


		Slider slider = new Slider(1, 25, 1);
		Text t = new Text("  Speed Slider      ");
		t.setFill(Color.HOTPINK);
		t.setFont(Font.font("Apple Chancery", FontWeight.BOLD, 20));
		slider.setPrefWidth(200);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMin(0);
		slider.setValue(1);
		hb1.setAlignment(Pos.TOP_LEFT);
		hb1.getChildren().addAll(l,slider, t, btn1, btn2, btn3);
		hb1.setTranslateY(12);

		Group root = new Group();

		for (Bug c : bugs) {
			root.getChildren().add(c);

			c.setOnMouseClicked(new MyCircleClickEventHandler());
		}

		for (Plant p : plants) {
			root.getChildren().add(p);

			p.setOnMouseClicked(new MyCircleClickEventHandler());
		}

		final Scene scene = new Scene(root, width, height);

		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				// System.out.println(balls.size());
				// go through all the circles
				for (Bug c : bugs) {
//					System.out.println("In....");
//					c.move();
					// when the circle hits the wall, it will bounce it back
					if (c.getCenterX() < c.getRadius() || c.getCenterX() + c.getRadius() > scene.getWidth()) {
						// dx = - dx;
						c.setDx(-c.getDx());
					}

					if (c.getCenterY() < c.getRadius() || c.getCenterY() + c.getRadius() > scene.getHeight()) {
						// dy = -dy;
						c.setDy(-c.getDy());
					}

					/**
					 * bugs move randomly
					 */
					double dxRandom = Math.random() * 5 * c.dx;
					double dyRandom = Math.random() * 5 * c.dy;

					c.setX(c.getX() + dxRandom);
					c.setCenterX(c.getX());

					c.setY(c.getY() + dyRandom);
					c.setCenterY(c.getY());

				}

				for (Plant p : plants) {

					if (p.getCenterX() < p.getRadius() || p.getCenterX() + p.getRadius() > scene.getWidth()) {

						p.setDx(-p.getDx());
					}

					if (p.getCenterY() < p.getRadius() || p.getCenterY() + p.getRadius() > scene.getHeight()) {

						p.setDy(-p.getDy());
					}

					p.setX(p.getX() + p.getDx());
					p.setCenterX(p.getX());

					p.setY(p.getY() + p.getDy());
					p.setCenterY(p.getY());

				}
			}
		});

		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();

		slider.valueProperty().addListener(new ChangeListener<Number>() {

			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				timeline.setRate(slider.getValue());
			}
		});

		primaryStage.setTitle("This is my mini Amazon!");
		primaryStage.setScene(scene);
		primaryStage.show();

		BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf("#E0FFFF"), new CornerRadii(10),
				new Insets(10));
		Background background = new Background(backgroundFill);
		hb2.setBackground(background);
		hb2.getChildren().addAll(root);
		Scene scene2 = new Scene(bp, width, height);
		primaryStage.setScene(scene2);
	}

	public static void main(String[] args) {
		launch();
	}

}

class MyCircleClickEventHandler implements EventHandler<MouseEvent> {
	// MyCircle mc;

	public MyCircleClickEventHandler(Bug mc) {
		// this.mc = mc;
	}

	public MyCircleClickEventHandler() {

	}

	@Override
	public void handle(MouseEvent event) {

		// TODO Auto-generated method stub
		System.out.println("Clicked on " + event.getSource());
		Random random = new Random();
		int textType = random.nextInt(5);
		if (textType == 0) {
			Amazon.l.setText("Kia Ora!");
		} else if (textType == 1) {
			Amazon.l.setText("Hi there!");
		} else if (textType == 2) {
			Amazon.l.setText("Good day!");
		} else if (textType == 3) {
			Amazon.l.setText("Hello!");
		} else if (textType == 4) {
			Amazon.l.setText("Good afternoon!");
		}
	}
}
