package fr.iutfbleau.sainmiky.pif;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ActionListener pour le bouton de fermeture.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class BFermer implements ActionListener{
	/** La fenêtre à fermer.*/
	private JFrame fen;

	/**
     * Constructeur de la classe BFermer.
     * 
     * @param fen la fenêtre à fermer.
     */
	public BFermer(JFrame fen){
		this.fen = fen;
	}


	/**
     * ferme la fenêtre qui est en attribue.
     * 
     * @param e l'évènement de l'action.
     */
	@Override
	public void actionPerformed(ActionEvent e){
		this.fen.dispose();
		System.out.println("fin du programme");
	}
}