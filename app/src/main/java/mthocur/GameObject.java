package mthocur;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;

public abstract class GameObject {
    public String tag;
    public boolean collider;

    public GameObject(){}

    public GameObject(String tag){
        this.tag = tag;
    }

}
