package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Zoltan Altfatter
 */
@ConfigurationProperties(prefix = "riak")
public class RiakProperties {

    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
