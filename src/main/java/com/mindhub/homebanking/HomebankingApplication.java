package com.mindhub.homebanking;



import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;


import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Autowired
	PasswordEncoder passwordEncoder;





	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {

		return (args) -> {
			// save a client

			Client client = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("123"));
			clientRepository.save(client);
			Client client1 = new Client("Juan", "García", "juan@mindhub.com", passwordEncoder.encode("321"));
			clientRepository.save(client1);
			Client client3 = new Client("Admin", "Admin", "admin@mindhub.com", passwordEncoder.encode("123"));
			clientRepository.save(client3);
			Account account1 = new Account("VIN001", LocalDate.now(), 5000, client);
			account1.setClient(client);
			accountRepository.save(account1);
			client.addAccount(account1);
			clientRepository.save(client);
			Account account2 = new Account("VIN002", LocalDate.now().plusDays(1), 7500, client);
			account2.setClient(client);
			accountRepository.save(account2);
			client.addAccount(account2);
			clientRepository.save(client1);
			Account account3 = new Account("VIN003", LocalDate.now().plusDays(1), 7500, client1);
			account3.setClient(client1);
			accountRepository.save(account3);

			clientRepository.save(client1);


			Transaction transaction = new Transaction(TransactionType.DEBIT, 1000.00, "extraction", LocalDate.now(), account1);
			transactionRepository.save(transaction);
			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 5000, "deposit", LocalDate.now(), account1);
			List<Transaction> transactionsS = new ArrayList<>();
			transactionsS.add(transaction);
			transactionsS.add(transaction1);
			account1.addTransaction(transactionsS);
			accountRepository.save(account1);
			transactionRepository.save(transaction1);
			Loan loan1 = new Loan("Hipotecario", 500000, Arrays.asList(6, 12, 24, 36, 48, 60));
			loanRepository.save(loan1);
			Loan loan2 = new Loan("Personal", 100000, Arrays.asList(6, 12, 24));
			loanRepository.save(loan2);
			Loan loan3 = new Loan("Automotriz", 300000, Arrays.asList(6, 12, 24, 36));
			loanRepository.save(loan3);


			ClientLoan clientloan1 = new ClientLoan(400000, 60);
			clientloan1.setLoan(loan1);
			clientloan1.getLoan().setName(loan1.getName());
			clientloan1.setClient(client);
			client.addLoan(clientloan1);
			clientLoanRepository.save(clientloan1);
			clientRepository.save(client);


			ClientLoan clientloan2 = new ClientLoan(50000, 12);
			clientloan2.setLoan(loan2);
			clientloan2.getLoan().setName(loan2.getName());
			clientloan2.setClient(client);
			clientLoanRepository.save(clientloan2);
			clientRepository.save(client);


			ClientLoan clientloan3 = new ClientLoan(100000, 6);
			clientloan3.setLoan(loan2);
			clientloan3.getLoan().setName(loan2.getName());
			clientloan3.setClient(client1);
			clientLoanRepository.save(clientloan3);
			clientRepository.save(client1);

			ClientLoan clientloan4 = new ClientLoan(200000, 24);
			clientloan4.setLoan(loan3);
			clientloan4.getLoan().setName(loan3.getName());
			clientloan4.setClient(client1);
			clientLoanRepository.save(clientloan4);
			clientRepository.save(client1);

			Card card1 = new Card(client.getFirstName() + " " + client.getLastName(), CardType.DEBIT, CardColor.GOLD, "123456789", 789, LocalDate.now().plusYears(5), LocalDate.now());
			card1.setClient(client);
			cardRepository.save(card1);
			clientRepository.save(client);
			Card card2 = new Card(client.getFirstName() + " " + client.getLastName(), CardType.CREDIT, CardColor.GOLD, "123456789A", 456, LocalDate.now().plusYears(5), LocalDate.now());
			card2.setClient(client);
			cardRepository.save(card2);
			clientRepository.save(client);
			Card card3 = new Card(client1.getFirstName() + " " + client1.getLastName(), CardType.CREDIT, CardColor.TITANIUM, "0123456789", 123, LocalDate.now().plusYears(5), LocalDate.now());
			card3.setClient(client1);
			cardRepository.save(card3);
			clientRepository.save(client1);
		};
	}

	}




