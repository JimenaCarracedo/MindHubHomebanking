package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.dtos.ApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<LoanDTO> findByName(String name);
}