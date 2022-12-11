package fr.isika.cda22.Projet_1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class LectureBin {
	
	public static void LectureBin() {
		
	
	System.out.println("Lecture du fichier bin");
try {
	RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin", "rw");
    raf.seek(0);
	int NumNoeud;
System.out.println("Lecture du fichier bin");
for (int j = 0; j < 58; j++) {
	String contenu = "";
	for(int i = 0; i < 58 ; i++) {
		contenu += raf.readChar();
	}
	for(int i = 0; i < 4 ; i++) {
		contenu += raf.readInt();
	}	
	NumNoeud= (int) ((raf.getFilePointer()-132)/132);
	System.out.println("Noeud " + NumNoeud + " : "+contenu +"\n");
}	
raf.close();
} catch (IOException e) {
  e.printStackTrace();}
	}
	
	
}
