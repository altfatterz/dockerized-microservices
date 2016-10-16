package com.example;

import com.basho.riak.client.api.RiakClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * @author Zoltan Altfatter
 */
@Configuration
@EnableConfigurationProperties(RiakProperties.class)
public class BookConfiguration {

    @Autowired
    RiakProperties riakProperties;

    @Bean
    RiakClient riakClient() throws UnknownHostException {
        return RiakClient.newClient(riakProperties.getHost());
    }
}
