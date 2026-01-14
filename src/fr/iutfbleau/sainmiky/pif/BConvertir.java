package fr.iutfbleau.sainmiky.pif;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * ActionListener pour le bouton de conversion.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class BConvertir implements ActionListener{
	/** La destination sous la forme d'un String.*/
	private String dest;
	/** L'image sous la forme d'un BufferedImage.*/
	private BufferedImage buff;
	/** La table des codes canoniques rouges.*/
	private TableCode tCanR;
	/** La table des codes canoniques verts.*/
	private TableCode tCanG;
	/** La table des codes canoniques bleus.*/
	private TableCode tCanB;

	/** Unique constructeur du bouton ActionListener pour le bouton de conversion.
	 * 
	 * @param dest La destination sous la forme d'un String.
	 * @param buff L'image sous la forme d'un BufferedImage.
	 * @param tCanR La table des codes canoniques rouges.
	 * @param tCanG La table des codes canoniques verts.
	 * @param tCanB La table des codes canoniques bleus.
	 */
	public BConvertir(String dest, BufferedImage buff, TableCode tCanR, TableCode tCanG, TableCode tCanB){
		this.dest = dest;
		this.buff = buff;
		this.tCanR = tCanR;
		this.tCanG = tCanG;
		this.tCanB = tCanB;
	}

	/** convertit l'image en attribut en .pif.
	 * 
	 * @param e l'évènement de l'action.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		this.dest = GestionChooser.recupDest(this.dest);
		GenerateurPif gen = new GenerateurPif(this.buff, this.tCanR, this.tCanG, this.tCanB);
		gen.genererPif(this.dest);
		JOptionPane.showMessageDialog(null,"Fichier généré avec succès.");
	}
}