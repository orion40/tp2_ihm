/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author whoami
 */
public class Track {
    private SimpleStringProperty trackName = new SimpleStringProperty();
    private SimpleStringProperty artistName = new SimpleStringProperty();
    private SimpleStringProperty duration = new SimpleStringProperty();

    public Track(String name) {
        this.setTrackName(name);
    }
    
    public Track(String name, String artist, String d) {
        this.setArtistName(name);
        this.setTrackName(artist);
        this.setDuration(d);
    }
    
    public SimpleStringProperty trackNameProperty(){
        return trackName;
    }
    
    public void setTrackName(String name){
        trackName.set(name);
    }
    
    public String getTrackName(){
        return trackName.get();
    }
    
    public SimpleStringProperty artistNameProperty(){
        return artistName;
    }
    
    public void setArtistName(String name){
        artistName.set(name);
    }
    
    public String getArtistName(){
        return artistName.get();
    }
    
    public SimpleStringProperty durationProperty(){
        return duration;
    }
    
    public void setDuration(String d){
        duration.set(d);
    }
    
    public String getDuration(){
        return duration.get();
    }
    
}
