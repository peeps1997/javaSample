package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public class MusicService {
	@Autowired
	MusicRepository musicRepo;
	
	public MusicFile getMusicFilebyName(String id) {
		for( MusicFile mf: musicRepo.findAll()) {
			System.out.println(mf.getName());
		}
		return musicRepo.findById(id).orElseGet(()->new MusicFile());
	}
	public MusicFile addMusicFile(MusicFile file) {
		return musicRepo.save(file);
	}
	
	public void deleteMusicFile(MusicFile file) {
		musicRepo.delete(file);
	}
}
