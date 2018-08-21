package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
	@Autowired
	MediaRepository mediaRepository;
	@Autowired
	UserRepository userRepository;
	public MediaFile getMusicFilebyName(String username, String name) {
		List<MediaFile> mList = userRepository.findById(username).get().getMedia();
		int index=-1;
		//System.out.println("Size:"+mList.size());
		for( int i=0;i<mList.size();i++) {
			
			if(name.equals(mList.get(i).getName())) {
				index=i;
			}}
		//System.out.println("INDEX OUTSIDE IF"+index);
		return mList.get(index);
	}
	
	public Optional<MediaUser> getUserbyId(String username){
		return userRepository.findById(username);
	}
	
	public List<MediaFile> getMusic(String username) {
		return userRepository.findById(username).get().getMedia();
	}

	public void addMusicFile(String username, MediaFile file) {
		MediaUser mUser = userRepository.findById(username).get();
		mUser.addMedia(file);
		userRepository.save(mUser);		
		System.out.println(file.toString() + " added for User: "+ username);
	}

	public void deleteMusicFile(String username, String name) {
		List<MediaFile> mList = userRepository.findById(username).get().getMedia();
		MediaUser mediaUser = userRepository.findById(username).get();
		int index=-1;
		//System.out.println(mList.size() + "Name to be deleted" + name);
		for( int i=0;i<mList.size();i++) 
			if(name.equals(mList.get(i).getName())) {
				index=i;
			//	System.out.println("Inside If INDEX: "+ index);
			}
		//System.out.println("Outside If INDEX: "+ index);
		mList.remove(index);
		mediaUser.setMedia(mList);
		userRepository.save(mediaUser);
	}

}
