package fr.utt.lo02.lusardi.tang.defaultpakage;

import fr.utt.lo02.lusardi.tang.controller.ViewController;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class PlayGame {
    private SetOfCard tas; //L'ensemble total de cartes 
    protected int nbOfPlayer;
    protected int nbPlayerReel;
    private Card[] trophie;
    private int nbTrophie;
    protected Player[] players;
    private Card[] cards; //L'ensemble de cartes a distribuer 
    private Visitor visitor;
    private ViewController viewController;

    public Player[] getPlayers() {
        return players;
    }

    public Card[] getTrophie() {
        return trophie;
    }

    public SetOfCard getTas(){return tas;}

    
    /**
     * Ajouter un joueur, initialiser la pile de cartes, sélectionner les trophées
     * 
     * @param viewController
     * 
     */
    public PlayGame(ViewController viewController){
            this.viewController=viewController;
            visitor = new PlayerVisitor();
            addPlayer(viewController);
            if(nbOfPlayer==3) nbTrophie=3;
            else if(nbOfPlayer==4) nbTrophie=2;

            // Creer un tas de carte contenant toutes les 18 cartes
            this.tas = new SetOfCard();

            // Tirer la carte reference, ajouter dans le tas sur la table (ici on le met aussi dans les trophies), et l'afficher
            trophie=new Card[nbTrophie];
            trophie[0]=tas.getTas().get(17);
            System.out.println(tas.getTas().get(17));
            tas.getTas().remove(17);

            tas.shuffle();

            //S'il y a 3 joueurs, ajouter deux cartes a trophie
            //S'il y a 4 joueurs, ajouter une carte a trophies
            for(int i=1;i<nbTrophie;i++) this.trophie[i]=tas.titerUneCarte();
            System.out.println("Les cartes trophies sont:");
            for(int n1=0;n1<nbTrophie;n1++) System.out.println(this.trophie[n1]);

            // Creer l'ensemble de cartes pour collecter et distribuer les cartes
            this.cards=new Card[2*nbOfPlayer];
            for(int i=0;i<2*nbOfPlayer;i++)
                cards[i]=tas.titerUneCarte();

        }

    /**
     * Ajouteur les joueurs
     * 
     * @param vc controller
     */
    public void addPlayer(ViewController vc) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Combien de joueurs êtes-vous?");
            nbOfPlayer = ViewController.nbJoueur;

            while ( nbOfPlayer> 4||nbOfPlayer <1) {
                System.out.println("Vous devez entrer un nombre entre 1 et 4");
                Scanner sr = new Scanner(System.in);
                nbOfPlayer=sr.nextInt();
            }

            Scanner sr1 = new Scanner(System.in);
            System.out.println("Combien de joueurs réels êtes-vous?");
            nbPlayerReel=vc.getChoixView().getNbJoueurPhysique();

            players = new Player[nbOfPlayer];
            for (int i = 0; i < nbPlayerReel; i++) {
                Scanner sr = new Scanner(System.in);
                StringBuffer sb = new StringBuffer();
                sb.append(i + 1);
                sb.append("er joueur, quel est votre nom?");
                System.out.println(sb.toString());
                String name=vc.getChoixView().getJoueurs().get(i).getName();
                players[i] = new Player(name);
            }

            for(int i=nbPlayerReel;i<nbOfPlayer;i++){
                players[i]=new VirtualPlayer("Joueur virtuel No."+(i+1),new EasyStrategy());
            }

            for(int i=0;i<players.length;i++){
                players[i].setId(i);
                System.out.println(players[i].getName()+" "+players[i].getId());
            }
        }

    /**
     * Determiner l'ordre dans lequel les joueurs vont jouer en comparant les valeurs des cartes
     * 
     * @param players
     * @return players
     */
    public Player[] comparer(Player[] players){
            for(Player p:players){
                System.out.println("Avant comparer les cartes jouer "+p.getName());
                System.out.println(p.getCardOffer());
            }
            //Creer un tableau pour stocker le nombre de cartes retournees, les trier du plus grand au plus petit
            int numCarte[] =new int[4];
            for(int i=0;i<nbOfPlayer;i++){
                if(players[i].getCardOffer().get(1).getVisibilite()==1)
                    numCarte[i]=players[i].getCardOffer().get(1).getIdCarte();
                else
                    numCarte[i]=players[i].getCardOffer().get(0).getIdCarte();
            }
            for(int i=0;i<4;i++){
                for(int j=i;j<4;j++){
                    int z;
                    if(numCarte[i]<numCarte[j]){
                        z=numCarte[i];
                        numCarte[i]=numCarte[j];
                        numCarte[j]=z;
                    }
                }
            }

            Player newPlayers[]=new Player[nbOfPlayer];
            for(int i=0;i<nbOfPlayer;i++){
                for(Player p:players){
                    if(p.getCardOffer().get(0).getIdCarte()==numCarte[i]||p.getCardOffer().get(1).getIdCarte()==numCarte[i])
                        newPlayers[i]=p;
                }
            }
            players=newPlayers;

            System.out.println("L'ordre est : ");
            for(Player p: players) System.out.println(p.getName());
            return players;
        }

    public Player[] initJoueurOrdre(Player[] players){
        Player[] players1=new Player[players.length];
        for(Player p:players){
            players1[p.getId()]=p;
        }
        return players1;
    }

    /**
     * Determine quand la partie doit s'arrreter, Quand la methode renvoie 1, on continue le prochain tour, sinon on arrete
     * 
     * @param round tour de jeu 
     * @return round 
     */
    public int round(int round) {
            System.out.println("Au debut de " + round + "er round: les cartes pour distribuer sont: ");
            for (int t = 0; t < 2 * nbOfPlayer; t++) System.out.println(cards[t].toString());
            int i = 0;

            players=initJoueurOrdre(players);

            //Montrer les cartes de ce tour sur l'interface graphique
           //Creer d'abord la boite du joueur
            viewController.initJoueurView(players);

            for (Player p : players) {
                ArrayList<Card> c = new ArrayList<Card>();
                c.add(cards[2 * i]);
                c.add(cards[2 * i + 1]);

                //Copier les 2 cartes a l'objet joueur, choisit une tarte a retourner
                p.setCardOffer(c);

                StringBuffer sb = new StringBuffer();
                sb.append("---------------Pour joueur ");
                sb.append(i + 1);
                sb.append(", vous avez les cartes:");
                System.out.println(sb.toString());
                System.out.println(p.getCardOffer());


                viewController.afficherCarteOffer(p);

                if(!(p instanceof VirtualPlayer)) {
                    System.out.println("player reel");
                    int choix = viewController.getChoix(p);
                    p.makeOffer(choix);
                }
                else if(p instanceof VirtualPlayer){
                    p.makeOffer(0);
                    System.out.println("player virtuel");
                }

                //Choisir une tarte a retourner
                //Modifier l'ensemble de cartes comme: cards [joueur1.visible, joueur1.invisible, joueur2.visible, joueur2.invisible...]
                for (Card ca : p.getCardOffer()) {
                    if (ca.getVisibilite() == 1) cards[2 * i] = ca;
                    else cards[2 * i + 1] = ca;
                }
                for (int t = 0; t < 2 * nbOfPlayer; t++) System.out.println(cards[t].toString());
                i++;
            }

            System.out.println("A la fin d'un round");

            viewController.removeCarteOffer();

            players = comparer(players);

            //Après avoir comparé la valeur de chaque carte retournée, sélectionnez les cartes sur la table dans l'ordre
            for (Player p : players) {
                StringBuffer sb = new StringBuffer();
                sb.append("Joueur "+p.getName());
                System.out.println(sb.toString());

                viewController.initOfferView(players);
                int[] choix=viewController.choisirCarteOffer(p);

                p.takeOffer(cards,choix);

                StringBuffer sb1 = new StringBuffer();
                sb1.append("Vous avez tirez la carte :  "+ p.getJest().get(round-1));
                System.out.println(sb1.toString());
            }
        viewController.removeCarteBalaBalaView();

            StringBuffer sbb = new StringBuffer();
            sbb.append("Apres cette tour, on a encore "+tas.getTas().size()+" cartes dans le tas pour distribuer");
            System.out.println(sbb.toString());
            System.out.println(tas.getTas());

            System.out.println("A la fin de cette tour, apres tirer des cartes, les cartes pour distrebuer sont : ");

            //Ajouter les cartes a l'ensemble de cartes sur table
            //Le tableau cards1 stocke 3 ou 4 des 6 ou 8 cartes sur la table qui n'ont pas été tirées, et rend ces cartes invisibles
            Card[] cards1 = cards;
            int index = 0;
            for (int t = 0; t < 2 * nbOfPlayer; t++) {
                if (!(cards[t] instanceof Reference)) {
                    cards1[index] = cards[t];
                    index++;
                }
            }
            for(int i1=0;i1<nbOfPlayer;i1++)
                cards1[i1].setVisibilite(0);

            if (tas.getTas().size() != nbOfPlayer) {
                for (int b = nbOfPlayer; b < 2 * nbOfPlayer; b++)
                    cards1[b] = tas.titerUneCarte();
                cards=cards1;
                for (int t = 0; t < 2 * nbOfPlayer; t++) System.out.println(cards[t].toString());
                return 1;
            }
             else{
                System.out.println("Tous les cartes sont distribuees!");
                 return 0;
            }


        }

    /**
     * 
     * @param cards
     * @param c
     * @return ca valeur de la carte
     */
    public Card getMaxMinCarte(ArrayList<Card> cards, char c){
        Card ca=cards.get(0);
        System.out.println(cards);
            for(int i=0;i<cards.size();i++){
                Card ca1=cards.get(i);
                if(c=='h'){
                    if(ca1.getValueInt()>=ca.getValueInt())
                        ca=ca1;
                }
                else if(c=='l'){
                    if(ca1.getValueInt()<=ca.getValueInt())
                        ca=ca1;
                }
            }
            return ca;
       }

    public Player getMaxMinJoueur(Card card){
           Player pm=players[0];
           for(Player p:players){
               if(p.getJest().contains(card))
                   pm=p;
           }
           return pm;
       }

    public Player comparerFaceValue(ArrayList<Player> players1){
        Player pm=players1.get(0);
        int max=0;
        for(Card c: pm.getJest()){
            max+=c.getValueInt();
        }

        for(Player p: players1){
            int faceValue=0;
            for(Card c: pm.getJest()){
                faceValue+=c.getValueInt();
            }
            if(faceValue>max){
                max=faceValue;
                pm=p;
            }
        }
        return pm;
    }

    /**
     * Lorsque la partie est finie, on calcule le score des joueurs
     * 
     * @return results score des joueurs  
     */
    public ArrayList<Result> terminer(){

            ArrayList<Result> results=new ArrayList<>();
            for(Player p:players){
                System.out.println("Le joueur "+p.getName()+" a les cartes jest : "+p.getJest());
                results.add(new Result(p.getName(),p.getJest()));
            }

            ArrayList<Card> cards=new ArrayList<>();
            for(int i=1;i<nbTrophie;i++){
                if(trophie[i].getConditon()==Condition.HighestCarreau){
                    for(Result r: results) {
                        if(r.getHighestLowest(Color.Carreau,'h')!=null)
                        cards.add(r.getHighestLowest(Color.Carreau,'h'));
                    }
                    if(cards.size()!=0){
                        Card card=getMaxMinCarte(cards,'h');
                        getMaxMinJoueur(card).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.HighestCoeur){
                    for(Result r: results){
                        if(r.getHighestLowest(Color.Coeur,'h')!=null)
                        cards.add(r.getHighestLowest(Color.Coeur,'h'));
                    }
                    if(cards.size()!=0){
                    Card card=getMaxMinCarte(cards,'h');
                    getMaxMinJoueur(card).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.HighestPique){
                    for(Result r: results) {
                        if(r.getHighestLowest(Color.Pique,'h')!=null)
                        cards.add(r.getHighestLowest(Color.Pique,'h'));
                    }
                    if(cards.size()!=0) {
                        Card card = getMaxMinCarte(cards, 'h');
                        getMaxMinJoueur(card).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.HighestTrefle){
                    for(Result r: results) {
                        if (r.getHighestLowest(Color.Trefle,'h')!=null)
                            cards.add(r.getHighestLowest(Color.Trefle,'h'));
                    }
                    if(cards.size()!=0) {
                        Card card = getMaxMinCarte(cards, 'h');
                        getMaxMinJoueur(card).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.LowestTrefle){
                    for(Result r: results)  {
                     if(r.getHighestLowest(Color.Trefle,'l')!=null)
                            cards.add(r.getHighestLowest(Color.Trefle,'l'));
                    }
                    if(cards.size()!=0) {
                        Card card = getMaxMinCarte(cards, 'l');
                        getMaxMinJoueur(card).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.LowestPique){
                    for(Result r: results){
                        if(r.getHighestLowest(Color.Pique,'l')!=null)
                        cards.add(r.getHighestLowest(Color.Pique,'l'));
                    }
                    if(cards.size()!=0){
                    Card card=getMaxMinCarte(cards,'l');
                    getMaxMinJoueur(card).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.LowestCarreau){
                    for(Result r: results)  {
                        if(r.getHighestLowest(Color.Carreau,'l')!=null)
                        cards.add(r.getHighestLowest(Color.Carreau,'l'));
                    }
                    if(cards.size()!=0) {
                        Card card = getMaxMinCarte(cards, 'l');
                        getMaxMinJoueur(card).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.LowestCoeur){
                    for(Result r: results) {
                        if(r.getHighestLowest(Color.Coeur,'l')!=null)
                        cards.add(r.getHighestLowest(Color.Coeur,'l'));
                    }
                    if(cards.size()!=0) {
                        Card card = getMaxMinCarte(cards, 'l');
                        getMaxMinJoueur(card).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.Majority2){
                    //Creer une nouvelle collection (joueurs1) pour stocker tous les joueurs avec le nombre maximum de Majorité2
                    ArrayList<Player> players1=new ArrayList<>();
                    ArrayList<String> names=new ArrayList<>();

                    Result result=results.get(0);
                    for(Result r: results){
                        if(r.getCarteDeux().size()>=result.getCarteDeux().size())     result=r;
                    }
                    for(Result result1:results){
                        if(result1.getCarteDeux().size()==result.getCarteDeux().size())    names.add(result1.getNom());
                    }
                    for(String name:names){
                        for(Player p:players) {
                            if (p.getName() == name)    players1.add(p);
                        }
                    }

                    if(players1.size()==1)  players1.get(0).getJest().add(trophie[i]);
                    else{
                        comparerFaceValue(players1).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.Majority3){
                    //Creer une nouvelle collection (players1) pour stocker tous les joueurs avec le nombre maximum de Majority3
                    ArrayList<Player> players1=new ArrayList<>();
                    ArrayList<String> names=new ArrayList<>();

                    Result result=results.get(0);
                    for(Result r: results){
                        if(r.getCarteTrois().size()>=result.getCarteTrois().size())     result=r;
                    }
                    for(Result result1:results){
                        if(result1.getCarteTrois().size()==result.getCarteTrois().size())    names.add(result1.getNom());
                    }
                    for(String name:names){
                        for(Player p:players) {
                            if (p.getName() == name)    players1.add(p);
                        }
                    }

                    if(players1.size()==1)  players1.get(0).getJest().add(trophie[i]);
                    else{
                        comparerFaceValue(players1).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.Majority4){
                    //Creer une nouvelle collection (joueurs1) pour stocker tous les joueurs avec le nombre maximum de Majorité4
                    ArrayList<Player> players1=new ArrayList<>();
                    ArrayList<String> names=new ArrayList<>();

                    Result result=results.get(0);
                    for(Result r: results){
                        if(r.getCarteQuatre().size()>=result.getCarteQuatre().size())     result=r;
                    }
                    for(Result result1:results){
                        if(result1.getCarteQuatre().size()==result.getCarteQuatre().size())    names.add(result1.getNom());
                    }
                    for(String name:names){
                        for(Player p:players) {
                            if (p.getName() == name)    players1.add(p);
                        }
                    }

                    if(players1.size()==1)  players1.get(0).getJest().add(trophie[i]);
                    else{
                        comparerFaceValue(players1).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.Joker){
                    String nom = null;
                    for(Result r:results){
                        if(r.isAvoirJoker())  nom=r.getNom();
                    }
                    for(Player p: players){
                        if(p.getName()==nom)
                            p.getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.BestJest){
                    //Creer une nouvelle collection (joueurs1) pour stocker tous les joueurs avec la plus grande valeur de jest
                    ArrayList<Player> players1=new ArrayList<>();
                    ArrayList<String> names=new ArrayList<>();

                    for(Result r:results) r.accept(visitor);

                    Result result=results.get(0);
                    for(Result r: results){
                        if(r.getJestValue()>=result.getJestValue())     result=r;
                        }
                    for(Result result1:results){
                        if(result1.getJestValue()==result.getJestValue())
                            names.add(result1.getNom());
                    }
                    for(String name:names){
                        for(Player p:players) {
                            if (p.getName() == name)
                                players1.add(p);
                        }
                    }

                    if(players1.size()==1)  players1.get(0).getJest().add(trophie[i]);
                    else{
                        comparerFaceValue(players1).getJest().add(trophie[i]);
                    }
                }
                else if(trophie[i].getConditon()==Condition.BestJestNoJoker){
                    Result result=results.get(0);
                    String nom=null;
                    for(Result r: results){
                        if(r.isAvoirJoker()==false){
                            if(r.getJestValue()>result.getJestValue())
                                result=r;
                        }
                    }
                    nom=result.getNom();
                    for(Player p:players){
                        if(p.getName()==nom) p.getJest().add(trophie[i]);
                    }
                }
            }


            results.clear();
            for(Player p:players){
                System.out.println("Le joueur "+p.getName()+" a les cartes jest : "+p.getJest());
                results.add(new Result(p.getName(),p.getJest()));
            }

            for(Result r:results){
                r.accept(visitor);
            }
            Result rm=results.get(0);
            for(Result r:results){
                System.out.println("Nom: "+r.getNom()+" Note: "+r.getJestValue());
                if(r.getJestValue()>rm.getJestValue()) rm=r;
            }

            System.out.println("Winner is : "+ rm.getNom());
            return results;

        }
}
