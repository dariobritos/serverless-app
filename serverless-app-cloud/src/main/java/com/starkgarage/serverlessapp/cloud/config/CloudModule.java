package com.starkgarage.serverlessapp.cloud.config;

import com.starkgarage.serverlessapp.cloud.handler.Handler;
import com.starkgarage.serverlessapp.service.ServerlessAppService;
import dagger.Module;
import dagger.Provides;
import io.norberg.rut.Router;

import javax.inject.Singleton;

@Module
public class CloudModule {

    private static final String RESOURCE_PATH = "/resource";

    @Provides
    @Singleton
    public Router<Handler> router(final ServerlessAppService serverlessAppService) {
        return Router.builder(Handler.class)
                .route("GET", RESOURCE_PATH, serverlessAppService::getResource)
                .build();
    }

}
