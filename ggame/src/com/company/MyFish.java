package com.company;

import java.awt.*;

public class MyFish extends Fish{
    public boolean LEFT;
    public boolean RIGHT;
    public boolean UP;
    public boolean DOWN;
    public MyFish(int x, int y, int w, int h, int speed, int grade, int cnt, Image img) {
        super(x, y, w, h, speed, grade, cnt, img);
        LEFT = false;
        RIGHT = false;
        UP = false;
        DOWN = false;
        speed = Math.abs(speed);
    }

    @Override
    void paintSelf(Graphics g) {
        g.drawImage(img, x, y, w + grade, h + grade, null);
    }

    @Override
    Rectangle getRectangle() {
        return super.getRectangle();
    }

    public void move(){
        if (LEFT){
            x -= speed;
        }
        if (RIGHT){
            x += speed;
        }
        if (UP){
            y -= speed;
        }
        if (DOWN){
            y += speed;
        }
    }
    public void eat(Fish fish){
        Rectangle my = super.getRectangle();
        Rectangle you = fish.getRectangle();
        if (my.intersects(you)){
            if (this.grade >= fish.grade){
                fish.isLive = false;
                this.cnt += fish.cnt;
                this.grade = this.cnt / 10 + 1;
            }else {
                this.isLive = false;
            }
        }
    }
    public void Living(){
        this.x = Window.w / 2;
        this.y = Window.h / 2;
        this.isLive = true;
        this.cnt = 0;
        this.grade = 1;
        LEFT = false;
        UP = false;
        DOWN = false;
        RIGHT = false;
    }
}
