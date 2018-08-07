package com.example.demo;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
@SpringBootApplication
public class SampleApplication {
		//static Downloader download=new Downloader();
	static MusicService musicService;
	static MusicRepository musicRepo;
	public static void main(String[] args) throws Exception  {
		
		SpringApplication.run(SampleApplication.class, args);
		//mongoOperation.save(new MusicFile("music123",new URL("https://www.sample-videos.com/audio/mp3/crowd-cheering.mp3")));
//		MusicService musicService=null;
//		musicService.getMusicFilebyName("random");
//		Query query2 = new Query();
//		query2.addCriteria(Criteria.where("id").is("abc"));
		//System.out.println("Found :"+musicRepo.findById("abc").toString());
		//File file = new File("C:\\Users\\ashekhawat\\Desktop\\sample1.mp4")
	}
	@Bean
    CommandLineRunner init(MusicRepository musicRepo) {

        return args -> {

            Optional<MusicFile> obj = musicRepo.findById("abc");
            System.out.println(obj.toString());

            List<MusicFile> obj2 = musicRepo.findAll();
            for(MusicFile mfile : obj2) {
            	System.out.println(mfile.toString());
            	}
            };

            

        };

	
}


