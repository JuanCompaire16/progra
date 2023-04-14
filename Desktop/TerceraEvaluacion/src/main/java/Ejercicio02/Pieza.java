package Ejercicio02;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Pieza extends Rectangle{
    public static final int DIMENSION = 60;
    private boolean mover = true;
    private int posicion;
    Image imagen;
    
    public Pieza(Image img, int pos){
        super((int)(Math.random()*(300-DIMENSION-8))+400,(int)(Math.random()*(370-DIMENSION-8))+30,DIMENSION,DIMENSION);
        imagen = img;
        posicion = pos;
    }

    public boolean isMover() {
        return mover;
    }

    public void setMover(boolean mover) {
        this.mover = mover;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    public void paint(Graphics g, Frame f){
        g.drawImage(imagen, x, y, f);
    }
    
    public void update(int posX, int posY){
        if(mover){
            x = posX - (DIMENSION/2);
            y = posY - (DIMENSION/2);
        }
    }
}
