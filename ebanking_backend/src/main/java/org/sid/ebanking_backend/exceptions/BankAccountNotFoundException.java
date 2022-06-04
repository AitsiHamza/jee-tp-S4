package org.sid.ebanking_backend.exceptions;

public class BankAccountNotFoundException extends Throwable {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
