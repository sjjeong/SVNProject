package com.unicodi.android.unicodi.utilhangyoung;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class UserFunctions {

	private JSONParser jsonParser;

	private final static String loginURL = "http://unicodi14.cafe24.com/";
	private final static String registerURL = "http://unicodi14.cafe24.com/";

	private final static String login_tag = "login";
	private final static String register_tag = "register";
	private final static String board_tag = "board";
	private final static String store_image_tag = "store_image";
	private final static String get_image_tag = "get_image";
	private final static String update_profile_tag = "updateProfile";
	private final static String uploade_closet_tag = "uploadCloset";
	private final static String get_closet_tag = "getCloset";

	// constructor
	public UserFunctions() {
		jsonParser = new JSONParser();
	}

	/**
	 * function make Login Request
	 * 
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String email, String password) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}

	/**
	 * Function register new user to DB
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @param phone
	 * @param gender
	 * */
	public JSONObject registerUser(String name, String email, String password,
			String phone, String gender) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("gender", gender));

		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		// return json
		Log.i("googrybug", json.toString());
		return json;
	}

	/**
	 * Function save board to DB
	 */
	public JSONObject registerBoard(String name, String email, String content) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", board_tag));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("content", content));

		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		Log.i("googrybug", json.toString());
		return json;
	}

	/**
	 * Function save image to DB
	 */
	public JSONObject registerImage(String bid, String image) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", store_image_tag));
		params.add(new BasicNameValuePair("board_id", bid));
		params.add(new BasicNameValuePair("board_image", image));

		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		return json;
	}

	/**
	 * Function update Profile to DB
	 */
	public JSONObject updateProfile(String account, String pimage) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", update_profile_tag));
		params.add(new BasicNameValuePair("email", account));
		params.add(new BasicNameValuePair("pimage", pimage));

		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		return json;
	}

	/**
	 * Function get Profile to DB
	 */
	public JSONObject getProfileImage(String account) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "getProfileImage"));
		params.add(new BasicNameValuePair("email", account));

		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		Log.i("googrybug", json.toString());
		return json;
	}

	/**
	 * Function upload closet to DB
	 */
	public JSONObject uploadCloset(String account, String position,
			String picture, String content) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "uploadCloset"));
		params.add(new BasicNameValuePair("account", account));
		params.add(new BasicNameValuePair("position", position));
		params.add(new BasicNameValuePair("picture", picture));
		params.add(new BasicNameValuePair("content", content));

		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		Log.i("googrybug", json.toString());
		return json;
	}

	/**
	 * Function upload closet to DB
	 */
	public JSONObject getCloset(String account) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "getCloset"));
		params.add(new BasicNameValuePair("account", account));

		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		Log.i("googrybug", json.toString());
		return json;
	}

	/**
	 * Function get Login status
	 * */
	public boolean isUserLoggedIn(Context context) {
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if (count > 0) {
			// user logged in
			return true;
		}
		return false;
	}

	/**
	 * Function to logout user Reset Database
	 * */
	public boolean logoutUser(Context context) {
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}

}