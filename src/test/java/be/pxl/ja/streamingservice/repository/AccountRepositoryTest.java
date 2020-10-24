package be.pxl.ja.streamingservice.repository;

import be.pxl.ja.streamingservice.model.Account;
import be.pxl.ja.streamingservice.exception.DuplicateEmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountRepositoryTest {
    private AccountRepository accountRepository;
    private Account account;

    @BeforeEach
    public void init() {
        accountRepository = new AccountRepository();
        account = new Account("test@test.com", "Test1234");
    }

    @Test
    public void addAccountThrowDuplicateEmailException() {
        accountRepository.addAccount(account);

        assertThrows(DuplicateEmailException.class, () -> {
            accountRepository.addAccount(account);
        });
    }

    @Test
    public void findAccountReturnAccount() {
        accountRepository.addAccount(account);

        Account returnAccount = accountRepository.findAccount(account.getEmail());

        assertEquals(account, returnAccount);
    }

    @Test
    public void findAccountReturnNull() {
        Account returnAccount = accountRepository.findAccount(account.getEmail());

        assertNull(returnAccount);
    }
}
