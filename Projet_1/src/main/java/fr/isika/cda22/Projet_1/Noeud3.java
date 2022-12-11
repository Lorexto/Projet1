package fr.isika.cda22.Projet_1;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;



public class Noeud3 {
	
	public final static int TAILLE_MAX_NOM = 20; // en caractères ! pas en octets
	public final static int TAILLE_MAX_PRENOM = 20;
	public final static int TAILLE_MAX_DPT = 4;
	public final static int TAILLE_MAX_ID = 10;
	public final static int TAILLE_MAX_ANNEE = 4;
	public final static int TAILLE_CLE_OCTET = TAILLE_MAX_NOM * 2 + TAILLE_MAX_PRENOM * 2 + TAILLE_MAX_DPT * 2 + TAILLE_MAX_ID * 2
			+ TAILLE_MAX_ANNEE * 2; //116
	public final static int TAILLE_IND_FG = 4;
	public final static int TAILLE_IND_FD = 4;
	public final static int TAILLE_IND_DBL = 4;
	public final static int TAILLE_NUM_NOEUD = 4; // 1 int 4 octets
	public final static int TAILLE_NOEUD = TAILLE_CLE_OCTET + TAILLE_IND_FG + TAILLE_IND_FD + TAILLE_IND_DBL + TAILLE_NUM_NOEUD; // 128
	
	///////////////////////// attributs///////////////////////////////////////
	private Stagiaire cle;
	private int filsGauche;
	private int filsDroit;
	private int doublon;
	private int numeroNoeud;
	private int compteTours=0;
	
	///////////////////////// Constructeurs////////////////////////////////
	
	public Noeud3() {
		super();
	}
	public Noeud3(Stagiaire cle, int filsGauche, int filsDroit, int doublon, int numeroNoeud) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.doublon = doublon;
		this.numeroNoeud = numeroNoeud;
	}
	
	///////////////////// getters & setters/////////////////////////////////////
	public Stagiaire getCle() {
		return cle;
	}
	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}
	public int getNumeroNoeud() {
		return numeroNoeud;
	}
	public void setNumeroNoeud(int numeroNoeud) {
		this.numeroNoeud = numeroNoeud;
	}
	public int getFilsGauche() {
		return filsGauche;
	}
	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}
	public int getFilsDroit() {
		return filsDroit;
	}
	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}
	public int getDoublon() {
		return doublon;
	}
	public void setDoublon(int doublon) {
			this.doublon = doublon;
		}
	
// /////////////////////TOSTRING/////////////////////////////////////////////
	@Override
	public String toString() {
		return "[cle=" + cle + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + ", doublon=" + doublon
				+ ", numeroNoeud=" + numeroNoeud + "]";
	}
//////////////////////////////////////////////////////////////////////////////////////
	//////// ECRIT LES STAGIAIRES EN FIN DE FICHIER BIN/////////
