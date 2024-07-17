package processing.test.qgame;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class qgame extends PApplet {

/*
#################################################
THE MAGNIFICANT ANKEBOT BROS.
MUHAMMED MANSUR KAVAK a.k.a. MANSUR THE EXALANS
AHMET ATASOGLU a.k.a. AHMET THE NUCLEER MAFIA
15.12.19
QUANTUM HACKATHON PROJESIDIR !!!!
#################################################
*/
String[] quotes = {
 "When you're not looking \n Im just a waveform!"
  
};


static final int scl = Global.SCL;
static final int FPS = 16;
float step = (float) 1/FPS;

float counter = 60;

int gridX;

int NBR_OF_ROCKS = 30;
Rock[] rocks;

Hero hero;
Hero hero_twin; // hadamard kapısında ortaya çıkacak ikiz

Hadamard h;
Not x;
Bomb b;

int lifeSecond = 3; // kapılar 2 sn aktif olsun
int LIFECYCLE = FPS * lifeSecond;
int life = 0;

char gained = 'N';
/*
  N : None
  X : Not Gate
  H : Hadamard Gate
  M : Measurement
*/

int score = 0;
/*
#################################################
*/


public void setup() {
  
  
  textFont(createFont("SansSerif", 30 * displayDensity));
  textAlign(CENTER, CENTER);
  gridX = width / scl;
  
  ellipseMode(CORNER);
  frameRate(FPS);
  int[] clr = {255, 24, 32};
  int[] clr2 = {64, 64, 64};
  hero = new Hero(clr);
  hero_twin = new Hero(clr2);
  rocks = new Rock[NBR_OF_ROCKS];
  
  h = new Hadamard();
  x = new Not();
  b = new Bomb();
    
  for (int i = 0; i < NBR_OF_ROCKS; i++) rocks[i] = new Rock();
  
}

public void draw() {
  if (mousePressed) {
    if (mouseX <= (float) width/2) {
        if (gained == 'X') hero.move(1);
        else hero.move(-1);
    }
    if (mouseX > (float) width/2) {
      if (gained == 'X') hero.move(-1);
      else hero.move(1);
    }
  }
  background(220);
  stroke(180);
  for (int i = 1; i < gridX; ++i) line(i * scl, 0, i * scl, height);
  fill(255, 126, 100);
  textSize(230);
  // textFont(font1);
  text(score, width/2, height/2 - 100);
  fill(173);
  hero.show();
  for (int i = 0; i < rocks.length; ++i) {
    rocks[i].show();
    rocks[i].fall();
    rocks[i].examine();
    if (hero.collisionDetector(rocks[i])) {
      score++;
      rocks[i] = new Rock();
    } if (hero_twin.getIsActive()) {
      if (hero_twin.collisionDetector(rocks[i])) {
        score++;
        rocks[i] = new Rock();
      }
    }
  }
    h.run();
    h.fall();
    h.examine();
    if (hero.collisionDetector(h)) {
      h.clearPos();
      if (gained == 'N') {
        life = LIFECYCLE;
        gained = h.whoAmI();
        // break;
      }
      if (gained == 'H') {
        if (h.whoAmI() == 'M') {
          gained = 'N';
          // break;
       }
    } 
  }
    if (hero_twin.collisionDetector(h)) {
      if (gained == 'H') {
        if (h.whoAmI() == 'M') {
          h.clearPos();
          gained = 'N';
          // break;
        }
      }
    }
    /////////////////////////////
    x.run();
    x.fall();
    x.examine();
    if (hero.collisionDetector(x)) {
      x.clearPos();
      if (gained == 'N') {
        life = LIFECYCLE;
        gained = x.whoAmI();
        // break;
      }
      if (gained == 'H') {
        if (x.whoAmI() == 'M') {
          gained = 'N';
          // break;
        }
    } 
    if (hero_twin.collisionDetector(x)) {
      if (gained == 'H') {
        if (x.whoAmI() == 'M') {
          x.clearPos();
          gained = 'N';
          // break;
        }
      }
    }
    }
    
    //////////////////////////////////
    b.run();
    b.fall();
    b.examine();
    if (hero.collisionDetector(b)) {
      b.clearPos();
      if (gained == 'N') {
        life = LIFECYCLE;
        gained = b.whoAmI();
        // break;
      }
      if (gained == 'H') {
        if (b.whoAmI() == 'M') {
          gained = 'N';
          // break;
        }
    } 
    if (hero_twin.collisionDetector(b)) {
      if (gained == 'H') {
        if (b.whoAmI() == 'M') {
          b.clearPos();
          gained = 'N';
          // break;
        }
      }
    }
    }
  ///////////////////
  
  // is there any gained gates???
  if (gained == 'X') {
    push();
    fill(0, 0, 255);
    textSize(48);
    // textFont(font1);
    text("NOT!\nTime remain: " + life, width/2, 250);
    life -= 1;
    if (life < 0) {
      gained = 'N';
    }
    pop();
  }
  else if (gained == 'H') {
    push();
    fill(160, 75, 0);
    textSize(48);
    // textFont(font1);
    text("HADAMARD!\nTime remain: " + life, width/2, 250);
    hero_twin.pos.x = width/2 - hero.pos.x + 5*scl;
    hero_twin.show();
    hero_twin.setActive(true);
    life -= 1;
      if (life < 0) {
        gained = 'N';
        hero_twin.toggle();
      }
    pop();
  }
  
  else if (gained == 'M') {
    push();
      gained = 'N';
    pop();
  }
  
  // textFont(font1);
  textSize(48);
  fill(150, 20, 240);
  text("Time: " + floor(counter), width/2, 48);
  counter = counter - step;
  
  if (floor(counter) == 0) {
    /*
    fill(255, 126, 100);
  textSize(230);
  // textFont(font1);
  text(score, width/2, height/2 - 100);
  */
    background(0);
    fill(255, 255, 0);
    textSize(32);
    text(quotes[0], width/2, height/2 + 200);
    
    fill(255, 0, 0);
    textSize(100);
    text("Your score", width/2, 100);
    fill(255, 0, 0);
    textSize(230);
    // textFont(font1);
    text(score, width/2, height/2 - 100);
    
    noLoop();
  }
  
}
class Bomb extends Gates implements Global {
  public PVector pos;
  public PVector vel;
  
  public Bomb() {
    this.pos = this.createRandPos().copy();
    this.vel = new PVector(0, SCL);
  }
  
  public void run() {
    push();
    fill(250, 0, 0);
    stroke(0);
    strokeWeight(2);
    rect(this.pos.x, this.pos.y, SCL, SCL);
    fill(255);
    textSize(24);
    text('M', this.pos.x + SCL/2, this.pos.y + SCL/2);
    pop();
    
  }
  
  public void fall() {
  this.pos.add(this.vel);
  }
  
  public void examine() {
    if (this.pos.y == height) {
      this.pos = this.createRandPos().copy();
    }
  }
    
  public PVector createRandPos() {
  int randX = floor(random(width/SCL)) * SCL;
  int randY = -floor(random(20)) * SCL;
  return new PVector(randX, randY);
  }
  
  public void clearPos() {
    this.pos = this.createRandPos().copy();
  }
  
  public char whoAmI() {
    return 'M';
  }
}
class Gates {
  public Gates() {
    super();
  }
}
class Hadamard extends Gates implements Global {
  public PVector pos;
  public PVector vel;
  
  public Hadamard() {
    this.pos = this.createRandPos().copy();
    this.vel = new PVector(0, SCL);
  }
  
  public void run() {
    push();
    fill(255);
    stroke(0);
    strokeWeight(2);
    rect(this.pos.x, this.pos.y, SCL, SCL);
    fill(0);
    // textFont('Georgia');
    textSize(24);
    text('H', this.pos.x + SCL/2, this.pos.y + SCL/2);
    pop();
  }
  
  public void fall() {
    this.pos.add(this.vel);
  }
  
  public void examine() {
    if (this.pos.y == height) {
      this.pos = this.createRandPos().copy();
    }
  }
    
  public PVector createRandPos() {
  int randX = floor(random(width/SCL)) * SCL;
  int randY = -floor(random(10)) * SCL;
  return new PVector(randX, randY);
  }
  
  public void clearPos() {
    this.pos = this.createRandPos().copy();
  }
  
  public char whoAmI() {
    return 'H';
  }
}

class Not extends Gates implements Global {
  public PVector pos;
  public PVector vel;
  
  public Not() {
    this.pos = this.createRandPos().copy();
    this.vel = new PVector(0, SCL);
  }
  
  public void run() {
    push();
    fill(255);
    stroke(0);
    strokeWeight(2);
    rect(this.pos.x, this.pos.y, SCL, SCL);
    fill(0);
   // textFont('Georgia');
    textSize(24);
    text('X', this.pos.x + SCL/2, this.pos.y + SCL/2);
    pop();
  }
  
  public void fall() {
    this.pos.add(this.vel);
  }
  
  public void examine() {
    if (this.pos.y == height) {
      this.pos = this.createRandPos().copy();
    }
  }
    
  public PVector createRandPos() {
  int randX = floor(random(width/SCL)) * SCL;
  int randY = -floor(random(10)) * SCL;
  return new PVector(randX, randY);
  }
  
  public void clearPos() {
    this.pos = this.createRandPos().copy();
  }
  
  public char whoAmI() {
    return 'X';
  }
}
interface Global {
  public int SCL = 40;
}
public class Hero implements Global {
    public int[] clr;
    private int kval;
    private int len;
    private PVector pos;
    public boolean isActive; 
    
  public Hero(int[] rgb) {
    this.clr = rgb;
    this.kval = 4;
    this.len = SCL * this.kval;
    this.pos = new PVector((width - this.len)/2, height - SCL * 2);
  
    this.isActive = true;
  }
  
  public void show() {
    noStroke();
    fill(this.clr[0], this.clr[1], this.clr[2]);
    rect(this.pos.x, this.pos.y, this.len, SCL);
  }
  
  public void move(int dir) {
    if (this.pos.x + dir * SCL < 0 
        || this.pos.x + this.len + dir*SCL > width) return;
    this.pos.x = this.pos.x + SCL * dir;
  }
  
  public boolean collisionDetector(Hadamard obj) {
    if ((obj.pos.y) == this.pos.y) {
      for (int i = 0; i < this.kval; i++) {
        if (obj.pos.x == this.pos.x+i*SCL || obj.pos.x+SCL == this.pos.x+i*SCL) 
          return true;
      }
    }
    return false;
  }
  public boolean collisionDetector(Not obj) {
    if ((obj.pos.y) == this.pos.y) {
      for (int i = 0; i < this.kval; i++) {
        if (obj.pos.x == this.pos.x+i*SCL || obj.pos.x+SCL == this.pos.x+i*SCL) 
          return true;
      }
    }
    return false;
  }
  
  public boolean collisionDetector(Bomb obj) {
    if ((obj.pos.y) == this.pos.y) {
      for (int i = 0; i < this.kval; i++) {
        if (obj.pos.x == this.pos.x+i*SCL || obj.pos.x+SCL == this.pos.x+i*SCL) 
          return true;
      }
    }
    return false;
  }
  
  public boolean collisionDetector(Rock obj) {
    if ((obj.pos.y) == this.pos.y) {
      for (int i = 0; i < this.kval; i++) {
        if (obj.pos.x == this.pos.x+i*SCL || obj.pos.x+SCL == this.pos.x+i*SCL) 
          return true;
      }
    }
    return false;
  }
  
  public void toggle() {
    this.isActive = !this.isActive;
  }
  
  public void setActive(boolean setVal) {
    this.isActive = setVal;
  }
  
  public boolean getIsActive() {
    return this.isActive;
  }
}
public class Rock implements Global {
  public PVector pos;
  public PVector vel;
  
  public Rock() {
    this.pos = this.createRandPos().copy(); 
    this.vel = new PVector(0, SCL);
  }
    
  public void show() {
    noStroke();
    fill(64);
    ellipse(this.pos.x, this.pos.y, SCL, SCL);
  }
  
  public void fall() {
    this.pos.add(this.vel);
  }
  
  public void examine() {
    if (this.pos.y > height) {
      this.pos = this.createRandPos().copy();
    }
  }
  
  public PVector createRandPos() {
    int randX = floor(random(width/SCL)) * SCL;
    int randY = -floor(random(20)) * SCL;
    return new PVector(randX, randY);
  }
}
  public void settings() {  fullScreen(P2D); }
}
