package Ejercicio03;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public class Arrow extends Point{
    public static final int VELX = 8;
    Image image;

    public Arrow(Image img, int posY){
        super(70, posY);
        image = img;
    }
    
    public void paint(Graphics g, Frame f){
        g.drawImage(image, x, y, 80, 10, f);
    }
    
    public void update(ArrayList<Balloon> balloons){
        x += VELX;
        for(Balloon bal : balloons)
            if(bal.contains(this)){
                balloons.remove(bal);
                break;
            }
    }
}
