package com.starkgarage.serverlessapp.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.starkgarage.serverlessapp.repository.domain.Resource;

public class ResourceRepository extends DynamoDBRepository<Resource> {

  public static final String RESOURCE_TABLE_NAME = "Resource-Table";

  public ResourceRepository(AmazonDynamoDB dynamoDBClient) {
    super(dynamoDBClient, Resource.class, RESOURCE_TABLE_NAME);
  }
}
