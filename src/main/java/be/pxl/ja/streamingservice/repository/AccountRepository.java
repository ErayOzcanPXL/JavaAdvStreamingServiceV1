package be.pxl.ja.streamingservice.repository;

import be.pxl.ja.streamingservice.model.Account;
import be.pxl.ja.streamingservice.exception.DuplicateEmailException;
import java.util.Map;
import java.util.HashMap;

public class AccountRepository {
    private Map<String, Account> accounts;

    public AccountRepository() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        if (accounts.putIfAbsent(account.getEmail(), account) != null) {
            throw new DuplicateEmailException("This email is already in use");
        }
    }

    public Account findAccount(String email) {
        return accounts.get(email);
    }
}
