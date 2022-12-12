package fr.isika.cda22.Projet_1;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class PageAdministrateur extends Scene{
	Label label;
	HBox hbx1;
	GridPane PageAdmin;

	public PageAdministrateur() throws FileNotFoundException {


		super(new GridPane(),800,800);

		PageAdmin= (GridPane)this.getRoot();

		Button BoutonVisualiser= new Button("Visualiser");
		Button BoutonAjout= new Button("+");
		Text Paragraphe= new Text("stdfglhuiyutrrwtryeturituyluiyuty5 \n 4tyyrterweqwetryyrterwe \n");


		HBox OptionsAdmin= new HBox();
		Pane AmbianceAdmin= new Pane();
		OptionsAdmin.getChildren().add(BoutonVisualiser);
		OptionsAdmin.getChildren().add(BoutonAjout);
		AmbianceAdmin.getChildren().add(Paragraphe);

		InputStream Stream= new FileInputStream("C:\\Users\\Lorexto\\Desktop\\PROJET 1\\Projet1\\Projet_1\\\\src\\\\main\\\\java\\\\fr\\\\isika\\\\cda22\\\\Projet_1\\GIFADMIN.gif");

		Image PageAdminImage= new Image(Stream);
		ImageView Img1 = new ImageView();
		Img1.setImage(PageAdminImage);
		Img1.setX(10);
		Img1.setY(10);
		Img1.setFitWidth(400);
		Img1.setPreserveRatio(true);
        PageAdmin.add(Img1, 0, 1, 5, 3);
		PageAdmin.add(AmbianceAdmin, 1, 9,2,2);
		PageAdmin.add(OptionsAdmin, 2, 11,3,3);


		OptionsAdmin.setSpacing(60);

		PageAdmin.setPadding(new Insets(5));
		PageAdmin.setHgap(20);
		PageAdmin.setVgap(20);
		StackPane listeStagiaires = new StackPane();
		ScrollPane pane = new ScrollPane();
//		ListView<Stagiaire> list = new ListView<Stagiaire>();
	    final ListView<Stagiaire> Stagiaires = new ListView<>();
	    ObservableList<Stagiaire> items = LectureBin.createArrayListFromBIN();
	    Stagiaires.setItems(items);
	    Stagiaires.setPrefSize(200, 250);
	    Stagiaires.setEditable(true);
	    Stagiaires.setCellFactory(ComboBoxListCell.forListView(items));
        listeStagiaires.getChildren().add(Stagiaires);
        PageAdmin.add(listeStagiaires, 2, 7);



	}




}
