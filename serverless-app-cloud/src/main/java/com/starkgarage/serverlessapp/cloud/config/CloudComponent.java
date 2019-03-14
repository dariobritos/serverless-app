package com.starkgarage.serverlessapp.cloud.config;

import com.starkgarage.serverlessapp.cloud.handler.Handler;
import com.starkgarage.serverlessapp.service.config.ServiceModule;
import dagger.Component;
import io.norberg.rut.Router;
import javax.inject.Singleton;

@Singleton
@Component(modules = {CloudModule.class, ServiceModule.class})
public interface CloudComponent {
  Router<Handler> router();
}
