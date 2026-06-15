package StrategyBassirouMouhamadou.comportements;

import EDU.gatech.cc.is.util.Vec2;
import EDU.gatech.cc.is.abstractrobot.*;
//import StrategyBassirouMouhamadou.*;
import StrategyBassirouMouhamadou.actions.*;
import StrategyBassirouMouhamadou.utilitaires.*;
import StrategyBassirouMouhamadou.strategie.*;

/**
 * Comportement deffensif de l'agent
 */
public class ComportementDeDefense extends Comportement {
    Game game;

    /**
     * @param game
     */
    public ComportementDeDefense(Game game) {
        this.game = game;
    }

    @Override
    public boolean isPossible() {
        int def = 0;

        for(Vec2 temmate : this.game.getTeammates()) {
            if(Processor.distance(this.game.getOurGoal(), temmate).r < this.game.getOurGoal().r) {
                def += 1;
            }
        }

        if(def < Game.defends) {
            return true;
        }

        return false;
    }

    @Override
    public Action realize() {
        AttractionRepulsion attr = new AttractionRepulsion(this.game);

        Vec2 ballAttr = attr.getAttractionOfBall();
        ballAttr.add(attr.getRepulsionOfTeammates(0.1));
        ballAttr.add(attr.getRepulsionOfOpponents(0.1));

        Vec2 limitRep = attr.getRepulsionOfLimit();
        limitRep.setr(limitRep.r * 0.6);
        ballAttr.add(limitRep);

        Vec2 ourgoal = this.game.getOurGoal();
        ourgoal.setr(ourgoal.r * 3.2);
        ballAttr.add(ourgoal);

        return new Action(this.game, ballAttr, false);
    }
}