package fr.iutfbleau.sainmiky.pif;
import java.util.Comparator;

/**
 * Comparateur pour les objets Noeud.
 * Compare la frequence des deux objets.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class CompNoeud implements Comparator<Noeud>{

	/**
     * Constructeur par défaut.
     */
	public CompNoeud(){
		super();
	}

	/**
     * Compare deux objets Noeud.
     * La comparaison se fait sur la frequence des deux objets.
     * 
     * 
     * @param n1 premier noeud à comparer
     * @param n2 second noeud à comparer
     * @return un entier négatif, zéro ou positif selon que le premier argument
     *         est inférieur, égal ou supérieur au second
     */
	@Override
	public int compare(Noeud n1, Noeud n2){
		return n1.getFreq() - n2.getFreq();
	}
}