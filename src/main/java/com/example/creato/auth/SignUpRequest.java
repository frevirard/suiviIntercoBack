package com.example.creato.auth;

import java.util.Set;

public class SignUpRequest {

    private String username;

    private String email;

    private String role;

    private String password;

    private String nom;

    public String prenoms;

    public String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
