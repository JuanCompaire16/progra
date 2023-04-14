
package Ejercicio01;

import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Caminando extends Frame implements Runnable{
    public static final int FILAS = 3;
    public static final int COLUMNAS = 4;
    public static final int ANCHURA = 400;
    public static final int ALTURA = 400;
    Thread animacion;
    Image imagen;
    Graphics noseve;
    Image fotogramas[][];
    String elementos[] = {"Guerrillero/g", "Hampon/h", "Vaquero/v"};
    Personaje persona;
    
    public static void main(String arg[]){
        Caminando app = new Caminando();
    }
    
    public Caminando(){
        super("Camina");
        init();
        start();
    }

    public void init() {
        this.pack();
        this.setSize(ANCHURA,ALTURA);
        this.setVisible(true);
        
        imagen = this.createImage(ANCHURA,ALTURA);
        noseve = imagen.getGraphics();
        fotogramas = new Image[FILAS][COLUMNAS];
//        for(int i=0; i < FILAS; i++)
//            for(int j=0; j < COLUMNAS; j++)
//                fotogramas[i][j] = getImage(getCodeBase(),"Ejercicio01/Sprites/" + elementos[i] + (j+1) + ".gif");
        try{
            for(int i = 0; i < FILAS ; i++)
                for(int j = 0; j < COLUMNAS; j++)
                    fotogramas[i][j] = ImageIO.read(new File("C://Users/marco/OneDrive/Escritorio/FP/Programación/3Ev/TerceraEvaluacion/src/main/java/Ejercicio01/Sprites/" + elementos[i] + (j + 1) + ".gif"));
        }catch(IOException e){}
        persona = new Personaje(fotogramas[0]);
    }

    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }
    
    public void paint(Graphics g){
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, ANCHURA, ALTURA);
        
        persona.paint(noseve, this);
        
        g.drawImage(imagen,0,0,this);
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    public void run(){
        while(true){
            persona.update();
            repaint();
            try{
                Thread.sleep(150);
            }catch(InterruptedException e){}
        }
    }
    
    public boolean keyDown(Event ev, int tecla){
        switch(tecla){
            case 103,71:
                persona.setImagenes(fotogramas[0]);
                break;
            case 104,72:
                persona.setImagenes(fotogramas[1]);
                break;
            case 118,86:
                persona.setImagenes(fotogramas[2]);
                break;
        }
        if(tecla == 27)
            System.exit(0);
        return true;
    }
}