package fr.isika.cda22.Projet_1;
import java.io.BufferedReader;
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
	public int ecrireNoeudFinBin(Noeud3 n,RandomAccessFile raf) {
		
		Stagiaire cle = n.getCle();
		try {			
			//RandomAccessFile raf = new RandomAccessFile(file, "rw");
		
			//raf.seek(TAILLE_NOEUD * (n.getNoeudFromCle(cle2).getNumeroNoeud()+1)); // on se place au bout du fichier binaire
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
	};
	//////////////////////////////////////////////////////////////////////////
	
	public void ajouterStagiaire(Noeud3 n, Noeud3 nParent,RandomAccessFile raf,File file) {
		
		
		 raf = new RandomAccessFile(file, "rw");
		
		//int curseur = ecrireNoeudFinBin(n,file); // on ajoute Cle, -1, -1, -1, numNoeud en fin de fichier binaire
		//ecrireNoeudArbre(n);
		Stagiaire cle = n.getCle();
		Stagiaire cleParent = nParent.getCle();
		
		System.out.println("Ajout .bin " + cle);
		
		 if(cle.getNom().compareTo(cleParent.getNom())==0) {
				if(nParent.getDoublon()==-1) {
					nParent.setDoublon(n.getNumeroNoeud());
					
					System.out.println("Ajout de " + n.getCle().getNom() + " doublon de " + nParent.getCle().getNom());
					
					System.out.println("Modification indice DB de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
			}
				else {
					
					//ajouterStagiaire(n, nParent);
					ajouterStagiaire(n, nParent.lireParentSuivant(nParent.getDoublon(), raf), raf,file);
				System.out.println("Ajout de " + n.getCle().getNom() + " doublon de " + nParent.getCle().getNom());				
				System.out.println("Modification indice DB de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
				System.out.println("++++++");			
				}	
		 }
		
		 else if (cle.compareTo(cleParent)>0) {
			 // si on va à droite
				if (nParent.getFilsDroit() == -1) {
					ecrireNoeudFinBin(n, file);
					// on met l'indice du noeud ici
					nParent.setFilsDroit(n.getNumeroNoeud());//
					modifierIndice(nParent.getNumeroNoeud()*TAILLE_NOEUD,n.getNumeroNoeud(),"FD");
					System.out.println(nParent);
					System.out.println("Ajout de " + n.getCle().getNom() + " fils droit de " + nParent.getCle().getNom());
					System.out.println("Modification indice FD de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
				}else {// on passe au FD, via l'indice
					//on lit le noeud à l'indice en question
					//System.out.println("Recherche ajout de " + n.getCle().getNom() + " vers " + nParent.noeudDroit.getCle());
					//modifierIndice(curseur, n.getNumeroNoeud(),"FD");
					ajouterStagiaire(n, nParent.lireParentSuivant(nParent.getFilsDroit(), raf), raf,file);
					
					System.out.println("  "+ nParent.getFilsDroit());
					System.out.println("Ajout de " + n.getCle().getNom() + " fils droit de " + nParent.getCle().getNom());
					System.out.println("Modification indice FD de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
					
				}
			}else if (cle.getNomLong().compareTo(cleParent.getNomLong())<0){// si on va à gauche
				if (nParent.getFilsGauche() == -1) {
					//System.out.println("Ajout de " + n.getCle().getNom() + " fils gaucheee de " + nParent.getCle().getNom());
					//System.out.println("Modification indice FG de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
					// on met l'indice du noeud ici
					//this.filsDroit = cle2.getNumeroNoeud();
					ecrireNoeudFinBin(n, file);
					nParent.setFilsGauche(n.getNumeroNoeud());
					System.out.println(nParent.getNumeroNoeud());
					System.out.println("Ajout de " + n.getCle().getNom() + " fils gauchet de " + nParent.getCle().getNom());
					System.out.println("Modification indice FG de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
					
				}else {// on passe au FD, via l'indice
					//on lit le noeud à l'indice en question
					//System.out.println("Recherche ajout de " + n.getCle().getNom() + " vers " + nParent.noeudGauche.getCle());
					//System.out.println("Recherche ajout de " + n.getCle().getNom() + " vers " + nParent.noeudGauche.getCle());
					ajouterStagiaire(nParent, nParent.lireParentSuivant(nParent.getFilsGauche(), raf), raf,file);
					System.out.println("Ajout de " + n.getCle().getNom() + " fils gaucheR de " + nParent.getCle().getNom());
					System.out.println("Modification indice FG de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
				}
			}	
	  }
///////////////////////////////////////////////////////////////////			
			
	
	private void modifierIndice(int curseur, int num, String fils) {
		
		try {
		RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinii.bin", "rw");
			
		if (fils == "FD") {
			System.out.println("avant "+(int)raf.getFilePointer());
			raf.seek(curseur+TAILLE_CLE_OCTET + TAILLE_IND_FG);
			raf.writeInt(num);
			raf.seek((int)raf.getFilePointer() + TAILLE_IND_DBL + TAILLE_NUM_NOEUD);
			System.out.println((int)raf.getFilePointer());
		}	if (fils == "FG")
		{
			
			
			
			System.out.println("avant "+(int)raf.getFilePointer());
			raf.seek(curseur+TAILLE_CLE_OCTET);
			System.out.println("curseur modif FG : "+(curseur + TAILLE_CLE_OCTET));
		    raf.writeInt(num);
			raf.seek((int)raf.getFilePointer() + TAILLE_IND_FD + TAILLE_IND_DBL + TAILLE_NUM_NOEUD);
			System.out.println((int)raf.getFilePointer());			
			
			
		}
		
		if(fils=="Doublon") {
			
			raf.seek(curseur-(TAILLE_NUM_NOEUD+TAILLE_IND_DBL));
			raf.writeInt(num);
			
		}
		
		raf.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//////////////////////////////////////////////	
	
	
	private Noeud3 lireParentSuivant(int numNoeudALire, RandomAccessFile raf) {
		
		try {
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

				int FG = raf.readInt();
				int FD = raf.readInt();
				int DBL = raf.readInt();
				int NumNoeud = raf.readInt();
	
				Stagiaire st = new Stagiaire(nom, prenom, dpt, id, annee);
				Noeud3 n = new Noeud3(st, FG, FD, DBL, NumNoeud);
				//raf.close();
				System.out.println("nouveau parent : " + n);
				return n;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	
	
///////////////////////////////////////////////////////////////////	
	
		
		public Noeud3 searchInBinFile(File file,Stagiaire cle,Noeud3 racine) {
			
			
			try {
				File fichierBIN= new File("src/main/java/fr/isika/cda22/Projet_1/fichbinii.bin");
				FileReader	fr = new FileReader(file);
				BufferedReader br= new BufferedReader(fr);
				String Line="";
				if (Line==null) {	return null	;		}
				else {
					while(br.ready()) {
						Line=br.readLine();
						if(Line.contains(cle.getNomLong())) {
						    if(racine.getCle().getNomLong()==this.cle.getNomLong()) {
						    	System.out.println(racine.getCle());
						    	return racine;
						    	
						    	}	
						}
						
						else {
							return null;
						}
					}
					}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
/////////////////////////////////////////////////
		
private Stagiaire successeur(Noeud3 racine) {
racine=racine.filsDroit;// a traduire pour du binaire...  avec la  taiille octets.
while(racine.filsGauche!=-1) {
	
racine=racine.filsGauche;
}
return racine.cle;
}
		
////////////////////////////////////////////////

private Stagiaire predecesseur(Noeud3 racine) {
	
racine=racine.filsGauche;
while(racine.filsDroit!=-1) {
racine=racine.filsDroit;
}
return racine.cle;
}	
		
///////////////////////////////////////////////////////////////////////////	
	
//public Noeud3 SupprimerNoeudStagiaire(Noeud3 racine,Stagiaire cle2) {
			
	try {			
		FileReader fr= new FileReader("src/main/java/fr/isika/cda22/Projet_1/fichbinii.bin");
		BufferedReader br= new BufferedReader(fr);
		String line;
		
	    while((line= br.readLine())!= null) { 
	    	
	    	
	
			if(this.cle.compareTo(cle2)==0) {
				
				return racine;
			}
		 if (racine.cle.compareTo(cle2)<0) {
				racine.filsGauche=SupprimerNoeudStagiaire(racine, cle2);
				}
			
			if(racine.cle.compareTo(cle2)>0){
				racine.filsGauche=SupprimerNoeudStagiaire(racine, cle2);
			}
			else {
				
				if(racine.filsGauche==-1 && racine.filsDroit==-1) {
					racine=null;
				}
				else if(racine.filsDroit!=-1) {
					racine.cle=sucesseur(racine);
					racine.filsDroit=SupprimerNoeudStagiaire(noeudsuivant, racine.cle);
				}
					else {
						racine.cle=predecesseur(racine);
						racine.filsGauche= SupprimerNoeudStagiaire(racine.filsGauche, racine.cle);
					}			
			}
        }
    }
	catch (IOException e) {
		e.printStackTrace();}
}
		
////////////////////////////////////////////////////////////////






}