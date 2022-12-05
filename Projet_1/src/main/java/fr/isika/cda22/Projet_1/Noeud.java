package fr.isika.cda22.Projet_1;


public class Noeud {

	// attributs

		private Stagiaire cle;
		private Noeud filsGauche;
		private Noeud filsDroit;
		

		// Constructeur

		public Noeud(Stagiaire cle, Noeud filsGauche, Noeud filsDroit) {
			super();
			this.cle = cle;
			this.filsGauche = filsGauche;
			this.filsDroit = filsDroit;
		}

		// getters & setters

		public Stagiaire getCle() {
			return cle;
		}

		public void setCle(Stagiaire cle) {
			this.cle = cle;
		}

		public Noeud getFilsGauche() {
			return filsGauche;
		}

		public void setFilsGauche(Noeud filsGauche) {
			this.filsGauche = filsGauche;
		}

		public Noeud getFilsDroit() {
			return filsDroit;
		}

		public void setFilsDroit(Noeud filsDroit) {
			this.filsDroit = filsDroit;
		}

		// méthodes spécifiques
		
		
		public String toString() {
			//gauche
			String res="";
		if (this.filsGauche!= null) {
			res+= this.filsGauche.toString();
			
		}
		// Noeud
		res+=this.cle.toString();
		//Droite
		if(this.filsDroit!=null) {
			res+=this.filsDroit.toString();
		}
			
			return res;
			
		}

		public void ajouterStagiaire(Stagiaire cle2) {

			if (this.cle.compareTo(cle2)>0) {
				if(this.filsDroit==null) {
					this.filsDroit=new Noeud(cle2, null, null);
				}
				else {
					this.filsDroit.ajouterStagiaire(cle2);
				}
			}
			
			
				else {
						if(this.filsGauche==null) {
							this.filsGauche=new Noeud(cle2, null, null);
						}
						else {
							this.filsGauche.ajouterStagiaire(cle2);
						}
						
				}	
				
			}
		
		
		
		
		
		
		
		public boolean contientStagiaire(Stagiaire cle2) {
			
			if(this.cle.compareTo(cle2)==0) {
				
				return true;
			}
			else if (this.cle.compareTo(cle2)<0) {
				if(this.filsGauche==null) 
				{return false;}
				else 
				{ return filsGauche.contientStagiaire(cle2);}
				}
			
			else {
				
				if (filsDroit==null) 
					{return false;}
				
				else 
				{return filsDroit.contientStagiaire(cle2);}
				
			}
			
		}
		
		public int nombreNoeuds() {
			int nGauche;
			int nDroit;
			
			if(this.filsGauche==null) {
				nGauche=0;
			}
			else
			{nGauche=filsGauche.nombreNoeuds();}
			
			if(this.filsDroit==null)
			{nDroit=0;}
			else 
			{nDroit=filsDroit.nombreNoeuds();}
			return 1+nGauche+nDroit;
		}
		
		
		
		public int numeroNoeud() {
			
			int num=0;
			if(this.filsGauche==null) {
				return num=0;}
				
			
			else if(this.filsDroit==null) {
		
			return num=nombreNoeuds() -1;
			}
			
			else  {
			
	
			return num++;}
			
		}
			
		
		
		
	
		
		
		public  Stagiaire  insertInArray() {
			
			Stagiaire [] MonTableau= new Stagiaire [nombreNoeuds()];
			int i=0;
			
			if (this.filsDroit==null) {
				
			return	MonTableau[nombreNoeuds()-1] = this.filsGauche.getFilsDroit().getCle();
			}
			
			else if (this.filsGauche==null)
			{
			return	MonTableau[0]= this.filsDroit.getFilsGauche().getCle();
				
			}
			
			else {
			return	MonTableau[i]=this.filsGauche.getFilsGauche().getCle();
			}
				
			 
			}
		
		
		
//		
//
//		public Noeud[] insertInArray() {
//			//gauche
//			String res="";
//		if (this.filsGauche!= null) {
//			res+= this.filsGauche.toString();
//			
//		}
//		// Noeud
//		res+=this.cle.toString();
//		//Droite
//		if(this.filsDroit!=null) {
//			res+=this.filsDroit.toString();
//		}
//			
//			return res;
//			
//		}
		
//		
		
		
//		
		
		
//		public void makeArray(Noeud cle, int i, Stagiaire[] array ) {
//		    if (cle != null) {
//		        array[i] = cle.getCle();   
//		        makeArray(filsGauche.getCle(), 2*i+1, array);
//		        makeArray(filsDroit.getCle(), 2*i+2, array);
//		   }}
		
		
		
		
		
	
}
