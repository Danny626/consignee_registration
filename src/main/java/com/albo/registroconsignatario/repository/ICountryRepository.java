package com.albo.registroconsignatario.repository;

import com.albo.registroconsignatario.model.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {
    
}
