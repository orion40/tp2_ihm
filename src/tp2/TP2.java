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
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage; 


/**
 * MAIN ...
 * @author whoami
 * 
 */
public class TP2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        BorderPane root = createGUI();
        
        Scene scene = new Scene(root, 700, 100);
        
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
    

    
    
    /**
     * Création de l'interface 
     * @return borderpanel contenant toute l'interface
     */
    public BorderPane createGUI() {
        
        /* Une déclaration créer les élément de la couche actuelle */
        /* Une affectation lie les éléments de la couche actuelle avec ...*/
        /* ... ceux de la couche inférieur */
        
        /* ARBRES DES COUCHES : 
        
                                                         scene
                                                           |
                                         ----------BorderPane borderPane----------
                                       /                 |                         \
                                  (left)              (center)                   (bottom)
                  GridPane functionGridPane       VBox generalPlayerBox          ????????
                         |                          /          \   
                   B*6  lecture          HBox playerBox     HBox mixBox
                                                 |                  |
                                               slide    slidePlayer + B*2 + i*2
        
        
        */
        
        
        
        /*************************************************************/
        /********************** COUCHE 4 *****************************/
        
        /***************/
        /* DECLARATION */
        // Image son
        Image sonHt = new Image(getClass().getResourceAsStream("sonH.png"), 15, 15, true, true);
        Image sonLt= new Image(getClass().getResourceAsStream("sonL.png"), 15, 15, true, true);
        ImageView sonH = new ImageView(sonHt);
        ImageView sonL = new ImageView(sonLt);
        
        // Button 
        Button listButton = new Button();
        Button mixButton = new Button();
        
        // Slide
        Slider slidePlayer = new Slider();
        
        /***************/
        /* AFFECTATION */
        
        
        
        
        
        /*************************************************************/
        /********************** COUCHE 3 *****************************/
        
        /***************/
        /* DECLARATION */
        // image des boutons        
        Image backimg = new Image(getClass().getResourceAsStream("backimg.png"), 30, 30, true, true);
        Image playimg = new Image(getClass().getResourceAsStream("playimg.png"), 30, 30, true, true);
        Image forwimg = new Image(getClass().getResourceAsStream("forwimg.png"), 30, 30, true, true);
        Image previmg = new Image(getClass().getResourceAsStream("previmg.png"), 30, 30, true, true);
        Image stopimg = new Image(getClass().getResourceAsStream("stopimg.png"), 30, 30, true, true);
        Image nextimg = new Image(getClass().getResourceAsStream("nextimg.png"), 30, 30, true, true);

        // Button de lecture
        Button backButton = new Button();
        Button playButton = new Button();
        Button forwardButton = new Button();
        Button previousButton = new Button();
        Button stopButton = new Button();
        Button nextButton = new Button();
        
        // Box pour generalPlayerBox
        HBox playerBox = new HBox(); // slide lecture musique
        HBox mixBox = new HBox(); // Controle son + ouverture menu son
       
        
        /***************/
        /*    SETUP    */
        // image des boutons
        backButton.setPadding(Insets.EMPTY);
        backButton.setGraphic(new ImageView(backimg));
        playButton.setPadding(Insets.EMPTY);
        playButton.setGraphic(new ImageView(playimg));
        forwardButton.setPadding(Insets.EMPTY);
        forwardButton.setGraphic(new ImageView(forwimg));
        previousButton.setPadding(Insets.EMPTY);
        previousButton.setGraphic(new ImageView(previmg));
        stopButton.setPadding(Insets.EMPTY);
        stopButton.setGraphic(new ImageView(stopimg));
        nextButton.setPadding(Insets.EMPTY);
        nextButton.setGraphic(new ImageView(nextimg));

        
        /***************/
        /* AFFECTATION */
        // Slider pour la HBox "playerBox"
        playerBox.getChildren().add(new Slider());
        mixBox.getChildren().addAll(sonL,slidePlayer, sonH, listButton, mixButton);
        
        
        
        
        /*************************************************************/
        /********************** COUCHE 2 *****************************/
        
        /***************/
        /* DECLARATION */
        GridPane functionGridPane = new GridPane(); // A gauche dans root
        VBox generalPlayerBox = new VBox(); // Au centre dans root
        
        
        /***************/
        /* AFFECTATION */
        // Button du GridePan "functionGridePan"
        functionGridPane.add(backButton, 0, 0);
        functionGridPane.add(playButton, 1, 0);
        functionGridPane.add(forwardButton, 2, 0);
        functionGridPane.add(previousButton, 0, 1);
        functionGridPane.add(stopButton, 1, 1);
        functionGridPane.add(nextButton, 2, 1);
        
        // Box du generalplayer
        generalPlayerBox.getChildren().addAll(playerBox, mixBox);
        
        
        
        /*************************************************************/
        /********************** COUCHE 1 *****************************/
        
        /***************/
        /* DECLARATION */
        BorderPane borderPane = new BorderPane(); // Root
        
        /***************/
        /*    SETUP    */
        borderPane.setBackground(
            new Background(
                new BackgroundFill(
                    Color.GREY, CornerRadii.EMPTY, Insets.EMPTY
                )
            )
        );
        
        /***************/
        /* AFFECTATION */
        borderPane.setLeft(functionGridPane); // Boutton de lecture
        borderPane.setCenter(generalPlayerBox); // Player
        
        
        
        return borderPane;
    }
    
    


}