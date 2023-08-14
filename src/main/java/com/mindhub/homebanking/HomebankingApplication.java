package com.mindhub.homebanking;


import com.mindhub.homebanking.Models.*;


import com.mindhub.homebanking.Repositories.*;


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
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository) {

		return (args) -> {
			// save a client

			Client client = (new Client("Melba", "Morel", "melba@mindhub.com"));
			clientRepository.save(client);
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
			Loan loan1 = new Loan("Hipotecario", 500000, Arrays.asList(6,12,24,36,48,60));
			loanRepository.save(loan1);
			Loan loan2 = new Loan("Personal", 100000, Arrays.asList(6,12,24));
			loanRepository.save(loan2);
			Loan loan3 = new Loan("Automotriz", 300000, Arrays.asList(6,12,24,36));
			loanRepository.save(loan3);


			ClientLoan clientloan1 = new ClientLoan();
			clientloan1.setAmount(400000);
			clientloan1.setName(loan1.getName());
			clientloan1.setMaxAmount(loan1.getMaxAmount());
			clientloan1.setPayment(Collections.singletonList(loan1.getPayment().get(5)));
			clientloan1.setClient(client);
			client.addLoan(clientloan1);

			clientLoanRepository.save(clientloan1);
			loanRepository.save(clientloan1);
			clientRepository.save(client);
			/*ClientLoan clientloan2 = new ClientLoan();
			clientloan2.setAmount(50000);
			clientloan2.setLoan(loan2);
			loan2.setPayment(Collections.singletonList(loan1.getPayment().get(1)));
			clientloan2.setClient(client);
			clientLoanRepository.save(clientloan2);
*/




		};
	}
}