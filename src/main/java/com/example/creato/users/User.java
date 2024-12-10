package com.example.creato.users;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERINTERCO")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private boolean accountVerified;
    private String email;
    private String nom;
    private String prenom;
    private String avatar;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userinterco_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        // TODO Auto-generated method stub
        return this.roles;
    }

    public String getUsername() {
        // TODO Auto-generated method stub
        return this.userName;
    }

    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    public String getEmail() {
        return this.email;

    }

    public boolean getAccountVerified() {
        return this.accountVerified;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setAccountVerified(Boolean bool) {
        this.accountVerified = bool;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.roles.add(role);
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getAvatar() {
        return avatar;
    }

}
