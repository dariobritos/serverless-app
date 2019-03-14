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
public class GatewayRequestAuthorizer {
  private String userId;
  private String principalId;
  private String roles;
  private String delegators;
}
