package fr.utt.lo02.lusardi.tang.view;
import javax.swing.*;
import javax.swing.text.View;

import fr.utt.lo02.lusardi.tang.controller.ViewController;
import fr.utt.lo02.lusardi.tang.defaultpakage.Player;
import fr.utt.lo02.lusardi.tang.defaultpakage.Reference;
import fr.utt.lo02.lusardi.tang.defaultpakage.VirtualPlayer;
import fr.utt.lo02.lusardi.tang.model.CardModel;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class CardOfferView extends JPanel{
    private JPanel cardOfferView;
    private JPanel offerPanel;
    private ArrayList<JPanel> ensembleCardPanel=new ArrayList<>();
    private ArrayList<JPanel> ensembleButtonPanel1=new ArrayList<>();
    private JRadioButton[] buttons=new JRadioButton[2*ViewController.nbJoueur];

    private boolean isOver;
    private Player[] players;
    private String nom;
    private JPanel[] ensembleButtonPanel=new JPanel[ViewController.nbJoueur];
    private int choix[] = {-1,-1,-1,-1};
    private int choixButton=-1;

    public JPanel getOfferPanel() {
        return offerPanel;
    }

    public CardOfferView(){
        isOver=false;
        players=null;
        cardOfferView=null;
        offerPanel=null;
        ensembleCardPanel=new ArrayList<>();
    }

    /**
     * Boites contenant les offres de carte des joueurs sur l'interface graphique 
     * 
     * @param players les joueurs virtuels et physiques
     */
    public  CardOfferView(Player[] players){

        this.players=players;
        isOver=false;

        //Initialiser le plus grand panel
        offerPanel=new JPanel(new BorderLayout());
        offerPanel.setBackground(Color.WHITE);
        offerPanel.setOpaque(true);
        offerPanel.setVisible(true);
        offerPanel.setBounds(10,170,800,250);


        Player[] players1=new Player[players.length];
        for(Player p:players) {
            players1[p.getId()] = p;
        }
        players=players1;
        for(Player p:players){


            //Creer un nouveau panel de joueur
            JPanel panelInfo=new JPanel(new BorderLayout());
            panelInfo.setOpaque(true);
            panelInfo.setVisible(true);

            //Creer un nouveau panel d'image et mettre deux cartes dessus
            JPanel panelCard=new JPanel(new GridLayout(1,2));
            panelCard.setBackground(Color.PINK);

            for(int i=0;i<2;i++){
                //Définir une carte de sauvegarde invisible
                CardModel cardModel=new CardModel(p.getCardOffer().get(i),20+i*100, 180);
                if(p.getCardOffer().get(i) instanceof Reference){
                    cardModel.setVisible(false);
                }
                if(p.getCardOffer().get(i).getVisibilite()==0){
                    cardModel.setImage(new ImageIcon("src/image/facedown.PNG").getImage());
                }

                panelCard.add(cardModel);
            }
            panelInfo.add(panelCard,BorderLayout.CENTER);

            //Nouvel emplacement de nom
            JLabel nom = new JLabel();
            nom.setText(p.getName());
            nom.setBounds(70+250*p.getId(),40,220,50);
            panelInfo.add(nom,BorderLayout.SOUTH);

            ensembleCardPanel.add(panelInfo);

        }

        JPanel panelJoueur=new JPanel(new GridLayout(1,4));
        for(JPanel jp:ensembleCardPanel){
            panelJoueur.add(jp);
        }

        offerPanel.add(panelJoueur,BorderLayout.CENTER);
    }

    public void afficherButton(Player player){


        JPanel panelbutton=new JPanel(new GridLayout(1,8));
        ButtonGroup group=new ButtonGroup();
        for(int i=0;i< 2*ViewController.nbJoueur;i++){
            JRadioButton radioButton = new JRadioButton("cette carte");
            buttons[i]=radioButton;
            group.add(buttons[i]);
            panelbutton.add(buttons[i]);
        }

        offerPanel.add(panelbutton,BorderLayout.SOUTH);

        JButton buttonConfirmer=new JButton(player.getName()+" confirmer");
        offerPanel.add(buttonConfirmer,BorderLayout.NORTH);
        if(!(player instanceof VirtualPlayer)) {
            buttonConfirmer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    for(int i=0;i<ViewController.nbJoueur*2;i++){
                        if(buttons[i].isSelected()){
                            isOver=true;
                            choixButton=i;

                        }
                    }
                }
            });
        }
        else
            isOver=true;
    }

    public int getChoix(){
        return choixButton;
    }

    public boolean isOver(){
        return isOver;
    }

}
