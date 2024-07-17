let quotes = [
 "When you're not looking\nIm just a waveform!",
  
];


const SCL = 40;
const FPS = 12;
const step = 1/FPS;

let counter = 60;

let gridX;

let NBR_OF_ROCKS = 20;
let rocks;

let hero;
let hero_twin; // hadamard kapısında ortaya çıkacak ikiz

let gates;

let lifeSecond = 2; // kapılar 2 sn aktif olsun
let LIFECYCLE = FPS * lifeSecond;
let life = 0;

let gained = 'N';
/*
  N : None
  X : Not Gate
  H : Hadamard Gate
  M : Measurement
*/

let score = 0;

let font1;
let img;

function preload() {
  font1 = loadFont('fast99.ttf');
}

function setup() {  
  createCanvas(400, 720);
  textAlign(CENTER, CENTER);
  gridX = width / SCL;
  
  ellipseMode(CORNER);
  frameRate(FPS);  
  hero = new Hero(color(255, 24, 32));
  hero_twin = new Hero(color(64));
  rocks = new Array();
  
  gates = [new Hadamard(), new Not(), new Bomb()];
    
  for (let i = 0; i < NBR_OF_ROCKS; i++) rocks.push(new Rock());
  }

function draw() {

  background(220);
  stroke(180);
  for (let i = 1; i < gridX; ++i) line(i * SCL, 0, i * SCL, height);
  fill(255, 126, 100);
  textSize(230);
  textFont(font1);
  text(score, width/2, height/2 - 100);
  fill(173);
  hero.show();
  for (let i = 0; i < rocks.length; ++i) {
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
  for (let i = 0; i < gates.length; ++i) {
    gates[i].run();
    gates[i].fall();
    gates[i].examine();
    if (hero.collisionDetector(gates[i])) {
      gates[i].clearPos();
      if (gained == 'N') {
        life = LIFECYCLE;
        gained = gates[i].whoAmI();
        break;
      }
      if (gained == 'H') {
        if (gates[i].whoAmI() == 'M') {
          gained = 'N';
          break;
        }
      } 
    }
    if (hero_twin.collisionDetector(gates[i])) {
      if (gained == 'H') {
        if (gates[i].whoAmI() == 'M') {
          gates[i].clearPos();
          gained = 'N';
          break;
        }
      }
    }
  }
  
  // is there any gained gates???
  if (gained == 'X') {
    push();
    fill(0, 0, 255);
    textSize(38);
    textFont(font1);
    text("NOT!\nTime remain: " + life, width/2, 100);
    life -= 1;
    if (life < 0) {
      gained = 'N';
    }
    pop();
  }
  else if (gained == 'H') {
    push();
    fill(160, 75, 0);
    textSize(38);
    textFont(font1);
    text("HADAMARD!\nTime remain: " + life, width/2, 100);
    hero_twin.pos.x = width/2 - hero.pos.x + SCL;
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
  
  textFont(font1);
  textSize(24);
  fill(150, 20, 240);
  text("Time: " + floor(counter), width/2, 20);
  counter -= step;
  if (floor(counter) == 0) {
    background(0);
    fill(255, 0, 0);
    textSize(54);
    text("Your score", width/2, 100);
    textSize(230);
    textFont(font1);
    text(score, width/2, height/2 - 100);
    let rand = floor(random(quotes.length));
    fill(255, 255, 0);
    textSize(24);
    text(quotes[rand], width/2, height/2 + 200);
    noLoop();
  }
}


function keyPressed() {
  if (keyCode === RIGHT_ARROW) {
       if (gained == 'X') hero.move(-1);
        else hero.move(1);
  }
  else if (keyCode === LEFT_ARROW) {

      if (gained == 'X') hero.move(1);
      else hero.move(-1);
  }
}