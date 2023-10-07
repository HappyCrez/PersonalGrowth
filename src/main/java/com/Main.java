package com;

import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("My Application");
	    primaryStage.setWidth(300);
	    primaryStage.setHeight(200);
	    
	    try {	    	
		    InputStream iconStream = getClass().getResourceAsStream("./icon.png");
		    Image image = new Image(iconStream);
		    primaryStage.getIcons().add(image);
	    }
	    catch (Exception e)
	    {
	    	System.out.println(e);
	    }

	    Label helloWorldLabel = new Label("Hello world!");
	    helloWorldLabel.setAlignment(Pos.CENTER);
	    Scene primaryScene = new Scene(helloWorldLabel);
	    primaryStage.setScene(primaryScene);

	    primaryStage.show();
		
	}
}
