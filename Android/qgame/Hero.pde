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
