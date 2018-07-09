package mthocur;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.ngdroidapp.Animation;

import java.lang.reflect.Array;

import istanbul.gamelab.ngdroid.util.Log;


public class Player extends GameObject {

    private int health;
    private int defence;
    private int attack;
    private int stamina;
    private int weapon;
    private int ammo;

    private Animation animation;
    private boolean collider = true;


    private Physics physics;
    private boolean canMoveRight = true;
    private boolean canMoveLeft = true;


    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean jumping = false;
    private boolean crouching = false;
    private boolean stop = true;


    private boolean onGround = false;
    private boolean falling = false;

    private int jumpStartY = 0;
    private int jumpMaxY = 100;



    private int velocityX;
    private int velocityY;
    private int intervalX = 1;
    private int intervalY = 0;

    public Player(){}

    public Player(String tag){
        super.tag = tag;
    }

    public Player(String tag,Animation animation){
        this.tag = tag;
        this.animation = animation;
        super.collider = true;
    }


    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void stop(){
        movingLeft = false;
        movingRight = false;
        jumping = false;
        crouching = false;
        stop = true;

        this.animation.setSpriteRow(this.animation.getSpriteRowStop());
        this.animation.setPlayStatus(false);
    }


    public void walkRight(){

        if(isOnGround() && canMoveRight){
            stop = false;
            movingLeft = false;
            movingRight = true;
            this.animation.setSpriteRow(this.animation.getSpriteRowRight());
            this.animation.setPlayStatus(true);
            intervalX = 1;
        }


    }

    public void walkLeft(){
        if(isOnGround() && canMoveLeft){
            stop = false;
            movingRight = false;
            movingLeft = true;
            this.animation.setSpriteRow(this.animation.getSpriteRowLeft());
            this.animation.setPlayStatus(true);
            intervalX = -1;
        }
    }

    public void jump(){
        stop = false;
        if(!jumping){
            jumping = true;
            animation.setPlayStatus(true);
            intervalY = -1;
            if(!movingRight || !movingLeft){
                intervalX = 0;
            }
            //animation.setSpriteIntervalX(0);
            setJumpStartY(animation.getSpriteDestinationY());
        }

    }

    public void updateJump(){

        if(isMovingLeft() || isMovingRight()){
            if(isJumping()){
                if(getJumpStartY() -  getAnimation().getSpriteDestinationY() >= 100){
                    //player.getAnimation().setPlayStatus(false);
                    intervalY = 0;
                    setJumping(false);
                }
                setFalling(true);
                setOnGround(false);
                getAnimation().setPlayStatus(false);
            }
        }
        if(isJumping()){
            if(getJumpStartY() -  getAnimation().getSpriteDestinationY() >= 100){
                getAnimation().setPlayStatus(false);
                intervalY = 0;
                setJumping(false);
            }
            setFalling(true);
            setOnGround(false);

        }
        if(!isJumping() && isOnGround() && (isMovingLeft() || isMovingRight())){
            getAnimation().setPlayStatus(true);
        }

    }

    public void updateMovingStatus(){
        if(isMovingRight()||isMovingLeft()){
            animation.setPlayStatus(true);
        }
    }

    public void updateLocation(int width){
        if( animation.getSpriteDestinationX()/2  <= 0){
            canMoveLeft = false;
            animation.setSpriteDestinationX( animation.getSpriteDestinationW()/8-2 );
        }
        if( animation.getSpriteDestinationX() >=  width){
            canMoveRight = false;
            animation.setSpriteDestinationX( width-animation.getSpriteDestinationW() );
        }

        velocityX = animation.getSpriteDestinationW()/8;
        velocityY = animation.getSpriteDestinationH()/8;
        if(!stop &&(movingLeft || movingRight|| jumping || crouching)){
            animation.setSpriteDestinationX( animation.getSpriteDestinationX() + velocityX * intervalX );
            animation.setSpriteDestinationY( animation.getSpriteDestinationY() + velocityY * intervalY );
        }
    }

    public void crouch(){
        this.animation.setSpriteRow(this.animation.getSpriteRowCrouch());
        this.animation.setPlayStatus(true);
    }

    public void update(){
        jump();
    }


    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public String getTag(){
        return super.tag;
    }
    public void setTag(String tag){
        super.tag = tag;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isCrouching() {
        return crouching;
    }

    public void setCrouching(boolean crouching) {
        this.crouching = crouching;
    }

    public int getJumpStartY() {
        return jumpStartY;
    }

    public void setJumpStartY(int jumpStartY) {
        this.jumpStartY = jumpStartY;
    }

    public int getJumpMaxY() {
        return jumpMaxY;
    }

    public void setJumpMaxY(int jumpMaxY) {
        this.jumpMaxY = jumpMaxY;
    }

    public boolean getCollider() { return collider; }

    public void setCollider(boolean collider) { this.collider = collider; }

    public Physics getPhysics() {
        return physics;
    }

    public void setPhysics(Physics physics) { this.physics = physics; }

    public boolean canMoveRight() {
        return canMoveRight;
    }

    public void setCanMoveRight(boolean canMoveRight) {
        this.canMoveRight = canMoveRight;
    }

    public boolean canMoveLeft() {
        return canMoveLeft;
    }

    public void setCanMoveLeft(boolean canMoveLeft) {
        this.canMoveLeft = canMoveLeft;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getIntervalX() {
        return intervalX;
    }

    public void setIntervalX(int intervalX) {
        this.intervalX = intervalX;
    }

    public int getIntervalY() {
        return intervalY;
    }

    public void setIntervalY(int intervalY) {
        this.intervalY = intervalY;
    }


}
