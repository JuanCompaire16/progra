package Ejercicio05;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Casilla extends Rectangle{
    public static final int DIM = 60;
    int valor;
    Color color;
    
    public Casilla(int posX, int posY, int v, Color c){
        super(posX, posY, DIM, DIM);
        color = c;
        valor = v;
    }

    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
        g.setColor(color);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g.drawString(""+valor, x+20, y+40);
    }
}
