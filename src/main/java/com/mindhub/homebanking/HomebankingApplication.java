package com.mindhub.homebanking;


import com.mindhub.homebanking.models.*;


import com.mindhub.homebanking.repositories.*;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.*;


import static com.mindhub.homebanking.models.CardColor.GOLD;
import static com.mindhub.homebanking.models.CardColor.TITANIUM;
import static com.mindhub.homebanking.models.TransactionType.CREDIT;
import static com.mindhub.homebanking.models.TransactionType.DEBIT;



@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {

		return (args) -> {
			// save a client

			Client client = (new Client("Melba", "Morel", "melba@mindhub.com"));
			clientRepository.save(client);
			Client client1 = (new Client("Juan", "García", "juan@mindhub.com"));
			clientRepository.save(client1);
			Account account1 = new Account("VIN001", LocalDate.now(), 5000, client);
			account1.setClient(client);
			accountRepository.save(account1);

			Account account2 = new Account("VIN002", LocalDate.now().plusDays(1), 7500, client);
			account2.setClient(client);
			accountRepository.save(account2);

			Account account3 = new Account("VIN003", LocalDate.now().plusDays(1), 7500, client1);
			account3.setClient(client1);
			accountRepository.save(account3);

			clientRepository.save(client1);


			Transaction transaction = new Transaction(DEBIT, 1000.00, "extraction", LocalDate.now(), account1);
			transactionRepository.save(transaction);
			Transaction transaction1 = new Transaction(CREDIT, 5000, "deposit", LocalDate.now(), account1);
			List<Transaction> transactions = new ArrayList<>();
			transactions.add(transaction);
			transactions.add(transaction1);
			account1.addTransaction(transactions);
			accountRepository.save(account1);
			transactionRepository.save(transaction1);
			Loan loan1 = new Loan("Hipotecario", 500000, Arrays.asList(6, 12, 24, 36, 48, 60));
			loanRepository.save(loan1);
			Loan loan2 = new Loan("Personal", 100000, Arrays.asList(6, 12, 24));
			loanRepository.save(loan2);
			Loan loan3 = new Loan("Automotriz", 300000, Arrays.asList(6, 12, 24, 36));
			loanRepository.save(loan3);

			Loan loan4 = new Loan();
			ClientLoan clientloan1 = new ClientLoan();

			clientloan1.setLoan(loan4);
			clientloan1.setAmount(400000);
			clientloan1.getLoan().setName(loan1.getName());
			clientloan1.setPayments(loan1.getPayments().get(5));
			clientloan1.setClient(client);

			loanRepository.save(loan4);
			clientRepository.save(client);

			clientLoanRepository.save(clientloan1);
			Loan loan5 = new Loan();
			ClientLoan clientloan2 = new ClientLoan();

			clientloan2.setLoan(loan5);
			clientloan2.setAmount(50000);
			clientloan2.getLoan().setName(loan2.getName());
			clientloan2.setPayments(loan2.getPayments().get(2));
			clientloan2.setClient(client);
			loanRepository.save(loan5);
			clientLoanRepository.save(clientloan2);
			clientRepository.save(client);

			Loan loan6 = new Loan();
			ClientLoan clientloan3 = new ClientLoan();

			clientloan3.setLoan(loan6);
			clientloan3.setAmount(100000);
			clientloan3.getLoan().setName(loan2.getName());
			clientloan3.setPayments(loan2.getPayments().get(1));
			clientloan3.setClient(client1);
			loanRepository.save(loan6);
			clientLoanRepository.save(clientloan3);
			clientRepository.save(client1);
			Loan loan7 = new Loan();
			ClientLoan clientloan4 = new ClientLoan();

			clientloan4.setLoan(loan7);
			clientloan4.setAmount(200000);
			clientloan4.getLoan().setName(loan3.getName());
			clientloan4.setPayments(loan3.getPayments().get(3));
			clientloan4.setClient(client1);
			loanRepository.save(loan7);
			clientLoanRepository.save(clientloan4);
			clientRepository.save(client1);

			Card card1 = new Card(client.getFirstName()+" "+client.getLastName(), CardType.DEBIT, GOLD, "123456789", 789, LocalDate.now().plusYears(5), LocalDate.now());
			card1.setClient(client);
			cardRepository.save(card1);
			clientRepository.save(client);
			Card card2 = new Card(client.getFirstName()+" "+client.getLastName(), CardType.CREDIT, GOLD, "123456789A", 456, LocalDate.now().plusYears(5), LocalDate.now());
			card2.setClient(client);
			cardRepository.save(card2);
			clientRepository.save(client);
			Card card3 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.CREDIT, TITANIUM, "0123456789", 123, LocalDate.now().plusYears(5), LocalDate.now());
			card3.setClient(client1);
			cardRepository.save(card3);
			clientRepository.save(client1);
		};

	}
}
