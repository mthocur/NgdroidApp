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
import mthocur.Ground;
import mthocur.Physics;
import mthocur.Player;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {

    public Player player;
    public Background mainBackground;
    public Background jumpButton;
    public Background controllerBg;
    public int[][] animationFrames= {{0,0},{1,8},{9,11},{11,13}};
    private Ground olumcizgisi;
    private Ground olumcizgisisol;
    private Ground olumcizgisisag;

    public Ground ground1;
    public Ground ground2;
    public Ground ground3;
    public Ground ground4;
    public Ground ground5;

    private Paint siyah;
    private Physics physic;
    private Bitmap tempImage;

    private int touchDownPosX;
    private int touchDownPosY;

    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {

        tempImage = Utils.loadImage(root,"gameObj1.png");
        ground1 = new Ground(new Background(
                tempImage,
                false,
                tempImage.getWidth(),//tilesourcew
                tempImage.getHeight(), // tile source h
                tempImage.getWidth(), //tiledestinaion w
                tempImage.getHeight(),//tile destination h
                0,
                0,
                getWidth()-250,
                getHeight()-100,
                new Rect(),
                new Rect(),
                true
        ));

        tempImage = Utils.loadImage(root,"gameObj2.png");
        ground2 = new Ground(new Background(
                tempImage,
                false,
                tempImage.getWidth(),     //tilesourcew
                tempImage.getHeight(),    // tile source h
                tempImage.getWidth(),     //tiledestinaion w
                tempImage.getHeight(),    //tile destination h
                0,
                0,
                this.getWidth()/2,
                this.getHeight()/2,
                new Rect(),
                new Rect(),
                true

        ));

        tempImage = Utils.loadImage(root,"gameObj2.png");
        ground3 = new Ground(new Background(
                tempImage,
                false,
                tempImage.getWidth(),     //tilesourcew
                tempImage.getHeight(),    // tile source h
                tempImage.getWidth(),     //tiledestinaion w
                tempImage.getHeight(),    //tile destination h
                0,
                0,
                this.getWidth()/2,
                this.getHeight()/2,
                new Rect(),
                new Rect(),
                true

        ));

        tempImage = Utils.loadImage(root,"gameObj4.png");
        ground4 = new Ground(new Background(
                tempImage,
                false,
                tempImage.getWidth(),
                tempImage.getHeight(),
                tempImage.getWidth(),
                tempImage.getHeight(),
                0,
                0,
                this.getWidth()/2,
                this.getHeight()/2,
                new Rect(),
                new Rect(),
                true
        ));

        tempImage = Utils.loadImage(root,"gameObj5.png");
        ground5 = new Ground(new Background(
                tempImage,
                false,
                tempImage.getWidth(),
                tempImage.getHeight(),
                tempImage.getWidth(),
                tempImage.getHeight(),
                0,
                0,
                this.getWidth()/2,
                this.getHeight()/2,
                new Rect(),
                new Rect(),
                true
        ));


        tempImage = Utils.loadImage(root,"controller.png");
        controllerBg = new Background(
                tempImage,
                false,
                tempImage.getWidth(),
                tempImage.getHeight(),
                tempImage.getWidth(),
                tempImage.getHeight(),
                0,
                0,
                0,
                0,
                new Rect(),
                new Rect(),
                false
        );


        tempImage = Utils.loadImage(root,"jump.png");

        jumpButton = new Background(
                tempImage,
                false,
                tempImage.getWidth(),
                tempImage.getHeight(),
                tempImage.getWidth(),
                tempImage.getHeight(),
                0,
                0,
                0,
                0,
                new Rect(),
                new Rect(),
                false
        );

        tempImage = Utils.loadImage(root,"BG.png");
        mainBackground = new Background(
                tempImage,
                false,
                tempImage.getWidth(),
                tempImage.getHeight(),
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

        tempImage = Utils.loadImage(root,"cowboy.png");
        player.setAnimation(new Animation(
                tempImage,
                new Rect(),new Rect(),
                128,
                128,
                0,
                0,
                256,
                256,
                100,
                100,
                animationFrames,
                2
        ));
        player.getAnimation().setPlayStatus(false);

        olumcizgisi = new Ground(new Rect(10,getHeight()-50,getWidth()-10,getHeight()-20));
        olumcizgisi.setTag("olumcizgisi");

        olumcizgisisol = new Ground(new Rect(5,5,10,getHeight()-5));
        olumcizgisisol.setTag("olumcizgisisol");

        olumcizgisisag = new Ground(new Rect(getWidth()-10,5,getWidth()-5,getHeight()-5));
        olumcizgisisag.setTag("olumcizgisisag");


        siyah = new Paint(Color.BLACK);
        physic = new Physics();



    }

    public void update() {

        physic.checkCollision(player,olumcizgisi);
        //physic.checkCollision(player,olumcizgisi,olumcizgisisag,olumcizgisisol);
        physic.addGravity(player);

        if(player.getAnimation().getPlayStatus()){
            player.getAnimation().playAnimation(true);
        }else{
            player.getAnimation().playAnimation(false);
        }

        player.updateJump();
        player.updateLocation(getWidth());
        player.updateMovingStatus();

    }

    public void draw(Canvas canvas) {

        mainBackground.drawBackground(canvas,getWidth(),getHeight());

        ground1.getBackground().drawBackgroundTo(
                canvas,
                getWidth(),
                getHeight(),
                (getWidth()-500)/2,
                (getHeight()-100)/2,
                512,
                128
        );

        ground2.getBackground().drawBackgroundTo(
                canvas,
                getWidth(),
                getHeight(),
                (getWidth()/2)-400,
                (getHeight()/2)+200,
                400,
                150
        );

        ground3.getBackground().drawBackgroundTo(
                canvas,
                getWidth(),
                getHeight(),
                (getWidth()/2)+400,
                (getHeight()/2)+200,
                200,
                100
        );

        ground4.getBackground().drawBackgroundTo(
                canvas,
                getWidth(),
                getHeight(),
                (getWidth()/2)-900,
                (getHeight()/2)+50,
                400,
                150
        );

        ground5.getBackground().drawBackgroundTo(
                canvas,
                getWidth(),
                getHeight(),
                (getWidth()/2)+489,
                (getHeight()/2)+50,
                400,
                150
        );

        controllerBg.drawBackgroundTo(
                canvas,
                getWidth(),
                getHeight(),
                0,
                getHeight()-controllerBg.getImage().getHeight()
        );

        jumpButton.drawBackgroundTo(
                canvas,
                getWidth(),
                getHeight(),
                getWidth()-jumpButton.getImage().getWidth(),
                getHeight()-jumpButton.getImage().getHeight()
        );

        player.getAnimation().drawToCanvas(canvas);
        //Log.i("as","draw");

        canvas.drawRect(olumcizgisi.getRect(),siyah);
        canvas.drawRect(olumcizgisisol.getRect(),siyah);
        canvas.drawRect(olumcizgisisag.getRect(),siyah);

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
        touchDownPosX = x;
        touchDownPosY = y;
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
                player.walkRight();
                Log.e("TD","SAĞ YÜRÜ");

            }else{
                player.walkLeft();
                Log.e("TD","SOL YÜRÜ");

            }
        }

        if( y >= getHeight()-jumpButton.getImage().getHeight() && x >= getWidth()-jumpButton.getImage().getWidth() ){
            //buton rect içerisinde bir yere dokunuldu
            //jumpbuton koordinatları içerisinde bir yere dokunuldu
            player.jump();
        }
    }

    public void touchMove(int x, int y, int id) {


    }

    public void touchUp(int x, int y, int id) {
        player.getAnimation().setPlayStatus(false);
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
