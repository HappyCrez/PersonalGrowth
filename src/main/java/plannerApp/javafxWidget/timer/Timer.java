package plannerApp.javafxWidget.timer;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
    private ImageView bigClock;
    private Rectangle bigArrow;

    private ImageView smallClock;
    private Rectangle smallArrow;    

    private Button startBtn;
    
    private Rotate rotation; 
    private Rotate smallClockRotation;
    private Timeline timelineBigClock;
    private Duration timeStop;
    private Boolean isStarted = false;
    
    private int maxTimeInSeconds = 25 * 60;
    private Duration maxTime = new Duration(maxTimeInSeconds * 1000);
    
    private AudioInputStream stream;
    private Clip timerBell;

    public Timer() {
        this.setAlignment(Pos.CENTER);
        this.setId("timerBox");
        
        timeStop = new Duration(0);

        createBigClock();
        createSmallClock();

        timerPane = new AnchorPane();
        timerPane.getChildren().addAll(bigClock, smallClock, smallArrow, bigArrow);
        
        AnchorPane.setLeftAnchor(bigArrow, 199.0);
        AnchorPane.setBottomAnchor(bigArrow, 198.0);
        
        AnchorPane.setRightAnchor(smallClock, 60.0);
        AnchorPane.setBottomAnchor(smallClock, 140.0);
        AnchorPane.setRightAnchor(smallArrow, 119.0);
        AnchorPane.setBottomAnchor(smallArrow, 200.0);

        try {
            stream = AudioSystem.getAudioInputStream(this.getClass().getResource("../../../sounds/timerBell.wav"));
            timerBell = AudioSystem.getClip();
            timerBell.open(stream);
        } catch(Exception exception) { exception.printStackTrace(); };
        
        timelineBigClock = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
            new KeyFrame(Duration.seconds(maxTimeInSeconds), new KeyValue(rotation.angleProperty(),360))
            );
        int partsCount = 5;
        for (int partNumber = 0; partNumber < partsCount; partNumber++) {
            int partDuration = maxTimeInSeconds / partsCount; 
            timelineBigClock.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(partDuration * partNumber), new KeyValue(smallClockRotation.angleProperty(), 0)),
            new KeyFrame(Duration.seconds(partDuration * partNumber + partDuration), new KeyValue(smallClockRotation.angleProperty(),360)));
        }
        timelineBigClock.setOnFinished((e) -> {
            timelineEnd();
        });

        startBtn = new Button("Start");
        startBtn.setId("TimerStart");
        startBtn.setOnAction((e) -> {
            timerTurn();
        });

        this.getChildren().addAll(timerPane, startBtn);
    }

    private void createBigClock() {
        bigClock = new ImageView();
        bigClock.setImage(new Image("/images/clock.png"));

        bigArrow = new Rectangle(3, 165);
        bigArrow.setRotate(180.0);
        bigArrow.setFill(Color.RED);

        rotation = new Rotate();
        rotation.pivotXProperty().bind(bigArrow.xProperty().add(1));
        rotation.pivotYProperty().bind(bigArrow.yProperty().add(2));
        bigArrow.getTransforms().add(rotation);
    }

    private void createSmallClock() {
        smallClock = new ImageView();
        smallClock.setImage(new Image("/images/clockSmall.png"));

        smallArrow = new Rectangle(2, 50);
        smallArrow.setRotate(180.0);
        smallArrow.setFill(Color.RED);

        smallClockRotation = new Rotate();
        smallClockRotation.pivotXProperty().bind(bigArrow.xProperty().add(1));
        smallClockRotation.pivotYProperty().bind(bigArrow.yProperty().add(2));
        smallArrow.getTransforms().add(smallClockRotation);
    }

    private void timelineEnd() {
        timeStop = Duration.ZERO;
        timerBell.setFramePosition(0);
        timerBell.start();
        timerTurn();
    }

    private void timerTurn() {
        if (isStarted) stop();
        else start();
        isStarted = !isStarted;
    }
    
    public void start() {
        timelineBigClock.playFrom(timeStop);
        startBtn.setText("Stop");
    }

    public void stop() {
        timeStop = timelineBigClock.getCurrentTime();
        if (timeStop.greaterThanOrEqualTo(maxTime))
            timeStop = Duration.ZERO;
        timelineBigClock.stop();
        startBtn.setText("Start");
    }

    @Override
    public void finalize() {
        timerBell.close();
        try {
            stream.close();
        } catch (IOException e) { }
    }
}
