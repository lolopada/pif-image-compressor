package fr.iutfbleau.sainmiky.pif;
import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Générateur de fichier au format PIF.
 * 
 * @author Loïc Sainton et Nicolas Miekisiak
 */
public class GenerateurPif{
	// caractéristiques du fichier :
	/** L'image sous la forme d'un BufferedImage.*/
	private BufferedImage buff;
	/** Les tables des codes canoniques.*/
	private TableCode tCanR;
	private TableCode tCanG;
	private TableCode tCanB;

	// variables pour le remplissage :
	/** tableau qui va servir pour l'écriture dans le fichier*/
	private byte[] concatenationMax; 
	/** indice qui indique le prochain emplacement*/
	private int indexConc;
	/** entier qui stock les prochain bits à rajouter au tableau.*/
	private Integer valAct;  // parce que parseByte() de Byte s'arrête à 127..., octets signés,etc
	/** taille encore disponible dans valAct (en bits)*/
	private int tailleDispo;

	/** Unique constructeur du générateur.
  	 * 
  	 * @param buff l'image à convertir
  	 * @param tCanR table des codes canoniques rouge.
  	 * @param tCanG table des codes canoniques vert.
  	 * @param tCanB table des codes canoniques bleu.
  	 */
	public GenerateurPif(BufferedImage buff, TableCode tCanR, TableCode tCanG, TableCode tCanB){
		this.buff=buff;
		this.tCanR=tCanR;
		this.tCanG=tCanG;
		this.tCanB=tCanB;

		this.concatenationMax = new byte[1024]; // tableau pour l'écriture qui écrit 1024 par 1024 octets.
		this.indexConc = 0;
		this.valAct = 0;
		this.tailleDispo = 8;
	}

	/** fonction qui réduit le code canonique en fonction de la taille disponible.
  	 * 
  	 * @param code le code canonique
  	 * @param trad flux utilisé pour l'écriture.
	 * 
  	 * @return le code réduit sous la forme d'un String
  	 */
	private String reduire(String code, DataOutputStream trad){
		while(code.length()>this.tailleDispo){
			String tp = code.substring(0,this.tailleDispo);
			code = code.substring(this.tailleDispo,code.length());
			this.tailleDispo -= tp.length();
			this.valAct += (Integer.parseInt(tp,2) & 0xFF);
			this.concatener();
			this.verifTab(trad);		
		}
		return code;
	}

	/** ajoute au tableau la valeur actuel et modifie les variables nécessaires.*/
	private void concatener(){
		this.concatenationMax[this.indexConc] = this.valAct.byteValue();
		this.indexConc+=1;
		this.valAct = 0;
		this.tailleDispo = 8;
	}

	/** vérifie que le tableau n'est pas rempli.
	 *  si il est rempli on l'écrit dans le flux et "vide" le tableau.
	 *  
	 * @param trad flux utilisé pour l'écriture.
	 */
	private void verifTab(DataOutputStream trad){
		if(this.indexConc==1023){
			try{
				trad.write(this.concatenationMax,0,this.indexConc);
			} catch(IOException e){
				System.err.println("erreur d'écriture");
				System.exit(1);
			}
			this.indexConc=0;
			this.concatenationMax = new byte[1024];
		}	
	}

	/** ajoute un nouveau code canonique à écrire.
	 * 
	 * @param code le code canonique
  	 * @param trad flux utilisé pour l'écriture.
	 */
	private void ajouter(String code, DataOutputStream trad){
		code = this.reduire(code, trad);
		this.tailleDispo -= code.length();
		this.valAct += (Integer.parseInt(code,2) & 0xFF)<<this.tailleDispo;
		if(this.tailleDispo==0){
			this.concatener();
			this.verifTab(trad);
		}
	}

	/** génère le fichier PIF à la destination indiquée.
	 * 
	 * @param dest la destination du fichier
	 */
	public void genererPif(String dest){
		int enTete = (this.buff.getWidth()<<16) | this.buff.getHeight();
		byte[] tabLongR = this.tCanR.toByte();
		byte[] tabLongG = this.tCanG.toByte();
		byte[] tabLongB = this.tCanB.toByte();

		try{
			FileOutputStream fPif = new FileOutputStream(dest);
			DataOutputStream trad = new DataOutputStream(fPif);		

			try{
				trad.writeInt(enTete);
				trad.write(tabLongR,0,256);
				trad.write(tabLongG,0,256);		
				trad.write(tabLongB,0,256);	
		
				for(int y=0; y<this.buff.getHeight(); y+=1){
					for(int x=0; x<this.buff.getWidth(); x+=1){
						Color coul = new Color(this.buff.getRGB(x,y));
						String codeR = this.tCanR.getCode(coul.getRed());
						String codeG = this.tCanG.getCode(coul.getGreen());
						String codeB = this.tCanB.getCode(coul.getBlue());
							
						this.ajouter(codeR, trad);
						this.ajouter(codeG, trad);
						this.ajouter(codeB, trad);
					}
				}
				// on vide la fin
				trad.write(this.concatenationMax,0,this.indexConc);
				
			}catch(IOException e){
				System.err.println("erreur d'écriture");
				System.exit(1);
			}
			try{
				trad.close();
			}catch(IOException e){
				System.err.println("erreur de fermeture");
				System.exit(1);
			}
		}catch(IOException e){
			System.err.println("erreur d'ouverture");
			System.exit(1);
		}
	}
}