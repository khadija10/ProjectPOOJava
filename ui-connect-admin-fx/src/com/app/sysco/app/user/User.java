package com.app.sysco.app.user;

public class User {
	protected int id;
	protected String nom;
	protected String prenom;
	protected String email;
	protected String adresse;
	protected String tel;
	protected String profil;
	protected String password;
	
	public User() {}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nom + " " + this.prenom + " - " + this.email;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getProfil() {
		return profil;
	}
	
	public void setProfil(String profil) {
		this.profil = profil;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
