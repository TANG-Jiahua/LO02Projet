package fr.utt.lo02.lusardi.tang.defaultpakage;
import java.util.ArrayList;

import fr.utt.lo02.lusardi.tang.controller.ViewController;
/**
 * 
 * @author alicelusardi, jiahuatang
 *
 */
public class Main {

    public static void main(String args[]){

        ViewController viewController = new ViewController();
        viewController.doStart();
        viewController.initChoixView();
        viewController.doChoixView();

        PlayGame pg=new PlayGame(viewController);

        viewController.afficherTrophie(pg.getTrophie());

        int j=1;
        while (pg.round(j)==1){
            StringBuffer sbb=new StringBuffer();
            sbb.append("Entrer dans la "+(j+1)+"er round");
            System.out.println(sbb.toString());

            pg.round(j);

            j++;
        }

        ArrayList<Result> results=new ArrayList<>();
        results=pg.terminer();
        viewController.terminer(results);
    }
}
