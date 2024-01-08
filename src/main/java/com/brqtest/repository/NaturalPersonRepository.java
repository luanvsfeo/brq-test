package com.brqtest.repository;

import com.brqtest.model.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, Long> {

    Optional<NaturalPerson> findOneByCpf(long cpf);
}
