package com.app.sysco.app.enseignantui;

import com.app.sysco.app.cours.Cours;
import com.app.sysco.app.edt.EmploiDuTemps;
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

public class EnseignantUi extends Application {
	private BorderPane root;
	private HBox navBox;
	private VBox gaucheBox;
	private VBox centerBoxEdt;
	private VBox centerBoxCours;
	private VBox centerBoxModule;
	private Scene defaultScene;
	private int width;
	private int heigth;
	private String relStyleSheet;
	private Button logoutButton;
	private Button edtButton;
	private Button coursButton;
	private Button moduleButton;
	
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
	
	
	private Label tableLabelModule;
	private TableView<Module> tableModule;
	
	private TableColumn<Module, String> idMCol;
	private TableColumn<Module, String> nomMCol;
	private TableColumn<Module, String> enseignantCol;
	private TableColumn<Module, String> nbhTotalCol;
	private TableColumn<Module, String> nbhEffCol;
	private TableColumn<Module, String> statut;

	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		width = 960;
		heigth = 540;
		
		tableLabelEdt = new Label("Emplois du Temps");
		tableLabelCours = new Label("Listes des cours");
		tableLabelModule = new Label("Listes des modules");
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
		moduleButton = new Button("Module");
		moduleButton.setMaxSize(200, 50);
		moduleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				root.setCenter(centerBoxModule);

				
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
		
		centerBoxModule = new VBox();
		centerBoxModule.setAlignment(Pos.CENTER);
		centerBoxModule.setPadding(new Insets(0, 0, 0, 20));
		
		
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
		
		
		
		
		idMCol = new TableColumn<>("N°");
		nomMCol = new TableColumn<>("Nom");
		enseignantCol = new TableColumn<>("Enseignant");
		nbhTotalCol = new TableColumn<>("Nombre d'heures totales");
		nbhEffCol = new TableColumn<>("Nombre d'heures effectuées");
		statut = new TableColumn<>("Statut");
		
		idMCol.setCellValueFactory(new PropertyValueFactory<>("id_module"));
		nomMCol.setCellValueFactory(new PropertyValueFactory<>("nom_module"));
		enseignantCol.setCellValueFactory(new PropertyValueFactory<>("id_enseignant"));
		nbhTotalCol.setCellValueFactory(new PropertyValueFactory<>("nbHeureTotal"));
		nbhEffCol.setCellValueFactory(new PropertyValueFactory<>("nbHeureEff"));
		statut.setCellValueFactory(new PropertyValueFactory<>("statut"));


		
		tableModule = new TableView<Module>();

		tableModule.getColumns().addAll(idMCol, nomMCol, enseignantCol, nbhTotalCol, nbhEffCol, statut);

	}



	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		navBox.getChildren().addAll(logoutButton);
		centerBoxEdt.getChildren().addAll(tableLabelEdt, tableEdt);
		centerBoxCours.getChildren().addAll(tableLabelCours, tableCours);
		centerBoxModule.getChildren().addAll(tableLabelModule, tableModule);
		gaucheBox.getChildren().addAll(edtButton, coursButton, moduleButton);
		primaryStage.setWidth(width);
		primaryStage.setHeight(heigth);
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
		

	}
	
	

}
