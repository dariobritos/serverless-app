package com.starkgarage.serverlessapp.service.config;

import com.starkgarage.serverlessapp.service.ServerlessAppImpl;
import com.starkgarage.serverlessapp.service.ServerlessAppService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public ServerlessAppService serverlessAppService(){
        return new ServerlessAppImpl();
    }
}
