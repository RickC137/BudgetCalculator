package com.bohrer.budgetapi.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    private Date startDate;
    private Date endDate;

    @JsonBackReference
    @OneToOne
    private User user;

    public Account(Date start, Date end, User user) {
        this.startDate = start;
        this.endDate = end;
        this.user = user;
    }

}
