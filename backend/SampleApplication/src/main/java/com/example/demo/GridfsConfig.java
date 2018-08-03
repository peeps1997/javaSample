package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.MongoClient;

public class GridfsConfig extends AbstractMongoConfiguration {
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
	    return new GridFsTemplate( mongoDbFactory(), mappingMongoConverter());
	}

	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new MongoClient("127.0.0.1");
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "musicDb";
	}
}
