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
public class GatewayIdentity {
  private String cognitoIdentityPoolId;
  private String accountId;
  private String cognitoIdentityId;
  private String caller;
  private String apiKey;
  private String sourceIp;
  private String cognitoAuthenticationType;
  private String cognitoAuthenticationProvider;
  private String userArn;
  private String userAgent;
  private String user;
}
