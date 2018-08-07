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
@RequestMapping("/music")
public class MusicController {
	
	@Autowired
	MusicService musicService;	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	@RequestMapping(value="/save", method=RequestMethod.POST)
    public void save(@RequestParam String name, @RequestParam String url) throws MalformedURLException {
		musicService.addMusicFile(new MusicFile(name, new URL(url)));
    }
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<MusicFile> readAll(){
//		Query query2 = new Query();
//		query2.addCriteria(Criteria.where("id").is("abc"));
//		System.out.println(mongoOperation.findOne(query2, MusicFile.class).toString());
		return musicService.getMusic();
	}
//	
//	@RequestMapping(value="/{name}", method=RequestMethod.GET)
//	public MusicFile read(@PathVariable("name") String name) {
//		//System.out.println(name);
//		MusicFile mfile=musicService.getMusicFilebyName(name);
//		System.out.println(mfile.getName()+" : "+mfile.getUrl());
//		return mfile;
//	}
//	
//	@RequestMapping(value="delete/{name}", method=RequestMethod.DELETE)
//	public void delete(@PathVariable("name") String name) {
//		musicService.deleteMusicFile(musicService.getMusicFilebyName(name));
//	}

}
