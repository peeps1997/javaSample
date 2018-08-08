package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
	@Autowired
	MediaRepository mediaRepository;
	
	public Optional<MediaFile> getMusicFilebyName(String name) {
	return mediaRepository.findById(name);
	}
	
	public List<MediaFile> getMusic() {
		return mediaRepository.findAll();
	}
	public void addMusicFile(MediaFile file) {
		 mediaRepository.save(file);
		 System.out.println(file.toString()+" added");
	}
	
	public void deleteMusicFile(Optional<MediaFile> optional) {
		mediaRepository.deleteById(optional.get().getName());
	}

	
}
