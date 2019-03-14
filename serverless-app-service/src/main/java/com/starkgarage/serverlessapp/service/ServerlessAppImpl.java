package com.starkgarage.serverlessapp.service;

import com.google.gson.Gson;
import com.starkgarage.serverlessapp.common.gateway.GatewayRequest;
import com.starkgarage.serverlessapp.common.gateway.GatewayResponse;
import com.starkgarage.serverlessapp.repository.domain.Resource;
import java.util.ArrayList;
import java.util.List;

public class ServerlessAppImpl implements ServerlessAppService {

  @Override
  public GatewayResponse getResource(GatewayRequest request) {
    Gson gson = new Gson();
    List<Resource> result = new ArrayList<>();
    return GatewayResponse.builder().body(gson.toJson(result)).statusCode(200).build();
  }
}
