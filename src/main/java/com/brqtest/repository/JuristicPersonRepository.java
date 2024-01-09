package com.brqtest.repository;

import com.brqtest.model.JuristicPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JuristicPersonRepository extends JpaRepository<JuristicPerson, Long> {

    Optional<JuristicPerson> findOneByCnpj(long cnpj);
}
