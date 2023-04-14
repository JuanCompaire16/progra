package Ejercicio04;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Tablero extends Frame implements Runnable{
    public static final int FWIDTH = 400;
    public static final int FHEIGHT = 400;
    public static final int NUM_FICHAS = 25;
    public static final int DIM = 5;
    Thread animacion;
    Image image;
    Graphics noseve;
    Image imagenes[];
    Lugar lugares[][];
    AudioInputStream acierto, error, exito;
    Clip aciertoClip, errorClip, exitoClip;
    Point hueco;
    
    public static void main(String arg[]){
        Tablero app = new Tablero();
    }
    
    public Tablero(){
        super("Tablero");
        init();
        start();
    }

    public void init(){
        this.pack();
        this.setSize(FWIDTH,FHEIGHT);
        this.setVisible(true);
        
        image = this.createImage(FWIDTH,FHEIGHT);
        noseve = image.getGraphics();
        
        imagenes = new Image[NUM_FICHAS];
        try{
            for(int i=0; i < NUM_FICHAS-1; i++){    
                imagenes[i] = ImageIO.read(new File("C://Users/marco/OneDrive/Escritorio/FP/Programación/3Ev/TerceraEvaluacion/src/main/java/Ejercicio04/botones/" + (i+1) + ".gif"));
            }  
            //imagenes[24] = null;
        }catch(IOException e){}
        
        lugares = new Lugar[DIM][DIM];
        for(int i=0; i < DIM; i++)
            for(int j=0; j < DIM; j++)
                lugares[i][j] = new Lugar(imagenes[(i*DIM)+j], j*Lugar.DIMENSION, i*Lugar.DIMENSION, (i*DIM)+j+1);
    
        /*try {
            error = AudioSystem.getAudioInputStream(new File("C://Users/marco/OneDrive/Escritorio/FP/Programación/3Ev/TerceraEvaluacion/src/main/java/Ejercicio04/sonidos/error.wav").getAbsoluteFile());
            acierto = AudioSystem.getAudioInputStream(new File("C://Users/marco/OneDrive/Escritorio/FP/Programación/3Ev/TerceraEvaluacion/src/main/java/Ejercicio04/sonidos/correct.wav").getAbsoluteFile());
            exito = AudioSystem.getAudioInputStream(new File("C://Users/marco/OneDrive/Escritorio/FP/Programación/3Ev/TerceraEvaluacion/src/main/java/Ejercicio04/sonidos/exito.wav").getAbsoluteFile());
            errorClip = AudioSystem.getClip();
            aciertoClip = AudioSystem.getClip();
            exitoClip = AudioSystem.getClip();*/
//            clip.open(error);
//            clip.start();
        /*}catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }*/
//        public void ReproducirSonido(String nombreSonido){
//            try {
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInputStream);
//                clip.start();
//            } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
//                System.out.println("Error al reproducir el sonido.");
//            }
//        }
//        @Override

//        public void actionPerformed(ActionEvent e) {
//            if(e.getSource() == boton1){
//                ReproducirSonido("src/tiro.wav");
//        }
    
        hueco = new Point(DIM-1,DIM-1);
        
        this.setLayout(new BorderLayout());
        this.add("South", new Button("Empezar"));
    }
    
    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }
    
    public void paint(Graphics g){
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, FWIDTH, FHEIGHT);
        
        for(int i=0; i < DIM; i++)
            for(int j=0; j < DIM; j++)
                lugares[i][j].paint(noseve, this);
        
        g.drawImage(image,0,0,this);
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
            }
        }
    }

    public boolean mover(Point click){
        Point desplazamiento, hasta;
        desplazamiento = new Point(delta(click.x, hueco.x),delta(click.y, hueco.y));
        if((desplazamiento.x == 0) && (desplazamiento.y == 0))
            return false;
        if((desplazamiento.x != 0) && (desplazamiento.y != 0))
            return false;
        hasta = new Point(click.x + desplazamiento.x, click.y + desplazamiento.y);
        
        if(!((hasta.x == hueco.x) && (hasta.y == hueco.y)))
            mover(hasta);
        lugares[hueco.x][hueco.y].setImagen(lugares[click.x][click.y].getImagen());
        lugares[click.x][click.y].setImagen(null);
        lugares[hueco.x][hueco.y].setValor(lugares[click.x][click.y].getValor());
        lugares[click.x][click.y].setValor(25);
        hueco.x = click.x; // hueco = click;
        hueco.y = click.y; // hueco = click;
        repaint();

        
        return true;
    }
    
    public int delta(int a, int b){
        if(a==b) return 0;
        else return ((b-a)/Math.abs(b-a));
    }
    
    public boolean mouseDown(Event ev, int x, int y){
        Point click;
        click = new Point(y/Lugar.DIMENSION, x/Lugar.DIMENSION);
        if(!mover(click)){
            //error.play
        }else{
            //acierto.play
            boolean ordenado = true;
            for(int i=0; i < DIM; i++)
                for(int j=0; j < DIM; j++)
                    if(lugares[i][j].getValor() != ((i*DIM)+j+1));
                        ordenado = false;
            if(ordenado){
                //exito.play;
            }
        }
        return true;
    }
  
    public boolean keyDown(Event ev, int tecla){
        if(tecla == 27)
            System.exit(0);
        return true;
    }
    
    public boolean action(Event ev, Object obj){
        if(ev.target instanceof Button)
            for(int i = 0; i < 100; i++)
                mover(new Point((int)(Math.random()*DIM),(int)(Math.random()*DIM)));
        return true;
    }
}