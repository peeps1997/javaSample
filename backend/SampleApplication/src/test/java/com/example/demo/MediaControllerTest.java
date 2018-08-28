package com.example.demo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MediaController.class, secure = false)
public class MediaControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MediaService mediaService;

	@Test
	public void testSave() throws Exception {
		String contentMfile = "{\r\n" + 
				"    \"name\" : \"e1aarth\",\r\n" + 
				"    \"url\" : \"http://static.videogular.com/assets/videos/videogular.mp4\"\r\n" + 
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/walle/media/save").accept(MediaType.APPLICATION_JSON).content(contentMfile).contentType(MediaType.APPLICATION_JSON);
		System.out.println("REQUEST BUILDER: "+requestBuilder.toString());
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{\"Status\":201}";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Status", result.getResponse().getStatus());
		System.out.println("\n JSON OBJECT :"+ jsonObj);
		JSONAssert.assertEquals(expected, jsonObj, false);

	}
	
	@Test
	public void testReadAll() throws Exception {
		List<MediaFile> mfile = new ArrayList<MediaFile>();
		
		mfile.add(new MediaFile("Earth",new URL("http://static.videogular.com/assets/videos/videogular.mp4")));
		mfile.add(new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4")));	
	
		Mockito.when(mediaService.getMusic(Mockito.anyString())).thenReturn(mfile);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/walle/media/all").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "[{\"name\":\"Earth\",\"url\":\"http://static.videogular.com/assets/videos/videogular.mp4\"},{\"name\":\"Bigger Meltdowns\",\"url\":\"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4\"}]";
		JSONAssert.assertEquals(expected,
				result.getResponse().getContentAsString(),false);
	}

	@Test
	public void testRead() throws Exception {
		MediaFile mfile = new MediaFile("Earth",new URL("http://static.videogular.com/assets/videos/videogular.mp4"));
		Mockito.when(mediaService.getMusicFilebyName(Mockito.anyString(), Mockito.anyString())).thenReturn(mfile);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/walle/media/Earth").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{\r\n" + 
				"    \"name\" : \"Earth\",\r\n" + 
				"    \"url\" : \"http://static.videogular.com/assets/videos/videogular.mp4\"\r\n" + 
				"}";
		JSONAssert.assertEquals(expected,
				result.getResponse().getContentAsString(),false);
		}
	

	@Test
	public void testDelete() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
				"/walle/delete/Earth").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{\"Status\":200}";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Status", result.getResponse().getStatus());
		System.out.println("\n JSON OBJECT :"+ jsonObj);
		JSONAssert.assertEquals(expected, jsonObj, false);
	}


}
