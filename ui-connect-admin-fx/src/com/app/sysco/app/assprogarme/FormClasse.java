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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormClasse extends Application {
	private String titre;
	private int uiWidth;
	private int uiHeight;
	
	private VBox root;
	private VBox titleVBox;
	private VBox fieldConnectVBox;
	private HBox buttonHBox;
	
	private Label logoTitleLabel;
	
	private Label nomLabel;
	private Label chefLabel;
	
	private TextField nomField;
	private ChoiceBox<User> chefField;
	
	private Button submitButton;
	private Button cancelButton;
	
	private Scene defaultScene;
	
	private String relStyleSheet;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		titre = "Create Classe";
		
		uiWidth = 500;
		uiHeight = 400;
		
		root = new VBox(40);
		
		titleVBox = new VBox();
		
		fieldConnectVBox = new VBox(10);
		
		buttonHBox = new HBox(10);
		
		logoTitleLabel = new Label("Create Classe");
		logoTitleLabel.setId("logoTitle");
		
		nomLabel = new Label("Nom de la classe");
		chefLabel = new Label("Chef de Classe");

		nomField = new TextField();
		
		//liste de la classe dans un liste observable
		ObservableList<User> profils = FXCollections.observableArrayList(UserDAO.listChefClass());
		
		
		chefField = new ChoiceBox<User>(profils);
		
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

			@Override
			public void handle(MouseEvent arg0) {
				if(onSubmit()) primaryStage.close();
			}
		});
		
		fieldConnectVBox.getChildren().addAll(
				nomLabel, 
				nomField, 				 
				chefLabel,
				chefField										
				);
		
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
		 * Recuperation des donnees du formulaire
		 * Chaque champ est teste pour s'assurer qu'il n'est
		 * pas vide. si il est vide, une  boite Alert est 
		 * afficher avec un message d'ereur est vide et la 
		 * fonction arrete son excecution et retourne un false  
		 */
		
		String nom = nomField.getText();
		if (nom.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ nom est vide");
			alert.showAndWait();
			
			return false;
		}
		
		User u = null;
		
		try {
			u = chefField.getValue();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Veuillez choisir une chef de classe");
			alert.showAndWait();
			
			return false;
		}
		
		Classe c = new Classe();
		c.setNomClasse(nom);
		c.setChefDeClasse(UserDAO.selectUser(u.getId()));
		
		String resultat = ClasseDAO.insertClasse(c);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout de la classe");
		alert.setHeaderText(resultat);
		alert.showAndWait();
		
		AssProgrammeUI.updateClasseTable();
		
		return true;
	}
}
