package com.example.demo.account;

import com.example.demo.transaction.Transaction;
import com.example.demo.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
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

    @OneToOne(mappedBy = "account")
    private User user;

    @OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "trx_id")
    private List<Transaction> transactions;



    public Account(double balance, String routingNumber)
    {
        this.balance = balance;
        this.routingNumber = routingNumber;
    }

}
