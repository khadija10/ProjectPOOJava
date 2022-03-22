package com.app.sysco.app.responsablepdui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ValiderPaiement extends Application {
	
	private VBox centerVbox;
	private Label paiementLabel;
	private Label moduleLabel;
	private Button validebutton;
	private Button demandeButton;
	private int heigth;
	private int width;
	private VBox root;
	private Scene defaultScene;
	private HBox btn;
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		heigth = 200;
		width = 500;
		paiementLabel = new Label("N° du paiement: ");
		moduleLabel = new Label("Nom module: ");
		
		validebutton = new Button("Valider Paiement");
		demandeButton = new Button("Demander des infos");
		
		centerVbox = new VBox(10);
		centerVbox.setAlignment(Pos.CENTER);	
		btn = new HBox(10);
		btn.setAlignment(Pos.CENTER);
		btn.setPadding(new Insets(20,20,20,20));
		root = new VBox();
		root.getChildren().addAll(centerVbox, btn);
		defaultScene = new Scene(root, width, heigth);
		root.setPadding(new Insets(10));


		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		centerVbox.getChildren().addAll(paiementLabel, moduleLabel);
		btn.getChildren().addAll(validebutton, demandeButton);
		primaryStage.setWidth(width);
		primaryStage.setHeight(heigth);
		
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
		

	}

}
