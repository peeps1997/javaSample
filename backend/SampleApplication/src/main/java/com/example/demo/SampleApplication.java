package com.example.demo;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
@SpringBootApplication
public class SampleApplication {
		//static Downloader download=new Downloader();
	static MusicService musicService;
	public static void main(String[] args)  {
		
		SpringApplication.run(SampleApplication.class, args);
		ApplicationContext ctx = 
                new AnnotationConfigApplicationContext(MongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		//mongoOperation.createCollection(MusicFile.class);
//		try {
//			mongoOperation.save(new MusicFile("crowd cheering", new URL("https://www.sample-videos.com/audio/mp3/crowd-cheering.mp3")));
//			
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//File file = new File("C:\\Users\\ashekhawat\\Desktop\\sample1.mp4")
	}
	
}


