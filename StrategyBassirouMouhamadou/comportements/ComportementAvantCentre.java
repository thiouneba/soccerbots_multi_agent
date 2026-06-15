package StrategyBassirouMouhamadou.comportements;

import EDU.gatech.cc.is.util.Vec2;
import EDU.gatech.cc.is.abstractrobot.*;
//import StrategyBassirouMouhamadou.*;
import StrategyBassirouMouhamadou.actions.*;
import StrategyBassirouMouhamadou.utilitaires.*;
import StrategyBassirouMouhamadou.strategie.*;

/**
 * Comportement de l'agent en avant centre
 * Il se comporte comme un avant centre réel dans le foot normal
 */
public class ComportementAvantCentre extends Comportement {
    Game game;

     /**
     * @param game
     */
    public ComportementAvantCentre(Game game) {
        this.game = game;
    }

    @Override
    public boolean isPossible() {
        int center = 0;

        for(Vec2 temmate : this.game.getTeammates()) {
            if(Processor.distance(this.game.getTheirGoal(), temmate).r < this.game.getTheirGoal().r) {
                center += 1;
            }
        }

        if(center < Game.goalkeeper) {
            return true;
        }

        return false;
    }

    @Override
    public Action realize() {
        AttractionRepulsion attrRep = new AttractionRepulsion(this.game);

        Vec2 oppRep = attrRep.getRepulsionOfOpponents(0.2);

        Vec2 ball = this.game.getBall();    
        ball.sub(this.game.getTheirGoal());
        ball.setr(1.25);
        ball.add(this.game.getTheirGoal());

        ball.add(oppRep);

        return new Action(this.game, ball, true);
    }

}
