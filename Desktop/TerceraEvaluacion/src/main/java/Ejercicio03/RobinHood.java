package Ejercicio03;

import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class RobinHood extends Frame implements Runnable{
    public static final int FWIDTH = 800;
    public static final int FHEIGHT = 600;
    public static final int NUM_BALLOONS = 8;
    public static final int TIME = 10;
    int chrono = 0;
    Thread animation;
    Image image;
    Graphics noseve;
    Image robinImg, arrowImg;
    Image balloonImgs[];
    Archer archer;
    ArrayList<Balloon> balloons;
    ArrayList<Arrow> arrows;
    
    public static void main(String arg[]){
        RobinHood app = new RobinHood();
    }
    
    public RobinHood(){
        super("RobinHood");
        init();
        start();
    }

    public void init(){
        this.pack();
        this.setSize(FWIDTH,FHEIGHT);
        this.setVisible(true);
        
        image = this.createImage(FWIDTH,FHEIGHT);
        noseve = image.getGraphics();
        
        balloonImgs = new Image[NUM_BALLOONS];
        try{
            robinImg = ImageIO.read(new File("C://Users/marco/OneDrive/Escritorio/FP/Programación/3Ev/TerceraEvaluacion/src/main/java/Ejercicio03/imagenes/robin.png"));
            arrowImg = ImageIO.read(new File("C://Users/marco/OneDrive/Escritorio/FP/Programación/3Ev/TerceraEvaluacion/src/main/java/Ejercicio03/imagenes/flecha.png"));
            for(int i=0; i < NUM_BALLOONS; i++)
                balloonImgs[i] = ImageIO.read(new File("C://Users/marco/OneDrive/Escritorio/FP/Programación/3Ev/TerceraEvaluacion/src/main/java/Ejercicio03/imagenes/globo" + i + ".png" ));
        }catch(IOException e){}
        
        archer = new Archer(robinImg);
        balloons = new ArrayList<Balloon>();
        balloons.add(new Balloon(balloonImgs[(int)(Math.random()*8)]));
        arrows = new ArrayList<Arrow>();

    }

    public void start() {
        animation = new Thread(this);
        animation.start();
    }
    
    public void paint(Graphics g){
        noseve.setColor(Color.WHITE);
        noseve.fillRect(0, 0, FWIDTH, FHEIGHT);
        
        archer.paint(noseve,this);
        
        for(Balloon bal : balloons)
            bal.paint(noseve, this);
        
        for(Arrow ar : arrows)
            ar.paint(noseve, this);
        
        g.drawImage(image,0,0,this);
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    public void run(){
        while(true){
            //Cada cierto tiempo metemos un globo a la lista
            chrono += TIME;
            if(chrono >= 600){
                balloons.add(new Balloon(balloonImgs[(int)(Math.random()*8)]));
                chrono = 0;
            }
            //Actualizamos la posición de todos los globos
            for(Balloon bal : balloons)
                bal.update();
            
            //Eliminamos de la lista el globo que haya llegado arriba
            if(balloons.get(0).y < -100)
                balloons.remove(0);
            
            //Tenemos que mover las flechas hacia la derecha
            for(Arrow ar : arrows)
                ar.update(balloons);
            
            //Eliminamos de la lista la flecha que ya no se vea en la pantalla
            if((!arrows.isEmpty()) && (arrows.get(0).x > 800))
                arrows.remove(0);
                      
            //Controlamos si alguna flecha rompe algún globo
//            for(Arrow ar : arrows)    
//                for(Balloon bal : balloons)
//                    if(bal.contains(ar)){
//                        balloons.remove(bal);
//                        break;
//                    }
                
            repaint();
            try{
                Thread.sleep(TIME);
            }catch(InterruptedException e){}
        }
    }
    public boolean mouseDown(Event ev, int x, int y){
        arrows.add(new Arrow(arrowImg,archer.posY+65));
        return true;
    }
    
    public boolean mouseMove(Event ev, int x, int y){
        archer.update(y-65);
        repaint();
        return true;
    }
    
    public boolean keyDown(Event ev, int tecla){
        if(tecla == 27)
            System.exit(0);
        if(tecla == 32)
            arrows.add(new Arrow(arrowImg,archer.posY+65));
        return true;
    }
    
    
}