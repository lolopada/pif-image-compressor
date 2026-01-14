package fr.iutfbleau.sainmiky.pif;

import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.awt.image.*;

/**
 * Application principale pour visualiser des images au format PIF.
 * Cette classe fournit une interface utilisateur permettant de sélectionner
 * et d'afficher des fichiers d'images PIF en les décodant automatiquement.
 * L'application peut être lancée avec un fichier en argument ou proposer
 * un sélecteur de fichiers si aucun argument n'est fourni.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class Visualisateur {
    /**
     * Constructeur de la classe Visualisateur.
     */
    public Visualisateur() {
    }

    /**
     * Point d'entrée principal de l'application visualisateur.
     * Gère la sélection du fichier image (via arguments ou sélecteur de fichiers),
     * décode l'image PIF et lance l'interface graphique de visualisation.
     * 
     * @param args arguments de la ligne de commande (optionnel : chemin vers un fichier .pif)
     */
    public static void main(String[] args) {
        File fichierImage = GestionChooser.recupImage(args);
        
        if (fichierImage != null && !(fichierImage.isFile() && fichierImage.getName().toLowerCase().endsWith(".pif"))) {
            System.out.println("Erreur : image existe pas ou n'est pas en pif.");
            System.exit(1);
        }
        
        ImagePIF imagePIF = LecteurPIF.lireImagePIF(fichierImage);
        if (imagePIF == null) {
            System.err.println("impossible de lire le fichier PIF.");
            System.exit(1);
        }
        
        BufferedImage imageDecodee = LecteurPIF.decoderImagePIF(imagePIF);
        //System.out.println("Taille de l'image : " + imageDecodee.getWidth() + "x" + imageDecodee.getHeight());
        if (imageDecodee == null) {
            System.err.println("impossible de décoder l'image.");
            System.exit(1);
        }
        
        FenetreVisualisateur fenetre = new FenetreVisualisateur(imageDecodee, fichierImage.getName());
        fenetre.afficher();
    }
}