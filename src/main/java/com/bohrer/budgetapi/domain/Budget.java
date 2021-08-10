package com.bohrer.budgetapi.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="budget")
@Getter @Setter @NoArgsConstructor
public class Budget {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int month; // 1-12
    private int year; 

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "budget")
    private Set<BudgetItem> items;
}
