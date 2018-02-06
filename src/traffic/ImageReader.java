package traffic;

import java.awt.*;
import java.awt.image.*;
import javax.swing.JPanel;

/**
 * Trida {@code ImageReader} vykresluje obrazky
 *
 * @author  Mukanova Zhanel
 * @version 1.00.0000 — 2017-10-31
 */

public class ImageReader extends JPanel{

    /** Vytvori promennou predstavujici obrazek pozadi */
    private BufferedImage img;

    /**
     * Vytvori pozadi se zadanym obrazkem 
     * @param img obrazek
     */
    ImageReader(BufferedImage img) {
        this.img = img;
    }

    /**
     * Vykresli Obrazek
     */
    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(img,0,0,null);
    }

    /**
     * Vymeni obrazek na novy
     * @param img novy obrazek
     */
    public void setImg(BufferedImage img)
    {
        this.img = img;
    }
}
