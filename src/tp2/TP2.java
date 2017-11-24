/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package tp2;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author whoami
 */
public class TP2 extends Application {
    Stage stage;
    BorderPane root, playlist;
    
    public void hidePlaylist(ToggleButton playlistToggleButton, String buttonDefaultStyle){
        /*
        On désactive la coloration du toggleButton, enlevons le bottom de
        root, et redimensionnant la fenêtre à la taille sans playlist.
        */        
        playlistToggleButton.setStyle(buttonDefaultStyle);
        root.setBottom(null);
        stage.setMinHeight(92);
        stage.setMaxHeight(92);
        stage.sizeToScene();
    }
    
    /*
            GRAPHICAL USER INTERFACE
    */
    
    /*
    Contient le nom de la piste, le temps de lecture, le slider
    de controle du temps de la piste.
    */
    public BorderPane createCurrentTrackInfoPane(){
        BorderPane currentTrackInfoPane = new BorderPane();
        
        currentTrackInfoPane.setLeft(new Label("Lecteur VLC"));
        // Rien au centre
        currentTrackInfoPane.setRight(new Label("00:00"));
        currentTrackInfoPane.setBottom(new Slider());
        
        return currentTrackInfoPane;
    }
    
    /* Contient l'icones de volume bas, le slider du son,
    et l'icone de volume haut.
    */
    public BorderPane createSoundControlPane(){
        BorderPane soundControlPane = new BorderPane();
        
        soundControlPane.setLeft(new Label("-"));
        soundControlPane.setCenter(new Slider());
        soundControlPane.setRight(new Label("+"));
        
        return soundControlPane;
    }
    
    /*
    Contient le bouton du mixer, et d'affichage de la playlist.
    */
    public BorderPane createOverallTrackControlButtonsPane(){
        BorderPane overallTrackControlButtonsPane = new BorderPane();
        ToggleButton playlistToggleButton = new ToggleButton(":=");
        String buttonDefaultStyle = playlistToggleButton.getStyle();
        ChangeListener stageResizeListener = (ChangeListener) new ChangeListener() {
            @Override
            public void changed(ObservableValue obs, Object t1, Object t2) {
                Double newVal = Double.valueOf(t1.toString());
                System.out.println("t1: " + t1);
                System.out.println("t2 " + t2);
                if (newVal.intValue() < 200 && playlistToggleButton.isSelected()){
                    playlistToggleButton.setSelected(false);
                    stage.heightProperty().removeListener(this);
                    hidePlaylist(playlistToggleButton, buttonDefaultStyle);
                }
                System.out.println("stage height 1"+stage.getHeight());

            }
        };
        
        playlistToggleButton.setOnAction((ActionEvent t) -> {
            if (playlistToggleButton.isSelected()){
                /*
                On Colore le button comme il faut, ajoutons la playlist,
                redimensionnant à la nouvelle taille.
                On ajoute un listener, au cas ou la playlist n'est plus suffisament visible.
                */
                playlistToggleButton.setStyle("-fx-background-color: #0000aa; ");
                root.setBottom(playlist);
                stage.sizeToScene();
                stage.setMaxHeight(stage.getHeight());
                stage.setHeight(stage.getHeight());
                System.out.println("stage height 1(playlist)"+stage.getHeight());
                stage.heightProperty().addListener(stageResizeListener);
                System.out.println("stage height 2(playist)"+stage.getHeight());
            }else{
                stage.heightProperty().removeListener(stageResizeListener);
                hidePlaylist(playlistToggleButton, buttonDefaultStyle);
            }
        });
        overallTrackControlButtonsPane.setLeft(new Button("|||"));
        overallTrackControlButtonsPane.setRight(playlistToggleButton);
        
        return overallTrackControlButtonsPane;
    }
    
    /*
    Contient le slider du volume à gauche, et les boutons
    pour le mixer et la playlist à droite.
    */
    public BorderPane createOverallTrackPane(){
        BorderPane overallTrackPane = new BorderPane();
        
        overallTrackPane.setLeft(createSoundControlPane());
        // Center is empty
        overallTrackPane.setRight(createOverallTrackControlButtonsPane());
        
        return overallTrackPane;
    }
    
    /*
    Contient les bouttons d'avance rapide, de lecture, et
    de recule rapide.
    */
    public BorderPane createCurrentTrackButtonsPane(){
        BorderPane currentTrackButtonsPane = new BorderPane();
        
        currentTrackButtonsPane.setLeft(new Button("<<"));
        currentTrackButtonsPane.setCenter(new Button(">"));
        currentTrackButtonsPane.setRight(new Button(">>"));
        
        return currentTrackButtonsPane;
    }
    
    /*
    Contient les boutons de piste précédente, arret, et piste suivante.
    */
    public BorderPane createAllTracksButtonsPane(){
        BorderPane allTracksButtonsPane = new BorderPane();
        
        allTracksButtonsPane.setLeft(new Button("|<"));
        allTracksButtonsPane.setCenter(new Button("||"));
        allTracksButtonsPane.setRight(new Button(">|"));
        
        return allTracksButtonsPane;
    }
    
    /*
    Contient la partie gauche du lecteur, c'est à dire
    les boutons de recule rapide, de lecture, d'avance rapide,
    de piste précédente, d'arrêt et de piste suivante.
    */
    public BorderPane createTracksButtonsPane(){
        BorderPane tracksButtonsPane = new BorderPane();
        
        tracksButtonsPane.setTop(createCurrentTrackButtonsPane());
        tracksButtonsPane.setBottom(createAllTracksButtonsPane());
        
        return tracksButtonsPane;
    }
    
