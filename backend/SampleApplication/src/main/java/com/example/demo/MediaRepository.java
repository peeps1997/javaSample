package com.example.demo;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<MediaFile, String> {

	@Override
	List<MediaFile> findAll();

	@SuppressWarnings("unchecked")
	@Override
	MediaFile save(MediaFile file);
	
	@Override
	Optional<MediaFile> findById(String name);

	@Override
	boolean existsById(String id);

	@Override
	void deleteById(String name);

	@Override
	void delete(MediaFile entity);

	@Override
	List<MediaFile> findAllById(Iterable<String> ids);
}