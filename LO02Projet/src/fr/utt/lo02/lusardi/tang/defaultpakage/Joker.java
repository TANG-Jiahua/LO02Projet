package fr.utt.lo02.lusardi.tang.defaultpakage;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class Joker extends Card{
    private boolean exist=false;

    Joker() {
            super(16,Value.NULL, Color.NULL,Condition.BestJest);
    }

    public Joker creerUnJoker(){
        if (exist) return null;
        else {
            Joker joker = new Joker();
            return joker;
        }
    }

    public String toString(){
        return ("Joker");
    }
}
