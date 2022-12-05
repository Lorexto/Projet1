package fr.isika.cda22.Projet_1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Lanceur {
	@SuppressWarnings("finally") // pour que le finally s'exécute correctement
	public static void main(String[] args) {
		Arbre monArbre = new Arbre();
		try {
			FileReader fr = new FileReader(
					"C:\\\\\\\\Users\\\\\\\\Lorexto\\\\\\\\Desktop\\\\\\\\PROJET 1\\\\\\\\Projet1\\\\\\\\Projet_1\\\\\\\\src\\\\\\\\main\\\\\\\\java\\\\\\\\fr\\\\\\\\isika\\\\\\\\cda22\\\\\\\\Projet_1\\\\\\\\STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr);
			// tant qu'il y a qqc à lire
			while (br.ready()) {
				int cpt = 0;
				while (cpt < 6) { //pour compter les données relatives à un stagiaire
					try {
						String[] data = new String[6]; // tableau de taille 6
						data[0] = br.readLine(); // nom
						data[1] = br.readLine(); // prenom
						data[2] = br.readLine(); //dpt
						data[3] = br.readLine(); //promo
						data[4] = br.readLine(); // année passée en String
						data[5] = br.readLine(); // pour lire le caractère *
						cpt++;
						Stagiaire st = new Stagiaire(data[0], data[1], data[2], data[3], data[4]); // création d'un nouveau stagiaire
						monArbre.ajouter(st); // ajout dans l'arbre
						
						// System.out.println(st.getNom() + " a été ajouté");
						// System.out.println(monArbre);
					} finally {
						break; // pour arrêter de lire
					}
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(monArbre.nombreNoeud());
		System.out.println(monArbre);
	}
}