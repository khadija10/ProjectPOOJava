package com.app.sysco.app.admin;

import java.util.List;
import java.util.Optional;

import com.app.sysco.app.ConnexionUI;
import com.app.sysco.app.user.User;
import com.app.sysco.db.connection.AdminDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminUI extends Application{
	
	private String titre;
	private int uiWidth;
	private int uiHeight;
	private Scene defaultScene;
	
	private Label tableUserLabel;
	
	private BorderPane root;
	private StackPane tablePane;
	
	private static TableView<User> table = new TableView<User>();
	private static TableColumn<User, String> idCol;
	private static TableColumn<User, String> nomCol;
	private static TableColumn<User, String> prenomCol;
	private static TableColumn<User, String> emailCol;
	private static TableColumn<User, String> addresseCol;
	private static TableColumn<User, String> telCol;
	private static TableColumn<User, String> profilCol;

	private HBox topHbox;
	private VBox leftVbox;
	private VBox centerVbox;
	
	private Button logoutButton;
	private Button addUserButton;
	private Button adminSetButton;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws Exception {
		titre = "Admin Dashboard";
		
		uiWidth = 960;
		uiHeight = 540;
		
		tableUserLabel = new Label("Litste des utilisateurs");
		
		logoutButton = new Button("DECONNEXION");
		addUserButton = new Button("AJOUTER UTILISATEUR");
		adminSetButton = new Button("COMPTE ADMIN");
		
		topHbox = new HBox(10);
		leftVbox = new VBox(30);
		centerVbox = new VBox(10);
		
		root = new BorderPane();
		root.setPadding(new Insets(20));
		
		//Tableau list des utilisateur
		tablePane = new StackPane();
		tablePane.setPadding(new Insets(30, 0, 0, 30));
		
		idCol = new TableColumn<>("id");
		nomCol = new TableColumn<>("Nom");
		prenomCol = new TableColumn<>("Prenom");
		emailCol = new TableColumn<>("Email");
		addresseCol = new TableColumn<>("Adresse");
		telCol = new TableColumn<>("Tel");
		profilCol = new TableColumn<>("profil");
		
		root.setTop(topHbox);
		root.setLeft(leftVbox);
		root.setCenter(centerVbox);
		
		defaultScene = new Scene(root, uiWidth, uiHeight);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		List<User> listu = UserDAO.listUser();
		
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
		
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			/*
			 * En cliquant deux fois de suite sur une element du tableau des utilisateurs
			 * l'id de cet element est recuprer puis est passe en parametre a la fonction
			 * onTable
			 */
			@Override
			public void handle(MouseEvent click) {
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
		
		primaryStage.setTitle(titre);
		primaryStage.setWidth(uiWidth);
		primaryStage.setHeight(uiHeight);
		
		primaryStage.setResizable(false);
		
		topHbox.getChildren().addAll(adminSetButton, logoutButton);
		topHbox.setAlignment(Pos.TOP_RIGHT);
		
		leftVbox.getChildren().addAll(addUserButton);
		leftVbox.setAlignment(Pos.CENTER);
		
		centerVbox.getChildren().addAll(tableUserLabel, tablePane);
		centerVbox.setAlignment(Pos.CENTER);
		
		tablePane.getChildren().add(table);
		
		primaryStage.setScene(defaultScene);
		primaryStage.show();
		
		logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				try {
					if(onLogout()) primaryStage.close();;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		});
		
		addUserButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				try {
					onAddUser();
				} catch (Exception e) {
					e.printStackTrace();
				};
			}			
		});
		
		adminSetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				try {
					onAdminSetButton();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		});
	}
	
	public static void updateTableUser() {
		List<User> listu = UserDAO.listUser();
		
		ObservableList<User> list;
		list = FXCollections.observableArrayList(listu);
		
		table.setItems(list);
	}
	
	public boolean onLogout() throws Exception {
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Deconnexion");
	      alert.setHeaderText("Voulez-vous vous deconnecter ?");

	      Optional<ButtonType> option = alert.showAndWait();

	      if (option.get() == null) {
	    	  return false;
	      } else if (option.get() == ButtonType.OK) {
	    	  ConnexionUI conUI = new ConnexionUI();
	    	  conUI.init();
	    	  conUI.start(new Stage());
	    	  return true;
	      } else if (option.get() == ButtonType.CANCEL) {
	    	  return false;
	      } else {
	    	  return false;
	      }
	}
	
	public void onAddUser() throws Exception {
		AddUserUI addUI = new AddUserUI();
		addUI.init();
		addUI.start(new Stage());
	}
	
	public void onAdminSetButton() throws Exception {
		compteAdmin cAdm = new compteAdmin();
		User admin = AdminDAO.selectAdmin();
		cAdm.init();
		cAdm.setAdmin(admin);
		cAdm.start(new Stage());
	}
	
	public static void onTable(int id) throws Exception {
		User u = UserDAO.selectUser(id);
		
		UpdateUser uuser = new UpdateUser();
		uuser.init();
		uuser.setUser(u);
		uuser.start(new Stage());
	}
}
