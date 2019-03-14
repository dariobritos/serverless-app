package com.starkgarage.serverlessapp.repository.config;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

@FunctionalInterface
public interface ScanCallback<V> {
  void doAddQueryParams(DynamoDBScanExpression dynamoDBExpression);
}
