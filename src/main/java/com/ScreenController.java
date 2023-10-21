package com;

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
	private int sceneChangedCount = 0;
	
	public ScreenController (Scene mainScene) {
		this.mainScene = mainScene;
		parentContainer = new StackPane();
		mainScene.setRoot(parentContainer);
	}
	
	public void addScreen(String screenName, Parent screen) {
		screens.put(screenName, screen);
	}

	public void activateScreen(String screenName) {
		Parent root = screens.get(screenName);
		if (sceneChangedCount == 0) {
			parentContainer.getChildren().add(root);
			sceneChangedCount++;
			return;
		}
			
		if (parentContainer.getChildren().contains(root)) {
			parentContainer.getChildren().remove(root);
		}
		root.translateXProperty().set(mainScene.getWidth());
		parentContainer.getChildren().add(root);
		
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
}
