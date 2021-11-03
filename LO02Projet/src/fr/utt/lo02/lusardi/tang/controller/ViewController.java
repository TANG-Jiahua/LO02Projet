package fr.utt.lo02.lusardi.tang.controller;
import javax.swing.*;

import fr.utt.lo02.lusardi.tang.defaultpakage.Card;
import fr.utt.lo02.lusardi.tang.defaultpakage.Player;
import fr.utt.lo02.lusardi.tang.defaultpakage.Result;
import fr.utt.lo02.lusardi.tang.model.Background;
import fr.utt.lo02.lusardi.tang.model.CardModel;
import fr.utt.lo02.lusardi.tang.view.CardOfferView;
import fr.utt.lo02.lusardi.tang.view.ChoixView;
import fr.utt.lo02.lusardi.tang.view.FinDuJeuView;
import fr.utt.lo02.lusardi.tang.view.JoueurView;
import fr.utt.lo02.lusardi.tang.view.StartRequireView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class ViewController{

    private Background background;
    private JFrame frame;
    private JButton btn_start;
    private CardModel cardRef;
    private static JLayeredPane layeredPane;
    private static final int HEIGHT = 500;
    private static final int WIDTH = 1000;
    private ChoixView choixView;
    private FinDuJeuView finDuJeuView;
    private static int ref_x;
    private static int ref_y;
    private static int ref_h;
    private static int ref_w;
    private ArrayList<JoueurView> joueurViewgroup;
    private JoueurView joueurView;
    private CardOfferView cardOfferView;
    private JPanel jest;


    private StartRequireView startRequireView;

    public static int nbJoueur;

    public ChoixView getChoixView() {
        return choixView;
    }

    public ViewController(){
        cardOfferView=new CardOfferView();
        joueurViewgroup=new ArrayList<>();
        joueurView=new JoueurView();
        cardRef=new CardModel((new ImageIcon("src/image/cardRef.png").getImage()));
        choixView=new ChoixView();
        startRequireView = new StartRequireView();
        background = new Background();
        btn_start=new JButton("start");
        btn_start.setVisible(false);
        btn_start.setBounds(WIDTH/2-50,HEIGHT/2-25,100,50);
        frame = new JFrame();
        frame.setSize(WIDTH,HEIGHT);
        layeredPane=new JLayeredPane();
        frame.setContentPane(layeredPane);
        layeredPane.add(background,1,1);
        layeredPane.add(btn_start,2,2);
        layeredPane.add(startRequireView.getPanel(),2,3);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    public void initCartes(ArrayList<Card> cartes){
        ArrayList<CardModel> cardModels=new ArrayList<>();
        for(Card c:cartes) {
            CardModel cm=new CardModel(new ImageIcon(c.getImageLocation()).getImage(),c);
            cardModels.add(cm);
        }
    }

    public void doStart(){
        btn_start.setVisible(true);
        btn_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPane.getComponent(layeredPane.getIndexOf(btn_start)).setVisible(false);
                layeredPane.getComponent(layeredPane.getIndexOf(startRequireView.getPanel())).setVisible(true);
            }
        });

        while (!startRequireView.isOver())
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nbJoueur=getStartRequireView().getNbJoueurs();

    }

    public void initChoixView(){
        layeredPane.add(choixView.getPanel(),10,3);
        choixView.setVisible(true);
    }

    public void doChoixView(){

        choixView.getPanel().setVisible(true);
        while (!choixView.isOver())
        {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cardRef.setPosition(WIDTH/2 - cardRef.getW()/2,HEIGHT/2 - cardRef.getH()/2 - 150);
        cardRef.setVisible(true);
        ref_x = cardRef.getX();
        ref_y = cardRef.getY();
        ref_w = cardRef.getW();
        ref_h = cardRef.getH();
        cardRef.setVisible(false);
    }

    public void afficherTrophie(Card[] trophies){
        int intervalle =10;
        int x=ref_x-ref_w-intervalle;
        int index=0;
        for(Card c: trophies){
            CardModel cardModel=new CardModel(c,300+index*150,ref_y);
            layeredPane.add(cardModel,10,index++);
            x=ref_x+ref_w+10;
        }
    }

    /**
     * 
     * @param player joueur 
     */
    public void afficherCarteOffer(Player player){
        int choix=0;
        for (Component component: layeredPane.getComponentsInLayer(3))
        {
            layeredPane.remove(component);
        }
        JoueurView joueurView = joueurViewgroup.get(player.getId());
        joueurView.afficherCarteOffer();
        choix=joueurView.getChoix();
        layeredPane.add(joueurView.getJoueurPanel(),3,1);
        while (!joueurView.isOver())
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        joueurView.setOver(false);
    }

    public int getChoix(Player player){
        int choix = joueurViewgroup.get(player.getId()).getChoix();
        System.out.println("-----------------------------");
        System.out.println("choix: " + choix);
        return choix;
    }

    public void initJoueurView(Player[] players){

        for(int i=0;i<ViewController.nbJoueur;i++){
            JoueurView jv=new JoueurView(players[i]);
            joueurViewgroup.add(jv);
            layeredPane.add(jv.getJoueurPanel(),3,1);
        }
    }

    public void initOfferView(Player[] players){
        Player[] players1=new Player[players.length];
        for(Player p:players){
            players1[p.getId()]=p;
        }
        players=players1;

        cardOfferView=new CardOfferView(players);
        layeredPane.add(cardOfferView.getOfferPanel(),11,2);
    }

    public int[] choisirCarteOffer(Player p){
         int[] choix=new int[2];

         cardOfferView.afficherButton(p);
         layeredPane.revalidate();
         layeredPane.repaint();
          while (!cardOfferView.isOver())
          {
              try {
                  Thread.sleep(500);
              } catch (InterruptedException e) {
                   e.printStackTrace();
              }
          }

          choix[0]=cardOfferView.getChoix()/2;
          choix[1]=cardOfferView.getChoix()%2;

          cardOfferView.getOfferPanel().setVisible(false);

        System.out.println("----------choix"+choix[0]+" "+choix[1]);
          return choix;
    }

    public StartRequireView getStartRequireView(){return startRequireView;}

    public void removeCarteOffer(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("remove card");

        for (JoueurView joueurView : joueurViewgroup)
        {
            layeredPane.remove(joueurView.getJoueurPanel());
        }
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public void removeCarteBalaBalaView(){
        System.out.println("---------remove card");
        System.out.println(layeredPane.getComponentsInLayer(11)[0].toString());
        layeredPane.remove(cardOfferView.getOfferPanel());
        cardOfferView.getOfferPanel().setVisible(false);
        cardOfferView.getOfferPanel().revalidate();
        cardOfferView.getOfferPanel().repaint();
        layeredPane.revalidate();
        layeredPane.repaint();
    }


    private void initFinDuJeu(){
        layeredPane.add(finDuJeuView.getPanel(),10,10);
    }

    /**
     * 
     * @param resultat score du joueur 
     */
    public void doFinDuJeu(Object[][]resultat){
        layeredPane.add(finDuJeuView.getPanel(),100,10);
        for(int i=0;i<resultat.length;i++){
            finDuJeuView.addData((String)resultat[i][0],(Integer)resultat[i][0]);
        }
        finDuJeuView.getPanel().setVisible(true);

        while (!finDuJeuView.isNext()){
            try{
                System.out.println("wait");
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void afficherJest(){
        layeredPane.add(jest);
    }

    
    /**
     * 
     * @param results score du joueur 
     */
    public void terminer(ArrayList<Result> results){
        layeredPane.removeAll();
        layeredPane.add(background);

        JPanel finDeJeu=new JPanel(new GridLayout(2,1));
        finDeJeu.setVisible(true);
        finDeJeu.setBounds(0,0,500,200);

        JPanel notePanel=new JPanel(new GridLayout(nbJoueur,1));
        for(Result r:results){
            JLabel note=new JLabel("nom: "+r.getNom()+" =====  note: "+r.getJestValue());
            notePanel.add(note);
        }
        finDeJeu.add(notePanel);

        JPanel cardPanel=new JPanel(new GridLayout(nbJoueur,1));
        for(int i=0;i<nbJoueur;i++){
            JPanel cardP=new JPanel(new GridLayout(1,2));
            JLabel name=new JLabel(results.get(i).getNom());
            cardP.add(name);

            JPanel cards=new JPanel(new GridLayout(4,1));
            int index=0;
            for(Card c:results.get(i).getJest()){
                CardModel cardModel=new CardModel(c,100+index*150,ref_y);
                cards.add(cardModel);
            }
            cardP.add(cards);
            cardPanel.add(cardP);
        }

        layeredPane.add(finDeJeu,40,1);

    }
}
