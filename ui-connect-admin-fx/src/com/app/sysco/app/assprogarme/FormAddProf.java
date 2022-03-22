package com.app.sysco.app.assprogarme;

import java.util.Optional;

import com.app.sysco.app.classe.Classe;
import com.app.sysco.app.user.User;
import com.app.sysco.db.connection.ClasseDAO;
import com.app.sysco.db.connection.UserDAO;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormAddProf extends Application {
	private String titre;
	private int uiWidth;
	private int uiHeight;
	
	private VBox root;
	private VBox titleVBox;
	private VBox fieldConnectVBox;
	private HBox buttonHBox;
	
	private Label logoTitleLabel;
	
	private Label ensLabel;
	
	private ChoiceBox<User> ensField;
	
	private Button submitButton;
	private Button cancelButton;
	
	private Scene defaultScene;
	
	private String relStyleSheet;
	private Classe classe;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void setClasse(Classe classe) {
		 this.classe = classe;
	}
	
	
	@Override
	public void init() throws Exception {
		titre = "Add Prof to Classe";
		
		uiWidth = 500;
		uiHeight = 300;
		
		root = new VBox(30);
		
		titleVBox = new VBox();
		
		fieldConnectVBox = new VBox(10);
		
		buttonHBox = new HBox(10);
		
		logoTitleLabel = new Label("Ajouter un prof a la classe");
		logoTitleLabel.setId("logoTitle");
		
		ensLabel = new Label("Professeur");
		
		//Liste des profs 
		ObservableList<User> profils = FXCollections.observableArrayList(UserDAO.listEns());
		
		//Liste des profs affichr dans une liste deroulante
		ensField = new ChoiceBox<User>(profils);
		
		submitButton = new Button("AJOUTER");
		cancelButton = new Button("ANNULER");
		
		relStyleSheet = "/com/app/ressources/connexion.css";
		
		defaultScene = new Scene(root, uiWidth, uiHeight);
		defaultScene.getStylesheets().add(getClass().getResource(relStyleSheet).toString());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(titre);
		primaryStage.setWidth(uiWidth);
		primaryStage.setHeight(uiHeight);
		primaryStage.setResizable(false);
		
		titleVBox.getChildren().add(logoTitleLabel);
		titleVBox.setAlignment(Pos.CENTER);
		
		cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
				if(onCancel()) primaryStage.close();
			}
		});
		
		submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			/*
			 * Lors du click sur le bouton d'ajout, la fonction onSubmit 
			 * est declenché puis la fenetre est fermer si la fonction
			 * retourne true(retourne true si le prof a bien ete ajpute dans la classe)
			 */
			@Override
			public void handle(MouseEvent arg0) {
				if(onSubmit()) primaryStage.close();
			}
		});
		
		fieldConnectVBox.getChildren().addAll(ensLabel,ensField);
		
		buttonHBox.getChildren().addAll(submitButton, cancelButton);
		buttonHBox.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(titleVBox, fieldConnectVBox, buttonHBox);
		
		root.setPadding(new Insets(20));
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
	}
	
	public boolean onCancel() {
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Fermeture");
	      alert.setHeaderText("Voulez-vous vraiment annuler la creation de la classe ?");

	      Optional<ButtonType> option = alert.showAndWait();

	      if (option.get() == null) {
	    	  return false;
	      } else if (option.get() == ButtonType.OK) {
	    	  return true;
	      } else if (option.get() == ButtonType.CANCEL) {
	    	  return false;
	      } else {
	    	  return false;
	      }
	}
	
	public boolean onSubmit() {
		
		/*
		 * Initialisation des donnees u et resultat
		 */
		User u = null;
		String resultat = "";
		
		/*
		 * Ici, si aucun prof n'est selectionner
		 * une exception est declenche et la fonction
		 * retourne un faux  
		 */
		try {
			u = ensField.getValue();
			
			Classe c = new Classe();
			c.setChefDeClasse(UserDAO.selectUser(u.getId()));
			
			resultat = ClasseDAO.addProfToClasse(u.getId(), classe.getIdClasse());
		} catch (Exception e) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Veuillez choisir un professeur");
			alert.showAndWait();
			
			return false;
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout de la classe");
		alert.setHeaderText(resultat);
		alert.showAndWait();
		
		FormUpdateClasse.updateTable();
		return true;
	}
}
