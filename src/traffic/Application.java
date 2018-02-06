package traffic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Trida {@code Application} je hlavni tridou projektu,
 * ktera zajistuje beh krizovatky
 *
 * @author  Mukanova Zhanel
 * @version 2.00.0000 — 2017-10-31
 */
public class Application {
    /** Okno, ve kterem bezi aplikace */
    private JFrame frame;
    /** Obrazek pozadi */
    private ImageReader img;
    /** Definice jednotlivych stavu */
    private BufferedImage orange;
    private BufferedImage black;
    private BufferedImage red1;
    private BufferedImage red;
    private BufferedImage state1;
    private BufferedImage state2;
    private BufferedImage state3;
    private BufferedImage state4;
    private BufferedImage state5;
    private BufferedImage state6;
    /** Zapnout/vypnout semafory */
    private boolean traffic = false;
    /** {@code cw1}  Emuluje zmacknuti tlacitka */
    private boolean cw1 = false;
    /** {@code cw2}  Emuluje zmacknuti tlacitka */
    private boolean cw2 = false;
    /** Intervaly */
    private long t = 3000;
    private long wait = 1000;

    /**
     * Nacte obrazky pro jednotlive stavy
     * semaforu
     * @return obrazek
     */
    private Application(){
        try {
            this.orange = ImageIO.read(new File("images/yellow.png"));
            this.black = ImageIO.read(new File("images/main.png"));
            this.red1 = ImageIO.read(new File("images/red-1.png"));
            this.red = ImageIO.read(new File("images/red.png"));
            this.state1 = ImageIO.read(new File("images/state1.png"));
            this.state2 = ImageIO.read(new File("images/state2.png"));
            this.state3 = ImageIO.read(new File("images/state3.png"));
            this.state4 = ImageIO.read(new File("images/state4.png"));
            this.state5 = ImageIO.read(new File("images/state5.png"));
            this.state6 = ImageIO.read(new File("images/state6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Postavi krizovatku v urcitem stavu na zadany cas
     * @param time  doba, ve kterou automat bude setrvavat 
     *              v jednom stavu
     */
    private void waittime(long time)
    {
        try{
            Thread.sleep(time);

        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vypnute semafory
     * Blika oranzova
     */
    private void start_traffic()
    {
        while(!this.traffic)
        {
            this.img.setImg(this.black);
            this.frame.repaint();
            this.waittime(this.wait);

            this.img.setImg(this.orange);
            this.frame.repaint();
            this.waittime(this.wait);

        }

        this.img.setImg(this.red);
        this.frame.repaint();
        waittime(this.t);

        this.img.setImg(this.red1);
        this.frame.repaint();
        waittime(this.t);
    }

    /**
     * Stav State1 
     */
    public void State1()
    {
        this.img.setImg(this.state1);

        if(this.cw1)
        {
            if(!this.cw2)
            {
                this.t = 3000;
            }

            this.cw1 = false;
        }

        this.frame.repaint();
        this.waittime(this.t);


    }


    /**
     * Stav State3 
     */
    public void State3()
    {
        this.img.setImg(this.state5);

        if(this.cw2)
        {
            if(!this.cw1)
            {
                this.t = 3000;
            }

            this.cw2 = false;
        }

        this.frame.repaint();
        this.waittime(this.t);
    }

    /**
     * Hlavni metoda aplikace
     * @param args nepouzitу
     */
    public static void main(String[]args){
        final Application app = new Application();
        // Okno
        app.frame = new JFrame("Traffic");
        // Tlacitka
        JButton crosswalk1 = new JButton("Crosswalk down");
        JButton crosswalk2 = new JButton("Crosswalk right");
        final JButton toogle = new JButton("Turn on traffic lights");
        app.img = new ImageReader(app.black);
        crosswalk1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                app.cw1 = true;
                app.waittime(1000);
                app.t = 1000;
                app.frame.repaint();
                //app.wait = 2000;


            }
        });
        crosswalk2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                app.cw2 = true;
                app.waittime(1000);
                app.t = 1000;
                app.frame.repaint();
                //app.wait = 2000;


            }
        });
        toogle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(!app.traffic)
                {
                    app.traffic = true;
                    toogle.setText("Turn off traffic lights");
                }
                else
                {
                    app.traffic = false;
                    toogle.setText("Turn on traffic lights");
                }

            }
        });
        app.img.add(toogle);
        app.img.add(crosswalk1);
        app.img.add(crosswalk2);
        app.frame.add(app.img);
        app.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.frame.setSize(835,560);
        app.frame.setVisible(true);
        while(true)
        {
            app.start_traffic();
            while(true)
            {

                app.State1();

                if(!app.traffic)
                    break;

                app.img.setImg(app.state2);
                app.frame.repaint();
                app.waittime(app.t);

                app.img.setImg(app.state3);
                app.frame.repaint();
                app.waittime(app.t);

                if(!app.traffic)
                    break;

                app.img.setImg(app.state4);
                app.frame.repaint();
                app.waittime(app.t);


                app.State3();
                if(!app.traffic)
                    break;

                app.img.setImg(app.state6);
                app.frame.repaint();
                app.waittime(app.t);


            }
        }

    }
}
