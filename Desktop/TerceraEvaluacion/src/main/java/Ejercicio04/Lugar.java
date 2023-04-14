package Ejercicio04;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Lugar extends Rectangle{
    public static final int DIMENSION = 48;
    private Image imagen;
    private int valor;
    
    public Lugar(Image img, int posX, int posY, int v){
        super(posX, posY, DIMENSION, DIMENSION);
        imagen = img;
        valor = v;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public void paint(Graphics g, Frame f){
        if(imagen != null)
            g.drawImage(imagen, x, y, f);
    }
}
