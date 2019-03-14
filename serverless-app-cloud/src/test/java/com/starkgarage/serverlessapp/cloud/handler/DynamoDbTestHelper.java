package com.starkgarage.serverlessapp.cloud.handler;

import cloud.localstack.DockerTestUtils;
import com.amazonaws.services.cognitoidentity.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamoDbTestHelper {
  private static final Logger log = LoggerFactory.getLogger(DynamoDbTestHelper.class);

  public DynamoDbTestHelper() {}

  public static void deleteTable(String tableName) throws Exception {
    try {
      AmazonDynamoDB dynamoDB = DockerTestUtils.getClientDynamoDb();
      dynamoDB.deleteTable(tableName);
    } catch (ResourceNotFoundException var2) {
      log.info(String.format("Table:%s does not exist", tableName));
    }
  }

  public static CreateTableRequest createDynamoDBTable(String tableName, String keyAttributeName) {
    return (new CreateTableRequest())
        .withTableName(tableName)
        .withProvisionedThroughput(
            (new ProvisionedThroughput()).withReadCapacityUnits(5L).withWriteCapacityUnits(6L))
        .withKeySchema(
            new KeySchemaElement[] {new KeySchemaElement(keyAttributeName, KeyType.HASH)})
        .withAttributeDefinitions(attributeDefs(keyAttributeName));
  }

  public static CreateTableRequest createDynamoDBTable(
      String tableName, String keyAttributeName, Map<String, String> secondaryIndicesMap) {
    return (new CreateTableRequest())
        .withTableName(tableName)
        .withProvisionedThroughput(
            (new ProvisionedThroughput()).withReadCapacityUnits(5L).withWriteCapacityUnits(6L))
        .withKeySchema(
            new KeySchemaElement[] {new KeySchemaElement(keyAttributeName, KeyType.HASH)})
        .withAttributeDefinitions(attributeDefs(keyAttributeName, secondaryIndicesMap))
        .withGlobalSecondaryIndexes(globalSecIndices(keyAttributeName, secondaryIndicesMap));
  }

  private static List<AttributeDefinition> attributeDefs(
      String keyAttributeName, Map<String, String> secondaryIndicesMap) {
    List<AttributeDefinition> defs = attributeDefs(keyAttributeName);
    secondaryIndicesMap.forEach(
        (key, value) -> {
          defs.add((new AttributeDefinition()).withAttributeName(value).withAttributeType("S"));
        });
    return defs;
  }

  private static List<AttributeDefinition> attributeDefs(String keyAttributeName) {
    List<AttributeDefinition> defs = Lists.newArrayList();
    defs.add(
        (new AttributeDefinition()).withAttributeName(keyAttributeName).withAttributeType("S"));
    return defs;
  }

  private static List<GlobalSecondaryIndex> globalSecIndices(
      String keyAttributeName, Map<String, String> secondaryIndicesMap) {
    List<GlobalSecondaryIndex> indices = Lists.newArrayList();
    secondaryIndicesMap.forEach(
        (key, value) -> {
          indices.add(
              (new GlobalSecondaryIndex())
                  .withIndexName(key)
                  .withProjection((new Projection()).withProjectionType(ProjectionType.ALL))
                  .withProvisionedThroughput(
                      (new ProvisionedThroughput())
                          .withReadCapacityUnits(5L)
                          .withWriteCapacityUnits(6L))
                  .withKeySchema(
                      new KeySchemaElement[] {new KeySchemaElement(value, KeyType.HASH)}));
        });
    return indices;
  }
}
