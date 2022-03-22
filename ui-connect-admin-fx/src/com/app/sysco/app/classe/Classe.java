package com.app.sysco.app.classe;

import java.util.List;

import com.app.sysco.app.user.User;

public class Classe {
	private int idClasse;
	private String nomClasse;
	private User chefDeClasse;
	private List<User> professeurs;
	
	public Classe() {}
	
	public String getNomClasse() {
		return nomClasse;
	}
	
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	
	public User getChefDeClasse() {
		return chefDeClasse;
	}
	
	public void setChefDeClasse(User chefDeClasse) {
		this.chefDeClasse = chefDeClasse;
	}

	public int getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

	public List<User> getProfesseurs() {
		return professeurs;
	}

	public void setProfesseurs(List<User> professeurs) {
		this.professeurs = professeurs;
	}
}
