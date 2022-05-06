# Music Visualiser Project

Name: Ogulcan Sarioglu

Student Number: d20123805

# Description of the assignment

# Instructions

1) After starting the project, select "Start" from the menu. 
2) The pocket universe generator gonna start drawing the landscape and the logo.
3) You can hover over the landscape with mouse, which also changes the colour of the landscape to give it a new feeling.
4) You can select Q for Quantum, S for Solid and O for OneDimensional versions of the Universe - the default is solid.
5) You can exit any time with exit from the menu. 

# How it works

It works by drawing a 3D landscape using a whitenoise. This landscape has its low and high points (mountains) to make it more realistic, also changes colours
with the help of a map function based on the mouse location. Then the beat detector comes in, when the kick is detected, the offset of the y axis of landspace is taken into absurd, which creates a spike like effect that is like a lightning since it comes back to normal generation when the kick is closed and it is animated this way
contuniously. The landscape has three mods, Quantum, Solid (default one) and One Dimensional which can be switched by pressing on the said buttons anytime which is
also highlighted on the menu. Menu is a basic JFrame implementation that works as well with the visuals.

Then slowly, as the song progress, the logo of the BTHM appears, created by the circles (bubbles) with a direct line at the end that I added accidently, but then
liked it and leave it because it gives logo a place and a gravity on the screen. 

# What I am most proud of in the assignment:

As I am very passionate about the possibilities of prodecurally generated worlds and enviroments, I am really glad that I got an opportunity to work
with these kind of algorithms creatively and freely while doing my coursework. I also loved the way the procedurally created landscape how interacts with the colorspace with the mouse input. I am also proud of representing, in my assigment, the story that the song itself is trying to tell - a world only created by 0 and 1, and the both advantages (instentenious chance of matter in seconds from quantum to "solid" forms) consequences that come with that, namely a new style of living that can be disturbed randomly and the user are in the mercy of the network (which the spikes in my
program triggered by the kick presents). The kick and the ryhtm in general in the songs has unexcepted variations which meant to do this, that I translated
into the disturbance in the virtual world's literal physical fabric. Being able to tell this story in the code that I write was really awesome. Also, the idea of bringin their logo or song name was always in my mind from the start of this project, and imigrating from just hanging the image to the bubble procedurel implementation was a huge leap for me in terms of understanding that anything is possible in the realm of programming and I should not settle for the easy because at the end it all worth it. 

Secondly, even though I am not "visulazer" or visual learner and my visual imagination is fairly limited and I prefer writing and music, I was glad that I
put something that is visually pleasing and also makes sense in the visual context, using the story-telling methods that I use. 

Thirdly, I research a lot about java and processing libraries and learnt how to use JFrame and action listeners, being able to use them with processing was a great addition to my skill set. Also I discovered the word of processing in depth with many hours spend researching to bring the concepts in my mind into life, and I learnt a lot about processing and felt empowered, as I said, that I gained the confidence that I can make my ideas come into life with coding. 






# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
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


