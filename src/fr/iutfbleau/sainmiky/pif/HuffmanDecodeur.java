package fr.iutfbleau.sainmiky.pif;

import java.util.*;

/**
 * Décodeur Huffman pour les images PIF.
 * Cette classe permet de décoder des données d'images compressées avec
 * l'algorithme de Huffman
 * en gérant séparément les composantes rouge, verte et bleue.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class HuffmanDecodeur {
    /** Arbres de Huffman pour le décodage optimisé pour le rouge */
    private Arbre arbreRouge;
    /** Arbres de Huffman pour le décodage optimisé pour le vert */
    private Arbre arbreVert;
    /** Arbres de Huffman pour le décodage optimisé pour le bleu */
    private Arbre arbreBleu;

    /**
     * Index de la prochaine position à lire dans la chaîne de bits lors du
     * décodage.
     */
    private int prochainIndex;

    /**
     * Constructeur du décodeur Huffman.
     * Initialise les dictionnaires de décodage pour chaque composante de couleur
     * à partir des tables de longueurs fournies.
     * 
     * @param tableRouge table des longueurs pour la composante rouge
     * @param tableVerte table des longueurs pour la composante verte
     * @param tableBleue table des longueurs pour la composante bleue
     */
    public HuffmanDecodeur(byte[] tableRouge, byte[] tableVerte, byte[] tableBleue) {
        this.arbreRouge = this.construireArbreCanonique(tableRouge);
        this.arbreVert = this.construireArbreCanonique(tableVerte);
        this.arbreBleu = this.construireArbreCanonique(tableBleue);
    }

    /**
     * Construit un arbre de Huffman canonique en construisant manuellement puis en
     * wrappant avec Arbre.
     * 
     * @param tableLongueurs table contenant les longueurs de code pour chaque
     *                       valeur (0-255)
     * @return arbre de décodage canonique
     */
    private Arbre construireArbreCanonique(byte[] tableLongueurs) {

        PriorityQueue<PaireLongueurValeur> paires = new PriorityQueue<>(new CompPaireLongueurValeur());
        for (int valeur = 0; valeur < 256; valeur++) {
            int longueur = tableLongueurs[valeur] & 0xFF; // conversion en entier non signé
            if (longueur > 0) {
                paires.add(new PaireLongueurValeur(longueur, valeur));
            }
        }

        Noeud racine = new Noeud(-1, 0);

        int code = 0;
        int longueurPrecedente = 0;

        while (!paires.isEmpty()) {
            PaireLongueurValeur paire = paires.poll();
            int longueurActuelle = paire.getLongueur();

            code <<= (longueurActuelle - longueurPrecedente);

            String codeBinaire = Integer.toBinaryString(code);
            String codeStr = String.format("%" + longueurActuelle + "s", codeBinaire).replace(' ', '0');

            this.insererCodeDansArbre(racine, codeStr, paire.getValeur());

            code++;
            longueurPrecedente = longueurActuelle;
        }

        return new Arbre(racine);
    }

    /**
     * Insère un code et sa valeur dans l'arbre.
     * 
     * @param racine racine de l'arbre
     * @param code   code binaire (chaîne de '0' et '1')
     * @param valeur valeur à associer au code
     */
    private void insererCodeDansArbre(Noeud racine, String code, int valeur) {
        Noeud noeudCourant = racine;

        for (int i = 0; i < code.length(); i++) {
            char bit = code.charAt(i);

            if (bit == '0') {
                // gauche
                if (noeudCourant.getFGauche() == null) {
                    noeudCourant.setFGauche(new Noeud(-1, 0));
                }
                noeudCourant = noeudCourant.getFGauche();
            } else if (bit == '1') {
                // droite
                if (noeudCourant.getFDroit() == null) {
                    noeudCourant.setFDroit(new Noeud(-1, 0));
                }
                noeudCourant = noeudCourant.getFDroit();
            }
        }

        noeudCourant.setCoul(valeur);
    }

    /**
     * Décode un tableau de données binaires compressées en pixels.
     * Traite séquentiellement chaque pixel en décodant ses composantes rouge, verte
     * et bleue.
     * 
     * @param donneesPixels données binaires compressées à décoder
     * @param nombrePixels  nombre de pixels attendus dans l'image
     * @return tableau d'entiers représentant les pixels décodés (format RGB)
     */
    public int[] decoder(byte[] donneesPixels, int nombrePixels) {
        int[] pixels = new int[nombrePixels];

        int bitIndex = 0;
        int pixelIndex = 0;

        while (pixelIndex < nombrePixels) {
            int rouge = this.decoderComposanteAvecArbre(donneesPixels, bitIndex, this.arbreRouge.getRacine());
            bitIndex = this.prochainIndex;

            int vert = this.decoderComposanteAvecArbre(donneesPixels, bitIndex, this.arbreVert.getRacine());
            bitIndex = this.prochainIndex;

            int bleu = this.decoderComposanteAvecArbre(donneesPixels, bitIndex, this.arbreBleu.getRacine());
            bitIndex = this.prochainIndex;

            int pixel = (rouge << 16) | (vert << 8) | bleu;
            pixels[pixelIndex] = pixel;
            pixelIndex++;
        }
        return pixels;
    }

    /**
     * Décode une composante de couleur en utilisant la navigation dans l'arbre.
     * 
     * @param bits  chaîne de bits à analyser
     * @param debut position de départ dans la chaîne de bits
     * @param arbre racine de l'arbre de décodage
     * @return valeur décodée de la composante, ou -1 si aucun code n'est trouvé
     */
    private int decoderComposanteAvecArbre(byte[] donneesPixels, int debut, Noeud arbre) {
        Noeud noeudCourant = arbre;
        int totalBits = donneesPixels.length * 8;

        for (int i = debut; i < totalBits; i++) {
            int byteIndex = i / 8;// recup loctet actuel
            int bitShift = 7 - (i % 8);// recup la pos dans loctet
            int bit = (donneesPixels[byteIndex] >> bitShift) & 1;// recup le bit

            if (bit == 0) {
                noeudCourant = noeudCourant.getFGauche();
            } else {
                noeudCourant = noeudCourant.getFDroit();
            }

            if (noeudCourant == null) {
                return -1;
            }

            if (noeudCourant.getCoul() != -1) {
                this.prochainIndex = i + 1;
                return noeudCourant.getCoul();
            }
        }

        return -1;
    }
}
