package Ejercicio04;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Ficha extends Rectangle{
    public static final int DIMENSION = 48;
    boolean mover = true;
    int posX, posY;
    Image imagen;
    
    public Ficha(Image img, int posX, int posY){
        super(0,0,DIMENSION,DIMENSION);
        imagen = img;
        x = posX;
        y = posY;
    }
    
    public void paint(Graphics g, Frame f){
        g.drawImage(imagen, x, y, f);
    }
    
    public void update(){
        
    }
}
