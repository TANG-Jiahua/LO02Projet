package fr.utt.lo02.lusardi.tang.defaultpakage;

import java.util.ArrayList;

/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class Result {
    private String nom;
    private ArrayList<Card> jest;
    private ArrayList<Card> cartePique;
    private ArrayList<Card> carteTrefle;
    private ArrayList<Card> carteCoeur;
    private ArrayList<Card> carteCarreau;
    private ArrayList<Card> carteAce;
    private ArrayList<Card> carteDeux;
    private ArrayList<Card> carteTrois;
    private ArrayList<Card> carteQuatre;
    private boolean avoirJoker=false;
    private boolean avoirAceCoeur=false;
    private int jestValue=0;

    public ArrayList<Card> getJest() {
        return jest;
    }

    public ArrayList<Card> getCarteAce() {
        return carteAce;
    }

    public ArrayList<Card> getCarteDeux() {
        return carteDeux;
    }

    public ArrayList<Card> getCarteTrois() {
        return carteTrois;
    }

    public ArrayList<Card> getCarteQuatre() {
        return carteQuatre;
    }

    public ArrayList<Card> getCarteCoeur() {
        return carteCoeur;
    }

    public ArrayList<Card> getCarteCarreau() {
        return carteCarreau;
    }

    public ArrayList<Card> getCartePique() {
        return cartePique;
    }

    public ArrayList<Card> getCarteTrefle() {
        return carteTrefle;
    }

    public String getNom()  {  return nom;  }

    /**
     * 
     * @return avoirJoker si le joueur possede le joker dans son jest, retourne vrai
     */
    public boolean isAvoirJoker() {
        return avoirJoker;
    }
    
    /**
     * 
     * @return avoirAceCoeur si le joueur possede l'ace de coeur, retourne vrai
     */
    public boolean isAvoirAceCoeur() {
    	return avoirAceCoeur;
    }

    /**
     * 
     * @param n
     * @param j jest 
     */
    public Result(String n, ArrayList<Card> j){
        nom=n;
        jest=j;

        cartePique=new ArrayList<>();
        carteTrefle=new ArrayList<>();
        carteCoeur=new ArrayList<>();
        carteCarreau=new ArrayList<>();
        carteAce=new ArrayList<>();
        carteDeux=new ArrayList<>();
        carteTrois=new ArrayList<>();
        carteQuatre=new ArrayList<>();
        for(Card c:jest){
            if(c.getColor()==Color.Pique)  cartePique.add(c);
            else if(c.getColor()==Color.Trefle)  carteTrefle.add(c);
            else if (c.getColor()==Color.Coeur)  carteCoeur.add(c);
            else if(c.getColor()==Color.Carreau) carteCarreau.add(c);

            if (c.getValueInt()==1 && c.getIdCarte()!=16)  carteAce.add(c);
            else if(c.getValueInt()==2) carteDeux.add(c);
            else if(c.getValueInt()==3) carteTrois.add(c);
            else carteQuatre.add(c);

            if(c.getIdCarte()==16) avoirJoker=true;
            if(c.getIdCarte()==15) avoirAceCoeur=true;
        }

    }

    public void setJestValue(int jestValue) {
        this.jestValue = jestValue;
    }

    public int getJestValue(){


        return jestValue;
    }

    public Card getMaxMin(ArrayList<Card> cards, char s){
        Card card=cards.get(0);
        for(Card c:cards){
            if(s=='h') {
                if (c.getValueInt() > card.getValueInt())
                    card = c;
            }
            else
                if(c.getValueInt() < card.getValueInt())
                    card = c;
        }
        return card;

    }

    public Card getHighestLowest(Color c,char ch){
            if(c==Color.Pique&&cartePique.size()!=0)
                return getMaxMin(cartePique, ch);
            else if(c==Color.Trefle&&carteTrefle.size()!=0)
                return getMaxMin(carteTrefle, ch);
            else if(c==Color.Coeur&&carteCoeur.size()!=0)
                return getMaxMin(carteCoeur, ch);
            else if(c==Color.Carreau&&carteCarreau.size()!=0)
                return getMaxMin(carteCarreau, ch);
            else return null;
    }

    public void accept(Visitor visitor){visitor.visit(this);}


}
