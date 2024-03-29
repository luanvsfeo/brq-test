package com.brqtest.repository;

import com.brqtest.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByNumberAndAgency(Long number, Long agency);

    boolean existsByAgency (Long agency);

    boolean existsByNumber (Long number);
}
