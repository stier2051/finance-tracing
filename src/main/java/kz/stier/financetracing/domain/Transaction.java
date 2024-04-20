package kz.stier.financetracing.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", allocationSize = 1, sequenceName = "transaction_seq")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "detail")
    private String detail;
}
