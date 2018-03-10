package com.my.httpclient;

import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class TestAdminsso {

	public static String getAdminSSOToken(String pUrl, String pCleintId, String pClientSecret) {

		String lSsoToken = null;
		CloseableHttpClient httpClient = null;
		try {

			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY,
					new UsernamePasswordCredentials(pCleintId, pClientSecret));
			httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider)
					.setSSLHostnameVerifier(new HostnameVerifier() {
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					}).build();

			HttpGet httpget = new HttpGet(pUrl);

			System.out.println("executing request" + httpget.getRequestLine());
			HttpResponse response;
			try {
				response = httpClient.execute(httpget);
				HttpEntity entity = response.getEntity();

				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content : " + entity.getContentLength());
					String cookie = EntityUtils.toString(entity);
					JSONObject result = new JSONObject(cookie);
					lSsoToken = result.has("sso") ? result.getString("sso") : "";
					System.out.println("AdminCookie:" + lSsoToken);
				}
				EntityUtils.consume(entity);
			} catch (ClientProtocolException e) {

				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {

			if (httpClient != null)
				try {
					httpClient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return lSsoToken;
	}

	public static void main(String[] args) throws JSONException {
		//getAdminSSOToken("https://dcu-stg.cisco.com/dcu/sso", "seBit", "Fx$UUk3l@#%dzdu");
		getAdminSSOToken("https://alfidx-app-prd-02:8443/dcu/sso", "seBit", "Fx$UUk3l@#%dzdu");
	}

}
