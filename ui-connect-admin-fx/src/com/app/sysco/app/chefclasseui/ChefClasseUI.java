package com.app.sysco.app.chefclasseui;

import com.app.sysco.app.cours.Cours;
import com.app.sysco.app.user.User;

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

public class ChefClasseUI extends Application {
	
	private BorderPane root;
	private Scene defaultScene;
	private int uiWidth;
	private int uiHeight;
	
	private String relStyleSheet;
	
	private Button logoutButton;
	private Label listCoursLabel; 
	
	private TableView<Cours> tableCours;
	private StackPane tableCoursPane;
	
	private TableColumn<Cours, String> idCoursCol;
	private TableColumn<Cours, String> nomCoursCol;
	private TableColumn<Cours, String> heureDebutCol;
	private TableColumn<Cours, String> heureFinCol;
	private TableColumn<Cours, String> moduleCol;
	private TableColumn<Cours, String> contenuCoursCol;
	private TableColumn<Cours, String> statutCol;
	private TableColumn<Cours, String> nomClasseCol;
	private TableColumn<Cours, String> dateCoursCol;
	
	private VBox centerVBox;
	private HBox navBox;
	
	@Override
	public void init() throws Exception {
		logoutButton = new Button("DECONNEXION");
		
		uiWidth = 960;
		uiHeight = 540;
		
		listCoursLabel = new Label("Liste des cours");
		listCoursLabel.setId("h1");
		listCoursLabel.setPadding(new Insets(10, 0, 20, 0));
		
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
		
		centerVBox = new VBox();
		centerVBox.setAlignment(Pos.CENTER);
		
		navBox = new HBox(10);
		navBox.setAlignment(Pos.TOP_RIGHT);
		
		root = new BorderPane();
		root.setTop(navBox);
		root.setCenter(centerVBox);
		root.setPadding(new Insets(10));
		
		relStyleSheet = "/com/app/ressources/connexion.css";
		
		defaultScene = new Scene(root, uiWidth, uiHeight);
		defaultScene.getStylesheets().add(getClass().getResource(relStyleSheet).toString());
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		navBox.getChildren().addAll(logoutButton);
		centerVBox.getChildren().addAll(listCoursLabel, tableCours);
		
		primaryStage.setTitle("Chef de classe");
		primaryStage.setWidth(uiWidth);
		primaryStage.setHeight(uiHeight);
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
