package com.starkgarage.serverlessapp.service;

import com.starkgarage.serverlessapp.common.gateway.GatewayRequest;
import com.starkgarage.serverlessapp.common.gateway.GatewayResponse;

public interface ServerlessAppService {
    GatewayResponse getResource(GatewayRequest request);
}