    public BorderPane createTrackControlPane(){
        BorderPane trackControlPane = new BorderPane();
        
        trackControlPane.setTop(createCurrentTrackInfoPane());
        trackControlPane.setBottom(createOverallTrackPane());
        
        return trackControlPane;
    }
    
    /* Contient tout le lecteur */
    public BorderPane createPlayerPane(){
        BorderPane playerPane = new BorderPane();
        
        playerPane.setLeft(createTracksButtonsPane());
        playerPane.setCenter(createTrackControlPane());
        
        return playerPane;
    }
    
    // TODO : contenu playlist ici
    
    public TreeTableView<Track> createPlayListTableView(){
        TreeTableView<Track> playlistTreeTableView = new TreeTableView<>();
        Track trackFolder = new Track("Cool tracks");
        Track track1 = new Track("Chosen Few", "Name Of The DJ", "5:13");
        Track track2 = new Track("Angerfist & Dr. Peacock", "Inframan", "4:22");
        Track track3 = new Track("Zyklon", "Chemical Waste", "3:37");
        Track track4 = new Track("The Speedfreak", "Boombox (Mat Weasel Remix)", "4:43");
        Track track5 = new Track("Dr. Peacock", "Frenchcore Worldwide", "5:28");
        TreeItem<Track> rootItem = new TreeItem<>(trackFolder);
        TreeItem<Track> trackItem1 = new TreeItem<>(track1);
        TreeItem<Track> trackItem2 = new TreeItem<>(track2);
        TreeItem<Track> trackItem3 = new TreeItem<>(track3);
        TreeItem<Track> trackItem4 = new TreeItem<>(track4);
        TreeItem<Track> trackItem5 = new TreeItem<>(track5);
        playlistTreeTableView.setColumnResizePolicy(TreeTableView.UNCONSTRAINED_RESIZE_POLICY);
        rootItem.setExpanded(true);
        rootItem.getChildren().setAll(trackItem1, trackItem2, trackItem3, trackItem4, trackItem5);
        
        //Creating a column
        TreeTableColumn<Track,String> columnName = new TreeTableColumn<>("Name");
        TreeTableColumn<Track,String> columnArtist = new TreeTableColumn<>("Artist");
        TreeTableColumn<Track,String> columnDuration = new TreeTableColumn<>("Duration");
        columnName.setPrefWidth(150);
        columnArtist.setPrefWidth(150);
        columnDuration.setPrefWidth(150);
        
        //Defining cell content
        columnName.setCellValueFactory((TreeTableColumn.CellDataFeatures<Track, String> p) ->
                new ReadOnlyStringWrapper(p.getValue().getValue().getTrackName()));
        columnArtist.setCellValueFactory((TreeTableColumn.CellDataFeatures<Track, String> p) ->
                new ReadOnlyStringWrapper(p.getValue().getValue().getArtistName()));
        columnDuration.setCellValueFactory((TreeTableColumn.CellDataFeatures<Track, String> p) ->
                new ReadOnlyStringWrapper(p.getValue().getValue().getDuration()));
        
        playlistTreeTableView.getColumns().addAll(columnName, columnArtist, columnDuration);
        playlistTreeTableView.setPrefHeight(200);
        playlistTreeTableView.setMinHeight(50);
        playlistTreeTableView.setRoot(rootItem);
        playlistTreeTableView.setFixedCellSize(0);
        
        
        return playlistTreeTableView;
    }
    
    /*
    Contient les boutons de controle de la playlist, c'est à dire
    les boutons d'ajoute, d'aléatoire, et de boucle.
    */
    public BorderPane createPlaylistButtonsPane(){
        BorderPane playlistButtonsPane = new BorderPane();
        
        playlistButtonsPane.setLeft(new Button("+"));
        playlistButtonsPane.setCenter(new Button("?"));
        playlistButtonsPane.setRight(new Button("loop"));
        
        return playlistButtonsPane;
    }
    
    /*
    Contient la partie basse de la playlist, c'est à dire les
    boutons de controle, l'indication du nombre de pistes,
    et un champs texte de recherche
    */
    public BorderPane createPlaylistControlPane(){
        BorderPane playlistControlPane = new BorderPane();
        
        playlistControlPane.setLeft(createPlaylistButtonsPane());
        playlistControlPane.setCenter(new Label("No tracks"));
        playlistControlPane.setRight(new TextField("Search field"));
        
        return playlistControlPane;
    }
    
    /* Contient toute la playlist */
    public BorderPane createPlaylistPane(){
        BorderPane tableViewPane = new BorderPane();
        BorderPane playlistPane = new BorderPane();
        
        TreeTableView<Track> playlistTreeTableView = createPlayListTableView();
        
        tableViewPane.setCenter(playlistTreeTableView);
        tableViewPane.setMinHeight(50);
        
        playlistPane.setTop(tableViewPane);
        playlistPane.setBottom(createPlaylistControlPane());
        
        return playlistPane;
    }
    
    /* Contient toute l'application */
    public BorderPane createRootPane(){
        BorderPane rootPane = new BorderPane();
        root = rootPane;
        playlist = createPlaylistPane();
        
        rootPane.setTop(createPlayerPane());
        rootPane.setBottom(null);
        
        return rootPane;
    }
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        Scene scene = new Scene(createRootPane());
                
        primaryStage.sizeToScene();
        primaryStage.setTitle("VLC Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(92);
        primaryStage.setMaxHeight(92);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
