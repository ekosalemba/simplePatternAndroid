package com.simplepatternandroid.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLUtils {

    public static X509TrustManager trustManager = new X509TrustManager() {

        @Override
        public void checkClientTrusted(
                X509Certificate[] arg0, String arg1)
                throws java.security.cert.CertificateException {
        }

        @Override
        public void checkServerTrusted(
                X509Certificate[] arg0, String arg1)
                throws java.security.cert.CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

    public static void trustSelfSignedSSL() {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(null, new TrustManager[]{trustManager}, null);
            //SSLContext.setDefault(ctx);
            HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
        } catch (Exception ex) {
            throw new RuntimeException("Exception occurred ", ex);
        }
    }

    public static void turnOnSslChecking() throws KeyManagementException, NoSuchAlgorithmException {
        // Return it to the initial state (discovered by reflection, now hardcoded)
        SSLContext.getInstance("SSL").init(null, null, null);
    }

}
