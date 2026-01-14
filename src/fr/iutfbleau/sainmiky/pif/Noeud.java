package fr.iutfbleau.sainmiky.pif;
import java.util.*;

/**
 * Classe qui représente un Noeud dans l'Arbre.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class Noeud{
	/** entier compris entre -1 et 255 qui représente la couleur, -1 = non défini*/
	private int coul;
	/** entier qui représente la fréquence*/
	private int freq;
	/** le fils gauche, peut être null*/
	private Noeud fGauche; 
	/** le fils droit, peut être null*/
	private Noeud fDroit;

	/** premier constructeur qui construit une feuille
	 *
	 * @param c la valeur de la couleur
	 * @param f la frequence de la couleur
	*/
	public Noeud(int c, int f){
		this.coul = c;
		this.freq = f;
		this.fGauche = null;
		this.fDroit = null;
	}

	/** premier constructeur qui un noeud en précisant ses enfants
	 *
	 * @param c la valeur de la couleur
	 * @param f la frequence de la couleur
	 * @param fg le Noeud du fils gauche
	 * @param fd le Noeud du fils droit
	*/
	public Noeud(int c, int f, Noeud fg, Noeud fd){
		this.coul = c;
		this.freq = f;
		this.fGauche = fg;
		this.fDroit = fd;
	}

	/** permet de récupérer la frequence
	 * 
	 * @return la fréquence
	*/
	public int getFreq(){
		return this.freq;
	}

	/** permet de récupérer la couleur
	 * 
	 * @return la couleur
	*/
	public int getCoul(){
		return this.coul;
	}

	/** permet de récupérer le fils gauche
	 * 
	 * @return le fils gauche
	*/
	public Noeud getFGauche(){
		return this.fGauche;
	}

	/** permet de récupérer le fils droit
	 *
	 * @return le fils droit
	*/
	public Noeud getFDroit(){
		return this.fDroit;
	}

	/** permet de modifier le fils gauche
	 * 
	 * @param fGauche le nouveau fils gauche
	 */
	public void setFGauche(Noeud fGauche){
		this.fGauche = fGauche;
	}

	/** permet de modifier le fils droit
	 * 
	 * @param fDroit le nouveau fils droit
	 */
	public void setFDroit(Noeud fDroit){
		this.fDroit = fDroit;
	}

	/** permet de modifier la couleur
	 * 
	 * @param coul la nouvelle couleur (valeur entre -1 et 255)
	 */
	public void setCoul(int coul){
		this.coul = coul;
	}

	/** fonction récursive qui affiche un Noeud et ses fils (si ils existent)
	 * pas très lisible en réalité mais fonctionnelle.
	 * 
	 * @param esp l'espace qui sera print avant
	 */
	public void afficher(int esp){
		for(int i = 0; i<esp; i+=1){
			System.out.print("  ");
		}
		System.out.println(this.getCoul() + ", " + this.getFreq());
		if(this.fGauche!=null){
			this.fGauche.afficher(esp+1);
		}
		if(this.fDroit!=null){
			this.fDroit.afficher(esp+1);
		}
	}

	/** fonction récursive qui rempli une liste pour la table des codes
	 * 
	 * @param liste la liste à remplir
	 * @param codeAct le code actuel
	 */
	public void calculTC(List<PaireValCode> liste, String codeAct){
		if(this.coul!=-1){
			liste.add(new PaireValCode(this.coul, codeAct));
		}
		if(this.fGauche!=null){
			this.fGauche.calculTC(liste, codeAct+"0");
		}
		if(this.fDroit!=null){
			this.fDroit.calculTC(liste, codeAct+"1");
		}
	}
}