package Ejercicio03;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

public class Archer {
    Image image;
    int posY;
    
    public Archer(Image img){
        image = img;
        posY = 150;
    }
    
    public void paint(Graphics g, Frame f){
        g.drawImage(image, 0, posY, f);
    }
    
    public void update(int py){
        posY = py;
        if(posY < 30) 
            posY = 30;
        if(posY > (600 - 6 - image.getHeight(null))) 
            posY = (600 - 6 - image.getHeight(null));
    }
}
