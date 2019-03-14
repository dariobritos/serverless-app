package com.starkgarage.serverlessapp.common.handler;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.starkgarage.serverlessapp.common.gateway.ErrorResponse;
import com.starkgarage.serverlessapp.common.gateway.GatewayRequest;
import com.starkgarage.serverlessapp.common.gateway.GatewayResponse;
import com.starkgarage.serverlessapp.common.util.GsonHelper;

public abstract class AbstractHandler implements RequestHandler<GatewayRequest, GatewayResponse> {
  public AbstractHandler() {}

  protected GatewayResponse gatewayResponse(int statusCode, Object body) {
    return GatewayResponse.builder()
        .statusCode(statusCode)
        .body(GsonHelper.gson().toJson(body))
        .headers(GatewayResponse.HEADERS_JSON)
        .build();
  }

  protected ErrorResponse errorResponse(String errorCode, String errorMessage) {
    return ErrorResponse.builder().errorCode(errorCode).errorMessage(errorMessage).build();
  }

  protected GatewayResponse internalServerError() {
    return this.gatewayResponse(
        500,
        this.errorResponse(
            String.valueOf(500), "Internal Server Error, Please contact administrator."));
  }

  protected GatewayResponse badRequest(String errorMessage) {
    return this.gatewayResponse(400, this.errorResponse(String.valueOf(400), errorMessage));
  }

  protected GatewayResponse notFound(String errorMessage) {
    return this.gatewayResponse(404, this.errorResponse(String.valueOf(404), errorMessage));
  }

  protected GatewayResponse unauthorized(String errorMessage) {
    return this.gatewayResponse(401, this.errorResponse(String.valueOf(401), errorMessage));
  }

  protected GatewayResponse forbidden(String errorMessage) {
    return this.gatewayResponse(403, this.errorResponse(String.valueOf(403), errorMessage));
  }

  protected GatewayResponse ok(Object responseBody) {
    return this.gatewayResponse(200, responseBody);
  }
}
