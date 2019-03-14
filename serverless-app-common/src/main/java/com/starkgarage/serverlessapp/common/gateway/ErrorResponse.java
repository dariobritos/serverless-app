package com.starkgarage.serverlessapp.common.gateway;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {
  private String errorCode;
  private String errorMessage;
}
