package com;

import java.util.HashMap;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class ScreenController {
	
	private HashMap<String, Parent> screens = new HashMap<String, Parent>();
	private Scene mainScene;
	
	public ScreenController (Scene mainScene) {
		this.mainScene = mainScene;
	}
	
	public void activateScreen(String screenName) {
		Parent root = screens.get(screenName);
		if (root != null)
			mainScene.setRoot(root);
	}
	
	public void addScreen(String screenName, Parent screen) {
		screens.put(screenName, screen);
	}
}
