package mthocur;

import android.graphics.Rect;

public class Ground extends GameObject {


    protected Rect rect;
    private Background background;
    public boolean collider = true;

    public Ground(){}

    public Ground(Background background){
        this.background = background;
    }
    public Ground(Rect rect){
        this.rect = rect;
    }

    public Rect getRect() {
        return background.getDestinationRect();
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Rect getBgRect(){
        return background.getDestinationRect();
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }



}
