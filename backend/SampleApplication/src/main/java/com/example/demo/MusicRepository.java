package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends CrudRepository<MusicFile, String> {
	
	@Override 
	List<MusicFile> findAll();
	
	@SuppressWarnings("unchecked")
	@Override
	MusicFile save(MusicFile file);
	
	@Override
	Optional<MusicFile> findById(String id);
	
	@Override
	boolean existsById(String id);
	
	@Override
	void deleteById(String id);
	
	@Override
	void delete(MusicFile entity);
	
	@Override
	List<MusicFile> findAllById(Iterable<String> ids);
	

}