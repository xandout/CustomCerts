package pw.pleasework.customcerts;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;

public class Main {
    public static void main(String[] args){
        File file = new File("cacerts");
        String cacertsFilePath = file.getAbsolutePath();
        System.setProperty("javax.net.ssl.trustStore", cacertsFilePath);
        System.out.println(String.format("Set TrustStore to %s", cacertsFilePath));
        try {

            DefaultHttpClient mHttpClient = new DefaultHttpClient();

            HttpGet theHttpGet = new HttpGet(args[0]);
            HttpResponse theHttpResponse = mHttpClient.execute(theHttpGet);

            System.out.println(theHttpResponse.getStatusLine().getStatusCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
