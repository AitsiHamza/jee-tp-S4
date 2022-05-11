package org.sid.ebanking_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebanking_backend.enums.AccountStatus;
import org.sid.ebanking_backend.enums.OperationType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "TYPE",length = 4)//, discriminatorType = DiscriminatorType.STRING)
public abstract class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.EAGER)
    private List<AccountOperation> accountOperations;
}
