package fr.isika.cda22.Projet_1;

public class Arbre {

	//attribut
	public Noeud racine;

	//constructeurs
	
	public Arbre() {
		super();
		this.racine = null;
	}
	
	public Arbre(Noeud racine) {
		super();
		this.racine = racine;
	}

	//Getters & Setters
	
	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}
	
	//méthodes spécifiques
	
	public boolean isEmpty() {
		return (this.racine == null);
	}

	@Override
	public String toString() {
		
		if (this.isEmpty()) {
		return "Arbre [VOID TREE ]";}
		else {
			return this.racine.toString();
		}
	}
	
	
	public void ajouter (Stagiaire cle) {
		if (isEmpty()) {
			this.racine= new Noeud(cle, null, null);
		}
		else {
			this.racine.ajouterStagiaire(cle);
			
		}
		
	}
	
	
	public boolean contains(Stagiaire cle2) {
		
		if (isEmpty()) {
			return false;
		}
		
		else {
			return 	this.racine.contientStagiaire(cle2);
		}
		
	}
	
	
	public int nombreNoeud() {
		if (isEmpty()) {
			return 0;
		}
		
		else {
			return 	this.racine.nombreNoeuds();
		}
		
	}
	
//	public Arbre[] toArray(Noeud racine) {
//		if (isEmpty()) {
//			return  Arbre[0]=null;;
//		}
//		
//		
//		else {		
//		Arbre []Tab =new Arbre[nombreNoeud()-1];
//		return Tab.insertInArray();
//		
//		}
		
	//}
	
	
//	public Stagiaire [] toArray() {
//	    int size = nombreNoeud();
//	    Stagiaire[] Array = new Stagiaire [nombreNoeud()];
//	    for(int i = 0; i <nombreNoeud(); i++) {
//	        makeArray(racine,0,Array);
//	        Array[i] = racine.getCle();
//	}
//
//	    return Array;
//	}
	
	
	
	
	
	}
