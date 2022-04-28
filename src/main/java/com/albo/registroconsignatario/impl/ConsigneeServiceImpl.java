package com.albo.registroconsignatario.impl;

import java.util.List;
import java.util.Optional;

import com.albo.registroconsignatario.model.Consignee;
import com.albo.registroconsignatario.repository.IConsigneeRepository;
import com.albo.registroconsignatario.service.IConsigneeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsigneeServiceImpl implements IConsigneeService {

    @Autowired
    private IConsigneeRepository consigneeRepository;

    @Override
    public Boolean deleteById(Long id) {
        if (!consigneeRepository.existsById(id)) {
			return false;
		}

		consigneeRepository.deleteById(id);
		return true;
    }

    @Override
    public List<Consignee> findAll() {
        return consigneeRepository.findAll();
    }

    @Override
    public Optional<Consignee> findById(Long id) {
        return consigneeRepository.findById(id);
    }

    @Override
    public Consignee saveOrUpdate(Consignee t) {
        return consigneeRepository.save(t);
    }
    
}
