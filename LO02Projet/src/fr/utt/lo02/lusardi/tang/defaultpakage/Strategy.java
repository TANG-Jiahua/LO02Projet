package fr.utt.lo02.lusardi.tang.defaultpakage;
/**
 * 
 * Pattern strategy pour determiner la startegie du joueur virtuel
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public interface Strategy {
    
    public int takeOffer(Card[] cards);
    public int  makeOffer();
}
