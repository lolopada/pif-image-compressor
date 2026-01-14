package fr.iutfbleau.sainmiky.pif;
import java.util.*;

/**
 * Classe qui représente une table de fréquences.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class TableFreq{
	/** le dico qui représente la table de fréquence*/
	private Map<Integer, Integer> dico;

	/** Unique constructeur d'une Table de fréquence*/
	public TableFreq(){
		this.dico = new HashMap<Integer, Integer>();
	}

	/** ajoute une clé ou incrément sa fréquence si elle existe déja
	 *
	 * @param cle la cle à ajouter/incrémenter
	*/
	public void ajouter(int cle){
		if(this.dico.containsKey(cle)){
			this.dico.put(cle, this.dico.get(cle)+1);
		}
		else{
			this.dico.put(cle, 1);
		}
	}

	/** renvoie la fréquence de la clé
	 *
	 * @param cle la cle à ajouter/incrémenter
	 *
	 *@return la fréquence reliée à la clé.
	*/
	public int getVal(int cle){
		if(!this.dico.containsKey(cle)){
			return 0;
		}
		return this.dico.get(cle);
	}

	/**Affiche la table de fréquence dans la console*/
	public void afficher(){
		for(Map.Entry<Integer,Integer> elem : this.dico.entrySet()){
			System.out.println("clé : "+elem.getKey()+", freq : "+elem.getValue());
		}	
	}

	/** renvoie une file de priorité qui représente la table de fréquence
	 *
	 *@return la file de priorité sous la forme d'une PriorityQueue.
	*/
	public PriorityQueue<Noeud> trier(){
		PriorityQueue<Noeud> fPrio = new PriorityQueue<>(new CompNoeud());

		for(Map.Entry<Integer,Integer> elem : this.dico.entrySet()){
			Noeud n = new Noeud(elem.getKey(),elem.getValue());
			fPrio.add(n);
		}
		
		return fPrio;
	}
}