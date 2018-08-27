package com.example.demo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class MediaController {

	@Autowired
	MediaService mediaService;
	@RequestMapping(value = "/{username}/media/save", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@PathVariable("username") String username, @RequestBody MediaFile mfile) throws MalformedURLException {
		MediaUser mUser = mediaService.addMusicFile(username, mfile);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{username}/media/save").buildAndExpand(mfile).toUri();
	//	System.out.println(mUser.getMedia().toString());
		return ResponseEntity.created(location).build();

	}

	@RequestMapping(value = "/{username}/media/all", method = RequestMethod.GET)
	public List<MediaFile> readAll(@PathVariable("username") String username) {
		return mediaService.getMusic(username);
	}

	@RequestMapping(value = "/{username}/media/{name}", method = RequestMethod.GET)
	public MediaFile read(@PathVariable("username") String username, @PathVariable("name") String name) {
		// System.out.println(name);
		MediaFile mfile = mediaService.getMusicFilebyName(username, name);
		return mfile;
	}

	@RequestMapping(value = "/{username}/delete/{name}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("username") String username,  @PathVariable("name") String name) {
		mediaService.deleteMusicFile(username, name);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{username}/delete/{name}").buildAndExpand(username, name).toUri();
		return ResponseEntity.ok().build();
		
	}
	
	
}
