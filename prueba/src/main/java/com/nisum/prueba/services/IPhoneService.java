package com.nisum.prueba.services;

import com.nisum.prueba.models.Phone;

import java.util.Collection;
import java.util.Optional;

public interface IPhoneService {
    Optional<Phone> findById(String id);
    Phone add(Phone model);
    Phone update(Phone model);
    void delete(Phone model);
    Collection<Phone> findAll();
}
