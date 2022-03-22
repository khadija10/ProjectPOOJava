package com.app.sysco.db.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sysco.app.user.User;

public class UserDAO {
	
	public static User authentification(User user) {
		
		User u = new User();
		
		try{
			Connection conn = ConnDB.getConnection();
			
			PreparedStatement ps=conn.prepareStatement("select * from user where login=? and password=?");
			
			ps.setString(1,user.getEmail());
			ps.setString(2,user.getPassword());
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				u.setId(rs.getInt(1));
				u.setNom(rs.getString(2));
				u.setPrenom(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setProfil(rs.getString(6));
				u.setAdresse(rs.getString(7));
				u.setTel(rs.getString(8));
			}
			
			conn.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return u;
	}
	
	public static String insertUser(User u) {
		Connection conn = ConnDB.getConnection();
		
		String resultat = "USER ADD WITH SUCCESS";
		String requete = "INSERT INTO user (nom, prenom, login, password, profil, adresse, tel) VALUES(? ,? ,?, ?, ?, ?, ?)";
		
		try {
			ConnDB.loadDriver();
			
			PreparedStatement ps = conn.prepareStatement(requete);
			
			ps.setString(1, u.getNom());
			ps.setString(2, u.getPrenom());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getProfil());
			ps.setString(6, u.getAdresse());
			ps.setString(7, u.getTel());
			
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			resultat = "Oups, l'insertion ne s'est pas bien passé !!!";
		}
		return resultat;
	}
	
	public static User selectUser(int id) {
		User u = new User();
		
		try{
			Connection conn = ConnDB.getConnection();
			
			PreparedStatement ps=conn.prepareStatement("select * from user where id_user=?");
			
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				u.setId(rs.getInt(1));
				u.setNom(rs.getString(2));
				u.setPrenom(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setProfil(rs.getString(6));
				u.setAdresse(rs.getString(7));
				u.setTel(rs.getString(8));
			}
			
			conn.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return u;
	}
	
	public static List<User> listUser() {
		List<User> users = new ArrayList<User>();
		Connection conn = ConnDB.getConnection();
		
		try {
			String query = "select * from user except (select * from user where profil=?) order by id_user DESC";

			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1,"ADMIN");
			ResultSet resultset = preparedStatement.executeQuery();
			
			while(resultset.next()) {
				User u = new User();
				u.setId(resultset.getInt("id_user")); 
				u.setNom(resultset.getString("nom"));
				u.setPrenom(resultset.getString("prenom"));
				u.setEmail(resultset.getString("login")); 
				u.setPassword(resultset.getString("password")); 
				u.setProfil(resultset.getString("profil"));
				u.setAdresse(resultset.getString("adresse")); 
				u.setTel(resultset.getString("tel"));
				
				users.add(u);
			}
			
		} catch (Exception e) {
			
		}
		
		return users;
	}
	
	public static List<User> listChefClass() {
		List<User> users = new ArrayList<User>();
		Connection conn = ConnDB.getConnection();
		
		try {
			String query = "select * from user where profil=? order by nom";

			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1,"CHEF_CLASSE");
			ResultSet resultset = preparedStatement.executeQuery();
			
			while(resultset.next()) {
				User u = new User();
				u.setId(resultset.getInt("id_user")); 
				u.setNom(resultset.getString("nom"));
				u.setPrenom(resultset.getString("prenom"));
				u.setEmail(resultset.getString("login")); 
				u.setPassword(resultset.getString("password")); 
				u.setProfil(resultset.getString("profil"));
				u.setAdresse(resultset.getString("adresse")); 
				u.setTel(resultset.getString("tel"));
				
				users.add(u);
			}
			
		} catch (Exception e) {
			
		}
		
		return users;
	}
	
	public static List<User> listEns() {
		List<User> users = new ArrayList<User>();
		Connection conn = ConnDB.getConnection();
		
		try {
			String query = "select * from user where profil=? order by nom";

			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1,"ENSEIGNANT");
			ResultSet resultset = preparedStatement.executeQuery();
			
			while(resultset.next()) {
				User u = new User();
				u.setId(resultset.getInt("id_user")); 
				u.setNom(resultset.getString("nom"));
				u.setPrenom(resultset.getString("prenom"));
				u.setEmail(resultset.getString("login")); 
				u.setPassword(resultset.getString("password")); 
				u.setProfil(resultset.getString("profil"));
				u.setAdresse(resultset.getString("adresse")); 
				u.setTel(resultset.getString("tel"));
				
				users.add(u);
			}
			
		} catch (Exception e) {
			
		}
		
		return users;
	}
	
	public static int updateUser(User u) {
		
		int status=0;
		
		try{
			Connection con= ConnDB.getConnection();
			PreparedStatement ps=con.prepareStatement("UPDATE user SET nom=?, prenom=?, login=?, profil=?, adresse=?, tel=? WHERE id_user=?");
			
			ps.setString(1, u.getNom());
			ps.setString(2, u.getPrenom());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getProfil());
			ps.setString(5, u.getAdresse());
			ps.setString(6, u.getTel());
			ps.setInt(7, u.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	
	public static int deleteUser(int id) {
		
		Connection con= ConnDB.getConnection();
		
		int status=0;
		
		try{
			PreparedStatement ps=con.prepareStatement("DELETE FROM user WHERE id_user=?");
			
			ps.setInt(1,id);
			
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	
	
}
