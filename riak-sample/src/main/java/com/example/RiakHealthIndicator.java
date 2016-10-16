package com.example;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.core.operations.PingOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Zoltan Altfatter
 */
@Component
public class RiakHealthIndicator implements HealthIndicator {

    private RiakClient riakClient;

    public RiakHealthIndicator(RiakClient riakClient) {
        Assert.notNull(riakClient, "RiakClient must not be null");
        this.riakClient = riakClient;
    }

    @Override
    public Health health() {
        PingOperation ping = new PingOperation();
        riakClient.getRiakCluster().execute(ping);
        try {
            ping.await();
            return Health.up().build();
        } catch (InterruptedException e) {
            return Health.down().build();
        }
    }
}
