package com.app.sysco.app.assprogarme;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormModule extends Application {
	private String titre;
	private int uiWidth;
	private int uiHeight;
	
	private VBox root;
	private VBox titleVBox;
	private VBox fieldConnectVBox;
	private HBox buttonHBox;
	
	private Label logoTitleLabel;
	
	private Label nomLabel;
	private Label nbheureTotalLabel;
	private Label nbheureEffLabel;
	private Label ensaignantLabel;
	private Label statutLabel;


	
	private TextField nomField;
	private TextField nbheureTotalField;
	private TextField nbheureEffField;
	private TextField enseignantField;
	private TextField statutField;
	
	private Button submitButton;
	private Button cancelButton;
	
	private Scene defaultScene;
	
	private String relStyleSheet;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		titre = "Create Module";
		
		uiWidth = 500;
		uiHeight = 550;
		
		root = new VBox(40);
		
		titleVBox = new VBox();
		
		fieldConnectVBox = new VBox(10);
		
		buttonHBox = new HBox(10);
		
		logoTitleLabel = new Label("Create Module");
		logoTitleLabel.setId("logoTitle");
		
		nomLabel = new Label("Nom");
		nbheureTotalLabel = new Label("Nombre d'Heures Totales");
		nbheureEffLabel = new Label("Nombre d'heures effectuées");
		ensaignantLabel= new Label("Enseignant");
		statutLabel = new Label("status du module");

		
		nomField = new TextField();
		nbheureTotalField = new TextField();
		nbheureEffField = new TextField();
		enseignantField = new TextField();
		statutField = new TextField();
		
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
				nbheureTotalLabel,
				nbheureTotalField,				
				nbheureEffLabel,
				nbheureEffField,
				ensaignantLabel,
				enseignantField,								
				statutLabel,
				statutField);
		
		buttonHBox.getChildren().addAll(submitButton, cancelButton);
		buttonHBox.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(titleVBox, fieldConnectVBox, buttonHBox);
		
		root.setPadding(new Insets(20));
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
	}


}
