package com.example.demo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.mockito.Mock;
import org.mockito.Mockito;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MediaService.class, secure = false)
public class MediaServiceTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UserRepository userRepository;
	
	@Autowired
	MediaService mediaService;
	
	@Test
	public void addMusicFileTest() throws Exception{
		List<MediaFile> mfile1 = new ArrayList<MediaFile>();
		mfile1.add(new MediaFile("Earth",new URL("http://static.videogular.com/assets/videos/videogular.mp4")));	
		MediaUser mUser1 = new MediaUser("walle", mfile1, "hidden", "ADMIN" );
		try {
		System.out.println("INSIDE TRY");
		Mockito.when(userRepository.findById(Mockito.anyString()).get()).thenReturn(mUser1);}
		catch(Exception e) {}
//		System.out.println(mediaService.addMusicFile("walle", new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"))));
		MediaUser mUser2 = mediaService.addMusicFile("walle", new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")));
		mfile1.add( new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")));
		mUser1 = new MediaUser("walle", mfile1, "hidden", "ADMIN" );
		assertEquals(mUser1, mUser2);
		
	}
	
	
	@Test
	public void getMusicTest() throws MalformedURLException {
		List<MediaFile> mfile = new ArrayList<MediaFile>();
		mfile.add(new MediaFile("Earth",new URL("http://static.videogular.com/assets/videos/videogular.mp4")));
		mfile.add(new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")));
		Mockito.when(mediaService.getMusic(Mockito.anyString())).thenReturn(mfile);
		List<MediaFile> result = mediaService.getMusic("walle");
		assertEquals(mfile, result);
	}
	
	@Test
	public void getUser() throws MalformedURLException {
		List<MediaUser> mfile = new ArrayList<MediaUser>();
		List<MediaFile> mfile1 = new ArrayList<MediaFile>();
		mfile1.add(new MediaFile("Earth",new URL("http://static.videogular.com/assets/videos/videogular.mp4")));
		mfile1.add(new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")));	
		MediaUser mUsere1 = new MediaUser("walle", mfile1, "hidden", "ADMIN" );
		MediaUser mUsere2 = new MediaUser("cr7", mfile1, "akie", "ADMIN" );
		MediaUser mUsere3 = new MediaUser("evader", mfile1, "tusick", "ADMIN" );
		MediaUser mUsere4 = new MediaUser("peeps", mfile1, "awp", "ADMIN" );
		mfile.add(mUsere1);
		mfile.add(mUsere2);
		mfile.add(mUsere3);
		mfile.add(mUsere4);
		//System.out.println(userRepository.findAll());
		Mockito.when(userRepository.findAll()).thenReturn(mfile);
		System.out.println(mfile);
		assertEquals(mfile, mediaService.getUsers());
	}
}
