package fr.iutfbleau.sainmiky.pif;
import java.util.PriorityQueue;

/**
 * Arbre binaire des Noeuds.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class Arbre{
	/** Le Noeud racine de l'arbre*/
	private Noeud racine;

	/** L'unique constructeur de l'arbre qui le génère à partir d'une PriorityQueue
	 * 
	 * @param fPrio la file de priorité qui va servir pour générer l'arbre.
	 */
	public Arbre(PriorityQueue<Noeud> fPrio){
		this.creer(fPrio);
	}

	/** Constructeur de l'arbre qui le génère à partir d'un Noeud
	 * 
	 * @param racine le Noeud racine de l'arbre.
	 */
	public Arbre(Noeud racine) {
		this.racine = racine;
	}

	/** La fonction privée qui créer l'arbre
	 * 
	 * @param fPrio la file de priorité qui va servir pour générer l'arbre.
	 */
	private void creer(PriorityQueue<Noeud> fPrio){
		while(!fPrio.isEmpty()){
			Noeud n1 = fPrio.poll();
			Noeud n2 = fPrio.poll();
			if(n2!=null){
				int totFreq = n1.getFreq() + n2.getFreq();
				Noeud parent = new Noeud(-1, totFreq, n1, n2);
				fPrio.add(parent);
			}
			else{
				this.racine = n1;
			}
		}
	}

	/** getter de la racine
	 * 
	 * @return le Noeud qui est la racine de l'arbre.
	 */
	public Noeud getRacine(){
		return this.racine;
	}

	/** permet d'afficher l'arbre*/
	public void afficher(){
		this.racine.afficher(0);
	}

}