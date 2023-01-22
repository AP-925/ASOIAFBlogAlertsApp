package com.notifierapp.Helper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.notifierapp.Constants.NotifierConstants;

public class SSLHelper {

	private static final Logger LOGGER = Logger.getLogger(NotifierConstants.HELPER_LOG_PLACEHOLDER);

	public static SSLSocketFactory socketFactory() {

		TrustManager[] trustAllCerts = new TrustManager[] { (TrustManager) new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {

			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {

			}
		} };

		try {
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			SSLSocketFactory result = sslContext.getSocketFactory();

			return result;
		} catch (Exception e) {
			LOGGER.info("Failed to create a SSL socket factory : " + e);
		}
		return new SSLSocketFactory() {

			@Override
			public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
					throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
					throws IOException, UnknownHostException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Socket createSocket(InetAddress host, int port) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String[] getSupportedCipherSuites() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String[] getDefaultCipherSuites() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
