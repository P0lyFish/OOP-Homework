/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


/**
 *
 * @author polyfish
 */
public class Speaker {
    static final String URL = "https://d1qx7pbj0dvboc.cloudfront.net/";
    static final String AUDIOEXT = ".mp3";
    static final String SAVE_FOLDER = "audio/";
    
    public boolean isAvailable(String word) {
        String mp3Source = URL + word + AUDIOEXT;
        
        try {
            new URL(mp3Source).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
    
    public void speak(String word) {
        Player player = null;
        String mp3Source = URL + word + AUDIOEXT;
        try {
            URLConnection urlConnection = new URL(mp3Source).openConnection();
            urlConnection.connect();
            player = new Player(urlConnection.getInputStream());
        } catch (IOException | JavaLayerException e) {
            System.out.println(e.getMessage());
        }

        try {
            player.play(300);
        } catch (JavaLayerException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Speaker speaker = new Speaker();
        
        speaker.speak("fuck");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }
}
