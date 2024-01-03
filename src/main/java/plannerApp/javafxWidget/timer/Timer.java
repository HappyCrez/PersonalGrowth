package plannerApp.javafxWidget.timer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Timer extends VBox{
    private AnchorPane timerPane;
    private ImageView clock;
    private Rectangle arrow;
    
    private Button startBtn;
    
    private Rotate rotation; 
    private Timeline timeline;   
    private Duration timeStop;
    private Boolean isStarted = false;
    
    private int maxTimeInSeconds = 25 * 60;
    private Duration maxTime = new Duration(maxTimeInSeconds * 1000);
    
    // private AudioInputStream stream;
    // private Clip timerBell;

    public Timer() {
        this.setAlignment(Pos.CENTER);
        this.setId("timerBox");
        
        timeStop = new Duration(0);

        clock = new ImageView();
        clock.setImage(new Image("/images/clock_small.png"));

        arrow = new Rectangle(3, 135);
        arrow.setRotate(180.0);
        arrow.setFill(Color.RED);

        rotation = new Rotate();
        rotation.pivotXProperty().bind(arrow.xProperty().add(1));
        rotation.pivotYProperty().bind(arrow.yProperty().add(2));
        arrow.getTransforms().add(rotation);

        timerPane = new AnchorPane();
        timerPane.getChildren().addAll(clock, arrow);
        
        AnchorPane.setLeftAnchor(arrow, 149.0);
        AnchorPane.setBottomAnchor(arrow, 148.0);

        // try {
        //     stream = AudioSystem.getAudioInputStream(this.getClass().getResource("../../../sounds/timerBell.wav"));
        //     timerBell = AudioSystem.getClip();
        //     timerBell.open(stream);
        // } catch(Exception exception) { exception.printStackTrace(); };
        
        timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
            new KeyFrame(Duration.seconds(maxTimeInSeconds), new KeyValue(rotation.angleProperty(),360))
            );
        timeline.setOnFinished((e) -> {
            //TODO::Notification
            timelineEnd();
        });

        startBtn = new Button("Start");
        startBtn.setId("TimerStart");
        startBtn.setOnAction((e) -> {
            timerTurn();
        });

        this.getChildren().addAll(timerPane, startBtn);
    }

    private void timelineEnd() {
        timeStop = Duration.ZERO;
        // timerBell.setFramePosition(0);
        // timerBell.start();
        timerTurn();
    }

    private void timerTurn() {
        if (isStarted) stop();
        else start();
        isStarted = !isStarted;
    }
    
    public void start() {
        timeline.playFrom(timeStop);
        startBtn.setText("Stop");
    }

    public void stop() {
        timeStop = timeline.getCurrentTime();
        if (timeStop.greaterThanOrEqualTo(maxTime))
            timeStop = Duration.ZERO;
        timeline.stop();
        startBtn.setText("Start");
    }

    @Override
    public void finalize() {
        // timerBell.close();
        // try {
        //     stream.close();
        // } catch (IOException e) { }
    }
}
