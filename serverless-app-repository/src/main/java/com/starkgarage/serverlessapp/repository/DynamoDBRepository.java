package com.starkgarage.serverlessapp.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.google.common.collect.ImmutableMap;
import com.starkgarage.serverlessapp.common.repository.IRepository;
import com.starkgarage.serverlessapp.common.repository.QueryCallback;
import com.starkgarage.serverlessapp.repository.config.ScanCallback;
import java.util.Collection;
import java.util.stream.Collectors;

public class DynamoDBRepository<V> implements IRepository<V> {

  final DynamoDBMapper dynamoDBMapper;
  final Class<V> type;

  public DynamoDBRepository(
      final AmazonDynamoDB dynamoDBClient, final Class<V> type, final String tableName) {
    this.dynamoDBMapper =
        new DynamoDBMapper(
            dynamoDBClient,
            new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(TableNameOverride.withTableNameReplacement(tableName))
                .build());
    this.type = type;
  }

  @Override
  public V add(V entity) {
    dynamoDBMapper.save(entity);
    return entity;
  }

  @Override
  public Collection<V> getAll() {
    PaginatedScanList<V> results = dynamoDBMapper.scan(type, new DynamoDBScanExpression());
    return results.stream().collect(Collectors.toList());
  }

  @Override
  public Collection<V> getAllUserCreatedRecords(String s) {
    return null;
  }

  @Override
  public void remove(V entity) {
    dynamoDBMapper.delete(entity);
  }

  @Override
  public V update(V entity) {
    dynamoDBMapper.save(entity);
    return entity;
  }

  @Override
  public V findById(String id) {
    PaginatedQueryList<V> results =
        dynamoDBMapper.query(
            type,
            new DynamoDBQueryExpression<V>()
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(
                    ImmutableMap.of(":id", new AttributeValue().withS(id))));
    if (!results.isEmpty()) {
      return results.stream().findFirst().get();
    } else {
      return null;
    }
  }

  @Override
  public Collection<V> query(QueryCallback<V> callback) {
    DynamoDBQueryExpression<V> expression = new DynamoDBQueryExpression<V>();
    callback.doAddQueryParams(expression);
    PaginatedQueryList<V> results = dynamoDBMapper.query(type, expression);
    return results;
  }

  public Collection<V> scan(ScanCallback<V> callback) {
    DynamoDBScanExpression expression = new DynamoDBScanExpression();
    callback.doAddQueryParams(expression);

    return dynamoDBMapper.scan(type, expression);
  }
}
