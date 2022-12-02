package fr.isika.cda22.Projet_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Lecture {

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("C:\\Users\\Lorexto\\Desktop\\PROJET 1\\Projet1\\Projet_1\\src\\main\\java\\fr\\isika\\cda22\\Projet_1\\AdminList.txt");
			
			BufferedReader br = new BufferedReader(fr);
			
			String contenu = "";
			
			// tant qu'il y a qqc à lire
			while(br.ready()) {
				contenu += br.readLine();
				contenu += "\n";
			}
			
			System.out.println(contenu);
			
//			int unicode = fr.read();
//			char caractereLu = (char) unicode; //on caste l'entier en char, transforme l'unicode en lettre
			
			String texte = "";
			int caractereLu = 0;
			
			while ((caractereLu = fr.read()) != -1) { // -1 fin du fichier
				texte += (char) caractereLu;
			}
			
			System.out.println(texte);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
