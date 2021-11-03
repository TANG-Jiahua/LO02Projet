package fr.utt.lo02.lusardi.tang.view;
import javax.swing.*;

import fr.utt.lo02.lusardi.tang.controller.ViewController;

import java.awt.*;
/**
 * Vue à la fin du jeu avec les notes de chaque joueur
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class FinDuJeuView {
    private JPanel panelTable;
    private JTable table;
    private String nomJoueur;
    private int note;
    private Object[] columnName  = {"Nom","note"};
    private Object[][] data;
    private int index;
    private JPanel panelBtn;
    private JPanel panel;

    private boolean next;

    private ViewController viewController;

    private static final int FIN_HEIGHT = 120;
    private static final int FIN_WIDTH = 260;

    public FinDuJeuView(){
        panelTable=new JPanel(new BorderLayout());
        index=0;
        data=new Object[4][2];
        panel=new JPanel(new BorderLayout(5,0));
        panel.setBounds(100,200,FIN_WIDTH,FIN_HEIGHT);
        panelBtn=new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
        next=false;
    }

    public void addData(String joueurNom,int note) {
        data[index][0]=joueurNom;
        data[index][1]=note;
        index++;
    }

    public void initData(){
        data=new Object[4][2];
        index=0;
    }

    public JPanel getPanel(){
        JButton btnFini=new JButton("Finir le jeu");
        btnFini.setBounds(0,0,100,50);

        panelBtn.add(btnFini);
        table=new JTable(data,columnName);
        table.setEnabled(false);
        panelTable.add(table.getTableHeader(),BorderLayout.NORTH);
        panelTable.add(table,BorderLayout.CENTER);
        panelTable.add(panelBtn,BorderLayout.SOUTH);
        panel.add(panelTable,BorderLayout.CENTER);
        panel.add(panelBtn,BorderLayout.SOUTH);
        panel.setVisible(false);
        return panel;
    }

    public void initNext(){
        next=false;
    }

    public boolean isNext(){
        return next;
    }
}
