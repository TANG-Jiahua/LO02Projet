package fr.utt.lo02.lusardi.tang.view;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class StartRequireView extends JPanel {

    private boolean isOver;
    private int nbJoueurs;
    private JPanel panel;
    private static final int HEIGHT = 150;
    private static final int WIDTH = 300;

    /**
     * Creation de la vue au demarage du jeu 
     */
    public StartRequireView() {
        this.isOver = false;

        //Créer une nouvelle boîte
        panel=new JPanel();
        panel.setVisible(false);
        panel.setLayout(new GridLayout(3,1));
        panel.setBounds(50, 50,500,300);
        panel.setBackground(Color.gray);
        panel.setOpaque(true);

        //Ajoutez la première ligne de description à la boîte et les deux barres d'options au milieu
        JPanel txtPanel=new JPanel();
        JPanel radioBtnPanel=new JPanel();
        panel.add(txtPanel);
        JLabel txtLable=new JLabel("Quel est votre choix?");
        txtPanel.setLayout(new FlowLayout());
        txtPanel.add(txtLable);

        //Ajouter du contenu dans les deux barres d'options du milieu
        radioBtnPanel.setLayout(new GridLayout(2,1));
        JRadioButton radioBtn1=new JRadioButton("3 Joueurs");
        radioBtnPanel.add(radioBtn1);
        JRadioButton radioBtn2=new JRadioButton("4 Joueurs");
        radioBtnPanel.add(radioBtn2);
        ButtonGroup group=new ButtonGroup();
        group.add(radioBtn1);
        group.add(radioBtn2);
        panel.add(radioBtnPanel);

        //Ajoutez le bouton de confirmation du bas

        JButton btn_confirmer = new JButton("confirmer");
        panel.add(btn_confirmer);
        btn_confirmer.setVisible(true);
        btn_confirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    if(radioBtn1.isSelected())    nbJoueurs=3;
                    if(radioBtn2.isSelected())    nbJoueurs=4;
                    if(radioBtn1.isSelected()||radioBtn2.isSelected()){
                        panel.setVisible(false);
                        isOver = true;
                    }
                    System.out.println(nbJoueurs);
            }
        });
    }

    public boolean isOver() {
        return isOver;
    }

    public JPanel getPanel(){return panel; }

    public int getNbJoueurs() { return nbJoueurs;}
}
