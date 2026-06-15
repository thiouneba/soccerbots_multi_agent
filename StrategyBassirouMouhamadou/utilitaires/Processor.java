package StrategyBassirouMouhamadou.utilitaires;

import EDU.gatech.cc.is.util.Vec2;
import EDU.gatech.cc.is.util.Units;

/**
 * Cette classe regroupe les méthodes de calcul sur les vecteurs.
 * C'est le processeur d'où son nom :)
 */
public class Processor {

    /**
     * Distance entre deux vecteurs.
     * @param vec1
     * @param vec2
     * @return result
     */
    public static Vec2 distance(Vec2 vec1, Vec2 vec2) {
        Vec2 result = new Vec2(vec1);
        result.sub(vec2);
        return result;
    }

    /**
     * Position absolue entre deux vecteurs.
     * @param vec1
     * @param vec2
     * @return result
     */
    public static Vec2 absolutePosition(Vec2 vec1, Vec2 vec2) {
        Vec2 result = new Vec2(vec1);
        result.add(vec2);
        return result;
    }

    /**
     * Vérifie si deux vecteurs ont la même position en considérant un écart alpha.
     * @param vec1
     * @param vec2
     * @param alpha
     * @return true si même position
     * @return false sinon
     */
    public static boolean areEquals(Vec2 vec1, Vec2 vec2, double alpha) {
        return (vec1.x < vec2.x+alpha && vec1.x > vec2.x-alpha && vec1.y < vec2.y+alpha && vec1.y > vec2.y-alpha);
    }

    /**
     * Convertion degrés en radian
     * @param degree
     * @return radian
     */
    public static double degreeToRadian(double degree) {
        double radian = degree * Math.PI/180;

        return radian;
    }

    /**
     * Calcul d'un vecteur se trouvant dans l'axe suivant une délimitation.
     * @param val
     * @param vec1
     * @param limit
     * @return true si bien dans l'axe
     * @return false sinon
     */
    public static boolean areInAxe(double val, Vec2 vec1, double limit) {
        return (Math.abs(degreeToRadian(vec1.t - val)) < limit);
    }

}                                                                                                                               
