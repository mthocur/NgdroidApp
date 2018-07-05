package mthocur;

import android.graphics.Rect;

public class Ground extends GameObject {


    protected Rect rect;
    public Background background;
    public boolean collider = true;

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

}
