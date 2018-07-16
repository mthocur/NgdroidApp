package mthocur;

import android.graphics.Rect;

public class Ground extends GameObject {


    private Background background;
    public boolean collider = true;

    public Ground(){}

    public Ground(Background background){
        this.background = background;
    }

    public Rect getRect() {
        return background.getDestinationRect();
    }

  public Rect getRectSource(){
      return background.getSourceRect();
  }

    public void setRect(Rect rect){
        background.setDestinationRect(rect);
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }



}
