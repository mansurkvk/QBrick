class Hadamard {
  constructor() {
    this.pos = this.createRandPos().copy();
    this.vel = createVector(0, SCL);
  }
  
  run() {
    push();
    fill(255);
    stroke(0);
    strokeWeight(2);
    rect(this.pos.x, this.pos.y, SCL, SCL);
    fill(0);
    textFont('Georgia');
    textSize(24);
    text('H', this.pos.x + SCL/2, this.pos.y + SCL/2);
    pop();
  }
  
  fall() {
    this.pos.add(this.vel);
  }
  
  examine() {
    if (this.pos.y == height) {
      this.pos = this.createRandPos().copy();
    }
  }
    
  createRandPos() {
  let randX = floor(random(width/SCL)) * SCL;
  let randY = -floor(random(10)) * SCL;
  return createVector(randX, randY);
  }
  
  clearPos() {
    this.pos = this.createRandPos().copy();
  }
  
  whoAmI() {
    return 'H';
  }
}

class Not {
  constructor() {
    this.pos = this.createRandPos().copy();
    this.vel = createVector(0, SCL);
  }
  
  run() {
    push();
    fill(255);
    stroke(0);
    strokeWeight(2);
    rect(this.pos.x, this.pos.y, SCL, SCL);
    fill(0);
    textFont('Georgia');
    textSize(24);
    text('X', this.pos.x + SCL/2, this.pos.y + SCL/2);
    pop();
  }
  
  fall() {
    this.pos.add(this.vel);
  }
  
  examine() {
    if (this.pos.y == height) {
      this.pos = this.createRandPos().copy();
    }
  }
    
  createRandPos() {
  let randX = floor(random(width/SCL)) * SCL;
  let randY = -floor(random(10)) * SCL;
  return createVector(randX, randY);
  }
  
  clearPos() {
    this.pos = this.createRandPos().copy();
  }
  
  whoAmI() {
    return 'X';
  }
}