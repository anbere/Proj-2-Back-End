package com.example.demo.transaction;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.account.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = {"origin", "destination"})
@EqualsAndHashCode(exclude = {"origin", "destination"})
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
    private Date date;
    private String status;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Account origin;
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH })
    private long destination;
    
	public Transaction(String type, double amount, String comment, Date date, String status, long destination) {
		super();
		this.type = type;
		this.amount = amount;
		this.comment = comment;
		this.date = date;
		this.status = status;
	}
    
    
    
    
}
