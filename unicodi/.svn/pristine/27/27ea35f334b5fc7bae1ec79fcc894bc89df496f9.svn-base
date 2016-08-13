package com.unicodi.android.unicodi;

import java.net.URLDecoder;
import java.util.HashMap;

import com.unicodi.android.unicodi.utilhangyoung.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends Activity implements OnClickListener {
	private Button btn_passwordChange, btn_logout;
	private TextView tv_setting_name, tv_setting_id, tv_setting_phone,
			tv_setting_gender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		setLayoutId();
		setUserData();
		btn_passwordChange.setOnClickListener(this);
		btn_logout.setOnClickListener(this);

	}

	private void setUserData() {
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		HashMap<String, String> user = db.getUserDetails();
		tv_setting_name.setText(URLDecoder.decode(user.get(DatabaseHandler.KEY_NAME)));
		tv_setting_id.setText(user.get(DatabaseHandler.KEY_EMAIL));
		tv_setting_phone.setText(user.get(DatabaseHandler.KEY_PHONE));
		tv_setting_gender.setText(URLDecoder.decode(user.get(DatabaseHandler.KEY_GENDER)));
	}

	private void setLayoutId() {
		btn_passwordChange = (Button) findViewById(R.id.btn_passwordChange);
		btn_logout = (Button) findViewById(R.id.btn_logout);
		tv_setting_name = (TextView) findViewById(R.id.tv_setting_name);
		tv_setting_id = (TextView) findViewById(R.id.tv_setting_id);
		tv_setting_phone = (TextView) findViewById(R.id.tv_setting_phone);
		tv_setting_gender = (TextView) findViewById(R.id.tv_setting_gender);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_passwordChange:
			startActivity(new Intent(SettingActivity.this,
					PasswordChangeActivity.class));
			break;
		case R.id.btn_logout:
			setResult(RESULT_OK);
			finish();
			break;
		}

	}

}
