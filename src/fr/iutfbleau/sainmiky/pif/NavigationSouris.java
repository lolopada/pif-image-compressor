package fr.iutfbleau.sainmiky.pif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe gérant la navigation à la souris dans un JScrollPane.
 * Permet de faire glisser l'image en maintenant le bouton gauche de la souris
 * enfoncé.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 * @version 1.0
 */
public class NavigationSouris implements MouseListener, MouseMotionListener {
    /** Le JScrollPane sur lequel la navigation est appliquée */
    private JScrollPane scrollPane;
    /** Le dernier point de position de la souris lors du glissement */
    private Point dernierPoint;

    /**
     * Constructeur de la classe NavigationSouris.
     * Initialise la navigation sur le JScrollPane donné et y ajoute les listeners.
     * 
     * @param scrollPane le JScrollPane sur lequel appliquer la navigation
     */
    public NavigationSouris(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
        this.dernierPoint = null;

        this.scrollPane.addMouseListener(this);
        this.scrollPane.addMouseMotionListener(this);
    }

    /**
     * Vérifie si le défilement est nécessaire dans le JScrollPane.
     * 
     * @return true si l'image dépasse la taille visible, false sinon
     */
    private boolean isScrollNeeded() {
        JScrollBar barreHorizontale = this.scrollPane.getHorizontalScrollBar();
        JScrollBar barreVerticale = this.scrollPane.getVerticalScrollBar();
        
        return barreHorizontale.getMaximum() > barreHorizontale.getVisibleAmount() ||
               barreVerticale.getMaximum() > barreVerticale.getVisibleAmount();
    }

    /**
     * Méthode appelée lorsqu'un bouton de la souris est pressé.
     * Si c'est le bouton gauche et que le défilement est nécessaire, initialise le point de départ et change le
     * curseur.
     * 
     * @param e l'événement de la souris
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && this.isScrollNeeded()) {
            this.dernierPoint = e.getPoint();
            this.scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    /**
     * Méthode appelée lorsqu'un bouton de la souris est relâché.
     * Si c'est le bouton gauche, remet le curseur par défaut et arrête le
     * glissement.
     * 
     * @param e l'événement de la souris
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.dernierPoint = null;
            this.scrollPane.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Méthode appelée lors du glissement de la souris.
     * Si un glissement est en cours, déplace l'image selon le mouvement de la
     * souris.
     * 
     * @param e l'événement de la souris
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.dernierPoint != null) {
            this.deplacerImage(e);
        }
    }

    /**
     * Méthode privée qui effectue le déplacement de l'image dans le JScrollPane.
     * Calcule les nouvelles positions des barres de défilement en fonction du
     * mouvement de la souris.
     * 
     * @param e l'événement de la souris contenant la position actuelle
     */
    private void deplacerImage(MouseEvent e) {
        JScrollBar barreHorizontale = this.scrollPane.getHorizontalScrollBar();
        JScrollBar barreVerticale = this.scrollPane.getVerticalScrollBar();

        int nouvellePositionX = barreHorizontale.getValue() + (this.dernierPoint.x - e.getX());
        int nouvellePositionY = barreVerticale.getValue() + (this.dernierPoint.y - e.getY());

        if (nouvellePositionX < 0) {
            nouvellePositionX = 0;
        } else if (nouvellePositionX > barreHorizontale.getMaximum() - barreHorizontale.getVisibleAmount()) {
            nouvellePositionX = barreHorizontale.getMaximum() - barreHorizontale.getVisibleAmount();
        }

        if (nouvellePositionY < 0) {
            nouvellePositionY = 0;
        } else if (nouvellePositionY > barreVerticale.getMaximum() - barreVerticale.getVisibleAmount()) {
            nouvellePositionY = barreVerticale.getMaximum() - barreVerticale.getVisibleAmount();
        }

        barreHorizontale.setValue(nouvellePositionX);
        barreVerticale.setValue(nouvellePositionY);
        this.dernierPoint = e.getPoint();
    }

    /**
     * Méthode appelée lors du mouvement de la souris (sans glissement).
     * Implémentation vide car non utilisée.
     * 
     * @param e l'événement de la souris
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * Méthode appelée lors d'un clic de souris.
     * Implémentation vide car non utilisée.
     * 
     * @param e l'événement de la souris
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Méthode appelée lorsque la souris entre dans la zone.
     * Implémentation vide car non utilisée.
     * 
     * @param e l'événement de la souris
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Méthode appelée lorsque la souris sort de la zone.
     * Implémentation vide car non utilisée.
     * 
     * @param e l'événement de la souris
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}