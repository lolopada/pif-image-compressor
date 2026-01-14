package fr.iutfbleau.sainmiky.pif;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
 
/**
 * Classe qui redéfinie le paint component pour dessiner l'image.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class Redef extends JComponent {
  /**l'image à dessiner*/
  private BufferedImage im;

  /**Unique constructeur
   * 
   * @param im l'image a dessiné
  */
  public Redef(BufferedImage im){
    super();
    this.im=im;
  }

  /** fonction qui dessine l'image
   * 
   * @param pinceau l'objet de la classe Graphics qui dessine
  */
  @Override
  protected void paintComponent(Graphics pinceau) {
    Graphics secondPinceau = pinceau.create();
    if (this.isOpaque()) {
      secondPinceau.setColor(this.getBackground());
      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    // si l'image est carrée (à peu près)
    if((this.im.getWidth()-this.im.getHeight())<20){
      Image redim = this.im.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
      secondPinceau.drawImage(redim, 0, 0,400, 400, this);
    }
    else{
      Image redim = this.im.getScaledInstance(450, 350, Image.SCALE_SMOOTH);
      secondPinceau.drawImage(redim, 0, 0,450, 350, this);
    }
  }
}