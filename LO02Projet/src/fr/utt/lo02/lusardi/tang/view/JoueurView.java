package fr.utt.lo02.lusardi.tang.view;
import javax.swing.*;

import fr.utt.lo02.lusardi.tang.defaultpakage.Card;
import fr.utt.lo02.lusardi.tang.defaultpakage.Player;
import fr.utt.lo02.lusardi.tang.defaultpakage.VirtualPlayer;
import fr.utt.lo02.lusardi.tang.model.CardModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.awt.Color.PINK;

/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class JoueurView extends JPanel{
    private JPanel joueurPanel;
    private boolean isOver;
    private Player player;
    private ArrayList<Card> jest;
    protected ArrayList<Card> cardOffer;
    private int choix;
    JLabel nom;

    public JoueurView(){
        nom = new JLabel();
        choix = 0;
        joueurPanel=new JPanel();
        isOver=false;
        player=null;
        jest=null;
        cardOffer=new ArrayList<>();
    }

    /**
     * Creation des panels pour chaque joueur, dont la position est déterminee par l'id du joueur 
     * 
     * @param player joueurs virtuels et physiques
     */
    public JoueurView(Player player){
        this.player=player;
        jest=player.getJest();
        cardOffer=player.getCardOffer();

        //Initialiser un panel rose dont la position est déterminée par l'ID du joueur entré
        joueurPanel=new JPanel(new BorderLayout());
        joueurPanel.setVisible(true);
        setOpaque(true);
        joueurPanel.setBackground(PINK);
        joueurPanel.setBounds(10+player.getId()*250,170,220,250);
        nom = new JLabel();
        nom.setText(player.getName());
        nom.setBounds(70+250*player.getId(),40,220,50);

        joueurPanel.add(nom,BorderLayout.NORTH);
    }

    public void afficherCarteOffer(){

        //Creer un nouveau panel
        int intervalle =100;
        JPanel cardPanel = new JPanel(new GridLayout(1,2));
        cardPanel.setBounds(0,0,200,200);

        //Placer la carte sur le panel
        for(int i=0;i<player.getCardOffer().size();i++){
            CardModel cardModel = new CardModel(player.getCardOffer().get(i), 20+i*intervalle, 180);
            cardPanel.add(cardModel);
        }
        cardPanel.setOpaque(false);

        //Mettre le panel sur la boîte rose
        joueurPanel.add(cardPanel,BorderLayout.CENTER);

        //Ajoutez la barre de sélection au-dessus de la carte. Confirmeur est au-dessus. Deux options sont ci-dessous
        JPanel bottonPanel=new JPanel(new BorderLayout());

        JPanel radioBtnPanel=new JPanel();
        radioBtnPanel.setLayout(new GridLayout(1,2));
        JRadioButton radioBtn1=new JRadioButton("cette carte");
        radioBtnPanel.add(radioBtn1);
        JRadioButton radioBtn2=new JRadioButton("cette carte");
        radioBtnPanel.add(radioBtn2);
        ButtonGroup group=new ButtonGroup();
        group.add(radioBtn1);
        group.add(radioBtn2);
        bottonPanel.add(radioBtnPanel,BorderLayout.NORTH);

        JButton btn_confirmer = new JButton("confirmer");
        bottonPanel.add(btn_confirmer,BorderLayout.SOUTH);

        joueurPanel.add(bottonPanel,BorderLayout.SOUTH);
        if(!(player instanceof VirtualPlayer)){
            btn_confirmer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (radioBtn1.isSelected()) choix = 0;
                    if (radioBtn2.isSelected()) choix = 1;
                    if (radioBtn1.isSelected() || radioBtn2.isSelected()) {
                        isOver = true;
                        System.out.println(choix);
                    }
                }
            });
        }
        else
            isOver = true;
        choix = 0;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public int getChoix() {
        return choix;
    }

    public JPanel getJoueurPanel() {
        return joueurPanel;
    }

    public boolean isOver(){
        return isOver;
    }
}