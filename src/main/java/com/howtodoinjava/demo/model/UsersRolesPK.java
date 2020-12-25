package com.howtodoinjava.demo.model;

import java.io.Serializable;

public class UsersRolesPK implements Serializable 
{



	  private static final long serialVersionUID = 1L;

	  private String user_id;

	  private String role_id;

	  public UsersRolesPK() {
	  }

	  public UsersRolesPK(String user_id, String role_id) {
	    this.user_id = user_id;
	    this.role_id = role_id;
	  }

	  public String getNom() {
	    return this.user_id;
	  }

	  public void setNom(String user_id) {
	    this.user_id = user_id;
	  }

	  public String getPrenom() {
	    return role_id;
	  }

	  public void setPrenom(String role_id) {
	    this.user_id = role_id;
	  }

	  public boolean equals(Object obj) {
	    boolean resultat = false;

	    if (obj == this) {
	      resultat = true;
	    } else {
	      if (!(obj instanceof UsersRolesPK)) {
	        resultat = false;
	      } else {
	    	  UsersRolesPK autre = (UsersRolesPK) obj;
	        if (!user_id.equals(autre.user_id)) {
	          resultat = false;
	        } else {
	          if (role_id != autre.role_id) {
	            resultat = false;
	          } else {
	            resultat = true;
	          }
	        }
	      }
	    }
	    return resultat;
	  }

	  public int hashCode() {
	    return (user_id + role_id).hashCode();
	  }
	}