package Ejercicio01;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

public class Personaje {
    private Image imagenes[];
    int actual = 0;
    
    public Personaje(Image[] imagenes){
        this.imagenes = imagenes;
    }

    public Image[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(Image[] imagenes) {
        this.imagenes = imagenes;
    }
    
    public void paint(Graphics g, Frame f){
        g.drawImage(imagenes[actual], 70, 70, 200, 300, f);
    }
    
    public void update(){
        actual = (actual + 1) % imagenes.length;
    }
}
