package com.app.sysco.app.comptableui;

import com.app.sysco.app.paiement.Paiement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComptableUI extends Application {
	private BorderPane root;
	private HBox navBox;
	private VBox centerBoxPaiement;
	private Scene defaultScene;
	private int width;
	private int heigth;
	private String relStyleSheet;
	private Button logoutButton;
	private StackPane tablePane;
	private Label tableLabelPaiement;
	private TableView<Paiement> tablePaiement;
	
	private TableColumn<Paiement, String> idPCol;
	private TableColumn<Paiement, String> validationCol;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		width = 960;
		heigth = 540;
		logoutButton = new Button("DECONNEXION");
		
		tableLabelPaiement = new Label("Listes des paiements");

		tablePane = new StackPane();
		tablePane.setPadding(new Insets(30, 0, 0, 30));
		
		idPCol = new TableColumn<>("N°");
		validationCol = new TableColumn<>("Validation");
		

		
		idPCol.setCellValueFactory(new PropertyValueFactory<>("id_paiement"));
		validationCol.setCellValueFactory(new PropertyValueFactory<>("validation"));
		
		
		tablePaiement = new TableView<Paiement>();

		tablePaiement.getColumns().addAll(idPCol, validationCol);
		
		centerBoxPaiement = new VBox();
		centerBoxPaiement.setAlignment(Pos.CENTER);
		centerBoxPaiement.setPadding(new Insets(0, 0, 0, 20));
		
		navBox = new HBox(10);
		navBox.setAlignment(Pos.TOP_RIGHT);
		
		root = new BorderPane();
		root.setTop(navBox);
		root.setCenter(centerBoxPaiement);
		root.setPadding(new Insets(10));

		defaultScene = new Scene(root, width, heigth);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		navBox.getChildren().add(logoutButton);
		centerBoxPaiement.getChildren().addAll(tableLabelPaiement, tablePaiement);
		primaryStage.setWidth(width);
		primaryStage.setHeight(heigth);
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
		
	}
}
