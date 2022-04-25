package com.company;

import java.awt.*;

public class Fish {
    int x;
    int y;
    int w;
    int h;
    int dir;
    int speed;
    int grade;
    int cnt;
    boolean isLive = true;
    Image img;
    static final int LEFT = 1;
    static final int RIGHT = -1;
    static final int UP = 2;
    static final int DOWN = 3;
    static final int DIE = 4;
    public Fish(int x, int y, int w, int h, int speed, int grade, int cnt, Image img) {
        this.dir = (int) (Math.random() * 2) == 1 ? LEFT : RIGHT;
        this.x = this.dir == LEFT ? x : Window.w;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed * dir;
        this.grade = grade;
        this.cnt = cnt;
        this.img = img;
    }

    void paintSelf(Graphics g){
        g.drawImage(img, x, y, w, h, null);
    }

    Rectangle getRectangle(){
        return new Rectangle(x, y, w, h);
    }
}