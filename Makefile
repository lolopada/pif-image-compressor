SRC=src/fr/iutfbleau/sainmiky/pif
BLD=build/fr/iutfbleau/sainmiky/pif
OPT=-d build -classpath build -sourcepath src

# Visualisateur :
$(BLD)/Visualisateur.class: $(SRC)/Visualisateur.java $(BLD)/FenetreVisualisateur.class $(BLD)/LecteurPIF.class $(BLD)/ImagePIF.class $(BLD)/GestionChooser.class 
	javac $(OPT) $(SRC)/Visualisateur.java

$(BLD)/FenetreVisualisateur.class: $(SRC)/FenetreVisualisateur.java $(BLD)/NavigationSouris.class
	javac $(OPT) $(SRC)/FenetreVisualisateur.java

$(BLD)/LecteurPIF.class: $(SRC)/LecteurPIF.java $(BLD)/ImagePIF.class $(BLD)/HuffmanDecodeur.class
	javac $(OPT) $(SRC)/LecteurPIF.java

$(BLD)/ImagePIF.class: $(SRC)/ImagePIF.java
	javac $(OPT) $(SRC)/ImagePIF.java

$(BLD)/NavigationSouris.class: $(SRC)/NavigationSouris.java
	javac $(OPT) $(SRC)/NavigationSouris.java

$(BLD)/CompPaireLongueurValeur.class: $(SRC)/CompPaireLongueurValeur.java $(BLD)/PaireLongueurValeur.class
	javac $(OPT) $(SRC)/CompPaireLongueurValeur.java

$(BLD)/HuffmanDecodeur.class: $(SRC)/HuffmanDecodeur.java $(BLD)/PaireLongueurValeur.class $(BLD)/CompPaireLongueurValeur.class $(BLD)/Arbre.class $(BLD)/Noeud.class
	javac $(OPT) $(SRC)/HuffmanDecodeur.java

$(BLD)/PaireLongueurValeur.class: $(SRC)/PaireLongueurValeur.java
	javac $(OPT) $(SRC)/PaireLongueurValeur.java

# Convertisseur :
$(BLD)/Convertisseur.class: $(SRC)/Convertisseur.java $(BLD)/TableFreq.class $(BLD)/TableCode.class $(BLD)/Arbre.class $(BLD)/GestionChooser.class $(BLD)/FenetreConvertisseur.class
	javac $(OPT) $(SRC)/Convertisseur.java

$(BLD)/TableFreq.class: $(SRC)/TableFreq.java $(BLD)/Noeud.class $(BLD)/CompNoeud.class
	javac $(OPT) $(SRC)/TableFreq.java

$(BLD)/TableCode.class: $(SRC)/TableCode.java $(BLD)/Arbre.class $(BLD)/PaireValCode.class $(BLD)/CompPaireValCode.class
	javac $(OPT) $(SRC)/TableCode.java

$(BLD)/CompNoeud.class: $(SRC)/CompNoeud.java $(BLD)/Noeud.class
	javac $(OPT) $(SRC)/CompNoeud.java

$(BLD)/CompPaireValCode.class: $(SRC)/CompPaireValCode.java $(BLD)/PaireValCode.class
	javac $(OPT) $(SRC)/CompPaireValCode.java

$(BLD)/PaireValCode.class: $(SRC)/PaireValCode.java
	javac $(OPT) $(SRC)/PaireValCode.java

$(BLD)/GenerateurPif.class: $(SRC)/GenerateurPif.java
	javac $(OPT) $(SRC)/GenerateurPif.java

$(BLD)/FenetreConvertisseur.class: $(SRC)/FenetreConvertisseur.java $(BLD)/Redef.class $(BLD)/BFermer.class $(BLD)/BConvertir.class $(BLD)/BTable.class $(BLD)/TableFreq.class $(BLD)/TableCode.class
	javac $(OPT) $(SRC)/FenetreConvertisseur.java

$(BLD)/BFermer.class: $(SRC)/BFermer.java
	javac $(OPT) $(SRC)/BFermer.java

$(BLD)/BConvertir.class: $(SRC)/BConvertir.java $(BLD)/GenerateurPif.class $(BLD)/GestionChooser.class 
	javac $(OPT) $(SRC)/BConvertir.java

$(BLD)/BTable.class: $(SRC)/BTable.java
	javac $(OPT) $(SRC)/BTable.java

$(BLD)/Redef.class: $(SRC)/Redef.java
	javac $(OPT) $(SRC)/Redef.java

# Classes communes :
$(BLD)/Arbre.class: $(SRC)/Arbre.java $(BLD)/Noeud.class
	javac $(OPT) $(SRC)/Arbre.java

$(BLD)/Noeud.class: $(SRC)/Noeud.java
	javac $(OPT) $(SRC)/Noeud.java

$(BLD)/GestionChooser.class: $(SRC)/GestionChooser.java
	javac $(OPT) $(SRC)/GestionChooser.java

visualisateur.jar: $(BLD)/Visualisateur.class
	jar cvfe visualisateur.jar fr.iutfbleau.sainmiky.pif.Visualisateur -C build fr

convertisseur.jar: $(BLD)/Convertisseur.class 
	jar cvfe convertisseur.jar fr.iutfbleau.sainmiky.pif.Convertisseur -C build fr

run-visualisateur: visualisateur.jar
	java -jar visualisateur.jar $(img)

run-convertisseur: convertisseur.jar
	java -jar convertisseur.jar $(img) $(dest)

javadoc:
	javadoc -d doc -sourcepath src -subpackages fr.iutfbleau.sainmiky.pif

clean:
	rm -rf build doc visualisateur.jar convertisseur.jar 

.PHONY: clean run

#make run img=[image.pif]