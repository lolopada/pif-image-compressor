package fr.iutfbleau.sainmiky.pif;
import java.io.*;
import javax.swing.*;

/**
 * Classe statique qui permet de gérer les utilisations du JFileChooser.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class GestionChooser{

	/** Constructeur privée pour pas qu'un objet soit créé et pas de dans la javadoc*/
	private GestionChooser(){
	}

	/** Fonction qui permet de récupérer l'image potentiellement fournie par la ligne de commande
	 * 
	 *@param tab le tableau de String dans lequel le chemin vers l'image est précisé (ici args)
	 *
	 *@return le fichier sous la forme d'un File
	*/
	public static File recupImage(String[] tab){
		File image = null;
		if(tab.length == 0){
			JFileChooser selecteurFichier = new JFileChooser();
	        selecteurFichier.setCurrentDirectory(new File("."));
	        selecteurFichier.setDialogTitle("choisir une image");

			int resultat = selecteurFichier.showOpenDialog(null);

			if (resultat == JFileChooser.APPROVE_OPTION) {
		        image = selecteurFichier.getSelectedFile();
	        }
			else {
	            System.out.println("aucune image selectionnee");
	            System.exit(0);
	        }
		}
		else{
			image = new File(tab[0]);
		}
		return image;
	}

	/** Fonction qui permet de récupérer la destination potentiellement fournie par la ligne de commande
	 * 
	 *@param texte le chemin potentiel (peut être null)
	 *
	 *@return le chemin final
	*/
	public static String recupDest(String texte){
		if(texte == null){
			JFileChooser selecteurFichier = new JFileChooser();
	        selecteurFichier.setCurrentDirectory(new File("."));
	        selecteurFichier.setDialogTitle("choisir une destination");

			int resultat = selecteurFichier.showOpenDialog(null);

			if (resultat == JFileChooser.APPROVE_OPTION) {
		        texte = selecteurFichier.getSelectedFile().getAbsolutePath();
	        }
			else {
	            System.out.println("aucune destination selectionnee");
	            System.exit(0);
	        }
		}
		return texte;
	}
}