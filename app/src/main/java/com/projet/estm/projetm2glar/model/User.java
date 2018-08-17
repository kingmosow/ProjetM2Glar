package com.projet.estm.projetm2glar.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "T_User")

public class User  {
    @DatabaseField(columnName = "userId", generatedId = true)
    private int userId;
    @DatabaseField
    private String userFirstName;
    @DatabaseField
    private String userLastName;
    @DatabaseField
    private String login;
    @DatabaseField
    private String mdp ;
    @DatabaseField
    private String email ;
    @DatabaseField
    private Date dateNaissance;

    public User() {
    }

    public User(String userFirstName, String userLastName, String login, String mdp, String email, Date dateNaissance) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.login = login;
        this.mdp = mdp;
        this.email = email;
        this.dateNaissance = dateNaissance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                ", email='" + email + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
