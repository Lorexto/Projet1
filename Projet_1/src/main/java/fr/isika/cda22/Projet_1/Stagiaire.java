package fr.isika.cda22.Projet_1;
public class Stagiaire {
	//attributs
	
	private String nom;
	private String prenom;
	private String dpt;
	private String id;
	private String annee;
	
	//constructeurs
	
	public Stagiaire(String nom, String prenom, String dpt, String id, String annee) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.dpt = dpt;
		this.id = id;
		this.annee = annee;
		
	}
	
	
	
	//getters & setters
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDpt() {
		return dpt;
	}
	public void setDpt(String dpt) {
		this.dpt = dpt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	
	
	//méthodes spécifiques
	public String toString() {
		
		return "(" + nom + " " + prenom + " " + dpt + " " + id + " " + annee+ ")";
	}
	
	public int compareTo(Stagiaire myStagiaire){
		if (myStagiaire.getNom().compareTo(this.nom) == 0) { // 0 si c'est identique
			return myStagiaire.getPrenom().compareTo(this.prenom);	// on compare les prénoms
		} else {
			return myStagiaire.getNom().compareTo(this.nom); // sinon on compare les noms
		}	
	}
}






