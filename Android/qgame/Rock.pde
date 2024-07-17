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
