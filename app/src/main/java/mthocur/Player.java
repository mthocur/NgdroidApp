package mthocur;

import android.graphics.Rect;

import com.ngdroidapp.Animation;

public class Player extends GameObject {

    private int health;
    private int defence;
    private int attack;
    private int stamina;
    private int weapon;
    private int ammo;
    private boolean moving;
    private Animation animation;


    public Player(){}

    public Player(String tag){
        super.tag = tag;
    }

    public Player(String tag,Animation animation){
        this.tag = tag;
        this.animation = animation;
    }

    public void stop(){
        if(moving){
            moving = false;
        }

        this.animation.setSpriteRow(this.animation.getSpriteRowStop());
        this.animation.setPlayStatus(false);
    }

    public void walkRight(){
        if (moving){
            stop();
        }
        moving = true;
        this.animation.setSpriteRow(this.animation.getSpriteRowRight());
        this.animation.setPlayStatus(true);
        this.animation.setSpriteIntervalX(1);
        this.animation.setSpriteIntervalY(0);
        this.animation.setSpriteDestinationX(this.animation.getSpriteDestinationX() + this.animation.getSpriteVelocityX()*this.animation.getSpriteIntervalX());
        this.animation.setSpriteDestinationX(this.animation.getSpriteDestinationY() + this.animation.getSpriteVelocityY()*this.animation.getSpriteIntervalY());
    }

    public void walkLeft(){
        if (moving){
            stop();
        }
        moving = true;
        this.animation.setSpriteRow(this.animation.getSpriteRowLeft());
        this.animation.setPlayStatus(true);
        this.animation.setSpriteIntervalX(-1);
        this.animation.setSpriteIntervalY(0);
        this.animation.setSpriteDestinationX(this.animation.getSpriteDestinationX() + this.animation.getSpriteVelocityX()*this.animation.getSpriteIntervalX());
        this.animation.setSpriteDestinationX(this.animation.getSpriteDestinationY() + this.animation.getSpriteVelocityY()*this.animation.getSpriteIntervalY());
    }

    public void jump(){
        moving = true;
        this.animation.setSpriteRow(this.animation.getSpriteRowJump());
        this.animation.setPlayStatus(true);
    }

    public void crouch(){
        this.animation.setSpriteRow(this.animation.getSpriteRowCrouch());
        this.animation.setPlayStatus(true);
    }

    public void move(){

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



}
