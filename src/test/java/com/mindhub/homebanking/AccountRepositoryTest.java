package com.mindhub.homebanking;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void existAccounts(){

        List<Account> accounts = accountRepository.findAll();

        assertThat(accounts,is(not(empty())));

    }



    @Test
    public void existPersonalAccount(){

        List<Account> accounts = accountRepository.findAll();


        assertThat(accounts, hasItem(hasProperty("number", is(startsWith("VIN")))));

    }
}
