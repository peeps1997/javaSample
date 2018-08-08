package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
	@Autowired
	MediaRepository mediaRepository;
	
	public Optional<MediaFile> getMusicFilebyName(String id) {
	return mediaRepository.findById(id);
	}
	
	public List<MediaFile> getMusic() {
		return mediaRepository.findAll();
	}
	public void addMusicFile(MediaFile file) {
		 mediaRepository.save(file);
		 System.out.println(file.toString()+" added");
	}
	
	public void deleteMusicFile(Optional<MediaFile> optional) {
		mediaRepository.deleteById(optional.get().getId());
	}

	
}
