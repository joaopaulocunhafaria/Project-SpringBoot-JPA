package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entites.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.Exception.DataBaseException;
import com.example.demo.services.Exception.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id);
		updateUser(entity, obj);
		return repository.save(entity);
	}

	private void updateUser(User entity, User obj) {
		entity.setEmail(obj.getEmail());
		entity.setName(obj.getName());
		entity.setPhone(obj.getPhone());
	}
}
