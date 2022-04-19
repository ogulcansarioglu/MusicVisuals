package ie.tudublin;

import java.io.Console;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;



public class cube extends PApplet {

    int OFF_MAX = 100;

    
    
    int colorFromOffset(int offset) {
        return (int) ((offset + OFF_MAX) / (2.0 * OFF_MAX) * 255);
    }
    
}
