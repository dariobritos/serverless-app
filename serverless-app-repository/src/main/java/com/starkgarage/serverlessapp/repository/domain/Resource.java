package com.starkgarage.serverlessapp.repository.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
@DynamoDBTable(tableName = "ServerlessHubCMS-HelpHubJiraReference")
//@DynamoDBTable(tableName = "ServerlessHubCMS-HelpHubJiraReference-{Environment}")
public class Resource {

    @DynamoDBAutoGeneratedKey
    @DynamoDBHashKey(attributeName = "id")
    private String id;

}
