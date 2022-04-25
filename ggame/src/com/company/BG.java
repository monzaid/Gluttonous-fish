package com.company;

import javax.swing.*;
import java.awt.*;

public class BG {
    Toolkit tk = Toolkit.getDefaultToolkit();
    Image bg = tk.getImage("image\\bgg.jpg");
    Image Fish0 = tk.getImage("image\\Fish0.jpg");
    Image Fish1 = tk.getImage("image\\Fish1.jpg");
    Image Fish2 = tk.getImage("image\\Fish2.jpg");
    Image Fish3 = tk.getImage("image\\Fish3.jpg");
    Image Fish4 = tk.getImage("image\\Fish4.jpg");
    Image Fish5 = tk.getImage("image\\Fish5.jpg");
    Image Fish6 = tk.getImage("image\\Fish6.jpg");
    Image MMyFish = tk.getImage("image\\MyFish.jpg");
    public void paintSelf(Graphics g){
        g.drawImage(bg, 0, 0, null);
    }
    public void paintSelf1(Graphics g){
        g.drawImage(bg, 100, 0, null);
    }

}
