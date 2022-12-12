package fr.isika.cda22.Projet_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

	public static TextField User_ID;
	public static PasswordField userMdpCheck;
	public PageAdministrateur PageAdmin;




	public static boolean loginButton(EventHandler<? super MouseEvent> eventHandler) throws IOException
    {
        //Chemin jusqu'au fichir Admin
        Path path = Paths.get("C\\\\src\\\\main\\\\java\\\\fr\\\\isika\\\\cda22\\\\Projet_1\\\\AdminList.txt");

        //Compte le nombre de lignes dans le fichier
        long count = Files.lines(path).count();

        /// Pour chaque ligne
           for (int i = 0; i < count; i++)
           {
                String line = Files.readAllLines(path).get(i); // on lit toutes les lignes
                if(!line.trim().equals("")) // si on ne recontre pas de vide
                {//on cree un tableau de string et on definit une methode de decoupage entre useId et mdp ( la virgule)

                    String[] profile = line.split(",");

                    String userId = profile[0]; // en première position user id
                   String mdp = profile[1]; // en deuxième position mot de passe

                   System.out.println(profile);

                  //trim() supprime les expaces avant ou apres le mot si il y en a
                    if(	userId.trim().equals(VueLogin.User_ID.getText())) {
                        //Controle du mot de passe
                        if(mdp.trim().equals(VueLogin.mdp_User.getText()))
                        {Alert msg = new Alert(AlertType.CONFIRMATION);
                            msg.setTitle(VueLogin.User_ID.getText());
                            msg.setContentText("Connexion reussie");
                            msg.showAndWait();

                            //Stockage des infos
                            Admin.setUserId(userId);
                            Admin.setMdp(mdp);
                         return true;
                            }
                    }

                      }

           if(VueLogin.User_ID.getText() == null) {
                System.out.println("Votre UserID est incorrect");
                Alert msg = new Alert(AlertType.ERROR);
                msg.setTitle(VueLogin.User_ID.getText());
                msg.setContentText("Cet identifiant n'existe pas " + VueLogin.User_ID.getText());
                msg.showAndWait();
                return false;

               } else if (VueLogin.User_ID.getText() == null){
                    System.out.println("Pas d'utilisisateur avec ces identifiants");
                    Alert msg = new Alert(AlertType.ERROR);
                    msg.setTitle(VueLogin.User_ID.getText());
                    msg.setContentText("Mot de passe erronné");
                    msg.showAndWait();
                    return false;
               }

    }
		return false;
    }




}



