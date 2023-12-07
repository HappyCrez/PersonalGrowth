package plannerApp;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Animation {
    public enum animationStyles
	{
		instantShow,
		rightToLeft,
		leftToRight,
		downToUp
	}

    public static void animate(Scene scene, Pane parent, Parent children, animationStyles style) {
        switch (style) {
            case instantShow:
                instantShowAnimation(scene, parent, children);
                break;
            case rightToLeft:
                rightToLeftAnimation(scene, parent, children);
                break;
            case leftToRight:
                leftToRightAnimation(scene, parent, children);
                break;
            case downToUp:
                downToUpAnimation(scene, parent, children);
                break;
            default:
                System.out.println("Unrecognize style in activateScreen func: " + style);
                break;
            }
    }
	
    private static void instantShowAnimation(Scene scene, Pane parent, Parent children) {
		parent.getChildren().add(children);
	}
	
	private static void rightToLeftAnimation(Scene scene, Pane parent, Parent children) {
		children.translateXProperty().set(scene.getWidth());
		parent.getChildren().add(children);
		
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(children.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	private static void leftToRightAnimation(Scene scene, Pane parent, Parent children) {
		children.translateXProperty().set(-scene.getWidth());
		parent.getChildren().add(children);
		
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(children.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	private static void downToUpAnimation(Scene scene, Pane parent, Parent children) {
		children.translateYProperty().set(scene.getHeight());
		parent.getChildren().add(children);
		
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(children.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
}
