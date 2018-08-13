package com.example.demo;

import java.net.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "files")
public class MediaFile {

	@Id
	private String name;

	private URL url;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public MediaFile() {
	}

	public MediaFile(String name, URL url) {
		this.name = name;
		this.url = url;
	}

	@Override
	public String toString() {
		return " Name: " + this.name + "\t Url: " + this.url + " ";
	}
}
