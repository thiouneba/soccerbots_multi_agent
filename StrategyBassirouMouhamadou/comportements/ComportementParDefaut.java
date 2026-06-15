package StrategyBassirouMouhamadou.comportements;

import EDU.gatech.cc.is.util.Vec2;
import EDU.gatech.cc.is.abstractrobot.*;
//import StrategyBassirouMouhamadou.*;
import StrategyBassirouMouhamadou.actions.*;
import StrategyBassirouMouhamadou.utilitaires.*;
import StrategyBassirouMouhamadou.strategie.*;

/**
 * Comportement par défaut de l'agent
 */
public class ComportementParDefaut extends Comportement {
    Game game;

    /**
     * @param game
     */
    public ComportementParDefaut(Game game) {
        this.game = game;
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public Action realize() {
        AttractionRepulsion attrRep = new AttractionRepulsion(game);

        Vec2 ballAttr = attrRep.getAttractionOfBall();

        Vec2 temRep = attrRep.getRepulsionOfTeammates(0.4);

        Vec2 oppRep = attrRep.getRepulsionOfOpponents(1.0);
        temRep.add(oppRep);

        Vec2 pos = this.game.getPosition();
        pos.setr(pos.r*(-1.0));

        ballAttr.add(pos);

        // Action builder
        return new Action(this.game, ballAttr, false);

    }
}
