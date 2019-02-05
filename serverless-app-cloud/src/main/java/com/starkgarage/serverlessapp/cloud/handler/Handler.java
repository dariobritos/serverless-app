package com.starkgarage.serverlessapp.cloud.handler;


import com.starkgarage.serverlessapp.common.gateway.GatewayRequest;
import com.starkgarage.serverlessapp.common.gateway.GatewayResponse;

@FunctionalInterface
public interface Handler {
	GatewayResponse handle(GatewayRequest request);
}
