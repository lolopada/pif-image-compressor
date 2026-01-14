package fr.iutfbleau.sainmiky.pif;
import java.util.Comparator;

/**
 * Comparateur pour les objets PaireValCode.
 * Compare d'abord par longueur, puis par valeur en cas d'égalité de longueur.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class CompPaireValCode implements Comparator<PaireValCode> {

    /**
     * Constructeur par défaut.
     */
    public CompPaireValCode(){
    	super();
    }
    
     /**
     * Compare deux objets PaireValCode.
     * La comparaison se fait d'abord sur la longueur, puis sur la valeur
     * si les longueurs sont égales.
     * 
     * @param a première paire à comparer
     * @param b seconde paire à comparer
     * @return un entier négatif, zéro ou positif selon que le premier argument
     *         est inférieur, égal ou supérieur au second
     */
    @Override
    public int compare(PaireValCode a, PaireValCode b) {
        // compare la longueur des codes.
        if (a.getCode().length() != b.getCode().length()) {
            return a.getCode().length() - b.getCode().length();
        }
        // compare les valeurs si besoin.
        return a.getValeur() - b.getValeur();
    }
}