package com.example;

import com.basho.riak.client.api.RiakClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * @author Zoltan Altfatter
 */
@Configuration
public class BookConfiguration {

    @Bean
    RiakClient riakClient() throws UnknownHostException {
        return RiakClient.newClient();
    }
}
