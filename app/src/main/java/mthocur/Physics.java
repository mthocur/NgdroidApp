package mthocur;

import android.graphics.Rect;

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

        if(!colliding){
            player.getAnimation().setSpriteDestinationY(player.getAnimation().getSpriteDestinationY()+GRAVITY);
        }

    }

    public boolean isColliding(Object obj1,Object obj2){


        if(obj1.getClass() == Player.class){

            if( ((Player) obj1 ).getAnimation().getSpriteDestination().intersect( ((Rect) obj2) ) ){
                //((Player) obj1).getPhysics().setColliding(true);
                Log.i("123124125125","ÇARPTI");
            }
            //
            //Log.i("OBJECT",""+obj1.getClass());
            //Log.i("OBJECT",""+((Player) obj1).getTag());
            return true;
        }
        return false;

    }

}


