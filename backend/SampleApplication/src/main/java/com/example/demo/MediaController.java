package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class MediaController {

	@Autowired
	MediaService mediaService;
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	@RequestMapping(value = "/{username}/media/save", method = RequestMethod.POST)
	public void save(@PathVariable("username") String username, @RequestBody MediaFile mfile) throws MalformedURLException {
		mediaService.addMusicFile(username, mfile);
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
	public void delete(@PathVariable("username") String username,  @PathVariable("name") String name) {
		mediaService.deleteMusicFile(username, name);
	}

}
