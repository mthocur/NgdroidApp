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

        if(!player.isOnGround() && !player.isJumping()){
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
                                //Log.i("1","oyuncu x "+obj2.getClass());
                                //çarpışma var mı?
                                if(((Player) obj ).getAnimation().getSpriteDestination().intersect( ((Ground) obj2).getRect() )){
                                    //çarğışma dikey mi yatay mı?

                                    if(Collision.checkVerticalCollision( ((Player) obj ).getAnimation().getSpriteDestination() , ((Ground) obj2).getRect() )){
                                        //yatay çarpışma
                                        //Log.e("Physics","dikey çarpışma: "+((Player) obj ).getTag()+"*"+((Ground) obj2).getTag());
                                        //oyuncu nesnenin üstünde mi altında mı? düşmeli mi? yürüyebilmeli mi?
                                        if( ((Player) obj ).getAnimation().getSpriteDestination().top > ((Ground) obj2).getRect().top ){
                                            //Log.e("Physics","oyuncu aşağıda");
                                        }else{
                                            //Log.e("Physics","oyuncu üstte");
                                            ((Player) obj).setOnGround(true);
                                            ((Player) obj).setFalling(false);
                                            ((Player) obj ).setCanMoveRight(true);
                                            ((Player) obj ).setCanMoveLeft(true);

                                        }
                                    }
                                    if(Collision.checkHorizontalCollision( ((Player) obj ).getAnimation().getSpriteDestination() , ((Ground) obj2).getRect() )){
                                        //yatay çarpışma
                                        //Log.e("Physics","yatay çarpışma: "+((Player) obj ).getTag()+"*"+((Ground) obj2).getTag());
                                        //oyuncu nesnenin sağında mı solunda mı? sağa mı sola mı gidememeli?
                                        if( ((Player) obj ).getAnimation().getSpriteDestination().left > ((Ground) obj2).getRect().left ){
                                            //Log.e("Physics","oyuncu solda");
                                            //((Player) obj ).stop();
                                            ((Player) obj ).setCanMoveRight(false);
                                            ((Player) obj ).setCanMoveLeft(true);
                                            ((Player) obj ).setFalling(true);


                                        }else{
                                            //Log.e("Physics","oyuncu sağda");
                                            //((Player) obj ).stop();
                                            ((Player) obj ).setCanMoveRight(true);
                                            ((Player) obj ).setCanMoveLeft(false);
                                            ((Player) obj ).setFalling(true);
                                        }
                                    }

                                }
                                /*
                                if(((Player) obj ).getAnimation().getSpriteDestination().intersect( ((Ground) obj2).getRect() )){
                                    //Log.i("2","oyuncu x "+obj2.getClass());
                                    ((Player) obj).setOnGround(true);
                                    ((Player) obj).setFalling(false);
                                    /*if(((Player) obj).isMovingRight()){
                                        ((Player) obj).stop();
                                    }
                                    if(((Player) obj).isMovingLeft()){
                                        ((Player) obj).stop();
                                    }
                                    Log.i("Collision",""+((Player) obj).getTag()+" & "+((Ground) obj2).getTag());
                                }*/

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

    public boolean playerCanMoveRight(){
        return true;
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


