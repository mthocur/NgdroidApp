package mthocur;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;

public abstract class GameObject {
    protected String tag;
    private boolean collider;
    private boolean gravity;

    public GameObject(){ }

    public GameObject(String tag){
        this.tag = tag;
    }

    public GameObject(Class<?> gameObject, String tag, Bitmap image){
        this.tag = tag;
    }

    public void moveTo(int x, int y, int interval, int velocity){
        this
    }

    public void move(int size){

    }

    new GameObject();

    obj.move()

/*
    public Bitmap rotate(Bitmap image){
        if(image.getClass() == Bitmap.class){
            Matrix matrix = new Matrix();
            matrix.preScale(1, -1);
            Bitmap reflectionImage= Bitmap.createBitmap(image, 0, image.getHeight()/2, image.getWidth(), image.getHeight()/2, matrix, false);

            return reflectionImage;
        }
        return image;
    }*/

}
