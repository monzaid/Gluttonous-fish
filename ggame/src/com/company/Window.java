package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {
    static final int h = 1024;
    static final int w = 2048;
    private Image offScreenImage;
    private BG bg = new BG();
    private ArrayList<Fish> fishes = new ArrayList<>();
    private ArrayList<Fish> fishType = new ArrayList<>();
    private MyFish myFish = new MyFish(w / 2, h / 2, 90, 90, 15, 1, 1, bg.MMyFish);
    private int time = 0;
    private int state = 0;
    private int stateTemp;
    static final int BEGIN = 0;
    static final int RUN = 1;
    static final int STOP = 2;
    static final int FINAL = 3;
    static final int WIN = 4;
    public Window() throws HeadlessException {
        super("大雨吃小鱼");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(w, h);
        setResizable(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1 && state == BEGIN){
                    state = 1;
                    repaint();
                }
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_SPACE){
                    if (state == STOP){
                        state = stateTemp;
                    }else{
                        stateTemp = state;
                        state = STOP;
                    }
                }
                if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    if (state == FINAL){
                        state = RUN;
                        myFish.Living();
                        fishes.removeAll(fishes);
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W){
                    myFish.UP = true;
                }else if (e.getKeyCode() == KeyEvent.VK_S){
                    myFish.DOWN = true;
                }else if (e.getKeyCode() == KeyEvent.VK_A){
                    myFish.LEFT = true;
                }else if (e.getKeyCode() == KeyEvent.VK_D){
                    myFish.RIGHT = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W){
                    myFish.UP = false;
                }else if (e.getKeyCode() == KeyEvent.VK_S){
                    myFish.DOWN = false;
                }else if (e.getKeyCode() == KeyEvent.VK_A){
                    myFish.LEFT = false;
                }else if (e.getKeyCode() == KeyEvent.VK_D){
                    myFish.RIGHT = false;
                }
            }
        });
        setVisible(true);
        fishType.add(new SmallFish(-45, (int) (Math.random() * (h - 200) + 100), 80, 51, 10, 1, 1, bg.Fish0));
        fishType.add(new SmallFish(-45, (int) (Math.random() * (h - 200) + 100), 80, 80, 11, 2, 2, bg.Fish1));
        fishType.add(new SmallFish(-45, (int) (Math.random() * (h - 200) + 100), 100, 73, 12, 3, 3, bg.Fish2));
        fishType.add(new SmallFish(-45, (int) (Math.random() * (h - 200) + 100), 80, 60, 13, 4, 4, bg.Fish3));
        fishType.add(new SmallFish(-45, (int) (Math.random() * (h - 200) + 100), 130, 60, 20, 5, 5, bg.Fish4));
        fishType.add(new SmallFish(-45, (int) (Math.random() * (h - 200) + 100), 112, 88, 15, 6, 6, bg.Fish5));
        fishType.add(new SmallFish(-45, (int) (Math.random() * (h - 200) + 100), 120, 70, 15, 7, 7, bg.Fish6));
        while (true){
            time++;
            if (time % 10 == 0){
                int level = (int) (Math.random() * 7);
                Fish fish = fishType.get(level);
                fishes.add(new SmallFish(fish.x, (int) (Math.random() * (h - 200) + 100), fish.w, fish.h, fish.speed, fish.grade, fish.cnt, fish.img));
            }
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g){
        offScreenImage = createImage(w, h);
        Graphics gImage = offScreenImage.getGraphics();
        if (state == BEGIN){
            bg.paintSelf(gImage);
            gImage.setColor(Color.GREEN);
            gImage.setFont(new Font("宋体", Font.BOLD, 60));
            gImage.drawString("开始游戏", w / 2, h / 2);
        }else if (state == RUN){
            bg.paintSelf(gImage);
            for (int i = 0; i < fishes.size(); i++) {
                Fish fish = fishes.get(i);
                fish.x += fish.speed;
                if (fish.x < -100 || fish.x > w + 100){
                    fish.isLive = false;
                }
                if (!fish.isLive) {
                    fishes.remove(i);
                    i--;
                    continue;
                }
                fish.paintSelf(gImage);
            }
            for (Fish fish : fishes){
                fish.x += fish.speed;
                fish.paintSelf(gImage);
            }
            myFish.move();
            myFish.paintSelf(gImage);
            for (Fish fish : fishes){
                myFish.eat(fish);
            }
            if (!myFish.isLive){
                state = FINAL;
            }
            gImage.setColor(Color.BLACK);
            gImage.setFont(new Font("宋体", Font.BOLD, 60));
            gImage.drawString("分数：" + myFish.cnt, 100, 100);
            gImage.drawString("等级：" + myFish.grade, 500, 100);
        }else if (state == STOP){
            bg.paintSelf(gImage);
            gImage.setColor(Color.RED);
            gImage.setFont(new Font("宋体", Font.BOLD, 60));
            gImage.drawString("暂停", w / 2, h / 2);
        }else if (state == FINAL){
            bg.paintSelf(gImage);
            gImage.setColor(Color.RED);
            gImage.setFont(new Font("宋体", Font.BOLD, 60));
            gImage.drawString("失败", w / 2, h / 2);
        }else if (state == WIN){
            bg.paintSelf(gImage);
            gImage.setColor(Color.RED);
            gImage.setFont(new Font("宋体", Font.BOLD, 60));
            gImage.drawString("胜利", w / 2, h / 2);
        }
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
