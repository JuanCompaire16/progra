package Ejercicio05;

import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

public class Ruleta extends Frame implements Runnable{
    public static final int FWIDTH = 500;
    public static final int FHEIGHT = 800;
    public static final int NUM_CASILLAS = 36;
    public static final int FILAS = 12;
    public static final int COLUMNAS = 3;
    Thread animacion;
    Image imagen;
    Graphics noseve;
    public Casilla tablero[][];
    public int rojos[] = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,29,31,33,35};
    List<Integer> lRojos;
    
    public static void main(String arg[]){
        Ruleta app = new Ruleta();
    }
    
    public Ruleta(){
        super("Ruleta");
        init();
        start();
    }

    public void init(){
        this.pack();
        this.setSize(FWIDTH,FHEIGHT);
        this.setVisible(true);
        
        imagen = this.createImage(FWIDTH,FHEIGHT);
        noseve = imagen.getGraphics();
        
        lRojos = new ArrayList<Integer>();
        for(int i=0; i < rojos.length; i++)
            lRojos.add(new Integer(rojos[i]));
        
        tablero = new Casilla[FILAS][COLUMNAS];
        for(int i=0; i < FILAS; i++)
            for(int j=0; j < COLUMNAS; j++)
                if(lRojos.contains(new Integer((i*COLUMNAS)+j+1)))
                    tablero[i][j] = new Casilla(18+j * Casilla.DIM, 40+i * Casilla.DIM, (i*COLUMNAS)+ j + 1, Color.RED);
                else
                    tablero[i][j] = new Casilla(18+j * Casilla.DIM, 40+i * Casilla.DIM, (i*COLUMNAS)+ j + 1, Color.BLACK);
       
    }
    
    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }
    
    public void paint(Graphics g){
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, FWIDTH, FHEIGHT);
        
        for(int i=0; i < FILAS; i++)
            for(int j=0; j < COLUMNAS; j++)
               tablero[i][j].paint(noseve);
        
        g.drawImage(imagen,0,0,this);
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    public void run(){
        while(true){
            repaint();
            try{
                Thread.sleep(0);
            }catch(InterruptedException e){
            }
        }
    }
  
    public boolean keyDown(Event ev, int tecla){
        if(tecla == 27)
            System.exit(0);
        return true;
    }
}