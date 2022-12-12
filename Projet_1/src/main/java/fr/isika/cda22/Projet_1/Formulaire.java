package fr.isika.cda22.Projet_1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.application.Application;

	
	
public class Formulaire extends Application {
	
	//attributs
	public VueFormulaire vueFormulaire;
	
	@Override
	public void start(Stage stage) {
		
		vueFormulaire = new VueFormulaire();
		
	stage.setTitle("Formulaire d'ajout d'un stagiaire");
	stage.setScene(vueFormulaire);
	stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}

	public VueFormulaire getVueFormulaire() {
		return vueFormulaire;
	}

	public void setVueFormulaire(VueFormulaire vueFormulaire) {
		this.vueFormulaire = vueFormulaire;
	}
	
	
}
