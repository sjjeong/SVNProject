package com.example.haniumproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.haniumproject.Util.IntentUtil;
import com.example.haniumproject.Util.PreferenceUtil;

public class IntroActivity extends Activity {
	private EditText et_name, et_age;
	private Button btn_start;
	private RadioGroup rg_gender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		setLayoutId();
		setData();
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(IntroActivity.this,
						SearchBeaconActivity.class);
				String str_name, str_age, str_gender;
				str_name = str_age = str_gender = null;
				if (!et_name.getText().toString().equals("")) {
					str_name = et_name.getText().toString();
				}
				if (!et_age.getText().toString().equals("")) {
					str_age = et_age.getText().toString();
				}
				if (str_name != null && str_age != null) {
					int selectedGender = rg_gender.getCheckedRadioButtonId();
					RadioButton radioGenderButton = (RadioButton) findViewById(selectedGender);
					str_gender = radioGenderButton.getText().toString();
					PreferenceUtil.savePreferences(getApplicationContext(),
							PreferenceUtil.USER_NAME, str_name);
					PreferenceUtil.savePreferences(getApplicationContext(),
							PreferenceUtil.USER_AGE, str_age);
					PreferenceUtil.savePreferences(getApplicationContext(),
							PreferenceUtil.USER_GENDER, str_gender);
					intent.putExtra(IntentUtil.USER_NAME, str_name);
					intent.putExtra(IntentUtil.USER_AGE, str_age);
					intent.putExtra(IntentUtil.USER_GENDER, str_gender);
					startActivity(intent);
					// finish();
				} else {
					Toast.makeText(getApplicationContext(), "빈칸이 있습니다.",
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	private void setData() {
		et_name.setText(PreferenceUtil.getStringPreferences(
				getApplicationContext(), PreferenceUtil.USER_NAME));
		et_age.setText(PreferenceUtil.getStringPreferences(
				getApplicationContext(), PreferenceUtil.USER_AGE));
		String gender = PreferenceUtil.getStringPreferences(
				getApplicationContext(), PreferenceUtil.USER_GENDER);
		if (gender.equals("남"))
			rg_gender.check(R.id.radio_male);
		else
			rg_gender.check(R.id.radio_female);
	}

	private void setLayoutId() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_age = (EditText) findViewById(R.id.et_age);
		btn_start = (Button) findViewById(R.id.btn_start);
		rg_gender = (RadioGroup) findViewById(R.id.rg_gender);

	}

}
