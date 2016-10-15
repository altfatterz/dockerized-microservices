package com.example;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

/**
 * @author Zoltan Altfatter
 */
@Repository
public class BookRepository {

    private RiakClient riakClient;

    public BookRepository(RiakClient riakClient) {
        this.riakClient = riakClient;
    }

    public Book findByKey(String bucket, String key) {
        FetchValue fetchOp = new FetchValue.Builder(new Location(new Namespace(bucket), key)).build();
        try {
            return riakClient.execute(fetchOp).getValue(Book.class);
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    public boolean save(String bucket, String key, Book book) {
        StoreValue storeBookOp = new StoreValue.Builder(book).withLocation(new Location(new Namespace(bucket), key)).build();
        try {
            riakClient.execute(storeBookOp);
            return true;
        } catch (ExecutionException | InterruptedException e) {
            return false;
        }
    }

}
