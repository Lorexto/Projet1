package fr.isika.cda22.Projet_1;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class menu extends Application {
//	public static void main(String[] args) {
//		launch(args);
//	}
	public VueLogin Login;
	public PageAdministrateur PageAdmin;
	public LoginController LoginControl;

	@Override
	public void start(Stage stage) {

		Login= new VueLogin();
    	try {
			PageAdmin =new PageAdministrateur();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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

		VueFormulaire VueFormulaire = new VueFormulaire();
		// définir le nom de la fenetre
		stage.setTitle("menu");
		//création du menu bar
		MenuBar menuBar=new MenuBar();
		//création d'un menu pour ajouter des éléments de menu
		Menu menu=new Menu("portail de navigation");
		//créer des éléments de menu
		MenuItem item1 = new MenuItem ("Rechercher stagiaire");
		MenuItem item2 = new MenuItem ("Ajouter stagiaire");
		MenuItem item3 = new MenuItem ("Déconnexion");
		//ajouter des éléments au menu
		menu.getItems().add(item1);
		menu.getItems().add(item2);
		menu.getItems().add(item3);

		item2.setOnAction((ActionEvent t)->{
			stage.setScene(VueFormulaire);
		});

		//ajout d'un menu à la barre de menu
		menuBar.getMenus().add(menu);
		//création de VBox pour l'ajout de toutes les barres de menu
		VBox vBox=new VBox(menuBar);
		//ajout d'un volet de défilement à la scène
		Scene scene = new Scene(vBox, 800, 600);
		stage.setScene(scene);

		stage.show();















		}
		public static void main(String[] args) {

		launch(args);
		}
		}











