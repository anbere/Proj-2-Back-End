package com.example.demo.account;

import com.example.demo.transaction.Transaction;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = {"user", "origin", "destination"})
@EqualsAndHashCode(exclude = {"user", "origin", "destination"})
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
