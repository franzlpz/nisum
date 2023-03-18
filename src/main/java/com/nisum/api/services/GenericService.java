package com.nisum.api.services;

import java.util.Collection;
import java.util.Optional;

public interface GenericService <T, V> {
	Optional<T> findById(V id);
	void delete(T model);
	Collection<T> findAll();
}
