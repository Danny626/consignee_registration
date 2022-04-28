package com.albo.registroconsignatario.impl;

import java.util.List;
import java.util.Optional;

import com.albo.registroconsignatario.model.Country;
import com.albo.registroconsignatario.repository.ICountryRepository;
import com.albo.registroconsignatario.service.ICountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public Boolean deleteById(Long id) {
        if(!countryRepository.existsById(id)){
            return false;
        }

        countryRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country saveOrUpdate(Country t) {
        return countryRepository.save(t);
    }
    
}
