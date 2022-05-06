package d20123805.ie.tudublin;

import java.io.Console;
import java.security.Key;
import java.util.ArrayList;
import processing.*;


import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

import d20123805.ie.tudublin.Circle;




public class Visual extends PApplet {


/**
   *
   */
private static final String LUDENS_MP3 = "C:/Users/Olci/Desktop/MusicVisuals/java/src/d20123805/ie/tudublin/Ludens.mp3";
private static final String BMTH_LOGO = "C:/Users/Olci/Desktop/MusicVisuals/java/src/d20123805/ie/tudublin/bmth.png";
private static  int SOLID = 10;
private static final int WAVE = 5;
private static final int QUANTUM = 3;


int mode = 1; //stars with solid mode


int cols, rows;
int scale = 20;
int w = 4000;
int h = 2800; 




FFT fft;

int OFF_MAX = 300;
Minim minim;

AudioPlayer ap;
AudioInput ai;
AudioBuffer ab;
BeatDetect bd;

PShape vertex1, vertex2; 

float flying = 0;

float[][] terrain;

float i = 10.122f;

ArrayList<Circle> circles = new ArrayList<Circle>();
ArrayList<PVector> places; //global variable used both in NewCircle method and logo-circle logic
PImage img;




public void settings() {


  size(1000, 1000, P3D);
  cols = w / scale;
  rows = h/ scale;
  terrain = new float[cols][rows];
 
}

public void setup(){

    colorMode(RGB);

    //logo, image processing setup

    places = new ArrayList<PVector>();
    img = loadImage(BMTH_LOGO);
    img.loadPixels();

    for (int x = 0; x < img.width; x++) {
      for (int y = 0; y < img.height; y++) {
        int index = x + y * img.width;

        //looks at if the pixel is black or white, and creates a circle if it is white
        //as the logo is prepared this way 

        float b = brightness(img.pixels[index]);
        if (b > 1) {
          places.add(new PVector(x,y));
        }
        
      } 
    }

    circles = new ArrayList<Circle>();

    //music processing setup
   
    minim = new Minim(this);
    ap = minim.loadFile(LUDENS_MP3);
    fft = new FFT(ap.bufferSize(), ap.sampleRate());
    bd = new BeatDetect(ap.bufferSize(), ap.sampleRate());
    ap.play();
    ab = ap.mix;
  }


public void draw() {

  //float[] spectrum = fft.getSpectrumImaginary();
  bd.detect(ap.mix);
  flying -= 0.05;
  float yoff = flying;
  float xoff;
 
  fft.forward(ap.mix);

  background(0);

  
  //populate circle arrayList

  int total = 10;
  int count = 0;
  int attempts = 0;

  while (count <  total) {
    Circle newC = newCircle();
    if (newC != null) {
      circles.add(newC);
      count++;
    }
    attempts++;
    if (attempts > 1000) {
      noLoop();
      //println("FINISHED");
      break;
    } 
  }

  //circle growing logic


  for (Circle c : circles) {
    if (c.growing) {
      if (c.edges()) {
        c.growing = false;
      } else {
        for (Circle other : circles) {
          if (c != other) {
            float d = dist(c.x, c.y, other.x, other.y);
            if (d - 2 < c.r + other.r) {
              c.growing = false;
              break;
            }
          }
        }
      }
    }
    c.show(this);
    c.grow();
  }



   


  for (int y = 0; y < rows; y++) {
  
    xoff = 0;
    for (int x = 0; x < cols; x++) {

      //create terrain values for vertices

      terrain[x][y] = map(noise(xoff, yoff), 0, 1, -100, 200);
      int tempBand = (int) fft.getBand(x);
      line(x, width, x, height - tempBand); //frequency line for Q/D modes
      xoff += 0.1;
      //beat detection as to give a spike effect with a kick
      if (bd.isKick()) {
        yoff -= 5;  //gives the spike effect
      } 
   }
    yoff += 0.1;
  }

  //Key control for changing between three interdimensial modes

  if (keyPressed) {
    if (key == 'q' || key == 'Q') {
      mode = 0;
    } else if (key == 's' || key == 'S') {
      mode = 1;
    } else if (key == 'd' || key == 'D')
      mode = 2;
  }

  //determines the mode/shape

  if(mode == 0) {
  beginShape(QUANTUM);
} else if (mode == 1){
  beginShape(SOLID);
} else if (mode == 2) {
  beginShape(WAVE);
}

  
  
  translate(width/2, height/2+50);
  rotateX(PI/3);
  translate(-w/2, -h/2);
  translate(mouseX, mouseY);

 // procedurally creating the landscape

  for (int y = 0; y < rows-1; y++) {
    
    for (int x = 0; x < cols; x++) {
      
      vertex(x*scale, y*scale, terrain[x][y]);
      vertex(x*scale, (y+1)*scale, terrain[x][y+1]);

     
      //the mouse X cordinate changes the color of the landscape
      fill(color(map(mouseX, 0, width, 10, 50)));
      stroke(x *2  , y * 2 , 255);
    }
}

endShape();

}


//method for forming the new circles aroind the logo, returns a Circle

Circle newCircle() {
  
  int r = (int)(random(0,places.size()));
  PVector place = places.get(r);
  float x = place.x;
  float y = place.y;

  boolean valid = true;
  for (Circle c : circles) {
    float dist = dist(x, y, c.x, c.y);
    if (dist < c.r) {
      valid = false;
      break;
    }
  }

  if (valid) {
    return new Circle(x, y);
  } else {
    return null;
  }
}}
