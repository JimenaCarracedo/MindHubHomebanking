package com.mindhub.homebanking;


import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Models.Transaction;
import com.mindhub.homebanking.Repositories.AccountRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import com.mindhub.homebanking.Repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.*;

import static com.mindhub.homebanking.Models.TransactionType.CREDIT;
import static com.mindhub.homebanking.Models.TransactionType.DEBIT;


@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClientRepository repository, AccountRepository accountRepository, TransactionRepository transactionRepository) {

		return (args) -> {
			// save a client

			Client client = (new Client("Melba", "Morel", "melba@mindhub.com"));
			repository.save(client);
			Account account1 = new Account("VIN001", LocalDate.now(), 5000, client);
			accountRepository.save(account1);
			client.addAccount(account1);
			Account account2 = new Account("VIN002", LocalDate.now().plusDays(1), 7500, client);
			accountRepository.save(account2);
			client.addAccount(account2);



			Transaction transaction = new Transaction(DEBIT, 1000.00, "extraction", LocalDate.now(), account1);
			transactionRepository.save(transaction);
			Transaction transaction1 =new Transaction(CREDIT, 5000, "deposit", LocalDate.now(), account1);
			List<Transaction> transactions = new ArrayList<>();
			transactions.add(transaction);
			transactions.add(transaction1);
			account1.addTransaction(transactions);
			accountRepository.save(account1);
			transactionRepository.save(transaction1);

		};
	}
}