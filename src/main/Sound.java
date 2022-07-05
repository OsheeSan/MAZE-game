package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    /**
     * Додає до нашої бази усі необхідні звуки та музику
     */
    public Sound(){
        soundURL[0] = getClass().getResource("/sound/forest.wav");
        soundURL[1] = getClass().getResource("/sound/pickUp.wav");
        soundURL[2] = getClass().getResource("/sound/doorOpen.wav");
        soundURL[3] = getClass().getResource("/sound/death.wav");
        soundURL[4] = getClass().getResource("/sound/tp.wav");
    }

    /**
     * Налаштування для основної музики в грі
     */
    public void setMainMusic(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[0]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
        }catch (Exception e){

        }
    }

    /**
     * Вибираємо, яку саму музику увімкнути
     * @param i
     */
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
//            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//            gainControl.setValue(-20.0f);
        }catch (Exception e){

        }
    }

    /**
     * Граємо музику
     */
    public void play(){

        clip.start();
    }

    /**
     * Повторюємо музику
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Зупиняємо музику
     */
    public void stop(){
        clip.stop();
    }

}
