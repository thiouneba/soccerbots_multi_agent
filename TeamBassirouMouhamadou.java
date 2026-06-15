/*
 * Team Bassirou THIOUNE 
 * and 
 * Mouhamadou Mansour NDJIM
*/

import EDU.gatech.cc.is.util.Vec2;
import EDU.gatech.cc.is.abstractrobot.*;
import StrategyBassirouMouhamadou.comportements.Comportement;
import StrategyBassirouMouhamadou.comportements.ComportementParDefaut;
import StrategyBassirouMouhamadou.comportements.ComportementAvantCentre;
import StrategyBassirouMouhamadou.comportements.ComportementDeDebbogage;
import StrategyBassirouMouhamadou.comportements.ComportementOffenssif;
import StrategyBassirouMouhamadou.comportements.ComportementDeDefense;
import StrategyBassirouMouhamadou.utilitaires.*;

/**
 * @author Bassirou THIOUNE & Mouhamadou Mansour NDJIM
 * @version 1.0
 * Cette classe est la classe pricipalce.
 * Elle est à la charge du contrôle de l'agent,
 * et de l'ordonnancement des différents comportements,
 * par ordre de priorité.
*/
public class TeamBassirouMouhamadou extends ControlSystemSS {
    
    // Contextualisation du jeu.
    private Game game;

    // Différents comportements.
    private Comportement comp;
    private Comportement defaultComp;
    private Comportement defenseComp;
    private Comportement attaqueComp;
    private Comportement avantCentreComp;
    private Comportement debugComp;

    /*
     * Configuration du système de contrôle.
    */
    public void Configure() {

        // Instanciation des objets.
        game = new Game(abstract_robot);        

        defaultComp = new ComportementParDefaut(game);
        defenseComp = new ComportementDeDefense(game);
        attaqueComp = new ComportementOffenssif(game);
        avantCentreComp = new ComportementAvantCentre(game);
        debugComp = new ComportementDeDebbogage(game);

        /*
         * Ordonnancement des différents comportements définis.
         * Utilisation du pattern Chain of responsability.
        */
        // Quand le jeu est bloqué, on passe à l'attaque.
        debugComp.setComportementSuivant(attaqueComp);
        // Quand on perd la balle, on revient en défense.
        attaqueComp.setComportementSuivant(defenseComp);
        // Quand le ballon est remis au centre, on le récupère avant l'ennemi.
        defenseComp.setComportementSuivant(avantCentreComp);
        // Comportement basique d'un joueur en position neutre
        avantCentreComp.setComportementSuivant(defaultComp);
    }

    /*
     * Allouer le contrôle du système
    */
    public int takeStep() {

        // Initialisation du jeu
        game.init();

        // Comportement de plus haute priorité
        comp = debugComp;

        // Passage à une autre action selon le contexte
        while(!comp.isPossible()) {
            comp = comp.getComportementSuivant();
            game.init();
        }

        comp.realize();
        game.numberBlockPlusPlus();

        // tell the parent we're OK
	    return(CSSTAT_OK);

    }


}
