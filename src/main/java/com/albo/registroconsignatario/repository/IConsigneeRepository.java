package com.albo.registroconsignatario.repository;

import com.albo.registroconsignatario.model.Consignee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsigneeRepository extends JpaRepository<Consignee, Long> {
    
}
