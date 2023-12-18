package com.ibx.ibxcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = ReactiveUserDetailsServiceAutoConfiguration.class)
public class IbxCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbxCloudGatewayApplication.class, args);
    }

}
