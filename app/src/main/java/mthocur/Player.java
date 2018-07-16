package mthocur;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.ngdroidapp.Animation;
import com.ngdroidapp.NgApp;

import java.lang.reflect.Array;
import java.util.List;

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

    public boolean aktif = true;

    private boolean onGround = false;
    private boolean falling = false;

    private int jumpStartY = 0;
    private int jumpMaxY = 100;
    private int JumpingNumber = 4;

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

        intervalX = 0;
        intervalY = 0;

        this.animation.setSpriteRow(this.animation.getSpriteRowStop());
        this.animation.setPlayStatus(false);
    }
    public void stopMove(){
        movingLeft = false;
        movingRight = false;
        crouching = false;
     //   stop = true;

        intervalX = 0;


        this.animation.setSpriteRow(this.animation.getSpriteRowStop());
        this.animation.setPlayStatus(false);
    }


    public void walkRight(){

        if(canMoveRight){
            stop = false;
            movingLeft = false;
            movingRight = true;
            this.animation.setSpriteRow(this.animation.getSpriteRowRight());
            this.animation.setPlayStatus(true);
            intervalX = 1;
        }


    }

    public void walkLeft(){
        if(canMoveLeft){
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
        if(!jumping && (isOnGround() || getJumpingNumber() != 0 )){
            if(getJumpingNumber() != 0)
                setJumpingNumber(getJumpingNumber() - 1);
            setOnGround(false);
            jumping = true;
            animation.setPlayStatus(true);
            intervalY = -1;

            //animation.setSpriteIntervalX(0);
            setJumpStartY(animation.getSpriteDestinationY());
        }

    }

    public void updateJump(){

        if(isMovingLeft() || isMovingRight()){
            if(isJumping()){
                if(getJumpStartY() -  getAnimation().getSpriteDestinationY() >= (animation.getSpriteDestinationW())){
                    //player.getAnimation().setPlayStatus(false);
                   // Log.i("----------Jumppp","draw");
                    intervalY = 0;
                    setJumping(false);
                    aktif = false;
                }
                setFalling(true);
                setOnGround(false);
                getAnimation().setPlayStatus(false);
            }
        }
        if(isJumping()){
            if(getJumpStartY() -  getAnimation().getSpriteDestinationY() >=(animation.getSpriteDestinationW())){
                getAnimation().setPlayStatus(false);
                intervalY = 0;
                setJumping(false);
                aktif = false;
             //   Log.i("------------Jump","draw");
            }
            setFalling(true);
            setOnGround(false);

        }if(!isJumping()){
            intervalY = 0;
        }

        if(!isJumping() && isOnGround() && (isMovingLeft() || isMovingRight())){
            getAnimation().setPlayStatus(true);
        }
       // if(isOnGround()){
       //     setJumping(false);
       // }

    }

    public void updateMovingStatus(){
        if(isMovingRight()||isMovingLeft()){
            animation.setPlayStatus(true);
        }
    }

    public void updateLocation(int width){
   //     Log.i("7777",""+isOnGround());
        if(!isOnGround() && !isJumping()){
            setFalling(true);

        }
        if(isOnGround()){
            setJumpingNumber(4);
        }

        if( animation.getSpriteDestinationX()/2  <= 0){
            canMoveLeft = false;
            animation.setSpriteDestinationX( animation.getSpriteDestinationW()/8-2 );
        }
        if( animation.getSpriteDestinationX() >= width- animation.getSpriteDestinationW()){
            canMoveRight = false;
            animation.setSpriteDestinationX( width- animation.getSpriteDestinationW() );
        }
        velocityX = animation.getSpriteDestinationW()/8;
        velocityY = animation.getSpriteDestinationH()/8;
        if(!stop &&(movingLeft || movingRight|| jumping || crouching)){
         //   Log.i("eqeqwewqewq","qweweqeqwe" + intervalY);
            animation.setSpriteDestinationX( animation.getSpriteDestinationX() + velocityX * intervalX );
            animation.setSpriteDestinationY( animation.getSpriteDestinationY() + velocityY * intervalY );
        }
    }

    public void updateBulletLocations(List<Bullet> bullets){

        if(!bullets.isEmpty()){
            Log.i("Update/bullet","mermi listesi boş değil");
            for (int i = 0; i < bullets.size(); i++) {
                Bullet blt = bullets.get(i);
                if(blt.isLive()){

                    blt.getBackground().setTileDestinationX(blt.getBackground().getTileDestinationX() + blt.getSpeed()*blt.getDirection());
                    Log.i("Update/bullet","merminin şuanki konumu "+blt.getBackground().getTileDestinationX());
                }else{
                    bullets.remove(i);
                }
            }
        }


    }

    public void shoot(NgApp root, Bitmap bulletimage, List<Bullet> bullets) {
        getDirection();
        int bulletX;
        if (getDirection() == 1) {
            //bullet direction right
            bulletX = getAnimation().getSpriteDestination().centerX() + (getAnimation().getSpriteDestinationW() / 2 * getDirection());
            Log.i("sağa ateş", "bulletX:" + bulletX);
        } else if (getDirection() == -1) {
            //bullet direction left
            bulletX = getAnimation().getSpriteDestination().centerX() + (getAnimation().getSpriteDestinationW() / 2 * getDirection());
            Log.i("sola ateş", "bulletX:" + bulletX);

        } else {
            Log.i("ateş direction 0", "bulletX:");
            bulletX = 0;
        }

        int bulletY = getAnimation().getSpriteDestination().centerY();


        Bullet bullet = new Bullet(
                new Background(
                        bulletimage,
                        false,
                        bulletimage.getWidth(),     //tilesourcew
                        bulletimage.getHeight(),    // tile source h
                        bulletimage.getWidth(),     //tiledestinaion w
                        bulletimage.getHeight(),    //tile destination h
                        0,
                        0,
                        bulletX,
                        bulletY,
                        new Rect(),
                        new Rect(),
                        true
                ),
                getAttack(),
                false,
                true,
                getDirection(),
                30
        );

        bullets.add(bullet);

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




    public int getJumpingNumber() {
        return JumpingNumber;
    }
    public void setJumpingNumber(int JumpingNumber) {
        this.JumpingNumber = JumpingNumber;
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

    public int getDirection(){

        if(getAnimation().getSpriteRow() == 0){
            return 1;
        } else if(getAnimation().getSpriteRow() == 1){
            return -1;
        }else{
            return 0;
        }
    }
}
