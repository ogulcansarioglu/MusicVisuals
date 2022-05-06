package d20123805.ie.tudublin;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;


public class Circle extends PApplet {

    public float x;
    public float y;
    public float r;
    PApplet pa;

public Circle(float x, float y) 
{
        this.x = x;
        this.y = y;
        r = 1;
        
}

boolean growing = true;



  public void grow() {
    if (growing) {
      r = r + 0.5f;
    }
  }
  
  boolean edges() {
    return (x + r > width || x -  r < 0 || y + r > height || y -r < 0);
  }

  public void show(Visual pa) {
    
    pa.stroke(255);
    pa.strokeWeight(1);
    pa.noFill();
    pa.ellipse(x, y, r * 2, r * 2);
    color(200);
    System.out.println("INSIDE");
  }
    
}
