class Bomb {
  constructor() {
    this.pos = this.createRandPos().copy();
    this.vel = createVector(0, SCL);
  }
  
  run() {
    push();
    fill(250, 0, 0);
    stroke(0);
    strokeWeight(2);
    rect(this.pos.x, this.pos.y, SCL, SCL);
    fill(255);
    textFont('Georgia');
    textSize(24);
    text('M', this.pos.x + SCL/2, this.pos.y + SCL/2);
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
  let randY = -floor(random(20)) * SCL;
  return createVector(randX, randY);
  }
  
  clearPos() {
    this.pos = this.createRandPos().copy();
  }
  
  whoAmI() {
    return 'M';
  }
}