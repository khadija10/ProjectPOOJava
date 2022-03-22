package com.app.sysco.app.responsablepdui;

import com.app.sysco.app.cours.Cours;
import com.app.sysco.app.edt.EmploiDuTemps;
import com.app.sysco.app.paiement.Paiement;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResponsablePedUi extends Application {
	
	private BorderPane root;
	private HBox navBox;
	private VBox gaucheBox;
	private VBox centerBoxEdt;
	private VBox centerBoxCours;
	private VBox centerBoxPaiement;
	private Scene defaultScene;
	private int width;
	private int heigth;
	private String relStyleSheet;
	private Button logoutButton;
	private Button edtButton;
	private Button coursButton;
	private Button paiementButton;
	
	private StackPane tablePane;
	private Label tableLabelCours;
	private TableView<Cours> tableCours;
	
	private TableColumn<Cours, String> idCoursCol;
	private TableColumn<Cours, String> nomCoursCol;
	private TableColumn<Cours, String> heureDebutCol;
	private TableColumn<Cours, String> heureFinCol;
	private TableColumn<Cours, String> moduleCol;
	private TableColumn<Cours, String> contenuCoursCol;
	private TableColumn<Cours, String> statutCol;
	private TableColumn<Cours, String> nomClasseCol;
	private TableColumn<Cours, String> dateCoursCol;
	
	
	private Label tableLabelEdt;
	private TableView<EmploiDuTemps> tableEdt;
	
	private TableColumn<EmploiDuTemps, String> idCol;
	private TableColumn<EmploiDuTemps, String> debutCol;
	private TableColumn<EmploiDuTemps, String> finCol;
	private TableColumn<EmploiDuTemps, String> coursCol;
	private TableColumn<EmploiDuTemps, String> classeCol;
	
	
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
		
		tableLabelEdt = new Label("Emplois du Temps");
		tableLabelCours = new Label("Listes des cours");
		tableLabelPaiement = new Label("Listes des paiements");
		logoutButton = new Button("DECONNEXION");
		edtButton = new Button("Emploi du Temps");
		edtButton.setMaxSize(200, 50);

		edtButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				root.setCenter(centerBoxEdt);
				
			}
		});
		coursButton = new Button("Cours");
		coursButton.setMaxSize(200, 50);
		coursButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				root.setCenter(centerBoxCours);

				
			}
		});
		paiementButton = new Button("Paiement");
		paiementButton.setMaxSize(200, 50);
		paiementButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				root.setCenter(centerBoxPaiement);

				
			}
		});
		
		tablePane = new StackPane();
		tablePane.setPadding(new Insets(30, 0, 0, 30));
		
		idCol = new TableColumn<>("N°");
		debutCol = new TableColumn<>("Debut");
		finCol = new TableColumn<>("Fin");
		coursCol = new TableColumn<>("Cours");
		classeCol = new TableColumn<>("classe");

		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id_edt"));
		debutCol.setCellValueFactory(new PropertyValueFactory<>("debut"));
		finCol.setCellValueFactory(new PropertyValueFactory<>("fin"));
		coursCol.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
		coursCol.setCellValueFactory(new PropertyValueFactory<>("id_classe"));

		
		tableEdt = new TableView<EmploiDuTemps>();

		tableEdt.getColumns().addAll(idCol, debutCol, finCol, coursCol, classeCol);
		
		centerBoxEdt = new VBox();
		centerBoxEdt.setAlignment(Pos.CENTER);
		centerBoxEdt.setPadding(new Insets(0, 0, 0, 20));
		
		centerBoxCours = new VBox();
		centerBoxCours.setAlignment(Pos.CENTER);
		centerBoxCours.setPadding(new Insets(0, 0, 0, 20));
		
		centerBoxPaiement = new VBox();
		centerBoxPaiement.setAlignment(Pos.CENTER);
		centerBoxPaiement.setPadding(new Insets(0, 0, 0, 20));
		
		
		navBox = new HBox(10);
		navBox.setAlignment(Pos.TOP_RIGHT);
		
		gaucheBox = new VBox(10);
		gaucheBox.setAlignment(Pos.CENTER);
		
		root = new BorderPane();
		
		root.setTop(navBox);
		root.setLeft(gaucheBox);
		root.setCenter(centerBoxEdt);
		root.setPadding(new Insets(10));
		
		defaultScene = new Scene(root, width, heigth);
		
		TableColumn<Cours, String> idCoursCol = new TableColumn<>("N°");
		TableColumn<Cours, String> nomCoursCol = new TableColumn<>("Nom cours");
		TableColumn<Cours, String> heureDebutCol = new TableColumn<>("Heure de Debut");
		TableColumn<Cours, String> heureFinCol = new TableColumn<>("Heure de Fin");
		TableColumn<Cours, String> moduleCol = new TableColumn<>("Module");
		TableColumn<Cours, String> contenuCoursCol = new TableColumn<>("Contenu");
		TableColumn<Cours, String> statutCol = new TableColumn<>("Statut");
		TableColumn<Cours, String> nomClasseCol = new TableColumn<>("Nom Classe");
		TableColumn<Cours, String> dateCoursCol = new TableColumn<>("Date");
		
		idCoursCol.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
		nomCoursCol.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
		heureDebutCol.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
		heureFinCol.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
		moduleCol.setCellValueFactory(new PropertyValueFactory<>("id_module"));
		contenuCoursCol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
		statutCol.setCellValueFactory(new PropertyValueFactory<>("statut"));
		nomClasseCol.setCellValueFactory(new PropertyValueFactory<>("id_classe"));
		dateCoursCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		tableCours = new TableView<Cours>();
		
		tableCours.getColumns().addAll(idCoursCol, nomCoursCol, heureDebutCol, heureFinCol, moduleCol, contenuCoursCol, statutCol, nomClasseCol, dateCoursCol);
		
		idPCol = new TableColumn<>("N°");
		validationCol = new TableColumn<>("Validation");
		

		
		idPCol.setCellValueFactory(new PropertyValueFactory<>("id_paiement"));
		validationCol.setCellValueFactory(new PropertyValueFactory<>("validation"));
		
		
		tablePaiement = new TableView<Paiement>();

		tablePaiement.getColumns().addAll(idPCol, validationCol);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		navBox.getChildren().addAll(logoutButton);
		centerBoxEdt.getChildren().addAll(tableLabelEdt, tableEdt);
		centerBoxCours.getChildren().addAll(tableLabelCours, tableCours);
		centerBoxPaiement.getChildren().addAll(tableLabelPaiement, tablePaiement);
		gaucheBox.getChildren().addAll(edtButton, coursButton, paiementButton);
		primaryStage.setWidth(width);
		primaryStage.setHeight(heigth);
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
	}

}
