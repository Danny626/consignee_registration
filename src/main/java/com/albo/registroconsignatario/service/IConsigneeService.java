package com.albo.registroconsignatario.service;

import java.util.Optional;

import com.albo.registroconsignatario.model.Consignee;

public interface IConsigneeService extends IService<Consignee, Long> {
    
    Optional<Consignee> findByDocumentNumber(String documentNumber);
}
