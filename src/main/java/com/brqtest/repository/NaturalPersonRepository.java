package com.brqtest.repository;

import com.brqtest.model.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, Long> {
}
