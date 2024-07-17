class Rock {
  constructor() {
    this.pos = this.createRandPos().copy(); 
    this.vel = createVector(0, SCL);
  }
    
  show() {
    noStroke();
    fill(64);
    ellipse(this.pos.x, this.pos.y, SCL, SCL);
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
}