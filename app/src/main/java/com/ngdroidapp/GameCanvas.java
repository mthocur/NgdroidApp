package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;
import mthocur.Background;
import mthocur.Physics;
import mthocur.Player;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {

    public Player player;
    public Background mainBackground;
    public Background controllerBg;
    public int[][] animationFrames= {{0,0},{1,8},{9,11},{11,13}};
    private Rect bosluk;
    private Paint siyah;
    private Physics physic;

    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {

        controllerBg = new Background(
                Utils.loadImage(root,"controller.png"),
                false,
                Utils.loadImage(root,"controller.png").getWidth(),
                Utils.loadImage(root,"controller.png").getHeight(),
                128,
                128,
                0,
                0,
                0,
                0,
                new Rect(),
                new Rect(),
                false
        );

        mainBackground = new Background(
                Utils.loadImage(root,"ingame.png"),
                false,
                Utils.loadImage(root,"ingame.png").getWidth(),
                Utils.loadImage(root,"ingame.png").getHeight(),
                getWidth(),
                getHeight(),
                0,
                0,
                0,
                0,
                new Rect(),
                new Rect(),
                false
        );

        player = new Player();
        player.setTag("oyuncu");
        player.setPhysics(physic);
        player.setAnimation(new Animation(
                Utils.loadImage(root,"cowboy.png"),
                new Rect(),new Rect(),
                128,
                128,
                0,
                0,
                256,
                256,
                110,
                720,
                animationFrames,
                256/16,
                1,
                1,
                0,
                2
        ));

        bosluk = new Rect(10,getHeight()-50,getWidth()-10,getHeight()-20);
        siyah = new Paint(Color.BLACK);

        physic = new Physics();

    }

    public void update() {
        physic.isColliding(player,bosluk);

        physic.addGravity(player);
        //player.setGravity(1);
        if(player.getAnimation().getPlayStatus()){
            player.getAnimation().playAnimation(true);
        }else{
            player.getAnimation().playAnimation(false);
        }
        if(player.isMovingLeft() || player.isMovingRight()){
            if(player.isJumping()){
                if(player.getJumpStartY() -  player.getAnimation().getSpriteDestinationY() >= 100){
                    //player.getAnimation().setPlayStatus(false);
                    player.getAnimation().setSpriteIntervalY(0);
                    player.setJumping(false);
                }
            }
        }
        if(player.isJumping()){
            if(player.getJumpStartY() -  player.getAnimation().getSpriteDestinationY() >= 100){
                player.getAnimation().setPlayStatus(false);
                player.setJumping(false);
            }
        }



        //Log.i("----FRAME----",""+player.getAnimation().getCurrentFrame());


    }

    public void draw(Canvas canvas) {

        mainBackground.drawBackground(canvas,getWidth(),getHeight());

        controllerBg.drawBackgroundTo(
                canvas,
                getWidth(),
                getHeight(),
                0,
                getHeight()-controllerBg.getImage().getHeight(),
                controllerBg.getImage().getHeight(),
                getHeight()
        );

        player.getAnimation().drawToCanvas(canvas);
        //Log.i("as","draw");

        canvas.drawRect(bosluk,siyah);

    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return true;
    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void touchDown(int x, int y, int id) {
    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
        /**
         * x , y
         * 1. controllerBg.getImage().getWidth()/2 , getHeight()-controllerBg.getImage().getHeight()
         * 2. controllerBg.getImage().getWidth()/2 , getHeight()-controllerBg.getImage().getHeight()/2
         * 3. controllerBg.getImage().getWidth()/2 , getHeight()
         * 4. controllerBg.getImage().getWidth() , getHeight()-controllerBg.getImage().getHeight()/2
         * 5. 0 , getHeight()-controllerBg.getImage().getHeight()/2
         * 6. 0 , getHeight()-controllerBg.getImage().getHeight()
         * 7. controllerBg.getImage().getWidth() , getHeight()-controllerBg.getImage().getHeight()
         * 8. controllerBg.getImage().getWidth() , getHeight()
         * 9. 0 , getHeight()
         *
         *  buton alanı y 7> x 7<
         *
         *
         * sağ üst y 7> 4< x 1> 7<
         * sağ alt y 4> 8< x 2> 4<
         * sol üst y 6> 5< x 1<
         * sol alt y 5> 9< x 1<
         */
        if( y >= getHeight()-controllerBg.getImage().getHeight() && x <= controllerBg.getImage().getWidth() ){
            //buton rect içerisinde bir yere dokunuldu
            if(x > controllerBg.getImage().getWidth()/2 ){

                //sağ bölge
                if(y < getHeight()-controllerBg.getImage().getHeight()/2 ){
                    //sağ üst
                    Log.i("CONTROLLER","SAĞ ÜST");
                    player.walkRight();
                    player.jump();
                }else{
                    player.stop();
                    //sağ alt
                    Log.i("CONTROLLER","SAĞ ALT");
                }

            }else{

                //sol bölge
                if(y < getHeight()-controllerBg.getImage().getHeight()/2 ){
                    //sol üst
                    player.walkLeft();
                    player.jump();
                    Log.i("CONTROLLER","SOL ÜST");
                }else{
                    player.jump();

                    Log.i("CONTROLLER","SOL ALT");
                }
            }
        }
    }

    public void pause() {

    }

    public void resume() {

    }

    public void reloadTextures() {

    }

    public void showNotify() {
    }

    public void hideNotify() {
    }

}
