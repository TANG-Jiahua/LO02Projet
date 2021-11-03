package fr.utt.lo02.lusardi.tang.defaultpakage;
import java.util.ArrayList;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */

public class PlayerVisitor implements Visitor {
	@Override
	
	/**
	 * Calcul le score du joueur
	 * 
	 * @param resultat score du joueur
	 */
    public void visit(Result resultat) {
        int jestValue=0;

        for(Card c: resultat.getCartePique()){
            jestValue=jestValue+c.getValueInt();
        }
        System.out.println("pique: "+jestValue);
        for(Card c: resultat.getCarteTrefle()){

            jestValue=jestValue+c.getValueInt();
        }
        for(Card c:resultat.getCarteCarreau()){
            jestValue-=c.getValueInt();

        }

        System.out.println("nb carte ace"+resultat.getCarteAce().size());
        System.out.println(resultat.getCarteAce());

        if(resultat.getCarteAce().size()==1){
            System.out.println("Only one Ace!");
            if(resultat.getCarteAce().get(0).getIdCarte()==12||resultat.getCarteAce().get(0).getIdCarte()==13){
                jestValue+=4;
                System.out.println("+4");
            }
            else if(resultat.getCarteAce().get(0).getIdCarte()==14)
                jestValue-=4;
 
        }
        System.out.println("Ace: "+jestValue);
        
        if(resultat.isAvoirJoker()){
            if(resultat.getCarteCoeur().size()==0) {
                jestValue+=4;
            }

            else if(resultat.getCarteCoeur().size()<4){
                for(Card c:resultat.getCarteCoeur()) {
                    System.out.println("-"+c.getValueInt());
                    jestValue -= c.getValueInt();
                    System.out.println("===========");
                }
                if(resultat.isAvoirAceCoeur()&&resultat.getCarteAce().size()==1)    {
                    System.out.println("-----------");
                    jestValue-=4;
                }
            }

            else {
                for (Card c : resultat.getCarteCoeur()) jestValue += c.getValueInt();
                if (resultat.isAvoirAceCoeur()&&resultat.getCarteAce().size()==1) jestValue -= 4;
            }
        }
        

        for(Card c:resultat.getCartePique()){
            for(Card ca: resultat.getCarteTrefle()){
                if(c.getValue()==ca.getValue())
                    jestValue+=2;
            }
        }
        resultat.setJestValue(jestValue);
    }
    public static void main(String args[]){
        Card card0 =new Card(0,Value.Quatre,Color.Pique,Condition.LowestTrefle);
        Card card1 =new Card(1,Value.Quatre,Color.Trefle,Condition.HighestPique);
        Card card2 =new Card(2,Value.Quatre,Color.Carreau,Condition.BestJestNoJoker);
        Card card3 =new Card(3,Value.Quatre,Color.Coeur,Condition.Joker);
        Card card4 =new Card(4,Value.Trois,Color.Pique,Condition.Majority2);
        Card card5 =new Card(5,Value.Trois,Color.Trefle,Condition.HighestCoeur);
        Card card6 =new Card(6,Value.Trois,Color.Carreau,Condition.LowestCarreau);
        Card card7 =new Card(7,Value.Trois,Color.Coeur,Condition.Joker);
        Card card8 =new Card(8,Value.Deux,Color.Pique,Condition.Majority3);
        Card card9 =new Card(9,Value.Deux,Color.Trefle,Condition.LowestCoeur);
        Card card10 =new Card(10,Value.Deux,Color.Carreau,Condition.HighestCarreau);
        Card card11 =new Card(11,Value.Deux,Color.Coeur,Condition.Joker);
        Card card12 =new Card(12,Value.Ace,Color.Pique,Condition.HighestTrefle);
        Card card13 =new Card(13,Value.Ace,Color.Trefle,Condition.HighestPique);
        Card card14 =new Card(14,Value.Ace,Color.Carreau,Condition.Majority4);
        Card card15 =new Card(15,Value.Ace,Color.Coeur,Condition.Joker);
        Card card16=new Joker();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card16);
        cards.add(card3);cards.add(card12);cards.add(card15);

        Visitor visitor=new PlayerVisitor();
        Result result=new Result("test",cards);
        System.out.println(cards);
        System.out.println(result.getCarteCoeur());
        result.accept(visitor);
        System.out.println(result.getJestValue());

    }
}
