package introsde.app;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.ws.Endpoint;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class LocalDatabaseStandalone
{
    private static final URI BASE_URI = URI.create("http://localhost:5900/sdelab/");	
    
    /*public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
    {
    	System.out.println("Starting sdelab standalone HTTP server...");
        JdkHttpServerFactory.createHttpServer(BASE_URI, createApp());
        System.out.println("Server started on " + BASE_URI + "\n[kill the process to exit]");
    }*/
    
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException{
        String PROTOCOL = "http://";
        String HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        System.out.println(HOSTNAME);
        if (HOSTNAME.equals("127.0.0.1") || HOSTNAME.equals("127.0.1.1"))
        {
            HOSTNAME = "localhost";
        }
        String PORT = "9002";
        String BASE_URL = "/sdelab/";

        // We need this so the App will run on Heroku properly where we got the assigned port
        // in an environment value named PORT
        if(String.valueOf(System.getenv("PORT")) != "null") {
            PORT = String.valueOf(System.getenv("PORT"));
        }

        // Assemble the final endpoint URL
        String endpointUrl = PROTOCOL + HOSTNAME + ":" + PORT + BASE_URL;
        URI BASE_URI = URI.create(endpointUrl);
        // End publish the endpoint
        JdkHttpServerFactory.createHttpServer(BASE_URI, createApp());
        System.out.println("Starting final-local-database service...");
        System.out.println("--> Published at = " + endpointUrl);
    }

    public static ResourceConfig createApp() {
    	System.out.println("Starting sdelab REST services...");
        return new MyApplicationConfig();
    }
}
