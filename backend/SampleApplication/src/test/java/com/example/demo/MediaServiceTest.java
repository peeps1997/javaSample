package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MediaService.class, secure = false)
public class MediaServiceTest {
	@MockBean
	UserRepository userRepository;
	
	@MockBean
	MediaService mediaService;
	
	@Test
	public void addMusicFileTest() throws Exception{
//		MediaService mdService = mock(MediaService.class);
		List<MediaFile> mfile1 = new ArrayList<MediaFile>();
		mfile1.add(new MediaFile("Earth",new URL("http://static.videogular.com/assets/videos/videogular.mp4")));	
		MediaUser mUser1 = new MediaUser("walle", mfile1, "hidden", "ADMIN" );
//		System.out.println("INSIDE TRY");
//		Mockito.when(userRepository.findById(Mockito.anyString()).get()).thenReturn(mUser1);
		Mockito.when(mediaService.addMusicFile(Mockito.anyString(), Mockito.any(MediaFile.class))).thenReturn(mUser1);
//		System.out.println(mediaService.addMusicFile("walle", new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"))));
		MediaUser mUser2 = mediaService.addMusicFile("walle", new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")));
		mfile1.add( new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")));
		mUser1 = new MediaUser("walle", mfile1, "hidden", "ADMIN" );
		assertEquals(mUser1.toString(), mUser2.toString());	
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
	public void getUsersTest() throws MalformedURLException {
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
	
	@Test
	public void deleteMusicFileTest() throws Exception{
		
	}
}
