package com.unicodi.android.unicodi.utilgoogry;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

public class AppDataManagerUtil {
	private static AppDataManagerUtil instance;
	public static final String AUTOLOGIN = "autoLogin";
	public static final String AUTOLOGINID = "autoLoginId";
	public static final String AUTOLOGINPASSWORD = "autoLoginPassword";
	public static final String PROFILEMEMO = "profileMemo";

	// public static final String PROFILEPICTUREPATH = "profilepicturepath";

	private AppDataManagerUtil() {
	}

	public static AppDataManagerUtil getInstance() {
		if (instance == null) {
			instance = new AppDataManagerUtil();
		}
		return instance;
	}

	public static String getStringPreferences(Context context, String key) {
		SharedPreferences pref = context.getSharedPreferences("pref",
				Context.MODE_PRIVATE);
		return pref.getString(key, "");
	}

	public static Boolean getBooleanPreferences(Context context, String key) {
		SharedPreferences pref = context.getSharedPreferences("pref",
				Context.MODE_PRIVATE);
		return pref.getBoolean(key, false);
	}

	public static void savePreferences(Context context, String key, Object value) {
		SharedPreferences pref = context.getSharedPreferences("pref",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		if (value instanceof String) {
			editor.putString(key, (String) value);
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean) value);
		}
		editor.commit();
	}

//	private Bitmap bmpProfilePicture;
	private String user_name, user_id, user_phone, user_gender;

//	public Bitmap getBmpProfilePicture() {
//		return bmpProfilePicture;
//	}
//
//	public void setBmpProfilePicture(Bitmap bmpProfilePicture) {
//		this.bmpProfilePicture = bmpProfilePicture;
//	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

}
