package com.starkgarage.serverlessapp.common.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

@FunctionalInterface
public interface QueryCallback<T> {
    void doAddQueryParams(DynamoDBQueryExpression<T> var1);
}
