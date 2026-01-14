package fr.iutfbleau.sainmiky.pif;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

/**
 * Classe représentant une fenêtre pour visualiser des images.
 * Cette classe permet d'afficher une image dans une fenêtre Swing
 * avec des fonctionnalités de navigation par souris.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class FenetreVisualisateur {
    /** L'image à afficher */
    private BufferedImage image;
    /** Le nom de l'image pour le titre de la fenêtre */
    private String nomImage;
    /** La fenêtre principale de l'application */
    private JFrame fenetre;
    /** Le panneau de défilement contenant l'image */
    private JScrollPane scrollPane;
    /** Le label contenant l'image */
    private JLabel labelImage;
    /** Le gestionnaire de navigation à la souris */
    private NavigationSouris navigation;
    /** La marge horizontale pour le calcul de la taille de la fenêtre */
    private int margeLargeur;
    /** La marge verticale pour le calcul de la taille de la fenêtre */
    private int margeHauteur;

    /**
     * Constructeur de la fenêtre visualisateur avec marges par défaut.
     * Prend directement une BufferedImage à afficher.
     * 
     * @param image    l'image à visualiser
     * @param nomImage le nom à afficher dans le titre de la fenêtre
     */
    public FenetreVisualisateur(BufferedImage image, String nomImage) {
        this(image, nomImage, 100, 150);
    }

    /**
     * Constructeur de la fenêtre visualisateur avec marges personnalisées.
     * Prend directement une BufferedImage à afficher avec des marges spécifiques.
     * 
     * @param image        l'image à visualiser
     * @param nomImage     le nom à afficher dans le titre de la fenêtre
     * @param margeLargeur la marge horizontale pour le calcul de la taille de
     *                     fenêtre
     * @param margeHauteur la marge verticale pour le calcul de la taille de fenêtre
     */
    public FenetreVisualisateur(BufferedImage image, String nomImage, int margeLargeur, int margeHauteur) {
        this.image = image;
        this.nomImage = nomImage;
        this.margeLargeur = margeLargeur;
        this.margeHauteur = margeHauteur;
        if (this.image != null) {
            this.creerFenetre();
        }
    }

    /**
     * Crée et configure la fenêtre d'affichage de l'image.
     * Met en place tous les composants Swing nécessaires :
     * - La fenêtre principale
     * - Le label contenant l'image
     * - Le panneau de défilement
     * - Le gestionnaire de navigation souris
     */
    private void creerFenetre() {
        this.fenetre = new JFrame("Visualisateur - " + this.nomImage);
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.fenetre.setResizable(true);

        this.labelImage = new JLabel(new ImageIcon(this.image));

        this.labelImage.setHorizontalAlignment(JLabel.CENTER);
        this.labelImage.setVerticalAlignment(JLabel.CENTER);

        this.scrollPane = new JScrollPane(this.labelImage);
        this.scrollPane.setPreferredSize(this.calculerTailleFenetre());
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        this.fenetre.add(this.scrollPane);

        this.fenetre.pack();
        this.fenetre.setLocationRelativeTo(null);

        this.navigation = new NavigationSouris(this.scrollPane);
    }

    /**
     * Affiche la fenêtre à l'écran.
     * Vérifie que l'image et la fenêtre sont bien initialisées avant l'affichage.
     */
    public void afficher() {
        if (this.image == null || this.fenetre == null) {
            System.out.println("image pas chargee bug");
            return;
        }

        this.fenetre.setVisible(true);
        // System.out.println("Image affichée : "+this.nomImage+"("+this.image.getWidth()+"x"+this.image.getHeight()+" pixels)");
    }

    /**
     * Retourne l'image chargée.
     * 
     * @return l'image sous forme de BufferedImage, ou null si aucune image n'est
     *         chargée
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Retourne la fenêtre principale.
     * 
     * @return la JFrame de la fenêtre, ou null si la fenêtre n'est pas créée
     */
    public JFrame getFenetre() {
        return this.fenetre;
    }

    /**
     * Calcule la taille optimale de la fenêtre en fonction de la taille de l'image
     * et des dimensions de l'écran.
     * Chaque dimension (largeur/hauteur) est calculée indépendamment pour éviter
     * les espaces blancs inutiles.
     * 
     * @return la dimension optimale pour la fenêtre
     */
    private Dimension calculerTailleFenetre() {
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize(); // taille ecran utilisateur

        int largeurMax = tailleEcran.width - this.margeLargeur;
        int hauteurMax = tailleEcran.height - this.margeHauteur;
        int imageLargeur = this.image.getWidth();
        int imageHauteur = this.image.getHeight();

        int largeurFenetre = (imageLargeur < largeurMax) ? imageLargeur : largeurMax;
        int hauteurFenetre = (imageHauteur < hauteurMax) ? imageHauteur : hauteurMax;
        return new Dimension(largeurFenetre+5, hauteurFenetre+5); //pour contrer les barres du jscrollpane meme sil elles sont en never ca enleve un petit espace je comprend pas
    }
}