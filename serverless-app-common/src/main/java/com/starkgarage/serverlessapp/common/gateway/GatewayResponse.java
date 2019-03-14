package com.starkgarage.serverlessapp.common.gateway;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
public class GatewayResponse {
  public static final Map<String, String> HEADERS_JSON =
      Collections.unmodifiableMap(
          new HashMap<String, String>() {
            private static final long serialVersionUID = 1L;

            {
              this.put("Content-Type", "application/json");
              this.put("Access-Control-Allow-Origin", "*");
              this.put("Access-Control-Allow-Credentials", "true");
              this.put("Accept-Encoding", "gzip");
            }
          });

  private String body;
  private int statusCode;
  private Map<String, String> headers;
}
