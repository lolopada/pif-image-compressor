package fr.iutfbleau.sainmiky.pif;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.util.PriorityQueue;
import java.awt.*;
import javax.swing.*;

/**
 * Le Main du convertisseur.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class Convertisseur {

	/**
     * Constructeur par défaut.
     */
    public Convertisseur(){
    }

	/** Le main
	 * 
	 * @param args le tableau d'arguments passé en ligne de commande
	 */
    public static void main(String[] args){
		File img = null;
		String dest = null;
		BufferedImage buff;
		TableFreq dicoR = new TableFreq();  // tableau de frequence rouge
	    TableFreq dicoG = new TableFreq();  // le vert
		TableFreq dicoB = new TableFreq();  // le bleu

		img = GestionChooser.recupImage(args);

		try{
			buff = ImageIO.read(img);
			
			for(int y=0; y<buff.getHeight(); y+=1){
				for(int x=0; x<buff.getWidth(); x+=1){
					Color coul = new Color(buff.getRGB(x,y));
					dicoR.ajouter(coul.getRed());
					dicoG.ajouter(coul.getGreen());
					dicoB.ajouter(coul.getBlue());
				}
			}

			// On génère tout ce qui est nécessaire pour le rouge
			PriorityQueue<Noeud> fPrioR = dicoR.trier();
			Arbre arbreHuffR = new Arbre(fPrioR);
			TableCode tcR = new TableCode(arbreHuffR);
			tcR.trier();
			TableCode tCanR = tcR.genererCanonique();

			// Idem pour le vert
			PriorityQueue<Noeud> fPrioG = dicoG.trier();
			Arbre arbreHuffG = new Arbre(fPrioG);
			TableCode tcG = new TableCode(arbreHuffG);
			tcG.trier();
			TableCode tCanG = tcG.genererCanonique();

			// Idem pour le bleu
			PriorityQueue<Noeud> fPrioB = dicoB.trier();
			Arbre arbreHuffB = new Arbre(fPrioB);
			TableCode tcB = new TableCode(arbreHuffB);
			tcB.trier();
			TableCode tCanB = tcB.genererCanonique();

			dest = args.length>1 ? args[1] : null;
			FenetreConvertisseur fen = new FenetreConvertisseur(buff, dest);
			fen.ajouterTablesFrequence(dicoR, dicoG, dicoB);
			fen.ajouterTablesCode(tcR, tcG, tcB);
			fen.ajouterTablesCanonique(tCanR, tCanG, tCanB);
			fen.genererFenetre();

        	fen.setVisible(true);

		} catch(IOException e){
			System.err.println("fichier incompatible ou inexistant");
			System.exit(1);
		} catch(NullPointerException f){
			System.err.println("fichier incompatible ou inexistant");
			System.exit(1);
		}
    }
}