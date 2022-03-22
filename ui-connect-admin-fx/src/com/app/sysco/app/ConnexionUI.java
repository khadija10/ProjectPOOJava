package com.app.sysco.app;

import java.util.Optional;

import com.app.sysco.app.admin.AdminUI;
import com.app.sysco.app.assprogarme.AssProgrammeUI;
import com.app.sysco.app.user.User;
import com.app.sysco.db.connection.UserDAO;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConnexionUI extends Application{
	
	private String titre;
	private int uiWidth;
	private int uiHeight;
	
	private VBox root;
	private VBox titleVBox;
	private VBox fieldConnectVBox;
	private HBox buttonHBox;
	
	private Label logoTitleLabel;
	private Label loginLabel;
	private Label passwordLabel;
	
	private TextField loginField;
	private PasswordField passwordField;
	
	private Button submitButton;
	private Button cancelButton;
	
	private Scene defaultScene;
	
	private String relStyleSheet;
	
	public static void lancer(String[] args) {
		Application.launch(args);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		titre = "Sysco Connexion user";
		
		uiWidth = 400;
		uiHeight = 500;
		
		root = new VBox(50);
		
		titleVBox = new VBox();
		
		fieldConnectVBox = new VBox(10);
		
		buttonHBox = new HBox(10);
		
		logoTitleLabel = new Label("SYSCO");
		logoTitleLabel.setId("logoTitle");
		
		loginLabel = new Label("Login");
		
		
		passwordLabel = new Label("Password");
		
		passwordField = new PasswordField();
		loginField = new TextField();
		
		submitButton = new Button("CONNEXION");
		
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
		
		submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (onSubmit()) primaryStage.close();
			}			
		});
		
		cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(onCancel()) primaryStage.close();
			}
		});
		
		titleVBox.getChildren().add(logoTitleLabel);
		titleVBox.setAlignment(Pos.CENTER);
		
		fieldConnectVBox.getChildren().addAll(loginLabel, loginField, passwordLabel, passwordField);
		
		buttonHBox.getChildren().addAll(submitButton, cancelButton);
		buttonHBox.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(titleVBox, fieldConnectVBox, buttonHBox);
		
		root.setPadding(new Insets(50));
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
	}
	
	
	public boolean onSubmit() {
		String login = loginField.getText();
		
		String password = passwordField.getText();
		
		User user = new User();
		User user2 = new User();
		
		user.setEmail(login);
		user.setPassword(password);
		
		try {
			user2 = UserDAO.authentification(user);
			String profil = user2.getProfil();
			
			if(user2.getAdresse() != null) {
				switch(profil) {
					case "COMPTABLE":
						// Comptabe UI
						break;
					case "RESP_PEDAGOGIQUE":
						// Resp pedagogique UI
						break;
					case "ENSEIGNANT":
						// Enseigant UI
						break;
					case "CHEF_CLASSE":
						// Chef de classe UI
						break;
					case "ASS_PROGRAMME":
						AssProgrammeUI apui = new AssProgrammeUI();
						try {
							apui.init();
							apui.start(new Stage());
							
							return true;
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						break;
					case "ADMIN":
						AdminUI aui = new AdminUI();
						try {
							aui.init();
							aui.start(new Stage());
							
							return true;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					default:
						throw new IllegalArgumentException();
				}
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Connexion");
				alert.setHeaderText("Mot de passe ou login incorrecte");

				alert.showAndWait();
			}		
		}catch(Exception e){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Connexion");
			alert.setHeaderText("Connection a la base echouer");
			alert.showAndWait();
		}
		return false;
	}
	
	public boolean onCancel() {
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Fermeture");
	      alert.setHeaderText("Voulez-vous vraiment fermer l'application?");

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
}
