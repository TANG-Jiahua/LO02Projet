package fr.utt.lo02.lusardi.tang.model;
import javax.swing.*;

import fr.utt.lo02.lusardi.tang.defaultpakage.Card;

import java.awt.*;
/**
 * Modele de carte
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class CardModel extends JPanel {
    private Card carte;
    private Image image;
    private int x;
    private int y;
    private int w;
    private int h;
    private static final int SCALE = 10;

    public CardModel(Image image, Card carte) {
        this.carte = carte;
        this.x=0;
        this.y=0;
        this.image = image;
        this.w = image.getWidth(null)/SCALE;
        this.h = image.getHeight(null)/SCALE;
        setBounds(x,y,w,h);
    }

    public CardModel(Card carte,int x,int y) {
        this.carte = carte;
        this.x=x;
        this.y=y;
        this.image = new ImageIcon(carte.getImageLocation()).getImage();
        this.w = image.getWidth(null)/SCALE;
        this.h = image.getHeight(null)/SCALE;
        setBounds(x,y,w,h);
    }


    public CardModel(Image image) {
        this.x=0;
        this.y=0;
        this.image = image;
        this.w = image.getWidth(null)/SCALE;
        this.h = image.getHeight(null)/SCALE;
        setBounds(x,y,w,h);
    }


    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        setBounds(x,y,w,h);
        System.out.println(x+"  "+y);
    }

    public int getW()
    {
        return w;
    }
    public int getH()
    {
        return h;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.image,0,0,w,h,0,0,image.getWidth(null),image.getHeight(null),null);

    }

    public void setImage(Image image) {
        this.image = image;
    }
}
