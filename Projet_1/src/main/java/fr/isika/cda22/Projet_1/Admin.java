package fr.isika.cda22.Projet_1;

public class Admin {
	
	protected String userId;
	protected String mdp;
	
	
	public Admin(String userId, String mdp) {
		super();
		this.userId = userId;
		this.mdp = mdp;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
	
	
	public int checkUser (String userIdInput, String mdpInput ) {
		
		
		
		if (AdminList.contains(userIdInput)) { // Si userId existe dans la liste
			
			if (this.mdp==mdpInput) {
				return 1; // mot de passe correspond
			}
			else {
				return 2;// mot de passe ne correspond pas
			}
		}
			else {
				return 3;// User id n'est pas dans la liste
				
			}
		
			
		}
		
	
	
	
	
	
	
	
	

}
