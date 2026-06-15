package StrategyBassirouMouhamadou.strategie;

import EDU.gatech.cc.is.abstractrobot.SocSmall;
import EDU.gatech.cc.is.util.Vec2;
import StrategyBassirouMouhamadou.utilitaires.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe implémente la stratégie attraction/répulsion que va adopter nos agents
 */
 public class AttractionRepulsion {
 
 	Game game;
 	
	/**
     	 * Contructeur de la classe
     	 * @param game
     	 */
    	public AttractionRepulsion(Game game) {

			this.game = game;      

    	}
    	
    	/**
    	 * Répulsion émise par notre agent
    	 * @param agent
    	 * @return rep
    	 */
    	public Vec2 getRepulsionOfAgent(Vec2 agent) {
    		Vec2 rep = new Vec2(agent);
    		rep.setr(-0.1/Math.pow(agent.r,2));
    		return rep;
    	}
    	
    	/**
    	 * Répulsion émise par les bords haut et bas du terrain
    	 * @return hautrep
    	 */
    	public Vec2 getRepulsionOfLimit() {
    		Vec2 hautRep = new Vec2(0.0, Game.ord_limit - this.game.getPosition().y);
    		hautRep.setr(-0.1/Math.pow(hautRep.r,2));
    		Vec2 basRep = new Vec2(0.0, -Game.ord_limit - this.game.getPosition().y);
    		hautRep.setr(-0.1/Math.pow(basRep.r,2));
    		
    		hautRep.add(basRep);
    		
    		return hautRep;
    	}
    	
    	/**
    	 * Répulsion émise par les coéquipiers suivant un facteur k de répulsion
    	 * @param k
    	 * @return rep
    	 */
    	public Vec2 getRepulsionOfTeammates(double k) {
    		Vec2 rep = new Vec2(0,0);
    		for(Vec2 temmate : this.game.getTeammates()) {
    			rep.add(this.getRepulsionOfAgent(temmate));
    		}
    		rep.setr(rep.r * k);
    		return rep;
    	}
    	
    	/**
    	 * Répulsion émise par les adversaires suivant un facteur k de répulsion
    	 * @param k
    	 * @return rep
    	 */
    	public Vec2 getRepulsionOfOpponents(double k) {
    		Vec2 rep = new Vec2(0,0);
    		for(Vec2 opponent : this.game.getOpponentsPlayers()) {
    			rep.add(this.getRepulsionOfAgent(opponent));
    		}
    		rep.setr(rep.r * k);
    		return rep;
    	}
    	
    	/**
    	 * Attraction de la balle suivant que le joueur soit devant ou derrière elle
    	 * @return attr
    	 */
    	public Vec2 getAttractionOfBall() {
    		// Agent is south of ball : va avoir tendence à être attiré vers le but adverse
    		if(this.game.northOrSouthOfBall()) {
    			Vec2 opponentGoal = new Vec2(this.game.getBall());
    			opponentGoal.sub(this.game.getTheirGoal());
    			Vec2 attr = new Vec2(opponentGoal);
    			
    			attr.setr(SocSmall.RADIUS-0.02);
    			attr.add(this.game.getBall());
    			attr.setr(this.getRepulsionOfLimit().r * 2);
    			attr.setr(this.getRepulsionOfOpponents(2).r);
    			
    			return attr;    			
    		}
    		
    		// Agent is north of ball : va avoir tendence à se replier vers son but
    		else {
    			Vec2 attr = new Vec2(this.game.getOurGoal());
				Vec2 ball = new Vec2(this.game.getBall());

				attr.setr((2.0/attr.r));
				ball.setr((-0.2/ball.r));

				attr.add(ball);
				attr.add(this.game.getBall());

				return attr;	
    		}
    	}
    	
 }
