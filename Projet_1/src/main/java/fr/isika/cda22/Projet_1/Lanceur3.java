package fr.isika.cda22.Projet_1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
public class Lanceur3 {
	
	public final static int TAILLE_NOEUD = 2*20 + 4*3;
	public final static int TAILLE_NOM = 2*20;
	public final static int TAILLE_IND_FG = 2*4;
	public final static int TAILLE_IND_FD = 2*4;
	public final static int TAILLE_IND_DBL = 2*4;
	
	@SuppressWarnings("finally") // pour que le finally s'exécute correctement
	public static void main(String[] args) {
		Noeud3 n = new Noeud3();
		n = readTxt();
		System.out.println("Lecture du fichier bin");
		try {
			File file= new File("src/main/java/fr/isika/cda22/Projet_1/fichbinii.bin");
			RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinii.bin", "rw");
			raf.seek(0);
			for (int j = 0; j < 100 ; j++) {
				String contenu = "";
				for(int i = 0; i < 58 ; i++) {
					contenu += raf.readChar();
				}	
				for(int i = 0; i < 4 ; i++) {
					contenu += raf.readInt();
				}	
				System.out.println("Noeud " + 0 + " : "+contenu +"\n");
		
				
           n.searchInBinFile(file, n.getCle(), n);
           
			}	
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
	} // fin main
	
	public static Noeud3 readTxt() {
		
		try {
			Noeud3 n = null;
			Noeud3 nParent = null;
			Arbre3 a = new Arbre3();
			File file= new File("src/main/java/fr/isika/cda22/Projet_1/TestStagiairesdizaine.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			int cptNoeud = 0;
			while (br.ready()) {
						
				String[] data = new String[6]; // tableau de taille 6
				data[0] = br.readLine(); // nom
				data[1] = br.readLine(); // prenom
				data[2] = br.readLine(); //dpt
				data[3] = br.readLine(); //promo
				data[4] = br.readLine(); // année passée en String
				data[5] = br.readLine(); // pour lire le caractère *
				Stagiaire st = new Stagiaire(data[0], data[1], data[2], data[3], data[4]);
				if (cptNoeud == 0) {	
					nParent = new Noeud3(st, -1, -1, -1, cptNoeud); // on part toujours du parent racine
				}
				
				System.out.println("");
				//System.out.println("Lecture .DON de " + data[0]);
				n = new Noeud3(st, -1, -1, -1, cptNoeud); // le nouveau neoud correspond à la ligne lue dans le .DON
				cptNoeud++;
				
				n.ajouterStagiaire(n, nParent, null, null);
				//a.ajouter(n, nParent);	
				
			}//while br
			br.close();
			fr.close();
			//LectureBin.LectureBin();
			return n;
		} catch (IOException e) {
			System.out.println("on est dans l'eception du lanceur");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
} // fin lanceur2