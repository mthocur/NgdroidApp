package mthocur;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;
import mthocur.GameObject;

public class Background extends GameObject {


    private Bitmap image;
    private Rect sourceRect;
    private Rect destinationRect;
    private boolean sprite = false;


    private int tileSourceW, tileSourceH, tileDestinationW, tileDestinationH;
    private int tileSourceX, tileSourceY, tileDestinationX, tileDestinationY;

    public Background(Bitmap image, Rect sourceRect, Rect destinationRect) {
        this.image = image;
        this.sourceRect = sourceRect;
        this.sprite = true;
        this.destinationRect = destinationRect;
    }

    public Background(Bitmap image, boolean sprite, int tileSourceW, int tileSourceH, int tileDestinationW, int tileDestinationH,
                      int tileSourceX, int tileSourceY, int tileDestinationX, int tileDestinationY, Rect sourceRect, Rect destinationRect, boolean collider
    ) {
        this.image = image;
        this.sprite = sprite;
        this.tileSourceW = tileSourceW;
        this.tileSourceH = tileSourceH;
        this.tileDestinationW = tileDestinationW;
        this.tileDestinationH = tileDestinationH;
        this.tileSourceX = tileSourceX;
        this.tileSourceY = tileSourceY;
        this.tileDestinationX = tileDestinationX;
        this.tileDestinationY = tileDestinationY;
        this.sourceRect = sourceRect;
        this.destinationRect  = destinationRect;
        super.collider = collider;

    }

    public void drawBackground(Canvas canvas,int width, int height) {

       if(sprite) {
           for (int i = 0; i < width; i += tileDestinationW) {
               for (int j = 0; j < height ; j += tileDestinationH) {
                   sourceRect.set(tileSourceX, tileSourceY, tileSourceX + tileSourceW, tileSourceY + tileSourceH);
                   destinationRect.set(i, j, i + tileDestinationW, j + tileDestinationH);
                   canvas.drawBitmap(image, sourceRect, destinationRect, null);
               }
           }
       } else {
           sourceRect.set(tileSourceX, tileSourceY, tileSourceX + tileSourceW, tileSourceY + tileSourceH);
           destinationRect.set(0, 0, width, height);
           canvas.drawBitmap(image, sourceRect, destinationRect, null);
       }

    }

    public void drawBackgroundTo(Canvas canvas, int width, int height, int left, int top) {

        canvas.drawBitmap(image, left,top,null);

    }

    public void drawBackgroundTo(boolean resize, Canvas canvas, int left, int top, int right, int bottom) {

        destinationRect.set(left, top, right, bottom);
        canvas.drawBitmap(image, new Rect(0,0,tileDestinationW,tileDestinationH), destinationRect,null);

    }

    public void drawBackgroundTo(Canvas canvas, int width, int height,int destinationLeft, int destinationTop, int destinationRight, int destinationBottom) {

        sourceRect.set(tileSourceX, tileSourceY, tileSourceX + tileSourceW, tileSourceY + tileSourceH);
        destinationRect.set(destinationLeft, destinationTop, destinationLeft + destinationRight, destinationTop + destinationBottom);
        canvas.drawBitmap(image, sourceRect, destinationRect, null);


    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Rect getSourceRect() {
        return sourceRect;
    }

    public void setSourceRect(Rect sourceRect) {
        this.sourceRect = sourceRect;
    }

    public Rect getDestinationRect() {
        return destinationRect;
    }

    public void setDestinationRect(Rect destinationRect) {
        this.destinationRect = destinationRect;
    }

    public boolean isSprite() {
        return sprite;
    }

    public void setSprite(boolean sprite) {
        this.sprite = sprite;
    }

    public int getTileSourceW() {
        return tileSourceW;
    }

    public void setTileSourceW(int tileSourceW) {
        this.tileSourceW = tileSourceW;
    }

    public int getTileSourceH() {
        return tileSourceH;
    }

    public void setTileSourceH(int tileSourceH) {
        this.tileSourceH = tileSourceH;
    }

    public int getTileDestinationW() {
        return tileDestinationW;
    }

    public void setTileDestinationW(int tileDestinationW) {
        this.tileDestinationW = tileDestinationW;
    }

    public int getTileDestinationH() {
        return tileDestinationH;
    }

    public void setTileDestinationH(int tileDestinationH) {
        this.tileDestinationH = tileDestinationH;
    }

    public int getTileSourceX() {
        return tileSourceX;
    }

    public void setTileSourceX(int tileSourceX) {
        this.tileSourceX = tileSourceX;
    }

    public int getTileSourceY() {
        return tileSourceY;
    }

    public void setTileSourceY(int tileSourceY) {
        this.tileSourceY = tileSourceY;
    }

    public int getTileDestinationX() {
        return tileDestinationX;
    }

    public void setTileDestinationX(int tileDestinationX) {
        this.tileDestinationX = tileDestinationX;
    }

    public int getTileDestinationY() {
        return tileDestinationY;
    }

    public void setTileDestinationY(int tileDestinationY) {
        this.tileDestinationY = tileDestinationY;
    }





}
