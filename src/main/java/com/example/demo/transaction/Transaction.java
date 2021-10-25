package com.example.demo.transaction;
import java.time.LocalDateTime;
import javax.persistence.*;

import com.example.demo.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private LocalDateTime time;
    private String status;

    @Transient
    private String originUsername;

    @Transient
    private String destinationUsername;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Account origin;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Account destination;

    public Transaction(String type, double amount, String comment) {
        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }

    public Transaction(double amount)
    {
        this.amount = amount;
    }
}
