package com.bohrer.budgetapi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="items")
@Getter @Setter @NoArgsConstructor
public class BudgetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    private String title; // 1-12
    private int amount; 
    private boolean isIncome;

    @ManyToOne
    @JoinColumn(name="budget_id", nullable = false)
    private Budget budget;
}
