package StrategyBassirouMouhamadou.utilitaires;

import EDU.gatech.cc.is.abstractrobot.SocSmall;
import EDU.gatech.cc.is.util.Vec2;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe décrit l'ensemble des informations et règlages relatifs au jeu et au terrain :
 * limites du terrains,
 * valeur maximale du temps de blocage,
 * le poste de chaque agent,
 * initialisation du jeu, etc. 
 */
 public class Game {

    // Limites du terrain.
    public final static double abs_limit = 1.24;
    public final static double ord_limit = 0.7625;

    // Temps max de blocage
    public final static int lock = 300;

    // Postes des différents agents
    public final static int attacks = 3; // 3 attaquants
    public final static int defends = 1; // 1 défenseur
    public final static int goalkeeper = 1; // 1 gardien de buts

    // Le robot
    private SocSmall abstract_robot;

    // Les attributs
    private long curr_time;          // L'instant présent
    private Vec2 ball;               // Coordonnées de la balle
    private Vec2 position;           // Position de l'agent
    private Vec2 ourgoal;            // But allié
    private Vec2 theirgoal;          // But adverse
    private Vec2[] teammates;        // Coéquipiers
    private Vec2[] opponents;        // Adversaires
    private int side;                // Côté du terrain à la possession des alliés
    private int myNum;               // Numéro du robot
    //private int fieldAxe;          // Axe du terrain
    private int numberBlock;         // Nombre de blocages

    private Vec2 ballPos;            // Position de la balle

    //private Process processor;       // Effectue les calculs de vecteurs



    /**
     * Contructeur de la classe
     * @param abstract_robot
     */
    public Game(SocSmall abstract_robot) {

        this.abstract_robot = abstract_robot;

        this.numberBlock = 0;
         
    }

    // Initialisation du jeu.
    public void init() {
        this.curr_time = this.abstract_robot.getTime();
        this.position = this.abstract_robot.getPosition(curr_time);
        this.ball = this.abstract_robot.getBall(curr_time);
        this.ourgoal = this.abstract_robot.getOurGoal(curr_time);
        this.theirgoal = this.abstract_robot.getOpponentsGoal(curr_time);
        this.teammates = this.abstract_robot.getTeammates(curr_time);
        this.opponents = this.abstract_robot.getOpponents(curr_time);
        //this.processor = new Process();

        // On détermine de quel côté du terrain l'équipe se trouve.
        if(this.ourgoal.x < 0) {
            this.side = -1;
        } else {
            this.side = 1;
        }
    }

    /**
     * Vérification de la position de balle par rapport à l'agent.
     * @return true si la balle est devant l'agent.
     * @return false sinon.
     */
    public boolean northOrSouthOfBall() {
        double pos = this.ball.x * this.ourgoal.x + this.ball.y * this.ourgoal.y;

        if(pos < 0) {
            //South of ball
            return true;
        } else {
            //North of ball
            return false;
        }
    }

    /**
     * Vérification de la présence d'ennemis dans l'axe suivant la délimitation.
     * @param vectorAxe
     * @param limit
     * @return true si présence d'ennemis.
     * @return false sinon
     */
     public boolean areOpponentsInAxe(Vec2 vectorAxe, double limit) {
        for(Vec2 opponent : this.opponents) {
            if(Processor.areInAxe(vectorAxe.t, opponent, limit)) {
                // Présence d'ennemis
                return false;
            }
        }
        // Absence d'ennemis
        return true;
     }

    /**
     * Vérifier qu'un coéquipier n'est pas plus proche du but allié que l'agent.
     * @return true si l'agent n'est pas plus proche du but allié.
     */
    public boolean closestTeammateToOurGoal() {
        for(Vec2 temmate : this.teammates) {
            if(Processor.distance(this.ourgoal, temmate).r < this.ourgoal.r) {
                // L'agent n'est plus proche
                return true;
            }
        }
        // L'agent est plus proche
        return false;
    }

    /**
     * Vérifier qu'un coéquipier n'est pas plus proche de la balle que l'agent.
     * @return true si l'agent n'est pas plus proche de balle.
     */
    public boolean closestTeammateToBall() {
        for(Vec2 temmate : this.teammates) {
            if(Processor.distance(this.ball, temmate).r < this.ball.r) {
                // L'agent n'est plus proche
                return true;
            }
        }
        // L'agent est plus proche
        return false;
    }

    /**
     * Vérification si la balle est bloquée par les agents.
     * @return true si oui
     * @return false sinon
     */
    public boolean isBallBlocked() {
        if(this.ballPos == null) {
            return false;
        }
        Vec2 oldP = this.ballPos;
        Vec2 newP = Processor.absolutePosition(this.ball, this.position);

        return Processor.areEquals(oldP, newP, 0.04);
    }

    /**
     * Incrémentation de la variable numberBlock à chaque fois que la balle est bloquée par les agents
    */
    public void numberBlockPlusPlus() {
        if(!this.isBallBlocked()) {
            numberBlock = 0;
        } else {
            numberBlock += 1;
        }
        this. ballPos = Processor.absolutePosition(this.ball, this.position);
    }

    /*******************************
    * Access to private variables.
    *******************************/
    
    /**
     * @return ball
     */
    public SocSmall getAbstractRobot() {
        return this.abstract_robot;
    }

    /**
     * @return ball
     */
    public Vec2 getBall() {
        return this.ball;
    }

    /**
     * @return curr_time
     */
    public long getCurrTime() {
        return this.curr_time;
    }

    /**
     * @return myNum
     */
    public int getMyNum() {
        return this.myNum;
    }

    /**
     * @return temmates
     */
    public Vec2[] getTeammates() {
        return this.teammates;
    }

    /**
     * @return opponents
     */
    public Vec2[] getOpponentsPlayers() {
        return this.opponents;
    }

    /**
     * @return ourgoal
     */
    public Vec2 getOurGoal() {
        return this.ourgoal;
    }

    /**
     * @return theirgoal
     */
    public Vec2 getTheirGoal() {
        return this.theirgoal;
    }

    /**
     * @return side
     */
    public int getSide() {
        return this.side;
    }

    /**
     * @return position
     */
    public Vec2 getPosition() {
        return this.position;
    }


    /**
     * @return numberBlock
     */
    public int getNumberBlock() {
        return this.numberBlock;
    }

    /*******************************
    * Upadate private variables.
    *******************************/
    
    /**
     * @param val
     */
    public void setNumberBlock(int val) {
        this.numberBlock = val;
    }

 }
