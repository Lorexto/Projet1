package fr.isika.cda22.Projet_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StagiairesListe {
	
	int NbStagiaires;
	
	static String newLine = System.getProperty("line.separator");
	
	public static LinkedList<StagiaireSS> StagiaireList= new LinkedList<StagiaireSS>();
	
	public static LinkedList<StagiaireSS> CreerStagiairesFromFile (File fichier) {
		
	
			try {
				
				FileReader fr= new FileReader(fichier);
				Scanner sc = new Scanner(fichier);
                LineNumberReader lnr= new LineNumberReader(fr);
			
				StringTokenizer token=null;
				
				String nom="";
				String prenom="";
				String dpt="";
			    String id="";
			    String annee="";
			    String asterisk="*";
				
				
				while(sc.hasNextLine()&& lnr.getLineNumber()<72) {
					
					sc.useDelimiter("  ");
					lnr.getLineNumber();
					
					
					
					//token= new StringTokenizer(sc.nextLine(),""+"");
					
					if(StagiaireList.isEmpty()) {
					
					
				    nom+=lnr.readLine();
				    System.out.println(nom);
			prenom+=lnr.readLine();
					System.out.println(prenom);
					dpt+=lnr.readLine();
				
					System.out.println(dpt);
					id+=lnr.readLine();
					System.out.println(id);
					annee=lnr.readLine();
					System.out.println(annee);
					asterisk=lnr.readLine();
					lnr.getLineNumber();
					System.out.println(asterisk);
//					
					StagiaireSS stagiaire= new StagiaireSS(nom,prenom,dpt,id,annee);
					
					
					
					
					StagiaireList.add(stagiaire);
					System.out.println(stagiaire.toString());
					StagiaireList.size();
					System.out.println(StagiaireList.size());}
					
					else if(StagiaireList.size() <13) {
						
						nom=lnr.readLine();
						lnr.getLineNumber();
						System.out.println(nom);
					prenom=lnr.readLine();
							System.out.println(prenom);
							lnr.getLineNumber();
							dpt=lnr.readLine();
						
							System.out.println(dpt);
							lnr.getLineNumber();
							id=lnr.readLine();
							System.out.println(id);
							lnr.getLineNumber();
							annee=lnr.readLine();
							lnr.getLineNumber();
							System.out.println(annee);
							asterisk=lnr.readLine();
							lnr.getLineNumber();
							System.out.println(asterisk);
//							
							//for (int i=StagiaireList.size(); (i< 72/6);i++) {
								
							StagiaireSS stagiaire2= new StagiaireSS(nom,prenom,dpt,id,annee);
							System.out.println(stagiaire2);
							StagiaireList.add(stagiaire2);
							//System.out.println(stagiaire2.toString());
							StagiaireList.size();
							System.out.println(StagiaireList.size());
						
					}
					else {
						CreerStagiairesFromFile(fichier);
					}
					

					lnr.getLineNumber();
					
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return StagiaireList;
		
		
		
		
		
		
		
}
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		File FichierTexte= new File("C:\\\\\\\\Users\\\\\\\\Lorexto\\\\\\\\Desktop\\\\\\\\PROJET 1\\\\\\\\Projet1\\\\\\\\Projet_1\\\\\\\\src\\\\\\\\main\\\\\\\\java\\\\\\\\fr\\\\\\\\isika\\\\\\\\cda22\\\\\\\\Projet_1\\\\\\\\TestStagiairesdizaine.txt");
		     CreerStagiairesFromFile(FichierTexte);
		   ListIterator<StagiaireSS> listIterator = StagiaireList.listIterator();
		   StagiaireSS first = listIterator.next();
	        System.out.println("First:" + first);
	        StagiaireSS s2= listIterator.next();
	        System.out.println("2:"+s2);
		   
		   
		
		
		
	}
}
