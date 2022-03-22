package com.app.sysco.db.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.app.sysco.app.user.User;

public class AdminDAO {
	
	public static User selectAdmin() {
		User u = new User();
		
		try{
			Connection conn = ConnDB.getConnection();
			
			PreparedStatement ps=conn.prepareStatement("select * from user where profil=?");
			
			ps.setString(1, "ADMIN");
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
	
	public static int updateAdmin(User u) {
		
		int status=0;
		
		try{
			Connection con= ConnDB.getConnection();
			PreparedStatement ps=con.prepareStatement("UPDATE user SET login=?, password=? WHERE profil=?");
			
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPassword());
			ps.setString(3, "ADMIN");
	
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
}
