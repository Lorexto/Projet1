package fr.isika.cda22.Projet_1;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class VueMenu extends Scene {
	GridPane VueMenu;
	public VueMenu()  {
		super(new GridPane(),800,600);
		GridPane root = (GridPane)this.getRoot();
		this.setRoot(root);
		//création du menu bar
		MenuBar menuBar=new MenuBar();
		VBox vBox=new VBox(menuBar);
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
	    
		//ajout d'un menu à la barre de menu
		menuBar.getMenus().add(menu);
		//création de VBox pour l'ajout de toutes les barres de menu
		
		//ajout d'un volet de défilement à la scène
//		vBox.getChildren().add(menuBar);
		root.add(vBox,1,1);
		
		}
		
		}
