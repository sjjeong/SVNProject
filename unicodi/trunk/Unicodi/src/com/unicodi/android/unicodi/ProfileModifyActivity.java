package com.unicodi.android.unicodi;

import java.io.ByteArrayOutputStream;
import java.io.File;
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

import com.unicodi.android.unicodi.utilgoogry.AppDataManagerUtil;
import com.unicodi.android.unicodi.utilgoogry.FileUtil;
import com.unicodi.android.unicodi.utilgoogry.ImageUtil;
import com.unicodi.android.unicodi.utilgoogry.IntentUtil;
import com.unicodi.android.unicodi.utilgoogry.PreferenceUtil;
import com.unicodi.android.unicodi.utilhangyoung.DatabaseHandler;
import com.unicodi.android.unicodi.utilhangyoung.ImageLoadTask;
import com.unicodi.android.unicodi.utilhangyoung.UserFunctions;

public class ProfileModifyActivity extends Activity {
	private ImageView iv_profileModifyFace;
	private EditText et_profileModifyMemo;
	private Button btn_profileModifyComplete;
	private AppDataManagerUtil instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profilemodify);
		setLayoutId();
		instance = AppDataManagerUtil.getInstance();

		Bitmap ret = null;
		try {
			// ret = new CommTask().execute().get();
			ret = new ImageLoadTask(getApplicationContext()).execute().get();
		} catch (Exception e) {
		}
		if (ret != null) {
			iv_profileModifyFace.setImageBitmap(ret);
			Log.v("ret", "work");
		}
		iv_profileModifyFace.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Intent intent = new Intent(Intent.ACTION_PICK);
				intent.setType(Images.Media.CONTENT_TYPE);
				intent.setData(Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, IntentUtil.REQ_TAKE_GALLERY);
				return false;
			}
		});
		btn_profileModifyComplete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * if (!et_profileModifyMemo.getText().equals(""))
				 * AppDataManagerUtil.savePreferences(getApplicationContext(),
				 * AppDataManagerUtil.PROFILEMEMO,
				 * et_profileModifyMemo.getText().toString());
				 */

				/*
				 * AppDataManagerUtil.savePreferences(getApplicationContext(),
				 * AppDataManagerUtil.PROFILEPICTUREPATH, bmpPath);
				 * instance.setBmpProfilePicture( originalBitmap);
				 */
				new CommTask().execute();
				setResult(RESULT_OK);
				finish();
			}
		});

		et_profileModifyMemo.setText(AppDataManagerUtil.getStringPreferences(
				getApplicationContext(), AppDataManagerUtil.PROFILEMEMO));
	}

	private void setLayoutId() {
		iv_profileModifyFace = (ImageView) findViewById(R.id.iv_profileModifyFace);
		et_profileModifyMemo = (EditText) findViewById(R.id.et_profileModifyMemo);
		btn_profileModifyComplete = (Button) findViewById(R.id.btn_profileModifyComplete);
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
				iv_profileModifyFace.setImageBitmap(originalBitmap);
			}
		}
	}

	class CommTask extends AsyncTask<Void, Void, Void> {

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
			iv_profileModifyFace.buildDrawingCache();
			Bitmap bmp = iv_profileModifyFace.getDrawingCache();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
			byte[] byteArray = byteArrayOutputStream.toByteArray();
			String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.updateProfile(account, encoded);

			finish();
			return null;
		}

	}

}
