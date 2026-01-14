package fr.iutfbleau.sainmiky.pif;

/**
 * Classe représentant une paire longueur-valeur pour l'algorithme de Huffman.
 * Cette classe est utilisée pour associer une longueur de code avec une valeur
 * de pixel
 * lors de la génération des codes de Huffman pour la compression d'images PIF.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class PaireLongueurValeur {
    /** Longueur du code de Huffman pour cette valeur. */
    private int longueur;

    /** Valeur du pixel (0-255) associée à cette longueur de code. */
    private int valeur;

    /**
     * Constructeur pour créer une nouvelle paire longueur-valeur.
     * 
     * @param longueur longueur du code de Huffman
     * @param valeur   valeur du pixel (0-255)
     */
    public PaireLongueurValeur(int longueur, int valeur) {
        this.longueur = longueur;
        this.valeur = valeur;
    }

    /**
     * Retourne une représentation textuelle de la paire longueur-valeur.
     * 
     * @return chaîne de caractères au format "PaireLongeurValeur:
     *         (longueur,valeur)"
     */
    @Override
    public String toString() {
        return "PaireLongeurValeur: (" + longueur + "," + valeur + ")";
    }

    /**
     * Retourne la longueur du code de Huffman.
     * 
     * @return longueur du code
     */
    public int getLongueur() {
        return this.longueur;
    }

    /**
     * Retourne la valeur du pixel associée.
     * 
     * @return valeur du pixel (0-255)
     */
    public int getValeur() {
        return this.valeur;
    }
}