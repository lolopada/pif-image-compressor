package fr.iutfbleau.sainmiky.pif;

/**
 * Classe qui représente une paire (valeur, code).
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class PaireValCode {
    /** la valeur du qui représente la couleur*/
    private int valeur;
    /** le code associé à la valeur*/
    private String code;
    
    /** premier constructeur qui construit une paire en précisant les deux attributs.
     *
     * @param valeur la valeur de la couleur
     * @param code le code associé à la valeur
    */
    public PaireValCode(int valeur, String code) {
        this.valeur = valeur;
        this.code = code;
    }

    /** deuxième constructeur qui construit une paire avec seulement une valeur.
     *
     * @param valeur la valeur de la couleur
    */
    public PaireValCode(int valeur) {
        this.valeur = valeur;
        this.code = "";
    }
    
    /** fonction qui transforme la pair en String.
     *
     * @return un String représentant la paire.
    */
    @Override
    public String toString() {
        return "valeur : "+this.valeur+", code : "+this.code;
    }

    /** fonction equals qui compare la paire à une autre
     * fonction salvatrice qui permet d'utiliser le contains
     *
     * @param pvc La paire avec laquelle on veut comparé l'objet
     * 
     * @return un entier négatif, zéro ou positif selon que le premier argument
     *         est inférieur, égal ou supérieur au second
    */
    @Override
    public boolean equals(Object pvc) {
        if(pvc==null)
            return false;
        if(pvc instanceof PaireValCode){
            PaireValCode temp = (PaireValCode) pvc;
            if(temp.getValeur()==this.valeur)
                return true;
        }
        return false;
    }

    /**fonction qui renvoie la valeur de la paire
     * 
     * @return la valeur
    */
    public int getValeur() {
        return this.valeur;
    }

    /**fonction qui renvoie le code de la paire
     * 
     * @return le code
    */
    public String getCode() {
        return this.code;
    }
}