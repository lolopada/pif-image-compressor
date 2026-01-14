package fr.iutfbleau.sainmiky.pif;

/**
 * Représentation d'une image au format PIF (Picture Image Format).
 * Cette classe encapsule toutes les données nécessaires pour une image PIF,
 * incluant ses dimensions, les tables de décodage Huffman pour chaque
 * composante de couleur,
 * et les données des pixels compressées.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class ImagePIF {
    /** Largeur de l'image en pixels. */
    private int largeur;

    /** Hauteur de l'image en pixels. */
    private int hauteur;

    /** Table de décodage Huffman pour la composante rouge (256 octets). */
    private byte[] tableRouge;

    /** Table de décodage Huffman pour la composante verte (256 octets). */
    private byte[] tableVerte;

    /** Table de décodage Huffman pour la composante bleue (256 octets). */
    private byte[] tableBleue;

    /** Données des pixels compressées selon l'algorithme de Huffman. */
    private byte[] donneesPixels;

    /** Nom du fichier image sans le chemin. */
    private String nomFichier;

    /** Chemin complet vers le fichier image. */
    private String cheminFichier;

    /**
     * Constructeur pour créer une instance d'ImagePIF.
     * Initialise tous les attributs de l'image avec les données fournies.
     * 
     * @param nomFichier    nom du fichier image
     * @param cheminFichier chemin complet vers le fichier
     * @param largeur       largeur de l'image en pixels
     * @param hauteur       hauteur de l'image en pixels
     * @param tableRouge    table de décodage Huffman pour la composante rouge
     * @param tableVerte    table de décodage Huffman pour la composante verte
     * @param tableBleue    table de décodage Huffman pour la composante bleue
     * @param donneesPixels données des pixels compressées
     */
    public ImagePIF(String nomFichier, String cheminFichier, int largeur, int hauteur, byte[] tableRouge,
            byte[] tableVerte, byte[] tableBleue, byte[] donneesPixels) {
        this.nomFichier = nomFichier;
        this.cheminFichier = cheminFichier;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.tableRouge = tableRouge;
        this.tableVerte = tableVerte;
        this.tableBleue = tableBleue;
        this.donneesPixels = donneesPixels;
    }

    /**
     * Retourne une représentation textuelle de l'image.
     * Affiche le nom du fichier, le chemin et les dimensions de l'image.
     * 
     * @return chaîne de caractères décrivant l'image
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("nom du fichier : ").append(this.nomFichier).append("\n");
        sb.append("chemin du fichier : ").append(this.cheminFichier).append("\n");
        sb.append("dimensions : ").append(this.largeur).append(" x ").append(this.hauteur).append(" pixels\n");
        return sb.toString();
    }

    /**
     * Retourne la largeur de l'image.
     * 
     * @return largeur en pixels
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Retourne la hauteur de l'image.
     * 
     * @return hauteur en pixels
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * Retourne la table de décodage pour la composante rouge.
     * 
     * @return tableau d'octets contenant la table rouge
     */
    public byte[] getTableRouge() {
        return tableRouge;
    }

    /**
     * Retourne la table de décodage pour la composante verte.
     * 
     * @return tableau d'octets contenant la table verte
     */
    public byte[] getTableVerte() {
        return tableVerte;
    }

    /**
     * Retourne la table de décodage pour la composante bleue.
     * 
     * @return tableau d'octets contenant la table bleue
     */
    public byte[] getTableBleue() {
        return tableBleue;
    }

    /**
     * Retourne les données des pixels compressées.
     * 
     * @return tableau d'octets contenant les données des pixels
     */
    public byte[] getDonneesPixels() {
        return donneesPixels;
    }

    /**
     * Retourne le nom du fichier image.
     * 
     * @return nom du fichier
     */
    public String getNomFichier() {
        return nomFichier;
    }

    /**
     * Retourne le chemin complet vers le fichier image.
     * 
     * @return chemin du fichier
     */
    public String getCheminFichier() {
        return cheminFichier;
    }
}