package fr.utt.lo02.lusardi.tang.defaultpakage;

public class Card {
	/**
	 * 
	 * @autor alicelusardi, jiahuatang 
	 */

    private Color couleur;
    private Value valeur;
    private Condition condition;
    private int visibilite;
    private int idCarte;
    private String imageLocation;

    /**
     * 
     * @param id identifiant de la carte
     * @param v valeur de la carte
     * @param c couleur de la carte
     * @param co condition
     */
    public Card(int id,Value v, Color c, Condition co){
        idCarte = id;
        couleur=c;
        valeur=v;
        condition=co;
        visibilite=0;
        imageLocation=null;
    }

    public Color getColor(){return couleur;}
    public Value getValue(){return valeur;}
    public int getValueInt(){
        if(valeur==Value.Deux)
            return 2;
        else if(valeur==Value.Trois)
            return 3;
        else if(valeur==Value.Quatre)
            return 4;
        else
            return 1;
    }
    public Condition getConditon(){return condition;}
    public int getVisibilite(){return visibilite;}
    public int getIdCarte(){return  idCarte;}

    public void setColor(Color c){couleur=c;}
    public void setValue(Value v){valeur=v;}
    public void setCondition(Condition c){c=c;}
    public void setVisibilite(int v){visibilite=v;}

    public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("No.");
            sb.append(idCarte);
            sb.append(" ");
            sb.append(valeur.toString());
            sb.append(" de ");
            sb.append(couleur.toString());
            sb.append(" avec ");
            sb.append(condition.toString());
            if(visibilite==1) sb.append(" avec visibitite");
            else sb.append(" sans visiblite");
            return sb.toString();
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String s){
        imageLocation=s;
    }



}
