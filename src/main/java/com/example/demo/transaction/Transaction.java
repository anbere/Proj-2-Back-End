package com.example.demo.transaction;

import com.example.demo.account.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@ToString (exclude = {"origin", "destination"})
@EqualsAndHashCode (exclude = {"origin", "destination"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    @Column(name = "trx_id")
    private Long id;
    private String type;
    private double amount;
    private String comment;
    private LocalDate date;
    private String status;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Account origin;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Account destination;

    public Transaction(String type, double amount, String comment) {
        this.type = type;
        this.amount = amount;
        this.comment = comment;
        this.date = LocalDate.now();
        this.status = "pending";
    }

    public Transaction(double amount)
    {
        this.amount = amount;
        this.date = LocalDate.now();
        this.status = "pending";
    }
}