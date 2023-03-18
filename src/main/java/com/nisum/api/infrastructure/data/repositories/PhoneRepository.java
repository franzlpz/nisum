package com.nisum.api.infrastructure.data.repositories;

import com.nisum.api.infrastructure.data.models.Phone;
import com.nisum.api.infrastructure.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, String> {
	List<Phone> findByUser(User user);
}
