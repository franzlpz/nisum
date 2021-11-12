package com.nisum.prueba.repositories;

import com.nisum.prueba.models.Phone;
import com.nisum.prueba.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPhoneRepository extends JpaRepository<Phone, String> {
    List<Phone> findByUser(User user);
}
