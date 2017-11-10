/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author whoami
 */
public class TP2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Scene scene = new Scene(createGUI(), 700, 100);
        
        primaryStage.setTitle("SUPER VLC OF THE FUTURE");
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(100);
        primaryStage.setMaxHeight(100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private BorderPane createGUI() {
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.GREY, CornerRadii.EMPTY, Insets.EMPTY
                        )));
        
        HBox playerBox = new HBox();
        HBox viewBox = new HBox();
        
        HBox playlistControlBox = new HBox();
        playerBox.getChildren().add(new Slider());
        
        GridPane functionGridPane = new GridPane();
        
        borderPane.setLeft(functionGridPane);
        borderPane.setCenter(playerBox);
        
        Button backButton = new Button();
        Button playButton = new Button();
        Button forwardButton = new Button();
        
        Button previousButton = new Button();
        Button stopButton = new Button();
        Button nextButton = new Button();
        
        functionGridPane.add(backButton, 0, 0);
        functionGridPane.add(playButton, 1, 0);
        functionGridPane.add(forwardButton, 2, 0);
        
        functionGridPane.add(previousButton, 0, 1);
        functionGridPane.add(stopButton, 1, 1);
        functionGridPane.add(nextButton, 2, 1);
        
        return borderPane;
    }
    
}
