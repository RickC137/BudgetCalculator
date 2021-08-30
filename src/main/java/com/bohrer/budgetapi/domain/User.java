package com.bohrer.budgetapi.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter @Setter @NoArgsConstructor
public class User {

    // should use a salt for the password and save it with the user info to the database
    // this would make it more secure.
    // Will leave as is for now though maybe add another user model that has this implemented
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(nullable=false, unique = true) 
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    public String getPassword(){
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Account account;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Budget> budgets;

    public String toString() {
        return username + ":" + password;
    }
    
}
