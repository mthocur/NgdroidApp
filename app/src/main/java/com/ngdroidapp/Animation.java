package com.ngdroidapp;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.util.Log;
/**
 * Created by mthocur on 27.06.2018.
 * Feel free to use and contribute
 * @author mthocur
 */
public class Animation{


    public Animation(){}

    /**
     * Fills class fields with given parameters
     * @param sprite Bitmap spritesheet object
     * @param spriteDestination Rect sprite destination object
     * @param spriteSource Rect sprite source object
     * @param spriteSourceW int sprite source width
     * @param spriteSourceH int sprite source height
     * @param spriteSourceX int sprite source X value
     * @param spriteSourceY int sprite source Y value
     * @param spriteDestinationW int sprite destination width
     * @param spriteDestinationH int sprite destination height
     * @param spriteDestinationX int sprite destination X value
     * @param spriteDestinationY int sprite destination Y value
     * @param animationFrames int[][] sprite animation frame limits eg. {{0,0},{1,8},{9,11},{11,13}}
     * @param spriteVelocityX int animation velocity x value
     * @param spriteVelocityY int animation velocity y value
     * @param spriteIntervalX int animation interval x value
     * @param spriteIntervalX int animation interval y value
     * @param spriteRow int row of the animation on y axis
     */
    public Animation(Bitmap sprite,
                     Rect spriteDestination,
                     Rect spriteSource,
                     int spriteSourceW,
                     int spriteSourceH,
                     int spriteSourceX,
                     int spriteSourceY,
                     int spriteDestinationW,
                     int spriteDestinationH,
                     int spriteDestinationX,
                     int spriteDestinationY,
                     int[][] animationFrames,
                     int spriteVelocityX,
                     int spriteVelocityY,
                     int spriteIntervalX,
                     int spriteIntervalY,
                     int spriteRow ){
        this.spriteSheet = sprite;
        this.spriteDestination = spriteDestination;
        this.spriteSource = spriteSource;
        this.spriteSourceW = spriteSourceW;
        this.spriteSourceH = spriteSourceH;
        this.spriteSourceX = spriteSourceX;
        this.spriteSourceY = spriteSourceY;
        this.spriteDestinationW = spriteDestinationW;
        this.spriteDestinationH = spriteDestinationH;
        this.spriteDestinationX = spriteDestinationX;
        this.spriteDestinationY = spriteDestinationY;
        this.animationFrames = animationFrames;
        this.spriteVelocityX = spriteVelocityX;
        this.spriteVelocityY = spriteVelocityY;
        this.spriteIntervalX = spriteIntervalX;
        this.spriteIntervalY = spriteIntervalY;
        this.spriteRow = spriteRow;
        Log.i("ANIMATION","An animation object has been created with all parameters given");
    }

    /**
     * If you want to build without all fields you can use this constructor.
     * All fields takes default values.
     * You need to build with filling some important fields after object definition using buildAnimation() func
     * Fill you need to use important fields manually with setter methods
     */
    public Animation buildAnimation()
    {
        Log.i("ANIMATION","An animation object has been created with builder pattern");
        return new Animation(spriteSheet,
                spriteDestination,
                spriteSource,
                spriteSourceW,
                spriteSourceH,
                spriteSourceX,
                spriteSourceY,
                spriteDestinationW,
                spriteDestinationH,
                spriteDestinationX,
                spriteDestinationY,
                animationFrames,
                spriteVelocityX,
                spriteVelocityY,
                spriteIntervalX,
                spriteIntervalY,
                spriteRow
        );
    }

    //Animasyonun bulunduğu spritesheet
    private Bitmap spriteSheet;

    //animasyonun oluşturulmasında kullanılacak spritelar
    private Rect spriteSource, spriteDestination;

    //animasyonun oluşturulmasında kullanılacak spriteların genişlik,yükseklik değerleri
    private int spriteSourceW = 128, spriteSourceH = 128, spriteDestinationW = 256, spriteDestinationH = 256;
    private int spriteDestinationX = 0, spriteDestinationY = 0, spriteSourceX = 0, spriteSourceY = 0;

    //oynamakta olan animasyonun şuandaki oynatılan frame numarası
    private int currentFrame=0;

    //animasyon yönleri için başlangıç ve bitiş frame numaraları
    private int[][] animationFrames = {{0,0}};
    private int spriteVelocityX = spriteDestinationW / 8;
    private int spriteVelocityY = spriteDestinationH / 8;
    private int spriteIntervalX =1;
    private int spriteIntervalY =0;

    //sprite row for current animation eg. 0.row = stop, 1.row = walk right
    private int spriteRow = 0;


    private int spriteRowStop = 0;
    private int spriteRowRight = 0;
    private int spriteRowLeft = 1;
    private int spriteRowJump = 3;
    private int spriteRowCrouch = 4;


    //animasyon oynatılmakta mı yoksa dondurulmuş mu
    //false stop, true playing
    private boolean playStatus = false;


    /**
     * Function for change the animation play/stop status
     * Use in update() function
     * @param play true plays the animation when animation is not playing; false stops the animation
     */
    public void playAnimation(boolean play){
        if (play) {
            playStatus = true;
            resumeAnimation();
        }else{
            playStatus = false;
        }

    }

