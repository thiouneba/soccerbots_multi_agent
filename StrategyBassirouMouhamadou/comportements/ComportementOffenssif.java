package StrategyBassirouMouhamadou.comportements;

import EDU.gatech.cc.is.util.Vec2;
import EDU.gatech.cc.is.abstractrobot.*;
//import StrategyBassirouMouhamadou.*;
import StrategyBassirouMouhamadou.actions.*;
import StrategyBassirouMouhamadou.utilitaires.*;
import StrategyBassirouMouhamadou.strategie.*;

/**
 * Comportement offensif de l'agent.
 * """Attaque + Tir"""
 */
public class ComportementOffenssif extends Comportement {
    Game game;
    /**
     * @param game
     */
    public ComportementOffenssif(Game game) {
        this.game = game;
    }

    @Override
    public boolean isPossible() {
        return !this.game.closestTeammateToBall();
    }

    @Override
    public Action realize() {
        AttractionRepulsion attr = new AttractionRepulsion(game);

        Vec2 ballAttr = attr.getAttractionOfBall();

        if(this.canKick(ballAttr)) {
            return new Action(this.game, this.game.getPosition(), false);
        }

        return new Action(this.game, ballAttr, false);
    }

    /**
     * @param vec
     * @return true si peut tirer
     * @return false sinon
    */
    public boolean canKick(Vec2 vec) {
        double oppDist = Game.abs_limit - Processor.distance(this.game.getTheirGoal(), vec).r;

        /****** Méthodes de régulation de l'intensité des tirs ******/
        // Tir à intensité faible
        if(oppDist < 0.15 && Processor.areInAxe(vec.t, this.game.getTheirGoal(), 0.04) && this.game.areOpponentsInAxe(vec, 0.04)) {
            return true;
        }

        // Tir à intensité moyenne
        if(oppDist < 0.8 && Processor.areInAxe(vec.t, this.game.getTheirGoal(), 0.08) && this.game.areOpponentsInAxe(vec, 0.08)) {
            return true;
        }

        // Tir à intensité forte
        if(oppDist < 0.8 && Processor.areInAxe(vec.t, this.game.getTheirGoal(), 0.12) && this.game.areOpponentsInAxe(vec, 0.12)) {
            return true;
        }

        return false;

    }
}