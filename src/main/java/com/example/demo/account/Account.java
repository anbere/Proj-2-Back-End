package com.example.demo.account;

import com.example.demo.transaction.Transaction;
import com.example.demo.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne(mappedBy = "account")
    private User user;

    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
//    @JoinColumn(name = "trx_id")
    private List<Transaction> origin;
    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
//    @JoinColumn(name = "trx_id")
    private List<Transaction> destination;
    /*@OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)*/

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
