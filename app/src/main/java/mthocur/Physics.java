package mthocur;

import android.graphics.Rect;

import java.util.Iterator;

import istanbul.gamelab.ngdroid.util.Log;

public class Physics {
    private final int GRAVITY = 20;

    public boolean isColliding() {
        return colliding;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }

    private boolean colliding = false;

    public void addGravity(Player player){

        if(!player.isOnGround()){
            player.getAnimation().setPlayStatus(true);
            player.getAnimation().setSpriteDestinationY(player.getAnimation().getSpriteDestinationY()+GRAVITY);
        }

    }

    public void checkCollision(Object... objects){
        for (Object obj : objects) {
            for(Object obj2 : objects){
                    if(obj.getClass() == Player.class){
                        //Log.i("Collision","oyuncu x "+obj2.getClass());
                        if(((Player)obj).getCollider() ){
                            if(obj2.getClass() == Player.class){
                                //Oyuncular çarpıştı
                            }else if(obj2.getClass() == Ground.class){
                                if(((Player) obj ).getAnimation().getSpriteDestination().intersect( ((Ground) obj2).getRect() )){
                                    ((Player) obj).setOnGround(true);
                                    ((Player) obj).setFalling(false);
                                    Log.i("Collision",""+((Player) obj).getTag()+" Taglı oyuncu");
                                }

                            }else if(obj2.getClass() == Bullet.class){
                                //oyuncu ile mermi çarpıştı
                            }
                        }

                    }else if(obj.getClass() == Ground.class){

                    }else if(obj.getClass() == Bullet.class){

                    }else if(obj.getClass() == Blank.class){

                    }


            }

        }

    }



    public boolean isColliding(Object obj1,Object obj2){


        if(obj1.getClass() == Player.class){

            if( ((Player) obj1 ).getAnimation().getSpriteDestination().intersect( ((Rect) obj2) ) ){
                //((Player) obj1).getPhysics().setColliding(true);
                Log.i("COLLIDER","ÇARPTI");
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


