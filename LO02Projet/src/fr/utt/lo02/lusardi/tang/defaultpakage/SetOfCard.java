package fr.utt.lo02.lusardi.tang.defaultpakage;
import java.util.LinkedList;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class SetOfCard{
    public final static int NOMBREDESUIT=18;
    private LinkedList<Card> tasDeCards;
    private Joker joker=new Joker();
    private Reference reference=new Reference();

    
    /**
     * On construit un tas de cartes contenant 18 cartes, 16 suit cartes, un joker et une reference
     */
    public SetOfCard(){
        tasDeCards = new LinkedList<Card>();
        Card card0 =new Card(0,Value.Quatre,Color.Pique,Condition.LowestTrefle);tasDeCards.add(card0);
        card0.setImageLocation("src/image/4P.PNG");
        Card card1 =new Card(1,Value.Quatre,Color.Trefle,Condition.HighestPique);tasDeCards.add(card1);
        card1.setImageLocation("src/image/4T.PNG");
        Card card2 =new Card(2,Value.Quatre,Color.Carreau,Condition.BestJestNoJoker);tasDeCards.add(card2);
        card2.setImageLocation("src/image/4C.PNG");
        Card card3 =new Card(3,Value.Quatre,Color.Coeur,Condition.Joker);tasDeCards.add(card3);
        card3.setImageLocation("src/image/4Co.PNG");
        Card card4 =new Card(4,Value.Trois,Color.Pique,Condition.Majority2);tasDeCards.add(card4);
        card4.setImageLocation("src/image/3P.PNG");
        Card card5 =new Card(5,Value.Trois,Color.Trefle,Condition.HighestCoeur);tasDeCards.add(card5);
        card5.setImageLocation("src/image/3T.PNG");
        Card card6 =new Card(6,Value.Trois,Color.Carreau,Condition.LowestCarreau);tasDeCards.add(card6);
        card6.setImageLocation("src/image/3C.PNG");
        Card card7 =new Card(7,Value.Trois,Color.Coeur,Condition.Joker);tasDeCards.add(card7);
        card7.setImageLocation("src/image/3Co.PNG");
        Card card8 =new Card(8,Value.Deux,Color.Pique,Condition.Majority3);tasDeCards.add(card8);
        card8.setImageLocation("src/image/2P.PNG");
        Card card9 =new Card(9,Value.Deux,Color.Trefle,Condition.LowestCoeur);tasDeCards.add(card9);
        card9.setImageLocation("src/image/2T.PNG");
        Card card10 =new Card(10,Value.Deux,Color.Carreau,Condition.HighestCarreau);tasDeCards.add(card10);
        card10.setImageLocation("src/image/2C.PNG");
        Card card11 =new Card(11,Value.Deux,Color.Coeur,Condition.Joker);tasDeCards.add(card11);
        card11.setImageLocation("src/image/2Co.PNG");
        Card card12 =new Card(12,Value.Ace,Color.Pique,Condition.HighestTrefle);tasDeCards.add(card12);
        card12.setImageLocation("src/image/5P.PNG");
        Card card13 =new Card(13,Value.Ace,Color.Trefle,Condition.HighestPique);tasDeCards.add(card13);
        card13.setImageLocation("src/image/5T.PNG");
        Card card14 =new Card(14,Value.Ace,Color.Carreau,Condition.Majority4);tasDeCards.add(card14);
        card14.setImageLocation("src/image/5C.PNG");
        Card card15 =new Card(15,Value.Ace,Color.Coeur,Condition.Joker);tasDeCards.add(card15);
        card15.setImageLocation("src/image/5Co.PNG");
        tasDeCards.add(new Joker());
        tasDeCards.get(16).setImageLocation("src/image/joker.png");
        tasDeCards.add(new Reference());
        tasDeCards.get(17).setImageLocation("src/image/cardRef.png");
    }

    public LinkedList<Card> getTas(){return tasDeCards;}

    public boolean isEmpty(){return tasDeCards.isEmpty();}

    public Card titerUneCarte(){return tasDeCards.poll();}

    public void shuffle(){
        for(int i=0;i<tasDeCards.size();i++){
            int position=(int)Math.round(((tasDeCards.size())-1)*Math.random());
            Card card = this.tasDeCards.pop();
            tasDeCards.add(position,card);
        }
    }

    public String toString(){ return tasDeCards.toString(); }



}
