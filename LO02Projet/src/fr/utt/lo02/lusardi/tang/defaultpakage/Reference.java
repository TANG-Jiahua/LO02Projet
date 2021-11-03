package fr.utt.lo02.lusardi.tang.defaultpakage;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class Reference extends Card{
    private boolean exist=false;

    Reference() {
        super(17,Value.Trois, Color.Coeur,Condition.HighestPique);
        setVisibilite(1);
    }



    public Reference creerUneReference(){
        if (exist) return null;
        else {
            Reference reference=new Reference();
            return reference;
        }
    }


    public String toString(){
        return ("Reference");
    }
}
