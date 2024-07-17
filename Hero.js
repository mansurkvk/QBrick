class Hero {
  constructor(color) {
    this.color = color;
    this.kval = 4;
    this.len = SCL * this.kval;
    this.pos = createVector((width - this.len)/2,height - SCL * 2);
  
    this.isActive = true;
  }
  
  show() {
    noStroke();
    fill(this.color);
    rect(this.pos.x, this.pos.y, this.len, SCL);
  }
  
  move(dir) {
    if (this.pos.x + dir * SCL < 0 
        || this.pos.x + this.len + dir*SCL > width) return;
    this.pos.x = this.pos.x + SCL * dir;
  }
  
  collisionDetector(obj) {
    if ((obj.pos.y) == this.pos.y) {
      for (let i = 0; i < this.kval; i++) {
        if (obj.pos.x == this.pos.x+i*SCL || obj.pos.x+SCL == this.pos.x+i*SCL) 
          return true;
      }
    }
    return false;
  }
  
  toggle() {
    this.isActive = !this.isActive;
  }
  
  setActive(setVal) {
    this.isActive = setVal;
  }
  
  getIsActive() {
    return this.isActive;
  }
}