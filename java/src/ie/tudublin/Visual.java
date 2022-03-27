

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
import processing.opengl.PShader;




public class Visual extends PApplet {


/**
   *
   */
private static final String LUDENS_MP3 = "C:/Users/Olci/Desktop/OLCI/MusicVisuals/java/src/ie/tudublin/Ludens.mp3";
int cols, rows;
int scale = 10;
int w = 2000;
int h = 1600;

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
    bd = new BeatDetect(ap.bufferSize(), ap.sampleRate());
    ap.play();
    ab = ap.mix;
    bd.detect(ap.mix);


}


public void draw() {

  flying -= 0.05;
  float yoff = flying;

  if (bd.isSnare()) {
   System.out.print("obj");
  } else {
    System.out.print("1");
  }


  for (int y = 0; y < rows; y++) {
    float xoff = 0;
    for (int x = 0; x < cols; x++) {
      terrain[x][y] = map(noise(xoff, yoff), 0, 1, -100, 100);
  
      xoff += 0.1;
      noStroke();
	 
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
