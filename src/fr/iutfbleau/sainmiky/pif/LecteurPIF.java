package fr.iutfbleau.sainmiky.pif;

import java.io.*;
import java.awt.image.BufferedImage;

/**
 * Lecteur de fichiers au format PIF (Picture Image Format).
 * Cette classe utilitaire fournit des méthodes statiques pour lire et décoder
 * les fichiers d'images au format PIF, incluant la lecture des en-têtes,
 * des tables de décodage Huffman et des données de pixels compressées.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class LecteurPIF {

    /**
     * Constructeur privé pour empêcher l'instanciation de cette classe utilitaire.
     */
    private LecteurPIF() {
    }

    /**
     * Lit un fichier PIF et retourne un objet ImagePIF contenant toutes les
     * données.
     * Parse l'en-tête du fichier, extrait les dimensions, les tables de décodage
     * et les données des pixels compressées.
     * 
     * @param fichier fichier PIF à lire
     * @return objet ImagePIF contenant les données du fichier, ou null en cas
     *         d'erreur
     */
    public static ImagePIF lireImagePIF(File fichier) {
        String nomFichier = fichier.getName();
        String cheminFichier = fichier.getAbsolutePath();

        if (!fichier.exists()) {
            System.err.println("Le fichier n'existe pas : " + cheminFichier);
            return null;
        }

        int taillefichier = (int) fichier.length();
        if (taillefichier < 772) {
            System.err.println("Fichier trop petit pour être un fichier PIF valide : " + taillefichier + " bytes");
            return null;
        }

        byte[] entete = new byte[4];
        int largeur = -1;
        int hauteur = -1;
        byte[] tableRouge = new byte[256];
        byte[] tableVerte = new byte[256];
        byte[] tableBleue = new byte[256];

        int tailleDonneesPixels = taillefichier - 772;
        byte[] donneesPixels = new byte[tailleDonneesPixels];

        FileInputStream img = null;
        try {
            img = new FileInputStream(fichier);

            try {
                // lire en-tête
                if (img.read(entete) == -1) {
                    System.err.println("Erreur lors de la lecture de l en-tête");
                    return null;
                }

                // analsyer entete
                largeur = ((entete[0] & 0xFF) << 8) | (entete[1] & 0xFF);
                hauteur = ((entete[2] & 0xFF) << 8) | (entete[3] & 0xFF);

                if (largeur <= 0 || hauteur <= 0) {
                    System.err.println("Dimensions invalides : " + largeur + "x" + hauteur);
                    return null;
                }

                // lire les tables de couleurs
                byte[] tablesData = new byte[768];
                if (img.read(tablesData) == -1) {
                    System.err.println("Erreur lors de la lecture des tables de codes");
                    return null;
                }

                // Analyser les tables de couleurs
                for (int i = 0; i < 256; i++) {
                    tableRouge[i] = tablesData[i];
                }

                for (int i = 0; i < 256; i++) {
                    tableVerte[i] = tablesData[256 + i];
                }

                for (int i = 0; i < 256; i++) {
                    tableBleue[i] = tablesData[512 + i];
                }

                // lire les donnee des pixel
                if (img.read(donneesPixels) != tailleDonneesPixels) {
                    System.err.println("Erreur lors de la lecture des données pixels");
                    return null;
                }

            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
                return null;
            }

            try {
                img.close();
            } catch (IOException e) {
                System.err.println("Erreur lors de la fermeture du fichier : " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de l'ouverture du fichier : " + e.getMessage());
            return null;
        }

        return new ImagePIF(nomFichier, cheminFichier, largeur, hauteur, tableRouge, tableVerte, tableBleue,
                donneesPixels);
    }

    /**
     * Décode un objet ImagePIF en BufferedImage.
     * Utilise le décodage Huffman pour produire une image Java standard utilisable pour l'affichage.
     * 
     * @param imagePIF objet ImagePIF à décoder
     * @return BufferedImage décodée prête à l'affichage, ou null en cas d'erreur
     */
    public static BufferedImage decoderImagePIF(ImagePIF imagePIF) {
        if (imagePIF == null)
            return null;

        HuffmanDecodeur decodeur = new HuffmanDecodeur(imagePIF.getTableRouge(), imagePIF.getTableVerte(),
                imagePIF.getTableBleue());

        int nombrePixels = imagePIF.getLargeur() * imagePIF.getHauteur();
        BufferedImage image = new BufferedImage(imagePIF.getLargeur(), imagePIF.getHauteur(),
                BufferedImage.TYPE_INT_RGB);

        int[] pixelsDecodes = decodeur.decoder(imagePIF.getDonneesPixels(), nombrePixels);
        if (pixelsDecodes == null) {
            System.err.println("erreur");
            return null;
        }

        for (int y = 0; y < imagePIF.getHauteur(); y++) {
            for (int x = 0; x < imagePIF.getLargeur(); x++) {
                int index = y * imagePIF.getLargeur() + x;
                image.setRGB(x, y, pixelsDecodes[index]);
            }
        }
        return image;
    }
}