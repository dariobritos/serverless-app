package com.starkgarage.serverlessapp.common.gateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRequestContext {
  private String accountId;
  private String resourceId;
  private String stage;
  private String requestId;
  private GatewayIdentity identity;
  private String resourcePath;
  private String httpMethod;
  private String apiId;
  private GatewayRequestAuthorizer authorizer;
}
