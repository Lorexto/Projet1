package fr.isika.cda22.Projet_1;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class VueLogin extends Scene {

	static TextField User_ID;
	static PasswordField mdp_User;
	Button submit;
	LoginController LoginControl;

	public VueLogin() {

		super(new GridPane(),800,800);
		GridPane rootLogin= (GridPane)this.getRoot();



		Label FenetreAuth= new Label (" VEUILLEZ VOUS CONNCETER");
		Label USERID=new Label("Votre Identifiant");
		Label USERMDP=new Label("Votre Mot De Passe");

		User_ID=new TextField();
		mdp_User=new PasswordField();
		submit=new Button ("Connexion");

	    HBox Utilisateur= new HBox();
	    HBox MotDePasse= new HBox();
	    HBox Connexion= new HBox();

	    Utilisateur.getChildren().add(USERID);
	    Utilisateur.getChildren().add(User_ID);
	    MotDePasse.getChildren().add(USERMDP);
	    MotDePasse.getChildren().add(mdp_User);
	    Connexion.getChildren().add(submit);




	    Utilisateur.setSpacing(60);
	    MotDePasse.setSpacing(60);

	    rootLogin.add(FenetreAuth,3,1);
	    rootLogin.add(Utilisateur,2,4);
	    rootLogin.add(MotDePasse,2,7 );
	    rootLogin.add(submit,4,10);




	    rootLogin.setPadding(new Insets(5));
	    rootLogin.setHgap(20);
	    rootLogin.setVgap(40);





		}


		public Button getSubmit() {
			return submit;
		}

		public void setSubmit(Button submit) {
			this.submit = submit;
		}


		public TextField getUser_ID() {
			return User_ID;
		}


		public void setUser_ID(TextField user_ID) {
			User_ID = user_ID;
		}


		public PasswordField getMdp_User() {
			return mdp_User;
		}


		public void setMdp_User(PasswordField mdp_User) {
			VueLogin.mdp_User = mdp_User;
		}














}