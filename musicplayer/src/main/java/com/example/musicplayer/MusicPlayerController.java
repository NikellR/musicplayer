package com.example.musicplayer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MusicPlayerController {
    @FXML
    private Button nextButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text songText;

    public Queue<Song> music;

    public boolean pause = true;

    public Timeline timeline;

    @FXML
    void nextBtnPressed(MouseEvent event) {
        timeline.jumpTo(Duration.ZERO);
        music.dequeue();
        songText.setText(String.valueOf(music.frontValue()));


    }

    @FXML
    void pauseBtnPressed(MouseEvent event) {
        if (pause == true) {
            timeline.pause();
            pause = false;
            pauseButton.setText("Resume");
        } else {
            timeline.play();
            pause = true;
            pauseButton.setText("Pause");
        }
    }

    @FXML
    void playBtnPressed(MouseEvent event) {
        BufferedReader br = null;
        try {
            //Reading the csv file
            br = new BufferedReader(new FileReader("src/Songs.csv"));

            music = new ArrayQueue<>();

            String line = "";
            //Read to skip the header
            br.readLine();
            //Reading from the second line
            while ((line = br.readLine()) != null) {
                String[] songDetails = line.split(",");

                if (songDetails.length > 0) {
                    Song song = new Song();
                    song.setTitle(songDetails[0]);
                    song.setArtist(songDetails[1]);

                    music.enqueue(song);
                }
            }
            songText.setText(String.valueOf(music.frontValue()));
            loadProgressBar();
            playButton.setDisable(true);


        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ie) {
                System.out.println("Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }

    }

    void loadProgressBar() { // Using the timeline util to simulate a song playing:
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.minutes(1), e-> {
                    music.dequeue();
                    songText.setText(String.valueOf(music.frontValue()));
                    progressBar.setProgress(0);
                }, new KeyValue(progressBar.progressProperty(), 1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}