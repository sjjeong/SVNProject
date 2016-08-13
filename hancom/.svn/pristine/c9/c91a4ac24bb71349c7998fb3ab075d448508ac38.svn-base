package com.googry.hancom.option.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
	private static Context context;
	private static SharedPreferences prefs;

	private static final String PREFNAME = "PrefName";

	public static final String KEY_DAYOFWEEKCHECKED = "KEY_DAYOFWEEKCHECKED";
	public static final String KEY_YEARCHECKED = "KEY_YEARCHECKED";
	public static final String KEY_DAYOFWEEK = "KEY_DAYOFWEEK";
	public static final String KEY_YEAR = "KEY_YEAR";

//	public static enum enum_dayofweek {
//		DayAuto, Monday, Tuesday, Wendsday, Thursday, Friday
//	};
//
//	public static enum enum_year {
//		First, Second, Third, Fourth
//	};

	public static void setContext(Context context) {
		PreferenceManager.context = context;
		prefs = context.getSharedPreferences(PREFNAME, context.MODE_PRIVATE);

	}

	public static void setData(String key, Object value) {
		if (context == null) {
			return;
		}
		SharedPreferences.Editor editor = prefs.edit();
		if (value instanceof String) {
			editor.putString(key, (String) value);
		} else if (value instanceof Integer) {
			editor.putInt(key, (Integer) value);
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean) value);
		} else {
			return;
		}
		editor.commit();
	}

	public static int getIntData(String key) {
		return prefs.getInt(key, -1);
	}

	public static String getStringData(String key) {
		return prefs.getString(key, "");
	}
	
	public static Boolean getBooleanData(String key){
		return prefs.getBoolean(key, false);
	}
}

