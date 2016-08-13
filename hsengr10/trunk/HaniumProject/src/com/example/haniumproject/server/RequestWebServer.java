package com.example.haniumproject.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class RequestWebServer extends Thread {
	private ArrayList<String> strList;

	public interface GetServerJsonMessage {
		void onGetServerMsgs(String requestCode, String[] parserString);
		void onGetServerMsg(String requestCode,String msg);
	}

	GetServerJsonMessage onGetServerJsonMessageListener = null;

	public void setOnGetServerJsonMessageListener(
			GetServerJsonMessage getServerJsonMessage) {
		onGetServerJsonMessageListener = getServerJsonMessage;
	}

	public void setMsgs(String... strs) {
		for (String str : strs) {
			strList.add(str);
		}
	}

	public RequestWebServer() {
		strList = new ArrayList<String>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (String str : strList) {
			SendByHttp(str);
		}
	}

	private synchronized void SendByHttp(String msg) {
		String URL = "http://113.198.84.51:8090/" + msg;

		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpGet get = new HttpGet(URL);

			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 15000);
			HttpConnectionParams.setSoTimeout(params, 15000);

			HttpResponse response = client.execute(get);

			BufferedReader bufreader = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent(),
							"utf-8"));

			String line = null;
			String result = "";

			while ((line = bufreader.readLine()) != null) {
				result += line;
			}
			if (onGetServerJsonMessageListener != null) {
//				String[] jsonString = null;
				// if (msg.equals(ExhibitionDataManager.BOOTHINFO)) {
				// jsonString = JsonParser.jsonBoothListParser(result);
				// } else if (msg.equals(ExhibitionDataManager.GETAP)) {
				// jsonString = JsonParser.jsonAPListParser(result);
				// } else if (msg.equals(ExhibitionDataManager.SHOWRANKING)) {
				// jsonString = JsonParser.jsonRankParser(result);
				// } else if (msg.contains(ExhibitionDataManager.CHECKININFO)) {
				// jsonString = JsonParser.jsonCheckInParser(result);
				//
				// }
//				String[] jsonName = {"msg1"};
//				jsonString = JsonParser.jsonParser(result, jsonName);
				onGetServerJsonMessageListener.onGetServerMsg(msg, result);
//				onGetServerJsonMessageListener.onGetServerMsgs(msg, jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			client.getConnectionManager().shutdown();
		}

	}
}
