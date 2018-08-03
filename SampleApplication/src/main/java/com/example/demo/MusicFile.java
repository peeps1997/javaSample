package com.example.demo;

import java.io.File;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class MusicFile {
	InputStream mfile;
	@Id
	Metadata metadata = new Metadata();
	
}
