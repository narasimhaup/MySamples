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
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class UpdateNodePropertyManager {

	private static String NODEINFO_URL="https://docs-stg.cisco.com/alfresco/s/com/cisco/alfresco/se/updateBeSbePFTags?nodeRef=";
	//private static String NODEINFO_URL="https://docs.cisco.com/alfresco/service/setproperty?property=sebom:artWorkflowStatus&value=Expired&nodeRef=";

	private String id;
//	CEC/Generic user ID
	private String userId;
//	CEC/Generic password
	private String password;
	
//	Start: Private Constructors
	private UpdateNodePropertyManager(){}
	private UpdateNodePropertyManager(String id, String userId, String password){
		this.id = id;
		this.userId = userId;
		this.password = password;
	}
	
	public static void main(String args[]){
		String filePath = "C:\\Users\\nupparap\\Desktop\\BESBE RETAG-01-08-2017\\batch40.csv";
		//List<String> nodeIds = readingingCSVFiles( new File(filePath));
		List<String> nodeIds = Arrays.asList("workspace://SpacesStore/8246d1ac-32dd-4c0f-8222-a67fb3742643");
		int count = 1; 
		for(String nodeid: nodeIds) {
			count ++;
			//if(count < 953)
				//continue;
			//if(!(nodeid.indexOf("workspace://SpacesStore/") > 0))
				//nodeid = "workspace://SpacesStore/" + nodeid;
			System.out.println("Processing Record[" +count+"]   " + nodeid);
			
		 new UpdateNodePropertyManager(nodeid,"alfcmadm","alfcmadm@oct14").execute();
		 
/*		 try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
*/		}
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
			//log.info("FILE HAS NO HEADERS");
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
	public static UpdateNodePropertyManager getInstance(String id, String userId, String password){
		return new UpdateNodePropertyManager(id, userId, password);
	}
	
//	Execute method to get file metadata
	public void execute(){
		InputStream in = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String strUrl = NODEINFO_URL + id;
		try {
			
			URL url = new URL(strUrl);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			strUrl = uri.toString();
			System.out.println(strUrl);
			HttpGet httpGet = new HttpGet(uri);
			//httpGet.addHeader("userid",this.userId );
			//httpGet.addHeader("password",this.password);
			httpGet.addHeader("ObSSOCookie",TestAdminsso.getAdminSSOToken("https://alfidx-app-stg-01:8443/dcu/sso", "seBit", "Fx$UUk3l@#%dzdu"));
			//httpGet.setHeader("Content-Disposition", "attachment");
			httpGet.setHeader("Connection","close");
			
			//System.out.println("URL: " + httpGet.toString());
			HttpResponse response = httpClient.execute(httpGet);
			System.out.println("Response:: " + response.getStatusLine().getStatusCode() + response.getStatusLine().getReasonPhrase());
			
			if (response.getStatusLine().getStatusCode()!=200){
				System.out.println("Unable to get Node Info");
				System.out.println("Response:: ");
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
