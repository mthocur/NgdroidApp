package mthocur;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;

public abstract class GameObject {
    protected String tag;
    protected boolean hasCollider;

    public GameObject(){}

    public GameObject(String tag){
        this.tag = tag;
    }

    public GameObject(String tag, Bitmap image){
        this.tag = tag;
    }

}
