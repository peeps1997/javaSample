package com.example.demo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends CrudRepository<MusicFile, String> {
	
	@Override
	Optional<MusicFile> findById(String id);
	
	@Override
	void delete(MusicFile deleted);
}