package com.unicodi.android.unicodi.utilhangyoung;

import java.util.HashMap;

import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
	private Context context;

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	public ImageLoadTask(Context context) {
		this.context = context;
	}

	@Override
	protected Bitmap doInBackground(Void... params) {
		DatabaseHandler db = new DatabaseHandler(context);
		HashMap<String, String> user = db.getUserDetails();

		// Account info
		String account = user.get(DatabaseHandler.KEY_EMAIL);

		UserFunctions userFunction = new UserFunctions();
		JSONObject json = userFunction.getProfileImage(account);

		try {
			JSONObject json_profile = json.getJSONObject("profile");
			String str = json_profile.getString(DatabaseHandler.KEY_IMAGE);
			byte[] decodedString = Base64.decode(str, Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
					0, decodedString.length);
			return decodedByte;
		} catch (Exception e) {
		}

		return null;
	}

}