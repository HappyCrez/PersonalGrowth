package com;

import javafx.application.Preloader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadWindow extends Preloader {
    
    private Stage loadWindow;
	private int loadWindowSize = 350;

    public void start(Stage loadWindow) throws Exception {
        this.loadWindow = loadWindow;
        Image logo = new Image("/images/logo.png");
        Scene loadScene = createLoadScene();
        setupLoadWindow(loadScene, logo);
    }

    private Scene createLoadScene() {
        Parent root = LoadHelper.loadView(new Object(), "loadingView");
        Scene scene = new Scene(root, loadWindowSize, loadWindowSize);
        scene.setFill(null);
        return scene;
    }

    private void setupLoadWindow(Scene loadScene, Image icon) {
        loadWindow.initStyle(StageStyle.TRANSPARENT);
        loadWindow.getIcons().add(icon);
        loadWindow.setScene(loadScene);
        loadWindow.sizeToScene();
        setStageCenter();
        loadWindow.show();
    }

    private void setStageCenter() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        loadWindow.setX((screenBounds.getWidth() - loadWindowSize) / 2); 
        loadWindow.setY((screenBounds.getHeight() - loadWindowSize) / 2);
    }
    
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        //bar.setProgress(pn.getProgress());
    }
 
    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            loadWindow.hide();
        }
    }    
}