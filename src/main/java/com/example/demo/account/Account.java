package com.example.demo.account;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.transaction.Transaction;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = {"origin", "destination", "user"})
@EqualsAndHashCode(exclude = {"origin", "destination", "user"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    @Column(name = "account_id")
    private Long id;
    private double balance;
    private String routingNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    private List<Transaction> origin;
    
    @JsonIgnore
    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
    private List<Transaction> destination;
    
    
//    @OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)
//    private List<Transaction> transactions;

   /* private List<Transaction> transactions;*/


    public Account(double balance, String routingNumber)
    {
        this.balance = balance;
        this.routingNumber = routingNumber;
    }

    public Account(double balance)
    {
        this.balance = balance;
    }

}
