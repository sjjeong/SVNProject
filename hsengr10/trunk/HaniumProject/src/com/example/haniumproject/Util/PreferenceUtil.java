package com.example.haniumproject.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
	public static final String USER_NAME = "user_name";
	public static final String USER_AGE = "user_age";
	public static final String USER_GENDER = "user_gender";

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

}
