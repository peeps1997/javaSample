package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<MediaUser, String>{
	
	@Override
	List<MediaUser> findAll();

	@SuppressWarnings("unchecked")
	@Override
	MediaUser save(MediaUser file);

	@Override
	Optional<MediaUser> findById(String username);

	@Override
	boolean existsById(String username);

	@Override
	void deleteById(String username);

	@Override
	void delete(MediaUser entity);
}
