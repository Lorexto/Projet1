package fr.isika.cda22.Projet_1;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class AppliLogin extends Application {

	public VueLogin Login;
	public PageAdministrateur PageAdmin;
	public LoginController LoginControl;
	public ChangesController controlleur;
public VueMenu vueMenu;
public VueFormulaire Formulaire;

    @Override
    public void start(Stage stage) throws FileNotFoundException {


    	Login= new VueLogin();
    	PageAdmin =new PageAdministrateur();
    	vueMenu= new VueMenu();
    	Formulaire= new VueFormulaire();

    	// Si on clique sur Valider
    	Login.getSubmit().setOnAction(eventAction ->{

    	try {
				LoginController.loginButton(Login.submit.getOnMouseClicked());

        	  if (LoginController.loginButton(Login.submit.getOnMouseClicked()))
        	         {stage.setScene(vueMenu);}
    		}
    	catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});    	
    	vueMenu.getAddButton().setOnAction(eventAction ->{
    		try {
				ChangesController.Ajouter(vueMenu.addButton.getOnMouseClicked());
				if(ChangesController.Ajouter(vueMenu.addButton.getOnMouseClicked()))
					stage.setScene(Formulaire);
					System.out.println("ne veut pas s'ouvrir");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});

    	Formulaire.getBtn().setOnAction(eventAction ->{
    		try {
				ChangesController.AddFormulaireToBIN(Formulaire.btn.getOnMouseClicked());
				if(ChangesController.AddFormulaireToBIN(Formulaire.btn.getOnMouseClicked())) {
					stage.setScene(vueMenu);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	});









        stage.setScene(Login);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}