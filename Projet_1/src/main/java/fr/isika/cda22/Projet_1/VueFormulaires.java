package fr.isika.cda22.Projet_1;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class VueFormulaires extends Scene {
Button btn;


	public VueFormulaires()  {
		super(new GridPane(),600,600);

		GridPane root = (GridPane)this.getRoot();// organise les composants de la fenetre
		root.setPadding(new Insets(20)); //
		root.setHgap(25);// espace entre label et champs remplissage (horizontal)
		root.setVgap(15);// espace entre chaque label(vertical)
		this.setRoot(root);// on crée la scene le conteneur principal qui contient tous les
													// composants

		Label labelNom = new Label("Nom:");
		TextField txtNom = new TextField();
		Label labelPrenom = new Label("Prenom:");
		TextField txtPrenom = new TextField();
		Label labelBirth = new Label("Departement");
		Label labelGenre = new Label("Promo");
		ToggleGroup toggleGroupe2 = new ToggleGroup();
		RadioButton rb1 = new RadioButton("CDA");
		RadioButton rb2 = new RadioButton("AL");

		Label labelLocalisation = new Label("Annee :");
		ChoiceBox choixAnnee = new ChoiceBox<>();

		 btn = new Button("Valider");
		root.add(labelNom, 0, 1);
		root.add(txtNom, 1, 1);
		root.add(labelPrenom, 0, 2);
		root.add(txtPrenom, 1, 2);
		root.add(labelBirth, 0, 3);

		root.add(labelGenre, 0, 4);


		root.add(createToogleGenreButtons(), 1, 4);
		//root.add(formation, 0, 5);
		HBox hb2 = new HBox();
		toggleGroupe2.getToggles().addAll(rb1, rb2);
		hb2.getChildren().addAll(rb1, rb2);
		hb2.setSpacing(20);
		root.add(hb2, 1, 5);
		root.add(labelLocalisation, 0, 6);
		root.add(createChoiceBox(), 1, 6);
		root.add(btn, 0, 7);


	}

	public Button getBtn() {
		return btn;
	}

	public void setBtn(Button btn) {
		this.btn = btn;
	}


	public HBox createToogleGenreButtons() {
		ToggleGroup toggleGroupe = new ToggleGroup();
		ToggleButton tgb1 = new ToggleButton("Feminin");
		ToggleButton tgb2 = new ToggleButton("Masculin");
		ToggleButton tgb3 = new ToggleButton("Autre");
		HBox hb1 = new HBox();
		toggleGroupe.getToggles().addAll(tgb1, tgb2, tgb3);
		hb1.getChildren().addAll(tgb1, tgb2, tgb3);
		hb1.setSpacing(20);

		return hb1;



	}

	public ChoiceBox createChoiceBox() {

		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll("Villes", "Paris", "Bordeaux", "Marseille", "lille", "Lyon");
		choiceBox.getSelectionModel().select(0);
		return choiceBox;
	}






}
