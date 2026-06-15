package StrategyBassirouMouhamadou.comportements;

import EDU.gatech.cc.is.util.Vec2;
import EDU.gatech.cc.is.abstractrobot.*;
import StrategyBassirouMouhamadou.actions.*;

/*
 * Classe abstraite définissant les différents comportements
 * que peut adopter l'agent.
*/
public abstract class Comportement {
    private Comportement suivant;

    /**
     * @param suivant 
     * @return 
     * Setter du comportement suivant
     */
    public void setComportementSuivant(Comportement suivant) {
        this.suivant = suivant;
    }

    /**
     * @return suivant
     * Getter du comportement suivant
     */
    public Comportement getComportementSuivant() {
        return this.suivant;
    }

    /**
     * Teste si l'action suivante est réalisable
     * @return true si oui
     * @return false sinon
     * Fonction à redéfinir
     */
    public abstract boolean isPossible();

    /**
     * Réaliser l'action
     * @return action
     * Fonction à redéfinir
     */
    public abstract Action realize();
}