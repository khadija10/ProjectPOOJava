package com.app.sysco.app.assprogarme;

import java.util.List;
import java.util.Optional;

import com.app.sysco.app.classe.Classe;
import com.app.sysco.app.user.User;
import com.app.sysco.db.connection.ClasseDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormUpdateClasse extends Application {
	
	private static Classe classe; //Il s'agit de la classe a modifier
	
	private String titre;
	private int uiWidth;
	private int uiHeight;
	
	private HBox root;
	private VBox leftBox;
	private VBox rightBox;
	
	private VBox titleVBox;
	private VBox fieldConnectVBox;
	private HBox buttonHBox;
	private HBox buttonProfHBox;
	
	private Label logoTitleLabel;
	private Label tableUserLabel;
	private Label nomLabel;
	private Label chefLabel;
	
	private TextField nomField;
	private ChoiceBox<User> chefField;
	
	private Button submitButton;
	private Button cancelButton;
	private Button addProfButton;
	
	private StackPane tablePane;
	
	private static TableView<User> table;
	private TableColumn<User, String> idCol;
	private TableColumn<User, String> nomCol;
	private TableColumn<User, String> prenomCol;
	private TableColumn<User, String> emailCol;
	private TableColumn<User, String> addresseCol;
	private TableColumn<User, String> telCol;
	private TableColumn<User, String> profilCol;
	
	private Scene defaultScene;
	
	private String relStyleSheet;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void setClasse(Classe c) {
		 classe = c;
	}
	
	@Override
	public void init() throws Exception {
		titre = "View Classe";
		
		uiWidth = 700;
		uiHeight = 400;
		
		root = new HBox(10);
		leftBox = new VBox(40);
		rightBox = new VBox(20);
		
		titleVBox = new VBox();
		
		fieldConnectVBox = new VBox(10);
		
		buttonHBox = new HBox(10);
		buttonProfHBox = new HBox(10);
		
		logoTitleLabel = new Label("Details Classe");
		logoTitleLabel.setId("logoTitle");
		tableUserLabel = new Label("Litste des professeurs de la classe");
		tableUserLabel.setId("listProfTitle");
		
		nomLabel = new Label("Nom de la classe");
		chefLabel = new Label("Chef de Classe");

		nomField = new TextField();
		nomField.setText(classe.getNomClasse());
		
		ObservableList<User> profils = FXCollections.observableArrayList(UserDAO.listChefClass());

		chefField = new ChoiceBox<User>(profils);
		chefField.setValue(classe.getChefDeClasse());
		
		submitButton = new Button("MODIFIER");
		cancelButton = new Button("ANNULER");
		addProfButton = new Button("AJOUTER PROF");
		
		//Tableau list des profs
		tablePane = new StackPane();
		tablePane.setPadding(new Insets(30, 0, 0, 30));
		
		table = new TableView<User>();
		
		idCol = new TableColumn<>("id");
		nomCol = new TableColumn<>("Nom");
		prenomCol = new TableColumn<>("Prenom");
		emailCol = new TableColumn<>("Email");
		addresseCol = new TableColumn<>("Adresse");
		telCol = new TableColumn<>("Tel");
		profilCol = new TableColumn<>("profil");
		
		relStyleSheet = "/com/app/ressources/connexion.css";
		
		defaultScene = new Scene(root, uiWidth, uiHeight);
		defaultScene.getStylesheets().add(getClass().getResource(relStyleSheet).toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(titre);
		primaryStage.setWidth(uiWidth);
		primaryStage.setHeight(uiHeight);
		primaryStage.setResizable(false);
		
		//Recuperation de la liste des prof de la classe
		List<User> listu = ClasseDAO.selectProfForClass(classe.getIdClasse());
		
		//Affectation de la liste des profs de la classe dans une liste observable
		ObservableList<User> list;
		list = FXCollections.observableArrayList(listu);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		addresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		profilCol.setCellValueFactory(new PropertyValueFactory<>("profil"));
		
		table.getColumns().addAll(idCol, nomCol, prenomCol, emailCol, addresseCol, telCol, profilCol);
		table.setItems(list);
		
		rightBox.getChildren().addAll(tableUserLabel, tablePane, buttonProfHBox);
		rightBox.setAlignment(Pos.CENTER);
		
		tablePane.getChildren().add(table);
		
		titleVBox.getChildren().add(logoTitleLabel);
		titleVBox.setAlignment(Pos.CENTER);
		
		addProfButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				try {
					onAddProf();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
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
		
		//Evenement de clique sur la table
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				
				/*
				 * L'evenement ne se declenche que si il s'agit d'un double clique
				 */
				if(click.getClickCount() == 2) {
					int id = table.getSelectionModel().getSelectedItem().getId();
					try {
						onTable(id);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		fieldConnectVBox.getChildren().addAll(
				nomLabel, 
				nomField, 				 
				chefLabel,
				chefField										
				);
		
		buttonHBox.getChildren().addAll(submitButton, cancelButton);
		buttonHBox.setAlignment(Pos.CENTER);
		
		buttonProfHBox.getChildren().addAll(addProfButton);
		buttonProfHBox.setAlignment(Pos.CENTER);
		
		
		root.getChildren().addAll(leftBox, rightBox);
		leftBox.getChildren().addAll(titleVBox, fieldConnectVBox, buttonHBox);
		
		root.setPadding(new Insets(20));
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
	}
	
	//Fonction de mise a jour de la table des professeur de la classe(vue)
	public static void updateTable() {
		List<User> listu = ClasseDAO.selectProfForClass(classe.getIdClasse());
		
		ObservableList<User> list;
		list = FXCollections.observableArrayList(listu);
		
		//list etant un observable, il va automatiquement se mettre a jour sur la fenetre sans avoir besoin de refresh
		table.setItems(list);
	}
	
	public boolean onCancel() {
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Fermeture");
	      alert.setHeaderText("Voulez-vous vraiment annuler la creation de la classe ?");

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
		 * afficher avec un message d'erreur et la 
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
		
		User u = null;
		
		try {
			u = chefField.getValue();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Champ invalide");
			alert.setHeaderText("Veuillez choisir une chef de classe");
			alert.showAndWait();
			
			return false;
		}
		
		Classe c = new Classe();
		c.setIdClasse(classe.getIdClasse());
		c.setNomClasse(nom);
		c.setChefDeClasse(UserDAO.selectUser(u.getId()));
		
		int resultat = ClasseDAO.updateClasse(c);
		
		if(resultat > 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mise a jour de la classe");
			alert.setHeaderText("UPDATE SUCCESS");
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mise a jour de la classe");
			alert.setHeaderText("UPDATE FAILED");
			alert.showAndWait();
		}
		
		//Excecute la fonction de mise a jour de la table des enseignants
		AssProgrammeUI.updateClasseTable();
		return true;
	}
	
	public void onAddProf() throws Exception {
		FormAddProf addProfForm = new FormAddProf();
		addProfForm.init();
		addProfForm.setClasse(classe);
		addProfForm.start(new Stage());
	}
	
	/*
	 * Fonction qui permet de faire appel a la fonction de
	 * suppression d'un professeur dans une classe
	 */
	public void onTable(int id) throws Exception {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("SUPPRESSION");
		alert.setHeaderText("Voulez-vous vraiment supprimer l'enseignant de la classe ?");
		
		Optional<ButtonType> option = alert.showAndWait();
		
		if (option.get() == null) {
			
		} else if (option.get() == ButtonType.OK) {
			ClasseDAO.deleteProfClass(id, classe.getIdClasse());
			updateTable();
		} else if (option.get() == ButtonType.CANCEL) {
		
		} else {
			
		}
	}
}
