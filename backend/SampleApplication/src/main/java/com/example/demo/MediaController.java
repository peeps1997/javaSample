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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RestController
@RequestMapping("/media")
public class MediaController {
	
	@Autowired
	MediaService mediaService;	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	@RequestMapping(value="/save", method=RequestMethod.POST)
    public void save(@RequestParam String name, @RequestParam String url) throws MalformedURLException {
		mediaService.addMusicFile(new MediaFile(name, new URL(url)));
    }
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<MediaFile> readAll(){
		return mediaService.getMusic();
	}
	
	@RequestMapping(value="/{name}", method=RequestMethod.GET)
	public Optional<MediaFile> read(@PathVariable("name") String name) {
		//System.out.println(name);
		Optional<MediaFile> mfile=mediaService.getMusicFilebyName(name);
		return mfile;
	}
	
	@RequestMapping(value="delete/{name}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("name") String name) {
		mediaService.deleteMusicFile(mediaService.getMusicFilebyName(name));
	}

}
