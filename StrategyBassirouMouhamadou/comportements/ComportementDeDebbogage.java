package StrategyBassirouMouhamadou.comportements;

import EDU.gatech.cc.is.util.Vec2;
//import StrategyBassirouMouhamadou.*;
import StrategyBassirouMouhamadou.actions.*;
import StrategyBassirouMouhamadou.utilitaires.*;
import StrategyBassirouMouhamadou.strategie.*;

/**
 * Comportement de deboggage du jeu.
 */
public class ComportementDeDebbogage extends Comportement {
    Game game;

    /**
     * @param game
     */
    public ComportementDeDebbogage(Game game) {
        this.game = game;
    }

    @Override
    public boolean isPossible() {
        if(this.game.getNumberBlock() > Game.lock+25) {
            game.setNumberBlock(0);
        }

        return (this.game.getNumberBlock() > Game.lock);
    }

    @Override
    public Action realize() {
        AttractionRepulsion attrRep = new AttractionRepulsion(game);

        Vec2 debug = new Vec2(0,0);

        Vec2 temRep = attrRep.getRepulsionOfTeammates(0.1);
        debug.add(temRep);

        Vec2 oppRep = attrRep.getRepulsionOfOpponents(1.0);
        debug.add(oppRep);

        return new Action(this.game, debug, false);
    }
    
}