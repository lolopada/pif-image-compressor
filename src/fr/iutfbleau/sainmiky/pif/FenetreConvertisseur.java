package fr.iutfbleau.sainmiky.pif;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
 
/**
 * La fenêtre principale du convertisseur.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class FenetreConvertisseur extends JFrame{

	/** L'image sous la forme d'un BufferedImage.*/
  	private BufferedImage im;
  	/** La destination sous la forme d'un String.*/
  	private String dest;
  	/** Le GridBagConstraints de la fenêtre.*/
  	private GridBagConstraints gbc;

  	/**table des fréquences rouges*/
  	private TableFreq tabFR;
    /**table des fréquences vertes*/
  	private TableFreq tabFG;
    /**table des fréquences bleues*/
  	private TableFreq tabFB;

  	/**table des codes initiaux rouges.*/
  	private TableCode tcR;
    /**table des codes initiaux verts.*/
  	private TableCode tcG;
    /**table des codes initiaux bleus.*/
  	private TableCode tcB;

  	/**table des codes canoniques rouges.*/
  	private TableCode tCanR;
    /**table des codes canoniques vertes.*/
  	private TableCode tCanG;
    /**table des codes canoniques bleues.*/
  	private TableCode tCanB;

  	/** Unique constructeur de la fenêtre.
  	 * 
  	 * @param im l'image à afficher
  	 * @param dest la destination (peut être null)
  	 */
  	public FenetreConvertisseur(BufferedImage im, String dest){
    	super();
    	this.im=im;
    	this.dest=dest;
    	this.gbc = new GridBagConstraints();
    	this.tabFR = null;
    	this.tabFG = null;
    	this.tabFB = null;
    	this.tcR = null;
    	this.tcG = null;
    	this.tcB = null;
    	this.tCanR = null;
    	this.tCanG = null;
    	this.tCanB = null;

  	}

  	/** fonction qui permet d'ajouter les tables de frequences.
  	 * 
  	 * @param tabFR table de frequences rouge.
  	 * @param tabFG table de frequences vert.
  	 * @param tabFB table de frequences bleu.
  	 */
  	public void ajouterTablesFrequence(TableFreq tabFR, TableFreq tabFG, TableFreq tabFB){
  		this.tabFR = tabFR;
  		this.tabFG = tabFG;
  		this.tabFB = tabFB;
  	}

  	/** fonction qui permet d'ajouter les tables de codes initiaux.
  	 * 
  	 * @param tcR table des codes initiaux rouge.
  	 * @param tcG table des codes initiaux vert.
  	 * @param tcB table des codes initiaux bleu.
  	 */
  	public void ajouterTablesCode(TableCode tcR, TableCode tcG, TableCode tcB){
  		this.tcR = tcR;
  		this.tcG = tcG;
  		this.tcB = tcB;
  	}

  	/** fonction qui permet d'ajouter les tables de codes canoniques.
  	 * 
  	 * @param tCanR table des codes canoniques rouge.
  	 * @param tCanG table des codes canoniques vert.
  	 * @param tCanB table des codes canoniques bleu.
  	 */
  	public void ajouterTablesCanonique(TableCode tCanR, TableCode tCanG, TableCode tCanB){
  		this.tCanR = tCanR;
  		this.tCanG = tCanG;
  		this.tCanB = tCanB;
  	}


  	/** fonction qui génère la fenetre.*/
  	public void genererFenetre(){
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setSize(750,500);
	        this.setLayout(new GridBagLayout());

	        this.ajouterImg(this.im,0,0);

	        JPanel pan = new JPanel();
	        pan.setLayout(new GridLayout(4,1,0,5));
	        pan.add(new JLabel("Tables :"));
	        JButton boutR = this.creerBouton("Voir les tables des rouges", this.tabFR, this.tcR, this.tCanR);
	        JButton boutG = this.creerBouton("Voir les tables des verts", this.tabFG, this.tcG, this.tCanG);
	        JButton boutB = this.creerBouton("Voir les tables des bleus", this.tabFB, this.tcB, this.tCanB);
	        pan.add(boutR);
	        pan.add(boutG);
	        pan.add(boutB);
	        this.ajouterPan(pan,5,1);

	        JButton boutFerm = new JButton("Fermer");
	        boutFerm.addActionListener(new BFermer(this));
	        JButton boutConv = new JButton("Convertir");
	        boutConv.addActionListener(new BConvertir(this.dest, this.im, this.tCanR, this.tCanG, this.tCanB));
	        this.gbc.gridx = 0;
		      this.gbc.gridy = 2;
	        this.add(boutFerm, this.gbc);
	        this.gbc.gridx = 6;
	        this.add(boutConv, this.gbc);
  	}

  	/** fonction pour créer un bouton
  	 * 
  	 * @param label le nom du bouton
  	 * @param tabF la table de fréquence
  	 * @param tc la table des codes initiaux
  	 * @param tCan la table des codes canoniques
  	 * 
  	 * @return un JButton  
  	 */
  	private JButton creerBouton(String label, TableFreq tabF, TableCode tc, TableCode tCan){
  	if(tabF!=null && tc!=null && tCan!=null){
	  	JButton bout = new JButton(label);
	        bout.addActionListener(new BTable(tabF, tc, tCan));
	        return bout;
	    }
	    System.err.println("veuillez remplir tous les attributs nécessaire");
	    return null;
  	}

    /** fonction qui ajoute l'image à la fenêtre
     * 
     * @param image L'image sous la forme d'un BufferedImage.
     * @param x coordonnée x dans le GridBagLayout
     * @param y coordonnée y dans le GridBagLayout
     */
  	private void ajouterImg(BufferedImage image, int x, int y){
  		this.gbc.gridx = x;
  		this.gbc.gridy = y;
  		this.gbc.gridwidth = 5;
  		this.gbc.gridheight = 1;
  		this.gbc.fill = GridBagConstraints.BOTH;
  		this.gbc.anchor = GridBagConstraints.CENTER;
  		this.gbc.weightx = 1.0;
  		this.gbc.weighty = 1.0;
  		this.gbc.insets = new Insets(0, 0, 5, 5);

      JComponent imageBase = new Redef(image);
      this.add(imageBase, this.gbc);

      this.resetGbc();
  	}

    /** fonction qui ajoute un panneau
     * 
     * @param pan Le panneau à ajouter.
     * @param x coordonnée x dans le GridBagLayout
     * @param y coordonnée y dans le GridBagLayout
     */
  	private void ajouterPan(JPanel pan, int x, int y){
  		this.gbc.gridx = x;
  		this.gbc.gridwidth = y;
  		this.gbc.weightx = 0.0;
  		this.gbc.weighty = 0.0;
  		this.gbc.fill = GridBagConstraints.NONE;
  		this.gbc.anchor = GridBagConstraints.NORTH;
  		this.gbc.insets = new Insets(5, 5, 5, 5);
  		this.add(pan, this.gbc);

        this.resetGbc();
  	}

    /** fonction qui réinitialise les paramètre du GridBagConstraint*/
  	private void resetGbc(){
  		this.gbc.gridx = 0;
  		this.gbc.gridy = 0;
  		this.gbc.gridwidth = 1;
  		this.gbc.gridheight = 1;
  		this.gbc.fill = GridBagConstraints.NONE;
  		this.gbc.anchor = GridBagConstraints.CENTER;
  		this.gbc.weightx = 0.0;
  		this.gbc.weighty = 0.0;
  		this.gbc.insets = new Insets(5, 5, 5, 5);
  	}
}