package plannerApp;

import java.util.HashMap;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreenController {
	
	private HashMap<String, Parent> screens = new HashMap<String, Parent>();
	private Scene mainScene;
	private StackPane parentContainer;
	
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

	public void activateScreen(String screenName, Animation.animationStyles style) {
		Parent root = screens.get(screenName);
		if (parentContainer.getChildren().contains(root)) {
			parentContainer.getChildren().remove(root);
		}
		Animation.animate(parentContainer, root, style);		
	}
}
