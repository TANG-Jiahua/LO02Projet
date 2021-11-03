package fr.utt.lo02.lusardi.tang.view;
import javax.swing.*;

import fr.utt.lo02.lusardi.tang.controller.ViewController;
import fr.utt.lo02.lusardi.tang.defaultpakage.EasyStrategy;
import fr.utt.lo02.lusardi.tang.defaultpakage.Player;
import fr.utt.lo02.lusardi.tang.defaultpakage.VirtualPlayer;

import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class ChoixView extends JPanel {
    private JPanel choixPanel;
    private boolean isOver;
    public static ArrayList<Player> joueurs;
    private JPanel joueurListePanel;
    private JButton confirmer;
    private int NbJoueurPhysique;
    private int nbJoueurVirtuel;

    public static final int C_HEIGHT = 300;
    public static final int C_WIDTH = 400;

    public static ArrayList<Player> getJoueurs() {
        return joueurs;
    }

    public int getNbJoueurPhysique() {
        return NbJoueurPhysique;
    }

    public int getNbJoueurVirtuel() {
        return nbJoueurVirtuel;
    }

    public ChoixView(){
        nbJoueurVirtuel=0;
        NbJoueurPhysique=0;

        isOver=false;
        joueurs=new ArrayList<>();

        //Initialisation du plus grand panel entier
        choixPanel=new JPanel(new BorderLayout());
        choixPanel.setVisible(false);
        choixPanel.setBounds(50,50,400,350);
        choixPanel.setBackground(Color.gray);

        //Initialiser les informations du joueur: quantité, nom
        for(int i=0;i<ViewController.nbJoueur;i++){
            String s="No."+(i+1);
            joueurs.add(new Player(s));
        }

        //Les informations pour chaque joueur sont organisées en quatre lignes et une colonne, placées au milieu du panel
        joueurListePanel=new JPanel(new GridLayout(4,1));
        choixPanel.add(joueurListePanel,BorderLayout.CENTER);

        //Titre en haut du panel
        JPanel titlePanel=new JPanel(new BorderLayout());
        choixPanel.add(titlePanel,BorderLayout.NORTH);

        //Définir la barre de titre, le plus à gauche est "Liste de joueurs" et le plus à droite est ajouter/supprimer des joueurs
        JLabel title=new JLabel("Liste de joueurs");
        titlePanel.add(title,BorderLayout.WEST);

        JPanel titleBoutonPanel =new JPanel(new BorderLayout());
        JButton addP=new JButton("+JP");
        JButton addV=new JButton("+JV");
        JButton minus=new JButton("-");
        titleBoutonPanel.add(addP,BorderLayout.WEST);
        titleBoutonPanel.add(addV,BorderLayout.CENTER);
        titleBoutonPanel.add(minus,BorderLayout.EAST);

        titlePanel.add(titleBoutonPanel,BorderLayout.EAST);

        //Mettre à jour le panneau
        updateListePanel();

        //Ajoutez des boutons de joueurs physiques 
        addP.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (joueurs.size() < ViewController.nbJoueur) {
                   NbJoueurPhysique++;
                   System.out.println("add JP");
                   joueurs.add(new Player("Joueur No." + NbJoueurPhysique));
                   joueurs.get(joueurs.size()-1).setId(joueurs.size()-1);
                   System.out.println("");
                   updateListePanel();
               }
           }
           });

        //Ajouter des boutons de joueur virtuel
        addV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (joueurs.size() < ViewController.nbJoueur) {
                    nbJoueurVirtuel++;
                    System.out.println("add JV");
                    joueurs.add(new VirtualPlayer("Virtuel joueur No." + nbJoueurVirtuel,new EasyStrategy()));
                    joueurs.get(joueurs.size()-1).setId(joueurs.size()-1);
                    updateListePanel();
                }
            }
        });

        //Supprimer le joueur
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (joueurs.size() > 1) {
                    if (joueurs.get(joueurs.size()-1)instanceof VirtualPlayer)
                    {
                        nbJoueurVirtuel--;
                    }
                    else
                    {
                        NbJoueurPhysique--;
                    }
                    joueurs.remove(joueurs.size() - 1);
                    updateListePanel();
                }
            }
        });


        joueurListePanel.setBounds(50,50,50,100);

        confirmer=new JButton("confirmer");
        choixPanel.add(confirmer,BorderLayout.SOUTH);
        confirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    isOver = true;
                    System.out.println(isOver);
                choixPanel.setVisible(false);
            }
        });
    }

    private void updateListePanel() {
        System.out.println("nb joueurs: "+joueurs.size());
        joueurListePanel.removeAll();
        for(Player p: joueurs){
            JPanel joueurPanel=new JPanel(new GridLayout(1,3));
            joueurPanel.setBounds(0,0,200,50);

            JLabel jname = new JLabel(p.getName());
            JLabel jtype=new JLabel();
            if(p instanceof VirtualPlayer) jtype= new JLabel("Joueur virtuel");
            else jtype=new JLabel("Joueur reel");
            joueurPanel.add(jname);
            joueurPanel.add(jtype);

            joueurPanel.setBackground(Color.WHITE);

            joueurListePanel.add(joueurPanel);
        }
        
        joueurListePanel.validate();
        joueurListePanel.repaint();
        choixPanel.repaint();
    }

    public void listerJoueurs(){
        for(Player p:joueurs){
            System.out.println(p.toString());
        }
    }

    public JPanel getPanel(){
        return choixPanel;
    }

    public boolean isOver(){return  isOver;}
}
