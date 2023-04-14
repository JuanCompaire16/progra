package Ejercicio03;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Balloon extends Rectangle{
    public static final int WIDTH = 50;
    public static final int HEIGHT = 70; 
    Image image;
    int velY;
    
    public Balloon(Image img){
        super((int)(Math.random() * 70) + 650, 600,WIDTH,HEIGHT);
        image = img;
        velY = (int)(Math.random() * 2) + 2;
    }
    
    public void paint(Graphics g, Frame f){
        g.drawImage(image, this.x, this.y, this.width, this.height, f);
    }
    
    public void update(){
        y -= velY;
    }
}
