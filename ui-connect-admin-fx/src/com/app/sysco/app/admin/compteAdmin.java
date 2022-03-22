package com.app.sysco.app.admin;

import com.app.sysco.app.user.User;
import com.app.sysco.db.connection.AdminDAO;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class compteAdmin extends Application{
	
	private User admin;
	
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
	private TextField passwordField;
	
	private Button submitButton;
	private Button cancelButton;
	
	private Scene defaultScene;
	
	private String relStyleSheet;
	
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		
		admin = new User();
		
		titre = "Sysco Connexion user";
		
		uiWidth = 400;
		uiHeight = 500;
		
		root = new VBox(50);
		
		titleVBox = new VBox();
		
		fieldConnectVBox = new VBox(10);
		
		buttonHBox = new HBox(10);
		
		logoTitleLabel = new Label("COMPTE ADMIN");
		logoTitleLabel.setId("logoTitle");
		
		loginLabel = new Label("Login");
		
		
		passwordLabel = new Label("Password");
		
		passwordField = new TextField();
		loginField = new TextField();
		
		submitButton = new Button("MODIFIER");
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
		
		fieldConnectVBox.getChildren().addAll(loginLabel, loginField, passwordLabel, passwordField);
		
		buttonHBox.getChildren().addAll(submitButton, cancelButton);
		
		loginField.setText(admin.getEmail());
		passwordField.setText(admin.getPassword());
		
		submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(onSubmit()) {
					primaryStage.close();
				};
			}
		});
		
		cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				onCancel();
			}
		});
		buttonHBox.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(titleVBox, fieldConnectVBox, buttonHBox);
		
		root.setPadding(new Insets(50));
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
	}
	
	public boolean onSubmit() {
		String login = loginField.getText();
		if (login.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ login est vide");
			alert.showAndWait();
			
			return false;
		};
		
		String password = passwordField.getText();
		if (password.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Le champ password est vide");
			alert.showAndWait();
			
			return false;
		};
		
		User u = new User();
		u.setEmail(login);
		u.setPassword(password);
		
		int resultat = AdminDAO.updateAdmin(u);
		
		if (resultat > 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mise a jour de l'utilisateur");
			alert.setHeaderText("UPDATE SUCCESS");
			alert.showAndWait();
			
			return true;
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mise a jour de l'utilisateur");
			alert.setHeaderText("UPDATE FAILED");
			alert.showAndWait();
			
			return false;
		}
		
	}
	
	public void onCancel() {
		
	}
}
