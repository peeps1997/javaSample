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
public class MusicService {
	@Autowired
	MusicRepository musicRepo;
	
	public Optional<MusicFile> getMusicFilebyName(String id) {
	return musicRepo.findById(id);
	}
	
	public List<MusicFile> getMusic() {
		return musicRepo.findAll();
	}
	public void addMusicFile(MusicFile file) {
		 musicRepo.save(file);
		 System.out.println(file.toString()+" added");
	}
	
	public void deleteMusicFile(MusicFile file) {
		musicRepo.delete(file);
	}

	
}
