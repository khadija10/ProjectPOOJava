package com.app.sysco.db.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sysco.app.classe.Classe;
import com.app.sysco.app.user.User;

public class ClasseDAO {
	
	public static String insertClasse(Classe c) {
		Connection conn = ConnDB.getConnection();
		
		String resultat = "Classe ajoute avec succes";
		String requete = "INSERT INTO classe (nom_classe, id_chef_classe, professeurs) VALUES(? ,?, '')";
		
		try {
			ConnDB.loadDriver();
			
			PreparedStatement ps = conn.prepareStatement(requete);
			
			ps.setString(1, c.getNomClasse());
			ps.setInt(2, c.getChefDeClasse().getId());
			
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			resultat = "Probleme d'insertion !";
		}
		return resultat;
	}
	
	public static List<User> selectProfForClass(int idClasse) {

		String profString = "";
		Connection conn = ConnDB.getConnection();
		List<User> users = new ArrayList<User>();
		
		try{	
			
			PreparedStatement ps=conn.prepareStatement("select professeurs from classe where id_classe=?");
			
			ps.setInt(1, idClasse);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				profString = rs.getString("professeurs");
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		
		String idGeted[] = profString.split(";");
		
		//Verification si le professeur est deja dans la classe
		try {
			conn = ConnDB.getConnection();
			
			for(String id : idGeted) {
				int idInt = Integer.parseInt(id); 
				users.add(UserDAO.selectUser(idInt));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return users;
	}
	
	/*
	 * Fonction d'ajout d'un prof dans une classe
	 * 
	 * La fonction recupere d'abord dans la table classe, le contenu de la colone "professeurs" qui est une chaine
	 * de caracetere contenant la liste des identifiants des professeurs separes entre eux par des points virgules
	 * Avec la methode Split(), on recupere individuellement tout les id des profs pour les stocker dans un tableau
	 * 
	 * 
	 */
	public static String addProfToClasse(int idProf, int idClasse) {
		
		String resultat = "Prof ajoute avec succes";
		String profString = "";
		Connection conn = ConnDB.getConnection();
		
		try{	
			
			PreparedStatement ps=conn.prepareStatement("select professeurs from classe where id_classe=?");
			
			ps.setInt(1, idClasse);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				profString = rs.getString("professeurs");
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		
		String idGeted[] = profString.split(";");
		
		//Verification si le professeur est deja dans la classe
		try {
			for(String id : idGeted) {
				int idInt = Integer.parseInt(id); 
				if(idInt == idProf) {
					return resultat = "Ce professeur est deja dans la classe !";
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		profString = idProf+";"+profString;
		
		String requete = "UPDATE classe set  professeurs=? WHERE id_classe=?";
		
		try {
			ConnDB.loadDriver();
			
			PreparedStatement ps = conn.prepareStatement(requete);
			
			ps.setString(1, profString);
			ps.setInt(2, idClasse);
			
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			resultat = "Probleme d'insertion !";
		}
		return resultat;
	}
	
	public static String deleteProfClass(int idProf, int idClasse) {

		
		String resultat = "Prof supprimer avec succes";
		String profString = "";
		Connection conn = ConnDB.getConnection();
		
		try{	
			
			PreparedStatement ps=conn.prepareStatement("select professeurs from classe where id_classe=?");
			
			ps.setInt(1, idClasse);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				profString = rs.getString("professeurs");
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		
		String idGeted[] = profString.split(";");
		
		String newProfString = "";
		
		//Verification si le professeur est deja dans la classe
		try {
			for(String id : idGeted) {
				int idInt = Integer.parseInt(id); 
				if(idInt == idProf) {
					
				}else {
					newProfString = id+";"+newProfString;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		String requete = "UPDATE classe set  professeurs=? WHERE id_classe=?";
		
		try {
			ConnDB.loadDriver();
			
			PreparedStatement ps = conn.prepareStatement(requete);
			
			ps.setString(1, newProfString);
			ps.setInt(2, idClasse);
			
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			resultat = "Probleme d'insertion !";
		}
		return resultat;
	
	}
	
	public static Classe selectClasse(int id) {
		Classe c = new Classe();
		
		try{
			Connection conn = ConnDB.getConnection();
			
			PreparedStatement ps=conn.prepareStatement("select * from classe where id_classe=?");
			
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				c.setIdClasse(id);
				c.setNomClasse(rs.getString("nom_classe"));
				c.setChefDeClasse(UserDAO.selectUser(rs.getInt("id_chef_classe")));
				c.setProfesseurs(null);
			}
			
			conn.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return c;
	}
	
	public static int updateClasse(Classe c) {
		
		int status=0;
		
		try{
			Connection con= ConnDB.getConnection();
			PreparedStatement ps=con.prepareStatement("UPDATE classe SET nom_classe=?, id_chef_classe=? WHERE id_classe=?");
			
			ps.setString(1, c.getNomClasse());
			ps.setInt(2, c.getChefDeClasse().getId());
			ps.setInt(3, c.getIdClasse());
			
			status = ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	
	public static void deleteClasse() {
		
	}
	
	public static List<Classe> listClasse() {
		List<Classe> classes = new ArrayList<Classe>();
		Connection conn = ConnDB.getConnection();
		
		try {
			String query = "select * from classe order by id_classe DESC";

			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet resultset = preparedStatement.executeQuery();
			
			while(resultset.next()) {
				Classe c = new Classe();
				c.setIdClasse(resultset.getInt("id_classe"));
				User chefDeClasse = UserDAO.selectUser(resultset.getInt("id_chef_classe"));
				c.setChefDeClasse(chefDeClasse);
				c.setNomClasse(resultset.getString("nom_classe"));
				c.setProfesseurs(null);
				
				classes.add(c);
			}
			
		} catch (Exception e) {
			
		}
		
		return classes;
	}
	
	
}
