package fr.iutfbleau.sainmiky.pif;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ActionListener pour un bouton de visualisation des tables.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class BTable implements ActionListener{
	/** la table des fréquences*/
	private TableFreq tabF;
	/** la table des codes initiaux*/
	private TableCode tc;
	/** la table des codes canonique*/
	private TableCode tCan;

	/** Unique constructeur de l'ActionListener du bouton de visualisation des tables
	 * 
	 * @param tableFrequence la table des fréquences
	 * @param tableCode la table des codes initiaux
	 * @param tableCanonique la table des codes canonique
	 */
	public BTable(TableFreq tableFrequence, TableCode tableCode, TableCode tableCanonique){
		this.tabF = tableFrequence;
		this.tc = tableCode;
		this.tCan = tableCanonique;
	}

	/**
     * génère la fenêtre des tables.
     * 
     * @param e l'évènement de l'action.
     */
	@Override
	public void actionPerformed(ActionEvent e){
		JFrame fenTable = new JFrame();
		fenTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fenTable.setSize(500,500);

       	JPanel pan = new JPanel(new GridLayout(257,4));
       	pan.add(new JLabel("valeur"));
       	pan.add(new JLabel("fréquences"));
       	pan.add(new JLabel("codes initiaux"));
       	pan.add(new JLabel("canoniques"));
       	Integer freqTemp;
       	String tCodeTemp;
       	for(int i = 0; i<256; i+=1){
       		pan.add(new JLabel(i+""));
       		freqTemp=this.tabF.getVal(i);
       		if(freqTemp==null)
       			freqTemp=0;
       		pan.add(new JLabel(freqTemp+""));

       		tCodeTemp=this.tc.getCode(i);
       		if(tCodeTemp==null)
       			tCodeTemp="";
       		pan.add(new JLabel(tCodeTemp));

       		tCodeTemp=this.tCan.getCode(i);
       		if(tCodeTemp==null)
       			tCodeTemp="";
       		pan.add(new JLabel(tCodeTemp));
       	}
       	JScrollPane vue = new JScrollPane(pan);
       	fenTable.add(vue);
       	fenTable.setVisible(true);
	}
}