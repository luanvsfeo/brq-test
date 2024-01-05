package com.brqtest.repository;

import com.brqtest.model.JuristicPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuristicPersonRepository extends JpaRepository<JuristicPerson,Long> {
}
