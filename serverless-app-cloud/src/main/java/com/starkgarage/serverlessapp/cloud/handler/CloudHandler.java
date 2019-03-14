package com.starkgarage.serverlessapp.cloud.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.starkgarage.serverlessapp.cloud.config.CloudComponent;
import com.starkgarage.serverlessapp.cloud.config.DaggerCloudComponent;
import com.starkgarage.serverlessapp.common.gateway.GatewayRequest;
import com.starkgarage.serverlessapp.common.gateway.GatewayResponse;
import com.starkgarage.serverlessapp.common.handler.AbstractHandler;
import io.norberg.rut.Router;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CloudHandler extends AbstractHandler {

  private static final String WARM_UP_SOURCE = "cloudwatch-warmup-trigger";

  private final Router<Handler> router;

  public CloudHandler() {
    this(DaggerCloudComponent.builder().build());
  }

  CloudHandler(CloudComponent component) {
    this(component.router());
  }

  private CloudHandler(Router<Handler> router) {
    this.router = router;
  }

  @Override
  public GatewayResponse handleRequest(GatewayRequest request, Context context) {
    if (request == null) {
      log.error("request cannot be null");
      return badRequest("invalid input");
    }

    log.info("inbound message - request" + request.toString());

    if (StringUtils.isNotBlank(request.getSource()) && WARM_UP_SOURCE.equals(request.getSource())) {
      log.debug("warm up triggered");
      return GatewayResponse.builder().statusCode(200).body("UP").build();
    }

    final Router.Result<Handler> result = router.result();

    router.route(request.getHttpMethod(), request.getResource(), result);

    if (result.isSuccess()) {
      GatewayResponse response = result.target().handle(request);

      log.info("inbound message - response: " + response.toString());

      return response;
    }

    return notFound("Resource path not found");
  }
}
