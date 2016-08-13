package com.unicodi.android.unicodi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.unicodi.android.unicodi.utilgoogry.FileUtil;
import com.unicodi.android.unicodi.utilgoogry.ImageUtil;
import com.unicodi.android.unicodi.utilgoogry.IntentUtil;
import com.unicodi.android.unicodi.utilgoogry.PreferenceUtil;
import com.unicodi.android.unicodi.utilhangyoung.DatabaseHandler;
import com.unicodi.android.unicodi.utilhangyoung.UserFunctions;

public class TestActivity extends Activity implements OnClickListener {
	private Button btn1, btn2;
	private ImageView iv1, iv2;
	private EditText et1, et2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		initLayoutId();
		registerButtonClickListener();
		iv1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				Intent intent = new Intent(Intent.ACTION_PICK);
				intent.setType(Images.Media.CONTENT_TYPE);
				intent.setData(Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, IntentUtil.REQ_TAKE_GALLERY);
				return false;
			}
		});
		btn1.setText("upload");
		btn2.setText("download");
	}

	private void registerButtonClickListener() {
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	private void initLayoutId() {
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		iv1 = (ImageView) findViewById(R.id.imageView1);
		iv2 = (ImageView) findViewById(R.id.imageView2);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.button1:
			new SaveImageCommTask().execute();
			break;
		case R.id.button2:
			new LoadImageCommTask().execute();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			String path = null;
			if (requestCode == IntentUtil.REQ_TAKE_CAMERA) {
				path = new File(PreferenceUtil.getPreferences(this)).getPath();
			} else if (requestCode == IntentUtil.REQ_TAKE_GALLERY) {
				path = FileUtil.getAbsolutePath(this, data.getData());
			}
			if (path != null) {
				Bitmap temp = BitmapFactory.decodeFile(path);
				Bitmap rotatedBitmap = FileUtil.GetRotatedBitmap(temp,
						FileUtil.GetExifOrientation(path));
				Bitmap originalBitmap = ImageUtil.resizeImageByDevice(this,
						rotatedBitmap);
				iv1.setImageBitmap(originalBitmap);
			}
		}
	}

	class SaveImageCommTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected Void doInBackground(Void... params) {
			DatabaseHandler db = new DatabaseHandler(getApplicationContext());
			HashMap<String, String> user = db.getUserDetails();

			// Account info
			String account = user.get(DatabaseHandler.KEY_EMAIL);
			// Image to Base64 string
			iv1.buildDrawingCache();
			Bitmap bmp = iv1.getDrawingCache();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
			byte[] byteArray = byteArrayOutputStream.toByteArray();
			String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

			UserFunctions userFunction = new UserFunctions();
			String content = "";
			if (et1.getText().toString().equals("")) {
				try {
					content = URLEncoder.encode(et1.getText().toString(),
							"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			JSONObject json = userFunction.uploadCloset(account, "top",
					encoded, content);
			Log.i("googrybug", "TestActivity\nSaveImage" + json.toString());
			return null;
		}
	}

	class LoadImageCommTask extends AsyncTask<Void, Void, Bitmap> {

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected Bitmap doInBackground(Void... params) {
			DatabaseHandler db = new DatabaseHandler(getApplicationContext());
			HashMap<String, String> user = db.getUserDetails();

			// Account info
			String account = user.get(DatabaseHandler.KEY_EMAIL);

			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.getCloset(account);

			Log.i("googrybug", "TestActivity\nLoadImage" + json.toString());
			// try {
			// JSONObject json_profile = json.getJSONObject("profile");
			// String str = json_profile.getString(DatabaseHandler.KEY_IMAGE);
			// Log.v("getProfile", str);
			// byte[] decodedString = Base64.decode(str, Base64.DEFAULT);
			// Bitmap decodedByte = BitmapFactory.decodeByteArray(
			// decodedString, 0, decodedString.length);
			// return decodedByte;
			// } catch (Exception e) {
			// }

			return null;
		}
	}
}
