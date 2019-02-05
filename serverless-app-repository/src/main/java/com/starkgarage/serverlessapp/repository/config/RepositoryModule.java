package com.starkgarage.serverlessapp.repository.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.starkgarage.serverlessapp.repository.ResourceRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class RepositoryModule {


	@Provides
	@Named("resourceRepository")
	@Singleton
	public ResourceRepository resourceRepository(final AmazonDynamoDB amazonDynamoDB) {
		return new ResourceRepository(amazonDynamoDB);
	}

	@Provides
	@Singleton
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard().build();
	}


	private static String env(String key){
		return System.getenv(key);
	}
}