    /**
     * You can't use this function when animation playStatus is false, set the status to true first
     * Use in draw() function
     */
    public void resumeAnimation(){
        playStatus = true;
        currentFrame++;
        if(currentFrame > animationFrames[1][1]) {
            currentFrame = animationFrames[1][0];
        }
        spriteSourceX = currentFrame * spriteSourceW;
        spriteSourceY = spriteRow * spriteSourceH;

        spriteDestinationX += spriteVelocityX * spriteIntervalX;
        spriteDestinationY += spriteVelocityX * spriteIntervalY;

    }

    /**
     * Function for draw animation to game canvas
     * @param canvas Canvas type object, give main game canvas to draw animation.
     */
    public void drawToCanvas(Canvas canvas){
        spriteSource.set(spriteSourceX, spriteSourceY, spriteSourceX + spriteSourceW, spriteSourceY + spriteSourceH);
        spriteDestination.set(spriteDestinationX, spriteDestinationY, spriteDestinationX + spriteDestinationW, spriteDestinationY + spriteDestinationH);
        canvas.drawBitmap(spriteSheet, spriteSource, spriteDestination, null);
    }


    //Getter and Setter Functions

    public int getSpriteRow() {
        return spriteRow;
    }

    public void setSpriteRow(int spriteRow) {
        this.spriteRow = spriteRow;
    }

    public boolean getPlayStatus(){
        return this.playStatus;
    }
    public void setPlayStatus(boolean state){
        this.playStatus = state;
    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(Bitmap spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public Rect getSpriteSource() {
        return spriteSource;
    }

    public void setSpriteSource(Rect spriteSource) {
        this.spriteSource = spriteSource;
    }

    public Rect getSpriteDestination() {
        return spriteDestination;
    }

    public void setSpriteDestination(Rect spriteDestination) {
        this.spriteDestination = spriteDestination;
    }

    public int getSpriteSourceW() {
        return spriteSourceW;
    }

    public void setSpriteSourceW(int spriteSourceW) {
        this.spriteSourceW = spriteSourceW;
    }

    public int getSpriteSourceH() {
        return spriteSourceH;
    }

    public void setSpriteSourceH(int spriteSourceH) {
        this.spriteSourceH = spriteSourceH;
    }

    public int getSpriteDestinationW() {
        return spriteDestinationW;
    }

    public void setSpriteDestinationW(int spriteDestinationW) {
        this.spriteDestinationW = spriteDestinationW;
    }

    public int getSpriteDestinationH() {
        return spriteDestinationH;
    }

    public void setSpriteDestinationH(int spriteDestinationH) {
        this.spriteDestinationH = spriteDestinationH;
    }

    public int getSpriteDestinationX() {
        return spriteDestinationX;
    }

    public void setSpriteDestinationX(int spriteDestinationX) {
        this.spriteDestinationX = spriteDestinationX;
    }

    public int getSpriteDestinationY() {
        return spriteDestinationY;
    }

    public void setSpriteDestinationY(int spriteDestinationY) {
        this.spriteDestinationY = spriteDestinationY;
    }

    public int getSpriteSourceX() {
        return spriteSourceX;
    }

    public void setSpriteSourceX(int spriteSourceX) {
        this.spriteSourceX = spriteSourceX;
    }

    public int getSpriteSourceY() {
        return spriteSourceY;
    }

    public void setSpriteSourceY(int spriteSourceY) {
        this.spriteSourceY = spriteSourceY;
    }

    public int getCurrentFrame() {
        return this.currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int[][] getAnimationFrames() {
        return animationFrames;
    }

    public void setAnimationFrames(int[][] animationFrames) {
        this.animationFrames = animationFrames;
    }

    public int getSpriteVelocityX() {
        return spriteVelocityX;
    }

    public void setSpriteVelocityX(int spriteVelocityX) {
        this.spriteVelocityX = spriteVelocityX;
    }

    public int getSpriteVelocityY() {
        return spriteVelocityY;
    }

    public void setSpriteVelocityY(int spriteVelocityY) {
        this.spriteVelocityY = spriteVelocityY;
    }

    public int getSpriteIntervalX() {
        return spriteIntervalX;
    }

    public void setSpriteIntervalX(int spriteIntervalX) {
        this.spriteIntervalX = spriteIntervalX;
    }

    public int getSpriteIntervalY() {
        return spriteIntervalY;
    }

    public void setSpriteIntervalY(int spriteIntervalY) {
        this.spriteIntervalY = spriteIntervalY;
    }

    public int getSpriteRowStop() {
        return spriteRowStop;
    }

    public void setSpriteRowStop(int spriteRowStop) {
        this.spriteRowStop = spriteRowStop;
    }

    public int getSpriteRowRight() {
        return spriteRowRight;
    }

    public void setSpriteRowRight(int spriteRowRight) {
        this.spriteRowRight = spriteRowRight;
    }

    public int getSpriteRowLeft() {
        return spriteRowLeft;
    }

    public void setSpriteRowLeft(int spriteRowLeft) {
        this.spriteRowLeft = spriteRowLeft;
    }

    public int getSpriteRowJump() {
        return spriteRowJump;
    }

    public void setSpriteRowJump(int spriteRowJump) {
        this.spriteRowJump = spriteRowJump;
    }

    public int getSpriteRowCrouch() {
        return spriteRowCrouch;
    }

    public void setSpriteRowCrouch(int spriteRowCrouch) {
        this.spriteRowCrouch = spriteRowCrouch;
    }



}



