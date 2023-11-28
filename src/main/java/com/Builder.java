package com;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Builder extends Application{
	
	private int mainWindowWidth = 800, mainWindowHeight = 600;
	private Stage mainWindow;
	private Scene mainScene;
	private ScreenController screenController;
	private Image windowIcon;
	
	public static void main(String args[]) {
		System.setProperty("javafx.preloader", "com.LoadWindow");
		launch(args);
	}

	@Override
	public void init() {
		// TODO::Delete delay for show (pre)loadingView
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start < 5000) ;

		windowIcon = new Image("/images/logo.png");
		mainScene = new Scene(new Pane(), mainWindowWidth, mainWindowHeight);
		screenController = new ScreenController(mainScene);
		LoadHelper.loadAllViews(screenController);
	}

	@Override
	public void start(Stage mainWindow) throws Exception {	
		this.mainWindow = mainWindow;
		setupWindow(mainWindow, mainScene);
		
		screenController.activateScreen("toDoListView", ScreenController.animationStyles.instantShow);
	}
	
	private void setupWindow(Stage window, Scene scene) {
		window.centerOnScreen();
		window.sizeToScene();
		window.setScene(scene);
		window.getIcons().add(windowIcon);
		window.setTitle("Personal Growth");
		mainWindow.show();
	}
}
