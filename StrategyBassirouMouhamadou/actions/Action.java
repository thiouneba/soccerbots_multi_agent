package StrategyBassirouMouhamadou.actions;

import EDU.gatech.cc.is.util.Vec2;
import EDU.gatech.cc.is.abstractrobot.*;
import static EDU.gatech.cc.is.abstractrobot.ControlSystemS.CSSTAT_OK;
import StrategyBassirouMouhamadou.utilitaires.*;

/**
 * Cette classe définit l'action que peut effectuer l'agent
 * Il s'agira principalement de l'action tirer quand c'est possible
 * Cette classe peut aussi être élargie en intégrant une interface derrière
 * si toutefois plusieurs actions différentes pourraient être définies
 */
public class Action {

    private Game game;
    private Vec2 fieldAxe;
    private boolean tire;

    /**
     * Constructeur de la classe
     * @param game
     * @param fieldAxe;
     * @param tire;
     */
    public Action(Game game, Vec2 fieldAxe, boolean tire) {
        this.game = game;
        this.fieldAxe = fieldAxe;
        this.tire = tire;

        this.makeTir();
    }

    /**
     * Définition de la fonctionnalité de tir
     */
    public int makeTir() {
        this.game.getAbstractRobot().setSteerHeading(this.game.getCurrTime(), this.fieldAxe.t);

        this.game.getAbstractRobot().setSpeed(this.game.getCurrTime(), 1.0);

        if(this.tire) {
            if(this.game.getAbstractRobot().canKick(this.game.getCurrTime())) {
                this.game.getAbstractRobot().kick(this.game.getCurrTime());
            }
        }

        // tell the parent we're OK
	    return(CSSTAT_OK);
    }
}
