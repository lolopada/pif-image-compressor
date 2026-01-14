package fr.iutfbleau.sainmiky.pif;

import java.util.Comparator;

/**
 * Comparateur pour les objets PaireLongueurValeur.
 * Compare d'abord par longueur, puis par valeur en cas d'égalité de longueur.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class CompPaireLongueurValeur implements Comparator<PaireLongueurValeur> {
    
    /**
     * Constructeur par défaut.
     */
    public CompPaireLongueurValeur(){
        super();
    }
    
    /**
     * Compare deux objets PaireLongueurValeur.
     * La comparaison se fait d'abord sur la longueur, puis sur la valeur
     * si les longueurs sont égales.
     * 
     * @param a première paire à comparer
     * @param b seconde paire à comparer
     * @return un entier négatif, zéro ou positif selon que le premier argument
     *         est inférieur, égal ou supérieur au second
     */
    @Override
    public int compare(PaireLongueurValeur a, PaireLongueurValeur b) {
        if (a.getLongueur() !=b.getLongueur()) {
            return a.getLongueur() - b.getLongueur();
        }
        return a.getValeur()- b.getValeur();
    }
}