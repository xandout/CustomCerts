package pw.pleasework.customcerts;

import org.apache.http.client.fluent.Request;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        File file = new File("cacerts");
        String cacertsFilePath = file.getAbsolutePath();
        System.setProperty("javax.net.ssl.trustStore", cacertsFilePath);
        System.out.println(String.format("Set TrustStore to %s", cacertsFilePath));
        try {
            Request.Get(args[0])
                    .connectTimeout(1000)
                    .socketTimeout(1000)
                    .execute().returnContent().asString();
            System.out.println("Success, no errors");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
