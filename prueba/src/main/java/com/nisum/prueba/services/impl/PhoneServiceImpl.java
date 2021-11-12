package com.nisum.prueba.services.impl;

import com.nisum.prueba.models.Phone;
import com.nisum.prueba.repositories.IPhoneRepository;
import com.nisum.prueba.services.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements IPhoneService {

    @Autowired
    private IPhoneRepository phoneRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Phone> findById(String id) {
        return phoneRepository.findById(id);
    }

    @Override
    @Transactional
    public Phone add(Phone model) {
        model.setActive(true);
        return phoneRepository.save(model);
    }

    @Override
    @Transactional
    public Phone update(Phone model) {
        return phoneRepository.saveAndFlush(model);
    }

    @Override
    @Transactional
    public void delete(Phone model) {
        phoneRepository.delete(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Phone> findAll() {
        return phoneRepository.findAll();
    }
}
