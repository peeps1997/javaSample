package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Controller
public class MusicController {

	MusicService musicService;	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	@RequestMapping(value="/music/save", method=RequestMethod.POST)
    public void save(@RequestParam String name, @RequestParam String url) throws MalformedURLException {
		MusicFile file=new MusicFile();
		file.setName(name);
		file.setUrl(new URL(url));
		mongoOperation.save(file);
    }
	
	@RequestMapping(value="/music/all", method=RequestMethod.GET)
	public MusicFile readAll(){
		return musicService.getMusicFilebyName("abc");
	}
	
	@RequestMapping(value="/music/{name}", method=RequestMethod.GET)
	public MusicFile read(@PathVariable("name") String name) {
		return musicService.getMusicFilebyName(name);
	}
	
	@RequestMapping(value="/music/{name}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("name") String name) {
		musicService.deleteMusicFile(musicService.getMusicFilebyName(name));
	}

}