///////////////////////////////////////////////////////////////////////////////////////	
	public int ecrireNoeudFinBin(Noeud3 n,RandomAccessFile raf) {
		
		Stagiaire cle = n.getCle();
		try {			
			raf.seek(raf.length());
			System.out.println(raf.length());
			raf.writeChars(cle.getNomLong());
			raf.writeChars(cle.getPrenomLong());
			raf.writeChars(cle.getDptLong());
			raf.writeChars(cle.getIdLong());
			raf.writeChars(cle.getAnneeLong());
			raf.writeInt(n.getFilsDroit());
			raf.writeInt(n.getFilsGauche());
			raf.writeInt(n.getDoublon());
			raf.writeInt(n.getNumeroNoeud());
			int curseur = (int)raf.getFilePointer();
			System.out.println(curseur);
			raf.close();
			return curseur;
		} catch (IOException e) {
			e.printStackTrace(); 
			return -1;}
	}
	//////////////////////////////////////////////////////////////////////////
	
	public void ajouterStagiaire(Noeud3 n, Noeud3 nParent,RandomAccessFile raf) throws FileNotFoundException, EOFException {
		
		File fileBIN= new File ("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin");
		raf = new RandomAccessFile(fileBIN, "rw");
		Stagiaire cle = n.getCle();
		Stagiaire cleParent = nParent.getCle();	
		System.out.println("Ajout .bin " + cle);
		
//////SI NOM ET PRENOM EGAUX. NB= NE FONCTIONNE QUE AVEC LE CONTAINS

		 if ( (cleParent.getNom().contains(cle.getNom())) && (cleParent.getPrenom().contains(cle.getPrenom())) ) {
			 if(nParent.getDoublon()==-1) {
					ecrireNoeudFinBin(n, raf);
					nParent.setDoublon(n.getNumeroNoeud());
					modifierIndice(nParent.getNumeroNoeud() * TAILLE_NOEUD, n.getNumeroNoeud(),"DBL", raf);
					System.out.println("Ajout de " + n.getCle().getNom() + " doublon de " + nParent.getCle().getNom());
					System.out.println("Modification indice DBL de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
					}
				else {
				ajouterStagiaire(n, nParent.lireParentSuivant(nParent.getDoublon(), raf), raf);
				System.out.println("Ajout de " + n.getCle().getNom() + " doublon de " + nParent.getCle().getNom());				
				System.out.println("++++++");			
				}
		 }
		//if (cle.compareTo(cleParent)>0){// SI Valeur nouveau nom inferieur a valeur nom racine on va a gauche/////		
		 else {
			 if(cle.compareTo(cleParent)<0) { // si on va à droite
				if (nParent.getFilsDroit() == -1) {
					ecrireNoeudFinBin(n, raf);
					//int curseur = ecrireNoeudFinBin(n, raf) - TAILLE_NOEUD; 
					System.out.println("Ajout de " + n.getCle().getNom() + " fils droit de " + nParent.getCle().getNom());
					System.out.println("Modification indice FD de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
					// on met l'indice du noeud ici
					nParent.setFilsDroit(n.getNumeroNoeud());
					
					System.out.println("noued du parent " + nParent.getNumeroNoeud());
					//System.out.println("curseur correspondant : " + (nParent.getNumeroNoeud() * TAILLE_NOEUD));
					modifierIndice(nParent.getNumeroNoeud() * TAILLE_NOEUD, n.getNumeroNoeud(),"FD", raf);
					System.out.println(nParent);
			}else {// on passe au FD, via l'indice
				System.out.println("parent de " + n.getCle().getNom() + " : " + nParent.getCle().getNom());
				System.out.println("on veut aller au neu "+ nParent.getFilsDroit());
				ajouterStagiaire(n, nParent.lireParentSuivant(nParent.getFilsDroit(), raf), raf);
			}
		 }
//if (cle.compareTo(cleParent)>0){// SI Valeur nouveau nom inferieur a valeur nom racine on va a gauche/////
		 else {
					if (nParent.getFilsGauche() == -1) {
						ecrireNoeudFinBin(n, raf);
						//int curseur = ecrireNoeudFinBin(n, raf) - TAILLE_NOEUD;
						//System.out.println(curseur);​
						System.out.println("Ajout de " + n.getCle().getNom() + " fils gauche de " + nParent.getCle().getNom());
						System.out.println("Modification indice FG de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
						nParent.setFilsGauche(n.getNumeroNoeud());
						System.out.println(nParent.getNumeroNoeud());
						modifierIndice(nParent.getNumeroNoeud() * TAILLE_NOEUD, n.getNumeroNoeud(),"FG", raf);
						System.out.println(nParent);

					}else {// on passe au FD, via l'indice
						//System.out.println("Recherche ajout de " + n.getCle().getNom() + " vers " + nParent.noeudGauche.getCle());
						System.out.println("parent de " + n.getCle().getNom() + " : " + nParent.getCle().getNom());
						ajouterStagiaire(n, nParent.lireParentSuivant(nParent.getFilsGauche(), raf), raf);					
						}
			}
		 }
		}
	  
///////////////////////////////////////////////////////////////////////////			
/////////////////MODIFIE LES INDICES FG FD DOUBLON AU FUR ET A MESURE	
///////////////////////////////////////////////////////////////////////////	
	public void modifierIndice(int curseur, int num, String fils,RandomAccessFile raf) {
		
		try {
		 raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin", "rw");
			
		if (fils == "FD") {
			System.out.println("avant "+(int)raf.getFilePointer());
			raf.seek(curseur+TAILLE_CLE_OCTET + TAILLE_IND_FG);
			raf.writeInt(num);
			raf.seek((int)raf.getFilePointer() + TAILLE_IND_DBL + TAILLE_NUM_NOEUD);
			System.out.println((int)raf.getFilePointer());
		}	
		else if (fils == "FG"){	
			System.out.println("avant "+(int)raf.getFilePointer());
			raf.seek(curseur+TAILLE_CLE_OCTET);
			System.out.println("curseur modif FG : "+(curseur + TAILLE_CLE_OCTET));
		    raf.writeInt(num);
			raf.seek((int)raf.getFilePointer() + TAILLE_IND_FD + TAILLE_IND_DBL + TAILLE_NUM_NOEUD);
			System.out.println((int)raf.getFilePointer());				
		}
		else if(fils=="DBL") {
			System.out.println("avant "+(int)raf.getFilePointer());
			raf.seek(curseur+(TAILLE_CLE_OCTET+TAILLE_IND_FG+TAILLE_IND_FD));
			raf.writeInt(num);
			raf.seek((int)raf.getFilePointer() + TAILLE_NUM_NOEUD);
			System.out.println((int)raf.getFilePointer());		
		}
		raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//////////////////////////////////////////////	
	// LIT LES NOEUDS SELON LE NUMERO CHOISI
//////////////////////////////////////////////	
	public Noeud3 lireParentSuivant(int numNoeudALire, RandomAccessFile raf) throws EOFException {
		
		try {
			while(numNoeudALire<= raf.length()/132) {
			raf.seek(numNoeudALire * TAILLE_NOEUD);
			System.out.println("dans le lire" + (int)raf.getFilePointer());
				String nom = "";
				for(int i = 0; i < 20 ; i++) {
					nom += raf.readChar();
				}	
				String prenom = "";
				for(int i = 0; i < 20 ; i++) {
					prenom += raf.readChar();
				}	
				String dpt = "";
				for(int i = 0; i < 4 ; i++) {
					dpt += raf.readChar();
				}	
				String id = "";
				for(int i = 0; i < 10 ; i++) {
					id += raf.readChar();
				}	
				String annee = "";
				for(int i = 0; i < 4 ; i++) {
					annee += raf.readChar();
				}	
//////////////////// LIRE les indices//////////////////////////////
				int FG = raf.readInt();
				int FD = raf.readInt();
				int DBL = raf.readInt();
				int NumNoeud = raf.readInt();
	
				Stagiaire st = new Stagiaire(nom, prenom, dpt, id, annee);
				Noeud3 n = new Noeud3(st, FG, FD, DBL, NumNoeud);
				//raf.close();
				System.out.println("nouveau parent : " + n);
				return n;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
		
	}

///////////////////////////////////////////////////////////////////	
	
public String lireNom(int numNoeudALire, RandomAccessFile raf) throws EOFException {	
	String nom="";
	try {
		raf.seek(numNoeudALire * TAILLE_NOEUD);
		System.out.println("Position curseur :  " + (int)raf.getFilePointer());		
		for(int j = 0; j < 20 ; j++) {
			nom += raf.readChar();}
		System.out.println(nom);
		}catch (IOException e) {
			e.printStackTrace();	
		}
		return nom;
}
	
/////////////////////////////////////////////////////////////////	
		//@SuppressWarnings("unused")
public Noeud3 searchInBinFile(Noeud3 n,RandomAccessFile raf, String nomRech) throws IOException {	
	try {	
		raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin", "rw");
		int sizeFile= (int) raf.length();
		int maxNoeuds= sizeFile/132;
		System.out.println(sizeFile+"HELLO");
		int j;
		int h;
		raf.getFilePointer();
		System.out.println(raf.getFilePointer());
		// pour limiter la recherche en nombre noeuds
		for(int i = nomRech.length(); i < TAILLE_MAX_NOM; i++) {
			nomRech= nomRech+"*"; // Transformer le nom en recherche comme les noms en fichier BIN pour comparaison
		    }
		System.out.println("NOM RECHERCHE:  "+ nomRech);
		// pour limiter la recherche en nombre noeuds
		 for ( h=0;h<maxNoeuds;h++) { 		
		// on compare la lecture des noms du fichierBIN avec le nom recherche
		if(n.lireNom(h, raf).compareTo(nomRech)==0){ 
	///////// LECTRURE PRENOMS DPT ID ANNEE FG FD DBL POS///////////
			String prenomBIN = "";
			for( j =0;  j<20 ; j++) {
			prenomBIN += raf.readChar();}
			System.out.println(prenomBIN);	
			String dptBIN = "";
			for(j =0;  j<4 ; j++) {
			dptBIN += raf.readChar();}
			System.out.println(dptBIN);
			String idBIN = "";
			for(j =0;  j<10 ; j++) {
			idBIN += raf.readChar();}
			System.out.println(idBIN);	
			String anneeBIN = "";
			for( j =0;  j<4 ; j++) {
			anneeBIN += raf.readChar();}
			System.out.println(anneeBIN);
/////////// FG-FD-DBL-NumeroNoeud/////////
			int FG = raf.readInt();
			System.out.println(FG);
			int FD = raf.readInt();
			System.out.println(FD);
			int DBL = raf.readInt();
			System.out.println(DBL);
			int NumNoeud = raf.readInt();
			System.out.println(NumNoeud);
			Stagiaire stTrouve = new Stagiaire(n.lireNom(h, raf), prenomBIN, dptBIN, idBIN, anneeBIN);
			Noeud3 n2 = new Noeud3(stTrouve, FG, FD, DBL, NumNoeud);
			System.out.println(" VOICI LE STAGIAIRE TROUVE:   "+n.lireNom(h, raf));
			System.out.println(n2.getCle());
							
			return n2;	}
	   } 
}catch (IOException e) {
	e.printStackTrace();
	raf.close();}
	return null;	}
		
/////////////////////////////////////////////////
///////////////////////////////////////////////////		
//	TROUVE ET ECRIT LES SUCCESSEURS DANS LE FICHIER BIN
//////////////////////////////////////////////////
public Noeud3 successeur(Noeud3 n,RandomAccessFile raf) throws IOException {


int PositionARemplacer= ((int) raf.getFilePointer());

n=lireParentSuivant(n.filsDroit, raf);
String nomFinal= n.getCle().getNom();
String prenomFinal=n.getCle().getPrenom();
String dptFinal=n.getCle().getDpt();
String idFinal= n.getCle().getId();
String anneeFinal=n.getCle().getAnnee();
RemplaceString(PositionARemplacer,nomFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2,prenomFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2,dptFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2,idFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2+TAILLE_MAX_ID*2,anneeFinal, raf);
System.out.println(n.getCle());
	System.out.println("le successeur est:  "+n);

// a traduire pour du binaire...  avec la  taiille octets.
while(n.filsGauche!=-1) {
	System.out.println(n.getFilsGauche());
    PositionARemplacer= ((int) raf.getFilePointer());
	n=lireParentSuivant(n.filsGauche, raf);
	nomFinal= n.getCle().getNom();
	prenomFinal=n.getCle().getPrenom();
    dptFinal=n.getCle().getDpt();
	idFinal= n.getCle().getId();
	anneeFinal=n.getCle().getAnnee();
	RemplaceString(PositionARemplacer,nomFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2,prenomFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2,dptFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2,idFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2+TAILLE_MAX_ID*2,anneeFinal, raf);
	System.out.println(n.getCle());
System.out.println("Le successeur final est  est:   "+ n.cle.toString());
}
return n;
}

////////////////////////////////////////////
///////////////////////////////////////////////////		
//TROUVE ET ECRIT LES PREDECESSEURS DANS LE FICHIER BIN
//////////////////////////////////////////////////

public Noeud3 predecesseur(Noeud3 n,RandomAccessFile raf) throws IOException {
System.out.println(n.getCle());
int PositionARemplacer= ((int) raf.getFilePointer());	
n= lireParentSuivant(n.filsGauche, raf);
System.out.println(n.getCle());
raf.seek(n.getNumeroNoeud());
//init variables a remplacer
String nomFinal= n.getCle().getNom();
String prenomFinal=n.getCle().getPrenom();
String dptFinal=n.getCle().getDpt();
String idFinal= n.getCle().getId();
String anneeFinal=n.getCle().getAnnee();
// appel methode modif
RemplaceString(PositionARemplacer,nomFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2,prenomFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2,dptFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2,idFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2+TAILLE_MAX_ID*2,anneeFinal, raf);
System.out.println(n.getCle());
//
while(n.filsDroit!=-1) {
	System.out.println(n.getCle());
	PositionARemplacer= ((int) raf.getFilePointer());
	n=lireParentSuivant(n.filsDroit, raf);
	
	// EXECUTER Le changement en ecriture
	nomFinal= n.getCle().getNom();
	prenomFinal=n.getCle().getPrenom();
    dptFinal=n.getCle().getDpt();
	idFinal= n.getCle().getId();
	anneeFinal=n.getCle().getAnnee();
	RemplaceString(PositionARemplacer,nomFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2,prenomFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2,dptFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2,idFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2+TAILLE_MAX_ID*2,anneeFinal, raf);
	System.out.println(n.getCle());
System.out.println("Le predecesseur alphabetique est : "+ n.cle.toString());

}
return n;
}
/////////////////////////////////////////////////
///// METHODE REMPLACER STRINGS DANS BIN
////////////////////////////////////////////////////
private void RemplaceString(int PositionARemplacer, String donneeARemplacer, RandomAccessFile raf) {

try {			
raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin", "rw");
raf.seek(PositionARemplacer);
raf.writeChars(donneeARemplacer);

}
catch (IOException e) {
e.printStackTrace();}
}
/////////////////////////////////////////////////////////////////////////////
public void SetToVoid(int PositionARemplacer, Noeud3 aEffacer,RandomAccessFile raf) throws IOException {
	try {			
		raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin", "rw");
		
		String VOID = "";
		for( int j =0;  j<20 ; j++) {
		raf.writeChars(VOID);}
	
		
	}
	catch (IOException e) {
	e.printStackTrace();}
	}

////////////////////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////////////////////////
//////////////////METHODE POUR SUPPRIMER NOEUD FICHIER BIN/////////////
///////////////////////////////////////////////////////////////////////////
public Noeud3 SupprimerNoeudStagiaireV2(Noeud3 aEffacer,RandomAccessFile raf) {
	try {			
		raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin", "rw");
		searchInBinFile(aEffacer, raf, aEffacer.getCle().getNomLong()).getNumeroNoeud();
		aEffacer.getFilsDroit();
		aEffacer.getFilsGauche();
		raf.seek(aEffacer.numeroNoeud*132);
		   if(aEffacer==null) {
			   System.out.println("Element inexistant");
			   return null;}  // 
		   
		   if(aEffacer.filsGauche==-1 && aEffacer.filsDroit==-1) {
			   System.out.println((int) raf.getFilePointer());
			   int positionElement=aEffacer.numeroNoeud*132;
			  raf.seek(positionElement);
			  String VOID = " ";
				for( int j =0;  j<64 ; j++) {
				raf.writeChars(VOID);}			   
				}
		   else if (aEffacer.getFilsDroit()!=-1) {
					aEffacer.setCle(successeur(aEffacer, raf).cle);
					System.out.println(aEffacer.cle.toString());
					//Noeud3 FilsaDroite= lireParentSuivant(aEffacer.getFilsDroit(), raf);
					//aEffacer= lireParentSuivant(aEffacer.filsDroit, raf);
					aEffacer= SupprimerNoeudStagiaireV2(lireParentSuivant(aEffacer.filsDroit, raf), raf);
					}
		   else { 
					aEffacer.getFilsGauche();
					aEffacer.setCle(predecesseur(aEffacer, raf).cle);
					//Noeud3 n2= lireParentSuivant(indiceFD, raf);
				//	Noeud3 nGauche= lireParentSuivant(aEffacer.filsGauche, raf);
					aEffacer= SupprimerNoeudStagiaireV2(lireParentSuivant(aEffacer.filsGauche, raf), raf);		
				}
	       return aEffacer;
	}
	catch (IOException e) {
		e.printStackTrace();}
	return aEffacer;
}
////////////////////////////////////////////////FIN METHODE/////////////////////////////////////////////



}