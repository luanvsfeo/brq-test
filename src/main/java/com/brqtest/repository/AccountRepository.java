package com.brqtest.repository;

import com.brqtest.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findOneByNumberAndAgency(Long number,Long agency);
}
