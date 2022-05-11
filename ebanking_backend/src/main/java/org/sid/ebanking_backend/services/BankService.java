package org.sid.ebanking_backend.services;

import org.sid.ebanking_backend.entities.BankAccount;
import org.sid.ebanking_backend.entities.CurrentAccount;
import org.sid.ebanking_backend.entities.SavingAccount;
import org.sid.ebanking_backend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void consulter(){
        BankAccount bankAccount=bankAccountRepository.findById("1d3eb6bc-f08a-4e51-ac63-46dda1c6af4b")
                .orElse(null);
        if(bankAccount!=null) {
            System.out.println("*************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("Over Draft -> " + ((CurrentAccount) bankAccount).getOverDraft());
                ;
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Interest Rate ->" + ((SavingAccount) bankAccount).getInterestRate());
                ;
            }
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println("=====================================");
                System.out.println(op.getType() + "\t" +
                        op.getOperationDate() + "\t" +
                        op.getAmount());
            });
        }
    }
}
