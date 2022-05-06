# Music Visualiser Project

Name: Ogulcan Sarioglu

Student Number: d20123805

# Description of the assignment

This is The Pocket Universe Generator that generates virtual worlds in three-level, Quantum, OneDimension, and Solid (Our World) by the music that is feeded into it and let the user manipulate it by choosing which level they want to be and the colors by while hovering with mouse. 

# Instructions

1) After starting the project, select "Start" from the menu. 
2) The pocket universe generator gonna start drawing the landscape and the logo.
3) You can hover over the landscape with mouse, which also changes the colour of the landscape to give it a new feeling.
4) You can select Q for Quantum, S for Solid and O for OneDimensional versions of the Universe - the default is solid.
5) You can exit any time with exit from the menu. 

# How it works

It works by drawing a 3D landscape using a noise function of processing. This landscape has its low and high points (mountains) to make it more realistic with y and x axes offsets, also changes colours with the help of a map function based on the mouse location. This all populates an 2D array, which then the vertixes are derived from. Then the beat detector comes in, when the kick is detected, the offset of the y axis of landspace is taken into absurd, which creates a spike like effect that is like a lightning since it comes back to normal generation when the kick is closed and it is animated this way
contuniously. The landscape has three mods, Quantum, Solid (default one) and One Dimensional which can be switched by pressing on the said buttons anytime which is
also highlighted on the menu. Menu is a basic JFrame implementation that works as well with the visuals.

Then slowly, as the song progress, the logo of the BTHM appears, created by the circles (bubbles) with a direct line at the end that I added accidently, but then
liked it and leave it because it gives logo a place and a gravity on the screen. 

# What I am most proud of in the assignment:

As I am very passionate about the possibilities of prodecurally generated worlds and enviroments, I am really glad that I got an opportunity to work
with these kind of algorithms creatively and freely while doing my coursework. I also loved the way the procedurally created landscape how interacts with the colorspace with the mouse input. 
But primarly, I am proud of representing, in my assigment, the story that the song itself is trying to tell - a world only created by 0 and 1, and the both advantages (instentenious chance of matter in seconds from quantum to "solid" forms) consequences that come with that, namely a new style of living that can be disturbed randomly and the user are in the mercy of the network (which the spikes in my
program triggered by the kick presents). The kick and the ryhtm in general in the songs has unexcepted variations which meant to do this, that I translated
into the disturbance in the virtual world's literal physical fabric. Being able to tell this story in the code that I write was really awesome. Also, the idea of bringin their logo or song name was always in my mind from the start of this project, and imigrating from just hanging the image to the bubble procedurel implementation was a huge leap for me in terms of understanding that anything is possible in the realm of programming and I should not settle for the easy because at the end it all worth it. 

Secondly, even though I am not "visulazer" or visual learner and my visual imagination is fairly limited and I prefer writing and music, I was glad that I
put something that is visually pleasing and also makes sense in the visual context, using the story-telling methods that I use. 

Thirdly, I self research a lot about java and processing libraries and learnt how to use JFrame and action listeners, being able to use them with processing was a great addition to my skill set. Also I discovered the word of processing in depth with many hours spend researching to bring the concepts in my mind into life, and I learnt a lot about processing and felt empowered, as I said, that I gained the confidence that I can make my ideas come into life with coding. 






# Code Snippets


I added code snippets from draw() method and userInterface class.

```Java
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

  //populating terrain array and spike effect inserted inside with yoff shifted with beat detection

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
```

User Interface Class:

```Java
public class userInterface implements ActionListener {
    private static int clicks = 0;

    private JLabel qLabel = new JLabel("Press Q for Quantum Mode");
    private JLabel sLabel = new JLabel("Press S For Solid Mode (Default");
    private JLabel oLabel = new JLabel("Press D For OneDimensional Mode");
    private JFrame frame = new JFrame();
    private Visual visual = new Visual();
    JButton buttonStart = new JButton("Start!");
    JButton buttonExit = new JButton("Exit!");
   


    public JButton getButtonStart() {
        return this.buttonStart;
    }

    public void setButtonStart(JButton buttonStart) {
        this.buttonStart = buttonStart;
    }

    public JButton getButtonExit() {
        return this.buttonExit;
    }

    public void setButtonExit(JButton buttonExit) {
        this.buttonExit = buttonExit;
    }
  

    public userInterface() {

        // the clickable button
       
        buttonStart.addActionListener(this);
        buttonExit.addActionListener(this);


        // the panel with the button and text
        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(buttonStart);
        panel.add(buttonExit);
        panel.add(qLabel);
        panel.add(sLabel);
        panel.add(oLabel);

        setUpButtonListeners(buttonStart, buttonExit);

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The Pocket Universe Generator");
        frame.pack();
        frame.setVisible(true);
    }

    public int getClicks() {
    return clicks;
 }
    public void setUpButtonListeners(JButton start, JButton exit){
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clicks++;
                
            }
        };
        ActionListener buttonListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               System.exit(0);
            }
        };

        start.addActionListener(buttonListener);
        exit.addActionListener(buttonListener2);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }



 
}

```

Images showing the main stages and functionalities of the program:

![The Main Idea](https://i.imgur.com/aiv9azG.png)
![OneDimensional Mode](https://i.imgur.com/pkUwwQz.png)
![Quantum Menu & Frequency Response at the bottom](https://i.imgur.com/OxJyXSn.png)
![Menu](https://i.imgur.com/2OKhSzj.png)
![Logo at the middle of Creation](https://i.imgur.com/mKfkB3U.png)


This is a youtube video of the demo: 

[![Ogulcan Sarioglu Music Video Link](https://img.youtube.com/vi/8uoKCN-It0M/0.jpg)](https://youtu.be/8uoKCN-It0M)

# References

Inspirated by CodingTrain regarding processing and Oracle Documents for JFrame and other java related self-research. 


