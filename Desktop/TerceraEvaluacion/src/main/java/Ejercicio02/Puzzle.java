package Ejercicio02;

import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Puzzle extends Frame implements Runnable{
    public static final int NUM_PIEZAS = 25;
    public static final int DIM = 5;
    public static final int ANCHURA = 700;
    public static final int ALTURA = 400;
    Thread animacion;
    Image imagen;
    Graphics noseve;
    Image imagenes[];
    Pieza piezas[];
    Pieza actual;
    Rectangle cuadricula[][];
    
    public static void main(String arg[]){
        Puzzle app = new Puzzle();
    }
    
    public Puzzle(){
        super("Puzzle");
        init();
        start();
    }

    public void init() {
        this.pack();
        this.setSize(ANCHURA,ALTURA);
        this.setVisible(true);
        
        imagen = this.createImage(ANCHURA,ALTURA);
        noseve = imagen.getGraphics();
        
        imagenes = new Image[NUM_PIEZAS];
        piezas = new Pieza[NUM_PIEZAS];
        
        try{
            for(int i = 0; i < NUM_PIEZAS ; i++){    
                imagenes[i] = ImageIO.read(new File("C://Users/marco/OneDrive/Escritorio/FP/Programación/3Ev/TerceraEvaluacion/src/main/java/Ejercicio02/directorioImagenes/" + (i+1) + ".png"));
                piezas[i] = new Pieza(imagenes[i],i);
            }
        }catch(IOException e){}
        
        cuadricula = new Rectangle[DIM][DIM];
        for(int i=0; i < DIM; i++)
            for(int j=0; j < DIM; j++)
                cuadricula[i][j] = new Rectangle(38+(i*Pieza.DIMENSION), 60+(j*Pieza.DIMENSION), Pieza.DIMENSION, Pieza.DIMENSION);
    }

    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }
    
    public void paint(Graphics g){
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, ANCHURA, ALTURA);
        
        noseve.setColor(Color.GRAY);
        for(int i=0; i < DIM; i++)
            for(int j=0; j < DIM; j++)
                noseve.drawRect(cuadricula[i][j].x,cuadricula[i][j].y, cuadricula[i][j].width, cuadricula[i][j].height);
        
        for(Pieza pieza : piezas)
            pieza.paint(noseve, this);
        
        g.drawImage(imagen,0,0,this);
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    public void run(){
        while(true){
            repaint();
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
            }
        }
    }
    
    public boolean mouseDown(Event ev, int x, int y){
        for(int i=0; i < NUM_PIEZAS; i++)
            if(piezas[i].contains(x,y)){
                actual = piezas[i];
            }
        return true;
    }
    
    public boolean mouseDrag(Event ev, int x, int y){
        if(actual != null){
            actual.update(x, y);
            repaint();
        }
        return true;
    }
    
    public boolean mouseUp(Event ev, int x, int y){
        if(actual != null){
            for(int i=0; i < DIM; i++)
                for(int j=0; j < DIM; j++)
                    if((actual.intersects(cuadricula[j][i])) && (actual.getPosicion() == ((i*DIM)+j))){
                        actual.x = 38+j*Pieza.DIMENSION;
                        actual.y = 60+i*Pieza.DIMENSION;
                        actual.setMover(false);
                    }
//                if(cuadricula[i][j].contains(x,y)){
//                    actual.x = cuadricula[i][j].x;
//                    actual.y = cuadricula[i][j].y;
//                }
            repaint();
            actual = null;
        }
        return true;
    }
    
    public boolean keyDown(Event ev, int tecla){
        if(tecla == 27)
            System.exit(0);
        return true;
    }
    
    
}