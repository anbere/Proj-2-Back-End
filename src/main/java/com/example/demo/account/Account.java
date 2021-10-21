package com.example.demo.account;

import com.example.demo.transaction.Transaction;
import com.example.demo.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = {"origin", "destination"})
@EqualsAndHashCode(exclude = {"origin", "destination"})
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

    @OneToMany(mappedBy = "origin", fetch = FetchType.EAGER)
//    @JoinColumn(name = "trx_id")
    private List<Transaction> origin;
    @OneToMany(mappedBy = "destination", fetch = FetchType.EAGER)
//    @JoinColumn(name = "trx_id")
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
