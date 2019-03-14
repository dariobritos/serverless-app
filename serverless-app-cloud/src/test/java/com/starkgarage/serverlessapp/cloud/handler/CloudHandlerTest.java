package com.starkgarage.serverlessapp.cloud.handler;

import cloud.localstack.DockerTestUtils;
import cloud.localstack.docker.LocalstackDockerTestRunner;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.starkgarage.serverlessapp.cloud.config.DaggerCloudComponent;
import com.starkgarage.serverlessapp.common.gateway.GatewayRequest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class})
@PowerMockRunnerDelegate(LocalstackDockerTestRunner.class)
@PowerMockIgnore(
    value = {"javax.management.*", "org.apache.http.*", "javax.net.ssl.*", "javax.crypto.*"})
@LocalstackDockerProperties(
    randomizePorts = true,
    services = {"dynamodb"})
public class CloudHandlerTest {

  public static final String RESOURCE_TABLE_NAME = "Resource-Table";
  private CloudHandler cloudHandler;
  private AmazonDynamoDB dynamoDB;

  @Before
  public void setup() {
    mockStatic();
    buildDatabase();
    this.cloudHandler = new CloudHandler(DaggerCloudComponent.builder().build());
  }

  private void mockStatic() {
    PowerMockito.mockStatic(System.class);
    PowerMockito.when(System.getenv("VARIABLE-1")).thenReturn("VALUE-1");
  }

  private void buildDatabase() {
    dynamoDB = DockerTestUtils.getClientDynamoDb();

    try {
      DynamoDbTestHelper.deleteTable(RESOURCE_TABLE_NAME);
    } catch (Exception e) {
      // Do Nothing
    }

    dynamoDB.createTable(DynamoDbTestHelper.createDynamoDBTable(RESOURCE_TABLE_NAME, "id"));

    Map<String, AttributeValue> item = new HashMap<>();
    item.put("id", new AttributeValue("id1"));
    dynamoDB.putItem(RESOURCE_TABLE_NAME, item);
  }

  @Test
  public void test1() {
    GatewayRequest request =
        GatewayRequest.builder().httpMethod("GET").resource("/resource").build();
    Context context = null;
    cloudHandler.handleRequest(request, context);
  }
}
