# Create a custom truststore for Java apps

First you need to get a certificate

`echo -n | openssl s_client -connect google.com:443 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > google.pem`

Next you will want to store it into a truststore, this will create one of "cacerts" doesn't exist.

`keytool -import -file google.pem -alias google -keystore cacerts`


    Enter keystore password:  
    Re-enter new password: 
    Owner: CN=*.google.com, O=Google Inc, L=Mountain View, ST=California, C=US
    Issuer: CN=Google Internet Authority G2, O=Google Inc, C=US
    Serial number: 6803ed031885eacc
    Valid from: Wed May 11 14:46:00 EDT 2016 until: Wed Aug 03 14:46:00 EDT 2016
    Certificate fingerprints:
    	 MD5:  54:6F:76:60:27:C7:3F:90:04:D6:7F:79:81:A9:5F:C2
    	 SHA1: 81:50:50:6A:2B:1C:60:02:C2:96:51:57:AC:25:FA:C9:51:FD:F5:A4
    	 SHA256: 08:00:E3:6D:FC:AF:AA:15:5D:AC:B4:37:AD:A8:0A:B9:9F:0F:8B:20:83:1C:55:2B:0E:82:F4:3E:14:8E:91:1F
    	 Signature algorithm name: SHA256withRSA
    	 Version: 3
    ...truncated...
    
    
    Trust this certificate? [no]:  yes
    Certificate was added to keystore


The password requested here is either the password for the existing `cacerts` or a password of your choosing.


# Using the truststore

You can either pass this in as a command line argument

    -Djavax.net.ssl.trustStore=/path/to/cacerts

Or you can set the truststore in your code

    File file = new File("cacerts");
	String cacertsFilePath = file.getAbsolutePath();
	getLogger().info("Setting trustStore to {}", cacertsFilePath);
	System.setProperty("javax.net.ssl.trustStore", cacertsFilePath);
