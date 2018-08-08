package com.example.demo;

import java.io.File;
import java.net.URL;
import org.apache.tika.metadata.Metadata;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="files")
public class MediaFile {
	
	@Id
	private String id;
	
	private URL url;
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id=id;
	}
	
	public URL getUrl() {
		return url;
	}
	
	public void setUrl(URL url) {
		this.url=url;
	}
	public MediaFile() {}
	public MediaFile(String id, URL url) {
		this.id=id;
		this.url=url;
	}
	
	@Override
	public String toString() {
		return " Name: "+this.id+"\t Url: "+this.url+" ";
	}
}
