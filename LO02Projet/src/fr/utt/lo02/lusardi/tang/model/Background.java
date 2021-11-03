package fr.utt.lo02.lusardi.tang.model;

import javax.swing.*;
import java.awt.*;
/**
 * Arriere de l'interface graphique
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class Background extends JPanel {
    private Image image;
    private int x;
    private int y;
    private int w;
    private int h;

    public Background(Image image, int x, int y, int w, int h) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Background(){
        image = new ImageIcon("src/image/image.jpg").getImage();
        x=0;
        y=0;
        w= image.getWidth(null);
        h = image.getHeight(null);
        setBounds(x,y,w,h);
    }

    public void paint(Graphics g) {
        g.drawImage(this.image,x,y,x+w,y+w,0,0,image.getWidth(null),image.getHeight(null),null);
    }
}