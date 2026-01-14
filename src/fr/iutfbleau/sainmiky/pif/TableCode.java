package fr.iutfbleau.sainmiky.pif;
import java.util.*;

/**
 * Classe qui représente une table de code.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class TableCode{
	/** la liste contenant toutes les valeurs et leur code*/
	private List<PaireValCode> liste;

	/** premier constructeur qui construit la table 
	 *
	 * @param l la liste utilisée pour la table
	*/
	public TableCode(List<PaireValCode>	 l){
		this.liste = l;
	}

	/** deuxième constructeur qui construit la table en générant la liste grâce à un arbre.
	 *
	 * @param arbreHuff l'arbre utilisée pour générer la table
	*/
	public TableCode(Arbre arbreHuff){
		this.liste = new LinkedList<PaireValCode>();
		this.creer(arbreHuff);
	}

	/** fonction qui initie la création de la table.
	 *
	 * @param arbreHuff l'arbre utilisée pour générer la table
	*/
	private void creer(Arbre arbreHuff){
		arbreHuff.getRacine().calculTC(this.liste, "");
	}

	/** fonction qui trie la liste selon le CompPaireValCode.*/
	public void trier(){
		this.liste.sort(new CompPaireValCode());
	}

	/** fonction qui permet d'afficher l'ensemble de la table dans la console.*/
	public void afficher(){
		for(PaireValCode elem : this.liste){
			System.out.println(elem);
		}
	}

	/** fonction qui récupère le code d'une valeur.
	 * 
	 * @param val la valeur 
	 * 
	 * @return le code correspondant à la valeur, null si elle n'y est pas.
	*/
	public String getCode(int val){
		for (PaireValCode elem : this.liste){
			if(elem.getValeur()==val){
				return elem.getCode();
			}
		}
		return null;
	}

	/** fonction qui prend un code et lui rajoute 1 en binaire.
	 * pas parfaite mais je l'aime beaucoup.
	 * 
	 * @param code le code initial 
	 * 
	 * @return le code incrémenté de 1 en binaire.
	*/
	private String plusUnBinaire(String code){
		String nvCode;
		char[] temp = code.toCharArray();
		int i = temp.length-1;

		// on parcourt à partir de la fin jusqu'à trouver un 0
		while(temp[i]=='1' && i>0){
			i-=1;
		}

		// on le met à 1
		temp[i]='1';
		i+=1;

		// on met tout le reste à droite à 0
		while(i<temp.length){
			temp[i]='0';
			i+=1;
		}

		nvCode = new String(temp);
		return nvCode;
	}

	/** fonction qui renvoie la table canonique de l'objet.
	 * 
	 * @return la table canonique sous la forme d'une TableCode.
	*/
	public TableCode genererCanonique(){
		int val, indCode, longCode;
		String code, nvCode;
		List<PaireValCode> listeCan = new LinkedList<>();

		if(this.liste.size() == 0){
			System.out.println("La table est vide");
			return new TableCode(listeCan);
		}
		
		// on fait la première valeur
		val = this.liste.get(0).getValeur();
		code = this.liste.get(0).getCode();
		nvCode = "";
		
		for(indCode=0; indCode<code.length(); indCode+=1){
			nvCode = nvCode+"0";
		}
		listeCan.add(new PaireValCode(val, nvCode));

		// on fait le reste à partir du deuxième
		for (int index=1; index<this.liste.size(); index+=1){

			PaireValCode elem = this.liste.get(index);

			// on récupère la valeur et la longueur du code
			val = elem.getValeur();
			longCode = elem.getCode().length();

			// on fait +1 en binaire grâce à une méthode exceptionnelle 
			nvCode = this.plusUnBinaire(nvCode);


			// on rajoute des 0 si la taille n'est pas la même
			for(indCode=nvCode.length(); indCode<longCode; indCode+=1){
				nvCode = nvCode+"0";
			}

			listeCan.add(new PaireValCode(val, nvCode));
		}
		return new TableCode(listeCan);
	}

	/** fonction qui renvoie un tableau de byte représentant la table.
	 * 
	 * @return le tableau de byte.
	*/
	public byte[] toByte(){
		byte[] tabLong = new byte[256];
		Integer l;
		byte longueur;
		
		for(int i=0; i<tabLong.length; i+=1){
			l=0;
			PaireValCode paireTemp = new PaireValCode(i);

			// si la valeur est dedans on récupère la longueur du code
			if(this.liste.contains(paireTemp)){
				l = this.getCode(i).length();	
			}
			longueur =  l.byteValue();  // conversion en byte
			tabLong[i] = longueur;
		}
		return tabLong;
	}
}