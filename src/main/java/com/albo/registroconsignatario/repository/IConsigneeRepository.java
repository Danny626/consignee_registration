package com.albo.registroconsignatario.repository;

import java.util.Optional;

import com.albo.registroconsignatario.model.Consignee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsigneeRepository extends JpaRepository<Consignee, Long> {

    Optional<Consignee> findByDocumentNumber(@Param("documentNumber") String documentNumber);
}
