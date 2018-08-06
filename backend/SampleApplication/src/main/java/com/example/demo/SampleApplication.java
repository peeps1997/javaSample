package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
@SpringBootApplication
public class SampleApplication {
		
	public static void main(String[] args) {
		MusicFile music = new MusicFile();
		SpringApplication.run(SampleApplication.class, args);
		ApplicationContext ctx = 
                new AnnotationConfigApplicationContext(GridfsConfig.class);
		GridFsOperations gridOperations = 
                 (GridFsOperations) ctx.getBean("gridFsTemplate");

		String audioFileLoc = "C:\\Users\\ashekhawat\\Desktop\\sample.mp3";

		try {

		music.mfile= new FileInputStream(new File(audioFileLoc));
		
		ContentHandler handler = new DefaultHandler();
		music.metadata = new Metadata();
		Parser parser = new Mp3Parser();
		ParseContext parseCtx = new ParseContext();
		parser.parse(music.mfile, handler, music.metadata, parseCtx);
		gridOperations.store(music.mfile, "new music", music.metadata);
		
		music.mfile.close();

		// List all metadata
		
		String[] metadataNames = music.metadata.names();

		for(String name : metadataNames){
		System.out.println(name + ": " + music.metadata.get(name));
		}

		// Retrieve the necessary info from metadata
		// Names - title, xmpDM:artist etc. - mentioned below may differ based
		// on the standard used for processing and storing standardized and/or
		// proprietary information relating to the contents of a file.

		System.out.println("Title: " + music.metadata.get("title"));
		System.out.println("Artists: " + music.metadata.get("xmpDM:artist"));
		System.out.println("Genre: " + music.metadata.get("xmpDM:genre"));

		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		} catch (SAXException e) {
		e.printStackTrace();
		} catch (TikaException e) {
		e.printStackTrace();
		}
		
	}
	
}


