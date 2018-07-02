package mthocur;

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

    public Physics getPhysics() {
        return physics;
    }

    public void setPhysics(Physics physics) {
        this.physics = physics;
    }

    private Physics physics;

    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean jumping = false;
    private boolean crouching = false;

    private int jumpStartY = 0;
    private int jumpMaxY = 100;


    public Player(){}

    public Player(String tag){
        super.tag = tag;
    }

    public Player(String tag,Animation animation){
        this.tag = tag;
        this.animation = animation;
        super.hasCollider = true;
    }

    public void stop(){

        movingLeft = false;
        movingRight = false;
        jumping = false;
        crouching = false;

        this.animation.setSpriteRow(this.animation.getSpriteRowStop());
        this.animation.setPlayStatus(false);
    }

    public void walkRight(){
        stop();
        if(!movingRight){
            movingRight = true;
            this.animation.setSpriteRow(this.animation.getSpriteRowRight());
            this.animation.setPlayStatus(true);
            this.animation.setSpriteIntervalX(1);
            //this.animation.setSpriteIntervalY(0);
        }
    }

    public void walkLeft(){
        stop();

        if(!movingLeft){
            Log.i("asdafdsafsgash","1234124");
            movingLeft = true;
            this.animation.setSpriteRow(this.animation.getSpriteRowLeft());
            this.animation.setPlayStatus(true);
            this.animation.setSpriteIntervalX(-1);
            //this.animation.setSpriteIntervalY(0);
        }
    }

    public void jump(){
        if(!jumping){
            jumping = true;
            animation.setPlayStatus(true);
            animation.setSpriteIntervalY(-1);
            //animation.setSpriteIntervalX(0);
            setJumpStartY(animation.getSpriteDestinationY());
        }

    }

    public void crouch(){
        this.animation.setSpriteRow(this.animation.getSpriteRowCrouch());
        this.animation.setPlayStatus(true);
    }

    public void update(){
        jump();
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



}
