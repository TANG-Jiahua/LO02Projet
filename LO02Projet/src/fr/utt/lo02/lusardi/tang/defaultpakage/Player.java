package fr.utt.lo02.lusardi.tang.defaultpakage;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class Player {
    private int id;
    protected String name;
    protected int nbCards;
    protected ArrayList<Card> jest; //Les carte que les joueurs tiennent a la main
    protected ArrayList<Card> cardOffer=new ArrayList<Card>(2); //Les carte sur la table

    /**
     * 
     * @param name nom du joueur
     */
    public Player(String name){
        this.name=name;
        jest=new ArrayList<Card>();
        cardOffer=new ArrayList<Card>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return  id;
    }

    public ArrayList<Card> getCardOffer(){return cardOffer;}

    public void setJest(ArrayList<Card> jest) {
        this.jest = jest;
    }

    public void setCardOffer(ArrayList<Card>cards){
        cardOffer=new ArrayList<Card>(2);
        for (Card c : cards)
        {
            cardOffer.add(c);
        }
    }

    /**
     * Choisir une carte a retourner
     * 
     * @param choix
     */
    public void makeOffer(int choix){
        Scanner sc = new Scanner(System.in);System.out.println("Quel est votre choix?(tappez 0 ou 1)");
        cardOffer.get(choix).setVisibilite(1);
    }

    /**
     * Afficher les cartes offertes sur la table
     * 
     * @param cards cartes sur la table
     * @param choix carte choisie pour retourner
     */
    public void takeOffer(Card[] cards,int[] choix){
        for(int i=0;i<cards.length;i++){
            if(i%2==0){
                if(cards[i] instanceof Reference){
                    System.out.println("Cette carte est deja tiree!");
                }
                else{
                    StringBuffer sb=new StringBuffer();
                    sb.append("Offer visible de ");
                    sb.append((i+2)/2);
                    sb.append("er joueur est : ");
                    sb.append(cards[i].toString());
                    System.out.println(sb.toString());
                }

            }
            else {
                StringBuffer sb=new StringBuffer();
                sb.append("Offer invisible de "+(i+2)/2+"er Player ");
                System.out.println(sb.toString());
            }
            i++;
        }

        Scanner sc = new Scanner(System.in);System.out.println("Vous voulez prendre la carte de quel joueur?");
        int choixJoueur=choix[0];
        System.out.println("choixJoueur= "+choixJoueur);

        System.out.println("Carte visible ou invisible?");
        int choixVisibilite=choix[1];
        System.out.println("choixVisibilite= "+choixVisibilite);

        System.out.println("2*choixJoueur + choixVisibilite="+(2*choixJoueur + choixVisibilite));
        jest.add(cards[2*choixJoueur + choixVisibilite]);
        cards[2*choixJoueur + choixVisibilite].setVisibilite(0);
        cards[2*choixJoueur+choixVisibilite]=new Reference();
    }

    public void readCard(){
        System.out.println(cardOffer);
    }

    public String getName(){
        return name;
    }

    public ArrayList<Card> getJest(){
        return jest;
    }

    public void setName(String name) {this.name=name;}



}