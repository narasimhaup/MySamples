package com.my.httpclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
/**
 * 
 * @author nupparap
 *
 */
public class HttpPostRequest {

	//private static String NODEINFO_URL="https://docs.cisco.com/alfresco/service/se/publish?requestType=expire&noderef=";
	private static String NODEINFO_URL="https://docs.cisco.com/alfresco/service/se/publish?requestType=expire&noderef=workspace://SpacesStore/";

	private String id;
	private String userId;
	private String password;
	
	private static List<String> failedNodes = new ArrayList();
//	Start: Private Constructors
	private HttpPostRequest(){}
	private HttpPostRequest(String id, String userId, String password){
		this.id = id;
		this.userId = userId;
		this.password = password;
	}
	
	public static void main(String args[]){
		String filePath = "C:\\Users\\nupparap\\Desktop\\expire\\Batch1.csv";
		List<String> nodeIds = readingingCSVFiles( new File(filePath));
		int i=0;
		for(String nodeid: nodeIds) {
			i++;
			if (i > 2)
				break;
			System.out.println("Processing record: " + i + "Failed" + failedNodes);
		 new HttpPostRequest(nodeid,"alfcmadm","alfcmadm@oct14").execute();
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	public static List<String> readingingCSVFiles(File csvFile) {

		List<String> list = new ArrayList<String>();
		//long count = 0;
		boolean isHeader = true;
		CSVParser parser = null;
		FileReader reader = null;
		try {
			reader = new FileReader(csvFile);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		try {
			parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
		} catch (IOException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException exc){
			isHeader = false;
		}
		try{
			for (CSVRecord csvRecord : parser) {
				list.add(csvRecord.get("Unique Identifier"));
				
			}
	} catch(NullPointerException exc){
		System.out.println("THIS FILE HAS RECORDS WITH NO HEADER");
	}
		System.out.println("Total noderefs for each batch----> "+list.size());
		
		return list;
	}
	

	
//	End: Private Constructors
	
//	Static facotry method to get instance of DownloadManager
	public static HttpPostRequest getInstance(String id, String userId, String password){
		return new HttpPostRequest(id, userId, password);
	}
	
//	Execute method to get file metadata
	public void execute(){
		
		 int DEFAULT_TIMEOUT = 5000;
		RequestConfig requestConfig = RequestConfig.custom()
		   .setConnectTimeout(DEFAULT_TIMEOUT)
		   .setConnectionRequestTimeout(DEFAULT_TIMEOUT)
		   .setSocketTimeout(DEFAULT_TIMEOUT)
		   .build();
		/**CloseableHttpClient httpClient = HttpClients.custom()
		   .setDefaultRequestConfig(requestConfig)
		   .build(); */

		InputStream in = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		String strUrl = NODEINFO_URL + id;
		try {
			
			URL url = new URL(strUrl);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			strUrl = uri.toString();
			System.out.println(strUrl);
			HttpPost httpPost = new HttpPost(uri);
			httpPost.addHeader("userid",this.userId );
			httpPost.addHeader("password",this.password);
			//httpGet.setHeader("Content-Disposition", "attachment");
			httpPost.setHeader("Connection","close");
			
			//System.out.println("URL: " + httpGet.toString());
			HttpResponse response = httpClient.execute(httpPost);
			System.out.println("Response:: " + response.getStatusLine().getStatusCode() + response.getStatusLine().getReasonPhrase());
			
			if (response.getStatusLine().getStatusCode()!=200){
				//System.out.println("Unable to get Node Info");
				//System.out.println("Response:: ");
				failedNodes.add(id);
				in = response.getEntity().getContent();
				Reader reader = new InputStreamReader(in);				
				in.close();
				httpClient.close();
				return;
			}
			in = response.getEntity().getContent();
			Reader reader = new InputStreamReader(in);
			//System.out.println(reader.toString());
			in.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
