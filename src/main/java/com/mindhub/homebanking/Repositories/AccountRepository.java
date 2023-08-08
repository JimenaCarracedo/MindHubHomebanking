package com.mindhub.homebanking.Repositories;

import com.mindhub.homebanking.DTO.AccountDTO;
import com.mindhub.homebanking.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {

}
