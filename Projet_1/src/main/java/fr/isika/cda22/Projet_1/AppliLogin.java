package fr.isika.cda22.Projet_1;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class AppliLogin extends Application {

	public VueLogin Login;
	public PageAdministrateur PageAdmin;
	public LoginController LoginControl;



    @Override
    public void start(Stage stage) throws FileNotFoundException {


    	Login= new VueLogin();
    	PageAdmin =new PageAdministrateur();

    	// Si on clique sur Valider
    	Login.getSubmit().setOnAction(eventAction ->{

    	try {
				LoginController.loginButton(Login.submit.getOnMouseClicked());

        	  if (LoginController.loginButton(Login.submit.getOnMouseClicked()))
        	         {stage.setScene(PageAdmin);}
    		}
    	catch (IOException e) {
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