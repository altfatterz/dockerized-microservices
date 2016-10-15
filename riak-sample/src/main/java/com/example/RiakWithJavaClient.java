package com.example;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * @author Zoltan Altfatter
 */

public class RiakWithJavaClient {

    public static void main(String[] args) throws UnknownHostException, ExecutionException, InterruptedException {

        // This method produces a client that connects to localhost on the default protocol buffers port (8087)
        // creates and starts an underlying RiakCluster with a single node
        RiakClient client = RiakClient.newClient();

        // First, we'll create a basic object storing a movie quote
        RiakObject quoteObject = new RiakObject()
                // We tell Riak that we're storing plaintext, not JSON, HTML, etc.
                .setContentType("text/plain")
                // Objects are ultimately stored as binaries
                .setValue(BinaryValue.create("You're dangerous, Maverick"));

        System.out.println("Basic object created");

        // In the new Java client, instead of buckets you interact with Namespace
        // objects, which consist of a bucket AND a bucket type; if you don't
        // supply a bucket type, "default" is used; the Namespace below will set
        // only a bucket, without supplying a bucket type
        Namespace quotesBucket = new Namespace("quotes");

        // With our Namespace object in hand, we can create a Location object,
        // which allows us to pass in a key as well
        Location quoteObjectLocation = new Location(quotesBucket, "Iceman");
        System.out.println("Location object created for quote object");

        // With our RiakObject in hand, we can create a StoreValue operation
        StoreValue storeOp = new StoreValue.Builder(quoteObject).withLocation(quoteObjectLocation).build();
        System.out.println("StoreValue operation created");

        client.execute(storeOp);
        System.out.println("Object storage operation successfully completed");

        // Now we can verify that the object has been stored properly by
        // creating and executing a FetchValue operation
        FetchValue fetchOp = new FetchValue.Builder(quoteObjectLocation).build();
        RiakObject fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        if (fetchedObject.getValue().equals(quoteObject.getValue())) {
            System.out.println("Success! The object we created and the object we fetched have the same value");
        } else {
            System.out.println("Error! Something went wrong.");
        }

        // the object can be accessed also with HTTP, you need riak_explorer to be started
        // http :9000/riak/clusters/default/types/default/buckets/quotes/keys/Iceman

        // And we'll delete the object
        DeleteValue deleteOp = new DeleteValue.Builder(quoteObjectLocation).build();
        client.execute(deleteOp);
        System.out.println("Quote object successfully deleted");

        // http :9000/riak/clusters/default/types/default/buckets/quotes/keys/Iceman
        // returns 404

        Book mobyDick = new Book();
        mobyDick.setTitle("Moby Dick");
        mobyDick.setAuthor("Herman Melville");
        mobyDick.setBody("Call me Ishmael. Some years ago...");
        mobyDick.setIsbn("1111979723");
        mobyDick.setCopiesOwned(3);
        System.out.println("Book object created");

        // Now we'll assign a Location for the book, create a StoreValue
        // operation, and store the book
        Namespace booksBucket = new Namespace("books");
        Location mobyDickLocation = new Location(booksBucket, "moby_dick");
        StoreValue storeBookOp = new StoreValue.Builder(mobyDick).withLocation(mobyDickLocation).build();
        client.execute(storeBookOp);
        System.out.println("Moby Dick information now stored in Riak");

        // And we'll verify that we can fetch the info about Moby Dick and
        // that that info will match the object we created initially
        FetchValue fetchMobyDickOp = new FetchValue.Builder(mobyDickLocation).build();
        Book fetchedBook = client.execute(fetchMobyDickOp).getValue(Book.class);
        System.out.println("Book object successfully fetched");

        //  Shut down the client and the underlying RiakCluster.
        client.shutdown();
    }

}
