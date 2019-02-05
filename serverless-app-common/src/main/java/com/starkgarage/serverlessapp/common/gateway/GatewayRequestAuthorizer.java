package com.starkgarage.serverlessapp.common.gateway;

import lombok.*;

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
