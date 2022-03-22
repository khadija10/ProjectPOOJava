package com.app.sysco.app.admin;

import java.util.Optional;

import com.app.sysco.app.user.User;
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

public class AddUserUI extends Application{
	
	private String titre;
	private int uiWidth;
	private int uiHeight;
	
	private VBox root;
	private VBox titleVBox;
	private VBox fieldConnectVBox;
	private HBox buttonHBox;
	
	private Label logoTitleLabel;
	
	private Label nomLabel;
	private Label prenomLabel;
	private Label emailLabel;
	private Label adresseLabel;
	private Label telLabel;
	private Label profilLabel;
	
	private TextField nomField;
	private TextField prenomField;
	private TextField emailField;
	private TextField adresseField;
	private TextField telField;
	
	private ChoiceBox<String> choiceProfilBox;
	
	private Button submitButton;
	private Button cancelButton;
	
	private Scene defaultScene;
	
	private String relStyleSheet;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		titre = "Add user";
		
		uiWidth = 400;
		uiHeight = 700;
		
		root = new VBox(50);
		
		titleVBox = new VBox();
		
		fieldConnectVBox = new VBox(10);
		
		buttonHBox = new HBox(10);
		
		logoTitleLabel = new Label("ADD USER");
		logoTitleLabel.setId("logoTitle");
		
		nomLabel = new Label("Nom");
		prenomLabel = new Label("Prenom");
		emailLabel = new Label("Email");
		adresseLabel = new Label("Adresse");
		telLabel = new Label("Numero de telephone");
		profilLabel = new Label("Profil");
		
		nomField = new TextField();
		prenomField = new TextField();
		emailField = new TextField();
		adresseField = new TextField();
		telField = new TextField();
		
		ObservableList<String> profils = FXCollections.observableArrayList("ASS_PROGRAMME", "CHEF_CLASSE", "COMPTABLE", "ENSEIGNANT", "RESP_PEDAGOGIQUE");

		choiceProfilBox = new ChoiceBox<String>(profils);
		
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
		
		fieldConnectVBox.getChildren().addAll(
				nomLabel, 
				nomField, 
				prenomLabel, 
				prenomField,
				emailLabel,
				emailField,
				adresseLabel,
				adresseField,
				telLabel,
				telField,
				profilLabel,
				choiceProfilBox);
		
		buttonHBox.getChildren().addAll(submitButton, cancelButton);
		buttonHBox.setAlignment(Pos.CENTER);
		
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
		
		root.getChildren().addAll(titleVBox, fieldConnectVBox, buttonHBox);
		
		root.setPadding(new Insets(50));
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
	}
	
	public boolean onCancel() {
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Fermeture");
	      alert.setHeaderText("Voulez-vous vraiment annuler la creation de l'utilisateur ?");

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
		 * afficher avec le message champ nom est vide et la 
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
		
		String prenom = prenomField.getText();
		if (prenom.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ prenom est vide");
			alert.showAndWait();
			
			return false;
		}
		
		String email = emailField.getText();
		if (email.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ email est vide");
			alert.showAndWait();
			
			return false;
		}
		
		String adresse = adresseField.getText();
		if (adresse.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ adresse est vide");
			alert.showAndWait();
			
			return false;
		}
		
		String tel = telField.getText();
		if (tel.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ tel est vide");
			alert.showAndWait();
			
			return false;
		}
		
		//Initialisation de la variable profil a cause du try catch
		String profil = null;
		
		/*
		 * Si aucun choix n'est selectionner au niveau de la liste deroulante
		 * une exception est declemche puis capurer pour afficher une boite
		 * Alert avec un message d'erreur puis la fonction s'arrete en retournant
		 * un false 
		 */
		try {
			profil = choiceProfilBox.getValue();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Veuillez choisir un profil");
			alert.showAndWait();
			
			return false;
		}
		
		User u = new User();
		u.setNom(nom);
		u.setPrenom(prenom);
		u.setEmail(email);
		u.setAdresse(adresse);
		u.setTel(tel);
		u.setProfil(profil);
		
		//Attribution d'un mot de passe basique au compte creer
		u.setPassword("0000");
		
		String resultat = UserDAO.insertUser(u);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajout de l'utilisateur");
		alert.setHeaderText(resultat);
		alert.showAndWait();
	
		AdminUI.updateTableUser();
		
		return true;
	}
	
}
