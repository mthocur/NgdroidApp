package mthocur;

public class Bullet extends GameObject {
    private int damage = 20;
    private boolean explode = false;
    private Background background;
    private int direction = 1;
    private int speed = 50;
    private boolean live = true;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }



    public Bullet(){}

    public Bullet(Background background, int damage, boolean explode, boolean collider, int direction, int speed){
        this.background = background;
        this.damage = damage;
        this.explode = explode;
        this.collider = collider;
        this.direction = direction;
        this.live = true;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isExplode() {
        return explode;
    }

    public void setExplode(boolean explode) {
        this.explode = explode;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
