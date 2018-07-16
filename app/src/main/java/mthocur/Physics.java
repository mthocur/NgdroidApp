package mthocur;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.BitSet;
import java.util.Iterator;

import istanbul.gamelab.ngdroid.util.Log;

import static android.graphics.Rect.intersects;

public class Physics {
    private final int GRAVITY = 20;

    public boolean isColliding() {
        return colliding;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }

    private boolean colliding = false;

    private Rect check1;
    private Rect check2;

    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private boolean collided = true;

    public void addGravity(Player player){

        if(!player.isOnGround() && !player.isJumping()){
            player.getAnimation().setPlayStatus(true);
            player.getAnimation().setSpriteDestinationY(player.getAnimation().getSpriteDestinationY()+GRAVITY);
        }

    }


    public void checkCollision(Object... objects){
        for (Object obj : objects) {
            for(Object obj2 : objects){
                if(obj.getClass() == Ground.class){
                    continue;
                }else if(obj.getClass() == Bullet.class){
                    continue;
                }else if(obj.getClass() == Blank.class){
                    continue;
                }
                else if(obj.getClass() == Player.class){
                        //Log.i("Collision","oyuncu x "+obj2.getClass());
                        if(((Player)obj).getCollider() ){
                            if(obj2.getClass() == Player.class){
                                //Oyuncular çarpıştı
                            }else if(obj2.getClass() == Ground.class){
                                //Log.i("1","oyuncu x "+obj2.getClass());
                                //çarpışma var mı?

                                check1 = new Rect( ((Player) obj).getAnimation().getSpriteDestination() );
                                check2 = new Rect( ((Ground) obj2).getRect() );

                                if(intersects(check1, check2 )){
                              //  if(Collision.isCollisionDetected(bitmap1, check1.left, check1.top, bitmap2, check2.left, check2.top)){
                                    //Log.e("Physics","çarpışma: ");
                                    //çarğışma dikey mi yatay mı?
                                    //Log.i("22",""+((Ground) obj2).getTag());
                                     if(Collision.checkVerticalCollision( check1 , check2 )){
                                         collided = true;

                                     //if(Collision.isCollisionDetected(bitmap1, check1.left, check1.top, bitmap2, check2.left, check2.top)){
                                       // Log.e("------Physics"," Check: "+ check1);
                                        //yatay çarpışma
                                      //  Log.e("Physics","dikey çarpışma: "+((Player) obj ).getTag()+"*"+((Ground) obj2).getTag());
                                        //oyuncu nesnenin üstünde mi altında mı? düşmeli mi? yürüyebilmeli mi?
                                      /*  if( ((Player) obj ).getAnimation().getSpriteDestination().top > ((Ground) obj2).getRect().top &&
                                            ((Player) obj ).getAnimation().getSpriteDestination().bottom > ((Ground) obj2).getRect().bottom  ){
                                            Log.e("Physics","oyuncu altta");
                                            ((Player) obj).setOnGround(false);
                                            ((Player) obj).setFalling(true);
                                            ((Player) obj ).setCanMoveRight(false);
                                            ((Player) obj ).setCanMoveLeft(false);
                                        }else{
                                            Log.e("Physics","oyuncu üstte");
                                            ((Player) obj).setOnGround(true);
                                            ((Player) obj).setFalling(false);
                                            ((Player) obj ).setCanMoveRight(true);
                                            ((Player) obj ).setCanMoveLeft(true);
                                        }*/
                                         if( ((Player) obj ).getAnimation().getSpriteDestination().top  < ((Ground) obj2).getRect().bottom &&
                                                 ((Player) obj ).getAnimation().getSpriteDestination().centerY() + (((Player) obj ).getAnimation().getSpriteDestinationW()/8)*3 <((Ground) obj2).getRect().top  ){
                                          /*   Log.e("Physics","oyuncu alt "+ ((Player) obj ).getAnimation().getSpriteDestination().top);
                                             Log.e("Physics","oyuncu üst "+ ((Player) obj ).getAnimation().getSpriteDestination().bottom);
                                             Log.e("Physics","yer alt " + ((Ground) obj2).getRect().bottom);
                                             Log.e("Physics","yer üst "+ ((Ground) obj2).getRect().top);*/
                                             ((Player) obj).setOnGround(true);
                                             ((Player) obj).setFalling(false);
                                             ((Player) obj ).setCanMoveRight(true);
                                             ((Player) obj ).setCanMoveLeft(true);
                                         }else{ //Log.e("Physics","oyuncu altta");
                                             ((Player) obj).setOnGround(false);
                                             ((Player) obj).setFalling(true);
                                             ((Player) obj ).setCanMoveRight(false);
                                             ((Player) obj ).setCanMoveLeft(false);

                                         }
                                    }else if((obj2.getClass() == Ground.class) && (obj.getClass() == Player.class)){
                                         //( (Player) obj).isOnGround(false) ;
                                         /*
                                         Log.e("Physics","Else");
                                         ((Player) obj).setFalling(true);
                                         ((Player) obj).setOnGround(false);
                                         ((Player) obj ).setCanMoveRight(true);
                                         ((Player) obj ).setCanMoveLeft(true);*/
                                     }
                                }else if (!collided){
                                    //Log.e("Physics","if");
                                    ((Player) obj).setFalling(true);
                                    ((Player) obj).setOnGround(false);
                                    ((Player) obj ).setCanMoveRight(true);
                                    ((Player) obj ).setCanMoveLeft(true);
                                }

                            }else if(obj2.getClass() == Bullet.class){
                                //oyuncu ile mermi çarpıştı
                            }
                        }
                    }
            }
        }
        collided = false;

    }

    public boolean playerCanMoveRight(){
        return true;
    }



    public boolean isColliding(Object obj1,Object obj2){


        if(obj1.getClass() == Player.class){

            if( ((Player) obj1 ).getAnimation().getSpriteDestination().intersect( ((Rect) obj2) ) ){
                //((Player) obj1).getPhysics().setColliding(true);
               // Log.i("COLLIDER","ÇARPTI");
            }
            //
            //Log.i("OBJECT",""+obj1.getClass());
            //Log.i("OBJECT",""+((Player) obj1).getTag());
            return true;
        }

        if(obj1.getClass() == Ground.class){

        }

        if(obj1.getClass() == Bullet.class){

        }

        if(obj1.getClass() == Blank.class){

        }




        return false;

    }

}


