package fr.isika.cda22.Projet_1;
import javafx.application.Application;
import javafx.stage.Stage;




public class menu2 extends Application {


	//attributs
	public  VueMenu  vueMenu;

	@Override
	public void start(Stage stage) {

		 vueMenu = new  VueMenu();

	stage.setTitle("menu");
	stage.setScene(vueMenu);
	stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public VueMenu getVueMenu() {
		return vueMenu;
	}

	public void setVueMenu(VueMenu vueMenu) {
		this.vueMenu = vueMenu;
	}


	}






