package com.starkgarage.serverlessapp.common.gateway;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRequest {
    private String source;
    private String resource;
    private String path;
    private String httpMethod;
    private Map<String, String> headers;
    private Map<String, String> queryStringParameters;
    private Map<String, String> pathParameters;
    private Map<String, String> stageVariables;
    private GatewayRequestContext requestContext;
    private String body;
    private boolean base64Encoded;
}
