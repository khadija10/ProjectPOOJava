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

public class UpdateUser extends Application{
	
	private User user; 
	
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
	private Button deleteButton;
	private Button resetPasswordButton;
	
	private Scene defaultScene;
	
	private String relStyleSheet;
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		
		user = new User();
		
		titre = "Add user";
		
		uiWidth = 400;
		uiHeight = 700;
		
		root = new VBox(50);
		
		titleVBox = new VBox();
		
		fieldConnectVBox = new VBox(10);
		
		buttonHBox = new HBox(10);
		
		logoTitleLabel = new Label("UPDATE USER");
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
		
		submitButton = new Button("MODIFIER");
		deleteButton = new Button("DELETE");
		resetPasswordButton = new Button("RESET PASSWORD");
		
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
		
		buttonHBox.getChildren().addAll(submitButton, deleteButton, resetPasswordButton);
		buttonHBox.setAlignment(Pos.CENTER);
		
		nomField.setText(user.getNom());
		prenomField.setText(user.getPrenom());
		emailField.setText(user.getEmail());
		adresseField.setText(user.getAdresse());
		telField.setText(user.getTel());
		choiceProfilBox.setValue(user.getProfil());
		
		submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				onSubmit();
			}
		});
		
		deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(onDelete()) primaryStage.close();;
			}
		});
		
		resetPasswordButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				onSubmit();
			}
		});
		
		root.getChildren().addAll(titleVBox, fieldConnectVBox, buttonHBox);
		
		root.setPadding(new Insets(50));
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
	}
	
	public void onSubmit() {
		String nom = nomField.getText();
		
		if (nom.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ nom est vide");
			alert.showAndWait();
			
			return;
		};
		
		String prenom = prenomField.getText();
		if (prenom.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ prenom est vide");
			alert.showAndWait();
			
			return;
		};
		
		String email = emailField.getText();
		if (email.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ email est vide");
			alert.showAndWait();
			
			return;
		};
		
		String adresse = adresseField.getText();
		if (adresse.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ adresse est vide");
			alert.showAndWait();
			
			return;
		};
		
		String tel = telField.getText();
		if (tel.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ tel est vide");
			alert.showAndWait();
			
			return;
		};
		
		String profil = null;
		
		try {
			profil = choiceProfilBox.getValue();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Veuillez choisir un profil");
			alert.showAndWait();
			
			return;
		}
		
		User u = new User();
		u.setId(user.getId());
		u.setNom(nom);
		u.setPrenom(prenom);
		u.setEmail(email);
		u.setAdresse(adresse);
		u.setTel(tel);
		u.setProfil(profil);
		
		int resultat = UserDAO.updateUser(u);
		
		if(resultat > 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mise a jour de l'utilisateur");
			alert.setHeaderText("UPDATE SUCCESS");
			AdminUI.updateTableUser();
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mise a jour de l'utilisateur");
			alert.setHeaderText("UPDATE FAILED");
			alert.showAndWait();
		}
	}
	
	public boolean onDelete() {
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Deconnexion");
	      alert.setHeaderText("Voulez-vous supprimer cette utilisateur ?");

	      Optional<ButtonType> option = alert.showAndWait();

	      if (option.get() == null) {
	    	  	return false;
	      } else if (option.get() == ButtonType.OK) {
  				UserDAO.deleteUser(user.getId());
  				AdminUI.updateTableUser();
  				return true;
	      } else if (option.get() == ButtonType.CANCEL) {
	    	  	return false;
	      } else {
	    	  	return false;
	      }
	}
	
	public void onResetPassord() {
		
	}
}
