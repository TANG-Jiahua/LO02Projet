package fr.utt.lo02.lusardi.tang.defaultpakage;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */

public class EasyStrategy implements Strategy{

	/**
	 * On prend un nombre aleatoire
	 * @param cards cartes
	 * @return n numero de la carte
	 */
    public int takeOffer(Card[] cards) {
        int n=0;
        for(int i=0;i<cards.length;i++){
            if(!(cards[i] instanceof Reference))
                n=i;
        }
        return n;
    }

    /**
     * On prend un nombre aleatoire
     * @return choix carte que le joueur à choisi de retourner
     */
    public int makeOffer() { 
        int choix = ((int)(10 * Math.random())) % 2;
        System.out.println("le joueur virtuel prend la carte: "+choix);

        return choix;
    }

}