package com.unicodi.android.unicodi.utilgoogry;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
	public static String getPreferences(Context context) {
		SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
		return pref.getString("path", "");
	}

	public static void savePreferences(Context context, String str) {
		SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("path", str);
		editor.commit();
	}

	public static void removePreferences(Context context) {
		SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.remove("path");
		editor.commit();
	}

	public static void removeAllPreferences(Context context) {
		SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.clear();
		editor.commit();
	}

}
