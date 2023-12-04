package plannerApp;

import java.util.HashMap;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreenController {
	
	private HashMap<String, Parent> screens = new HashMap<String, Parent>();
	private Scene mainScene;
	private StackPane parentContainer;
	
	public enum animationStyles
	{
		instantShow,
		rightToLeft,
		leftToRight,
		downToUp
	}
	
	public ScreenController (Scene mainScene) {
		this.mainScene = mainScene;
		parentContainer = new StackPane();
		parentContainer.setMinWidth(300);
		parentContainer.setMinHeight(300);
		mainScene.setRoot(parentContainer);
	}
	
	public void addScreen(String screenName, Parent screen) {
		screens.put(screenName, screen);
	}

	public void activateScreen(String screenName, animationStyles style) {
		Parent root = screens.get(screenName);
		if (parentContainer.getChildren().contains(root)) {
			parentContainer.getChildren().remove(root);
		}

		switch (style) {
		case instantShow:
			instantShowAnimation(root);
			break;
		case rightToLeft:
			rightToLeftAnimation(root);
			break;
		case leftToRight:
			leftToRightAnimation(root);
			break;
		case downToUp:
			downToUpAnimation(root);
			break;
		default:
			System.out.println("Unrecognize style in activateScreen func: " + style);
			break;
		}
	}
	
	private void instantShowAnimation(Parent root) {
		parentContainer.getChildren().add(root);
	}
	
	private void rightToLeftAnimation(Parent root) {
		root.translateXProperty().set(mainScene.getWidth());
		parentContainer.getChildren().add(root);
		
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	private void leftToRightAnimation(Parent root) {
		root.translateXProperty().set(-mainScene.getWidth());
		parentContainer.getChildren().add(root);
		
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	private void downToUpAnimation(Parent root) {
		root.translateYProperty().set(mainScene.getHeight());
		parentContainer.getChildren().add(root);
		
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
}
