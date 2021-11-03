package fr.utt.lo02.lusardi.tang.defaultpakage;

import java.util.ArrayList;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class VirtualPlayer extends Player{
    protected Strategy strat;

    /**
     * 
     * @param name nom du joueur virtuel
     * @param strat strategie du joueur virtuel
     */
    public VirtualPlayer(String name, Strategy strat) {
        super(name);
        jest=new ArrayList<Card>();
        cardOffer=new ArrayList<Card>();
        this.strat = strat;
    }

    /**
     * Methode qui appelle le pattern Strategy
     * 
     * @param c choix de la carte a retourner 
     */
    public void makeOffer(int c) {
        int choix = strat.makeOffer();
        cardOffer.get(choix).setVisibilite(1);
    }

    /**
     * Methode qui appelle le pattern Strategy 
     * 
     * @param cards
     * @param choix 
     */
    public void takeOffer(Card[] cards,int[] choix) {
        jest.add(cards[strat.takeOffer(cards)]);
        cards[strat.takeOffer(cards)].setVisibilite(0);
        cards[strat.takeOffer(cards)]=new Reference();
    }

}