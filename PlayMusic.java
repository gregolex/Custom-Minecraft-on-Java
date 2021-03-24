/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3cs4450;
import java.io.File; 
import java.io.IOException; 
import java.util.Scanner; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 
/**
 *
 * @author Robert Delfin
 * Purpose: Class Play Music plays a wav file music during the scene.
 * It can start and mute the music.
 */
public class PlayMusic {
    
	// to store current position 
	Long currentFrame; 
	Clip clip; 
	// current status of clip 
	String status = "start"; 
	AudioInputStream audioInputStream; 	 
	// constructor to initialize streams and clip 
	public PlayMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException{ 
		
		// create AudioInputStream object 
		audioInputStream = AudioSystem.getAudioInputStream(new File("ChillingMusic.wav").getAbsoluteFile()); 	
		// create clip reference 
		clip = AudioSystem.getClip(); 
		// open audioInputStream to the clip 
		clip.open(audioInputStream); 
		//clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 
        //Starts the mustic method
        public void startMusic(){ 
            try{ 		
                		         
                PlayMusic audioPlayer = new PlayMusic();
                audioPlayer.play();                                  			
            }catch (Exception ex){                 
                System.out.println("Error with playing sound.");  
                ex.printStackTrace(); 	    
            }
        }
	// Method to play the audio 
	public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException{ 
            if (status.equals("play")){ 
                System.out.println("audio is already playing"); 	
                return; 
            } 
            //start the clip	
            clip.start(); 
            status = "play"; 
	} 	
	// Method to pause the audio 
	public void pause(){   
            if (status.equals("paused")){
                System.out.println("audio is already paused");		
                return; 
            } 
            this.currentFrame = this.clip.getMicrosecondPosition(); 
            clip.stop(); 	
            status = "paused"; 
	} 		
	// Method to resume the audio 
	public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{ 		
            clip.close(); 	
            resetAudioStream(); 	
            clip.setMicrosecondPosition(currentFrame); 	
            this.play(); 	
	}
	// Method to reset audio stream 
        public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException{ 
            audioInputStream = AudioSystem.getAudioInputStream(new File("ChillingMusic.wav").getAbsoluteFile()); 
            clip.open(audioInputStream); 
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 
}
