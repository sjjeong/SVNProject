package com.unicodi.android.unicodi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.unicodi.android.unicodi.utilgoogry.AppDataManagerUtil;
import com.unicodi.android.unicodi.utilhangyoung.ImageLoadTask;

public class MyClosetDetailActivity extends Activity {
	private ImageView iv_myclosetdetail_closet;
	private ImageView iv_myclosetdetail_profileimage;
	private TextView tv_myclosetdetail_userId;
	private TextView tv_myclosetdetail_comment;

	// private TextView tv_myclosetdetail_timeline;

	private AppDataManagerUtil instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myclosetdetail);
		instance = AppDataManagerUtil.getInstance();
		setLayoutId();
		setData();

	}

	private void setData() {
		Intent intent = getIntent();
		Bitmap bmpCloset = intent.getParcelableExtra("bmpCloset");
		String comment = intent.getStringExtra("Comment");

		// profileimage
		Bitmap ret = null;
		try {
			// ret = new CommTask().execute().get();
			ret = new ImageLoadTask(getApplicationContext()).execute().get();
		} catch (Exception e) {
		}
		if (ret != null) {
			iv_myclosetdetail_profileimage.setImageBitmap(ret);
		}

		// closet
		iv_myclosetdetail_closet.setImageBitmap(bmpCloset);

		// userid
		tv_myclosetdetail_userId.setText(instance.getUser_id());

		// closet comment
		tv_myclosetdetail_comment.setText(comment);

	}

	private void setLayoutId() {

		iv_myclosetdetail_closet = (ImageView) findViewById(R.id.iv_myclosetdetail_closet);
		iv_myclosetdetail_profileimage = (ImageView) findViewById(R.id.iv_myclosetdetail_profileimage);
		tv_myclosetdetail_userId = (TextView) findViewById(R.id.tv_myclosetdetail_userId);
		tv_myclosetdetail_comment = (TextView) findViewById(R.id.tv_myclosetdetail_comment);

	}

}
