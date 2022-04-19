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




public class Visual extends PApplet {


/**
   *
   */
private static final String LUDENS_MP3 = "C:/Users/Olci/Desktop/OLCI/MusicVisuals/java/src/ie/tudublin/Ludens.mp3";
int cols, rows;
int scale = 20;
int w = 4000;
int h = 2800;

FFT fft;



Minim minim;

AudioPlayer ap;
AudioInput ai;
AudioBuffer ab;
BeatDetect bd;

PShape vertex1, vertex2; 

float flying = 0;

float[][] terrain;




public void settings() {
  size(1000, 1000, P3D);
  cols = w / scale;
  rows = h/ scale;
  terrain = new float[cols][rows];
 
}

public void setup(){

    colorMode(RGB);
   
    minim = new Minim(this);
    ap = minim.loadFile(LUDENS_MP3);
    fft = new FFT(ap.bufferSize(), ap.sampleRate());
    bd = new BeatDetect(ap.bufferSize(), ap.sampleRate());
    ap.play();
    ab = ap.mix;
  }


public void draw() {

  bd.detect(ap.mix);
  flying -= 0.05;
  float yoff = flying;
  float xoff;
 
  fft.forward(ap.mix);


    for (int i = 0; i < fft.specSize(); i++) {
    int tempBand = (int) fft.getBand(i);
    line(i, height, i, height - tempBand);
   
    // better look, but cut off in high frequencies
    //rect(i * 4, height, 4, -tempBand);
  }


  for (int y = 0; y < rows; y++) {

    
   
    xoff = 0;
    for (int x = 0; x < cols; x++) {
      terrain[x][y] = map(noise(xoff, yoff), 0, 1, -100, 200);
      int tempBand = (int) fft.getBand(x);
      line(x, height, x, height - tempBand);
      xoff += 0.1;
      if (bd.isKick()) {
        yoff -= 5;
      } 
   
	 
    }
    yoff += 0.1;

  
  }




  beginShape(TRIANGLE_STRIP);



  translate(width/2, height/2+50);
  rotateX(PI/3);
  background(0);
  translate(-w/2, -h/2);
  translate(mouseX, mouseY);
 
  for (int y = 0; y < rows-1; y++) {
    
    for (int x = 0; x < cols; x++) {
      vertex(x*scale, y*scale, terrain[x][y]);
      vertex(x*scale, (y+1)*scale, terrain[x][y+1]);
      fill(color(map(mouseX, 0, width, 10, 50)));
      stroke(x * 2 , y * 2 , 255);
      
    
     
      //rect(x*scl, y*scl, scl, scl);
    }
    //stroke(color(map(mouseY, 0, 1, 0, 255)));
   
  

  }
  endShape();
}

    
}
